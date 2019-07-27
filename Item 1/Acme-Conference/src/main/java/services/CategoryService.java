
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;

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


	// -------------------

	// Simple CRUD methods

	public Category create() {
		this.administratorService.findByPrincipal();

		final Category result = new Category();

		return result;
	}

	public Category save(final Category category) {
		Assert.notNull(category);

		return this.categoryRepository.save(category);
	}

	public Collection<Category> findAll() {
		return this.categoryRepository.findAll();
	}
}
