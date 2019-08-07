
package services;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CategoryRepository;
import domain.Category;
import domain.Conference;

@Service
@Transactional
public class CategoryService {

	// Managed repository
	@Autowired
	private CategoryRepository		categoryRepository;
	// ------------------

	// Supporting services
	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ConferenceService		conferenceService;

	// -------------------

	@Autowired
	private Validator				validator;


	// Simple CRUD methods

	public Category create() {
		final Category result = this.middleWare();

		return result;
	}

	public Category create(final int parentId) {
		final Category result = this.middleWare();
		final Category parent = this.findOne(parentId);

		result.setParent(parent);

		return result;
	}

	public Category save(final Category category) {
		Assert.notNull(category);
		Assert.isTrue(!category.getTitle().equals("CONFERENCE"));

		this.administratorService.findByPrincipal();

		return this.categoryRepository.save(category);
	}

	public void delete(final Category category) {
		Assert.notNull(category);

		this.administratorService.findByPrincipal();
		this.updateRelatedConferences(category);

		final int parentId = category.getId();
		final Collection<Category> children = this.findAllByParentId(parentId);
		final Category root = this.findRoot();

		for (final Category child : children) {
			child.setParent(root);

			this.categoryRepository.save(child);
		}

		this.categoryRepository.delete(category);
	}

	public Collection<Category> findAll() {
		return this.categoryRepository.findAll();
	}

	public Category findOne(final int categoryId) {
		Assert.isTrue(this.exists(categoryId));

		this.administratorService.findByPrincipal();

		return this.categoryRepository.findOne(categoryId);
	}

	public boolean exists(final int categoryId) {
		return this.categoryRepository.exists(categoryId);
	}

	// ---------------------

	// Other business methods
	public Collection<Category> findAllRootChildrenAndRoot() {
		this.administratorService.findByPrincipal();

		return this.categoryRepository.findAllRootChildrenAndRoot();
	}

	public Collection<Category> findAllByParentId(final int parentId) {
		Assert.isTrue(this.exists(parentId));

		this.administratorService.findByPrincipal();

		return this.categoryRepository.findAllByParentId(parentId);
	}
	// ----------------------

	// Auxiliary methods
	private Category findParentByChildId(final int childId) {
		return this.categoryRepository.findParentByChildId(childId);
	}

	private Category findRoot() {
		return this.categoryRepository.findRoot();
	}

	private Category middleWare() {
		this.administratorService.findByPrincipal();

		final Category result = new Category();

		return result;
	}

	private void updateRelatedConferences(final Category category) {
		final int childId = category.getId();
		final Category parent = this.findParentByChildId(childId);
		final Collection<Conference> conferences = this.conferenceService.findAllByCategoryId(childId);

		for (final Conference conference : conferences) {
			conference.setCategory(parent);

			this.conferenceService.update(conference);
		}
	}
	// -----------------

	public Category reconstruct(final Category category, final BindingResult binding) {
		final Category result = category;

		this.validator.validate(result, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return result;
	}
}
