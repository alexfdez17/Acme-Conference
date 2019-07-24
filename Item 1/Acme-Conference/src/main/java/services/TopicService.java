
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TopicRepository;
import domain.Topic;

@Service
@Transactional
public class TopicService {

	// Managed repository
	@Autowired
	private TopicRepository	topicRepository;


	// ------------------

	// Simple CRUD methods
	// -------------------

	// Other business methods
	public Topic findByName(final String name) {
		Assert.notNull(name);

		return this.topicRepository.findByName(name);
	}
	// ----------------------

}
