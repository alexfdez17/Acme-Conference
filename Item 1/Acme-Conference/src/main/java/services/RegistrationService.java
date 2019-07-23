
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RegistrationRepository;
import domain.Author;
import domain.Conference;
import domain.Registration;

@Service
@Transactional
public class RegistrationService {

	// Managed repository
	@Autowired
	private RegistrationRepository	registrationRepository;

	// ------------------

	// Supporting services
	@Autowired
	private AuthorService			authorService;

	@Autowired
	private ConferenceService		conferenceService;


	// -------------------

	// Simple CRUD methods
	public Registration create(final int conferenceId) {
		final Author principal = this.authorService.findByPrincipal();
		final Conference conference = this.conferenceService.findOne(conferenceId);

		Assert.isTrue(conference.getStartDate().after(new Date()));

		final Registration result = new Registration();

		result.setAuthor(principal);
		result.setConference(conference);

		return result;
	}

	public Registration save(final Registration registration) {
		Assert.notNull(registration);

		this.authorService.findByPrincipal();

		final Conference conference = registration.getConference();

		Assert.isTrue(conference.getStartDate().after(new Date()));

		return this.save(registration);
	}

	public Registration findOne(final int registrationId) {
		final Registration result = this.registrationRepository.findOne(registrationId);

		Assert.notNull(this.exists(registrationId));

		this.isOwnedByPrincipal(result);

		return result;
	}

	public boolean exists(final int registrationId) {
		return this.registrationRepository.exists(registrationId);
	}
	// -------------------

	// Other business methods
	public Collection<Registration> findAllByPrincipal() {
		final Author principal = this.authorService.findByPrincipal();
		final int principalId = principal.getId();

		return this.findAllByAuthorId(principalId);
	}
	// ----------------------

	// Dashboard

	public Double[] minMaxAvgStddevPerConference() {
		return this.registrationRepository.minMaxAvgStddevPerConference();
	}

	// ---------

	// Auxiliary methods
	private Collection<Registration> findAllByAuthorId(final int authorId) {
		return this.registrationRepository.findAllByAuthorId(authorId);
	}

	private void isOwnedByPrincipal(final Registration registration) {
		final Author principal = this.authorService.findByPrincipal();
		final Author author = registration.getAuthor();

		Assert.isTrue(principal.getId() == author.getId());
	}
	// -----------------

}
