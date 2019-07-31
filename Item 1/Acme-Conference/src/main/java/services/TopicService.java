
package services;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.TopicRepository;
import domain.Topic;

@Service
@Transactional
public class TopicService {

	// Managed repository
	@Autowired
	private TopicRepository			topicRepository;

	// ------------------

	// Supporting services
	@Autowired
	private AdministratorService	administratorService;

	// -------------------

	@Autowired
	private Validator				validator;


	// Simple CRUD methods
	public Topic create() {
		this.administratorService.findByPrincipal();

		return new Topic();
	}

	public Topic save(final Topic topic) {
		this.middleWare(topic);

		return this.topicRepository.save(topic);
	}

	public void delete(final Topic topic) {
		this.middleWare(topic);

		this.topicRepository.delete(topic);
	}

	public Collection<Topic> findAll() {
		this.administratorService.findByPrincipal();

		return this.topicRepository.findAll();
	}

	public Topic findOne(final int topicId) {
		Assert.isTrue(this.exists(topicId));

		this.administratorService.findByPrincipal();

		return this.topicRepository.findOne(topicId);
	}

	public boolean exists(final int topicId) {
		return this.topicRepository.exists(topicId);
	}
	// -------------------

	// Other business methods
	public Topic findByName(final String name) {
		Assert.notNull(name);

		return this.topicRepository.findByName(name);
	}

	public Topic reconstruct(final Topic topic, final BindingResult binding) {
		final Topic result = topic;

		this.validator.validate(result, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return result;
	}
	// ----------------------

	// Auxiliary methods
	private void middleWare(final Topic topic) {
		Assert.notNull(topic);

		this.administratorService.findByPrincipal();
	}
	// -----------------
}
