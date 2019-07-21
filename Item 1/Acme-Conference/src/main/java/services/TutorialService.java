
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TutorialRepository;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	// Managed Repository
	@Autowired
	private TutorialRepository	tutorialRepository;


	// Supported Services

	// CRUD

	public Tutorial create() {
		Tutorial result;

		result = new Tutorial();

		return result;
	}

	public Tutorial save(final Tutorial tutorial) {
		Assert.notNull(tutorial);
		Tutorial result;
		result = this.tutorialRepository.save(tutorial);
		this.tutorialRepository.flush();
		return result;
	}

	public void delete(final Tutorial tutorial) {
		Assert.notNull(tutorial);
		Assert.isTrue(tutorial.getId() != 0);

		this.tutorialRepository.delete(tutorial);
	}

	public Collection<Tutorial> findAll() {
		Collection<Tutorial> result;

		result = this.tutorialRepository.findAll();

		return result;
	}

	public Tutorial findOne(final int tutorialId) {
		Tutorial result;

		result = this.tutorialRepository.findOne(tutorialId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.tutorialRepository.flush();
	}

	//Other business methods

}
