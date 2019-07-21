
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PresentationRepository;
import domain.Presentation;

@Service
@Transactional
public class PresentationService {

	// Managed Repository
	@Autowired
	private PresentationRepository	presentationRepository;


	// Supported Services

	// CRUD

	public Presentation create() {
		Presentation result;

		result = new Presentation();

		return result;
	}

	public Presentation save(final Presentation presentation) {
		Assert.notNull(presentation);
		Presentation result;
		result = this.presentationRepository.save(presentation);
		this.presentationRepository.flush();
		return result;
	}

	public void delete(final Presentation presentation) {
		Assert.notNull(presentation);
		Assert.isTrue(presentation.getId() != 0);

		this.presentationRepository.delete(presentation);
	}

	public Collection<Presentation> findAll() {
		Collection<Presentation> result;

		result = this.presentationRepository.findAll();

		return result;
	}

	public Presentation findOne(final int presentationId) {
		Presentation result;

		result = this.presentationRepository.findOne(presentationId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.presentationRepository.flush();
	}

	//Other business methods

}
