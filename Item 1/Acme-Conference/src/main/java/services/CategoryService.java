
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

	// Simple CRUD methods

	public Category create() {
		this.administratorService.findByPrincipal();

		final Category result = new Category();

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

		for (final Category child : children)
			this.delete(child);

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
		return this.categoryRepository.findAllRootChildrenAndRoot();
	}

	public Collection<Category> findAllByParentId(final int parentId) {
		Assert.isTrue(this.exists(parentId));

		return this.categoryRepository.findAllByParentId(parentId);
	}

	public Category findRoot() {
		return this.categoryRepository.findRoot();
	}
	// ----------------------

	// Auxiliary methods
	private void updateRelatedConferences(final Category category) {
		final Category root = this.findRoot();
		final int categoryId = category.getId();

		Assert.isTrue(root.getId() != categoryId);

		final Collection<Conference> conferences = this.conferenceService.findAllByCategoryId(categoryId);

		for (final Conference conference : conferences) {
			conference.setCategory(root);

			this.conferenceService.update(conference);
		}
	}
	// -----------------
}
