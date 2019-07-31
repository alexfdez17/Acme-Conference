
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SponsorshipRepository;
import domain.Conference;
import domain.CreditCard;
import domain.Sponsor;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorshipService {

	// Managed repository
	@Autowired
	private SponsorshipRepository		sponsorshipRepository;

	// Supporting services
	@Autowired
	private SponsorService				sponsorService;

	@Autowired
	private SystemConfigurationService	systemConfigService;


	// Simple CRUD methods

	public Sponsorship create() {
		Sponsorship result;
		Sponsor sponsor;
		final CreditCard creditCard;

		result = new Sponsorship();

		sponsor = this.sponsorService.findByPrincipal();
		result.setSponsor(sponsor);

		creditCard = new CreditCard();
		result.setCreditCard(creditCard);

		return result;
	}
	public Sponsorship save(final Sponsorship sponsorship) {
		Assert.notNull(sponsorship);
		Sponsorship result;

		final Sponsor principal = this.sponsorService.findByPrincipal();
		final Sponsor owner = sponsorship.getSponsor();
		Assert.isTrue(principal.equals(owner));

		final Collection<String> makes = this.systemConfigService.findAllCreditCardMakes();
		Assert.isTrue(makes.contains(sponsorship.getCreditCard().getBrand()));

		final Date today = new Date();
		Assert.isTrue(sponsorship.getCreditCard().getExpirationYear() >= (today.getYear() - 100));

		if (sponsorship.getCreditCard().getExpirationYear() == (today.getYear() - 100))
			Assert.isTrue(sponsorship.getCreditCard().getExpirationMonth() >= (today.getMonth() + 1));

		result = this.sponsorshipRepository.save(sponsorship);
		return result;
	}

	public void delete(final Sponsorship sponsorship) {
		Assert.notNull(sponsorship);
		Assert.isTrue(sponsorship.getId() != 0);

		this.sponsorshipRepository.delete(sponsorship);
	}

	public Collection<Sponsorship> findAll() {
		Collection<Sponsorship> result;

		result = this.sponsorshipRepository.findAll();

		return result;
	}

	public Sponsorship findOne(final int sponsorshipId) {
		Assert.isTrue(this.exists(sponsorshipId));

		return this.sponsorshipRepository.findOne(sponsorshipId);
	}

	public boolean exists(final int sponsorshipId) {
		return this.sponsorshipRepository.exists(sponsorshipId);
	}

	public void flush() {
		this.sponsorshipRepository.flush();
	}

	// Other business methods

	public Collection<Sponsorship> findBySponsor(final Sponsor sponsor) {
		return this.sponsorshipRepository.findBySponsorId(sponsor.getId());
	}

	public Collection<Sponsorship> findByConference(final Conference conference) {
		return this.sponsorshipRepository.findByConference(conference.getId());
	}


	// Reconstruct

	@Autowired
	private Validator	validator;


	public Sponsorship reconstruct(final Sponsorship sponsorship, final BindingResult binding) {
		Sponsorship result;

		if (sponsorship.getId() == 0)
			result = sponsorship;
		else {
			final Sponsorship retrieved = this.sponsorshipRepository.findOne(sponsorship.getId());
			result = sponsorship;

			result.setConference(retrieved.getConference());
			result.setSponsor(retrieved.getSponsor());

		}

		this.validator.validate(result, binding);
		this.flush();
		return result;
	}

}
