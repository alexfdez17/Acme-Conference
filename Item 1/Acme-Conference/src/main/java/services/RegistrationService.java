
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.RegistrationRepository;
import domain.Author;
import domain.Conference;
import domain.CreditCard;
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

	@Autowired
	private Validator				validator;


	// Simple CRUD methods
	public Registration create(final int conferenceId) {
		final Author principal = this.authorService.findByPrincipal();
		final Conference conference = this.conferenceService.findOne(conferenceId);
		final Collection<Conference> conferences = this.conferenceService.findAllByRegisteredPrincipal();

		Assert.isTrue(!conferences.contains(conference));
		Assert.isTrue(conference.getStartDate().after(new Date()));

		final Registration result = new Registration();

		result.setAuthor(principal);
		result.setConference(conference);

		return result;
	}

	public Registration save(final Registration registration) {
		Assert.notNull(registration);

		final int registrationId = registration.getId();

		Assert.isTrue(!this.exists(registrationId));

		this.authorService.findByPrincipal();

		final Conference conference = registration.getConference();
		final Collection<Conference> conferences = this.conferenceService.findAllByRegisteredPrincipal();

		Assert.isTrue(!conferences.contains(conference));
		Assert.isTrue(conference.getStartDate().after(new Date()));

		final CreditCard creditCard = registration.getCreditCard();

		this.checkExpirationOnSave(creditCard);

		return this.registrationRepository.save(registration);
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

	public Registration reconstruct(final Registration registration, final BindingResult binding) {
		final Registration result = registration;
		final Author principal = this.authorService.findByPrincipal();
		final CreditCard creditCard = registration.getCreditCard();
		final String creditCardNumber = creditCard.getNumber();

		result.setAuthor(principal);

		final String isNumber = "\\d+";

		if (!creditCardNumber.matches(isNumber))
			binding.rejectValue("creditCard.number", "registration.credit.card.number.error");

		this.checkExpiration(creditCard, binding);
		this.validator.validate(result, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return result;
	}
	// ----------------------

	// Dashboard

	public Double[] minMaxAvgStddevPerConference() {
		return this.registrationRepository.minMaxAvgStddevPerConference();
	}

	// ---------

	// Auxiliary methods
	private void checkExpiration(final CreditCard creditCard, final BindingResult binding) {
		final Date today = new Date();
		final Calendar calendar = Calendar.getInstance();

		calendar.setTime(today);

		final int currentYear = calendar.get(Calendar.YEAR) % 100;
		final int currentMonth = calendar.get(Calendar.MONTH) + 1;

		final int expirationYear = creditCard.getExpirationYear();

		if (expirationYear < currentYear)
			binding.rejectValue("creditCard.expirationYear", "registration.credit.card.expiration.year.error");
		else if (expirationYear == currentYear && creditCard.getExpirationMonth() < currentMonth)
			binding.rejectValue("creditCard.expirationMonth", "registration.credit.card.expiration.month.error");
	}

	private void checkExpirationOnSave(final CreditCard creditCard) {
		final Date today = new Date();
		final Calendar calendar = Calendar.getInstance();

		calendar.setTime(today);

		final int currentYear = calendar.get(Calendar.YEAR) % 100;
		final int currentMonth = calendar.get(Calendar.MONTH) + 1;

		final int expirationYear = creditCard.getExpirationYear();

		Assert.isTrue(expirationYear >= currentYear);

		if (expirationYear == currentYear)
			Assert.isTrue(creditCard.getExpirationMonth() >= currentMonth);
	}

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
