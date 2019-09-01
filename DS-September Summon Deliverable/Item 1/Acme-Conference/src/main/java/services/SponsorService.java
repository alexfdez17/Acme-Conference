
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SponsorRepository;
import security.Authority;
import security.UserAccount;
import domain.Sponsor;
import forms.RegisterSponsorForm;

@Service
@Transactional
public class SponsorService {

	//Managed Repository
	@Autowired
	private SponsorRepository	sponsorRepository;

	// Supporting Services
	@Autowired
	private ActorService		actorService;


	// CRUD

	public Sponsor create() {
		Sponsor result;

		result = new Sponsor();

		return result;
	}

	public Sponsor save(final Sponsor sponsor) {
		Assert.notNull(sponsor);
		Sponsor result;

		Assert.isTrue(sponsor.getPhone().matches("\\d{4,99}"));

		result = this.sponsorRepository.save(sponsor);
		this.sponsorRepository.flush();
		return result;
	}

	public void delete(final Sponsor sponsor) {
		Assert.notNull(sponsor);
		Assert.isTrue(sponsor.getId() != 0);
		Assert.isTrue(this.sponsorRepository.exists(sponsor.getId()));

		this.sponsorRepository.delete(sponsor);
	}

	public Collection<Sponsor> findAll() {
		Collection<Sponsor> result;

		result = this.sponsorRepository.findAll();

		return result;
	}

	public Sponsor findOne(final int sponsorId) {
		Sponsor result;

		result = this.sponsorRepository.findOne(sponsorId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.sponsorRepository.flush();
	}

	//Other business methods

	public Sponsor findByPrincipal() {
		return this.actorService.findPrincipal();
	}

	public Sponsor registerSponsor(final RegisterSponsorForm registerSponsorForm) {
		final Sponsor result = this.create();

		final UserAccount userAccount = new UserAccount();
		final String password = registerSponsorForm.getPassword();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String hashedPassword = encoder.encodePassword(password, null);
		Assert.isTrue(registerSponsorForm.getPassword().equals(registerSponsorForm.getPassword2()));
		userAccount.setPassword(hashedPassword);
		userAccount.setUsername(registerSponsorForm.getUsername());

		final Authority authority = new Authority();
		authority.setAuthority(Authority.SPONSOR);
		final List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		Assert.isTrue(registerSponsorForm.getPhone().matches("\\d{4,99}"));

		result.setUserAccount(userAccount);
		result.setAddress(registerSponsorForm.getAddress());
		result.setEmail(registerSponsorForm.getEmail());
		result.setName(registerSponsorForm.getName());
		result.setMiddleName(registerSponsorForm.getMiddleName());
		result.setPhone(registerSponsorForm.getPhone());
		result.setPhoto(registerSponsorForm.getPhoto());
		result.setSurname(registerSponsorForm.getSurname());
		this.save(result);

		return result;
	}


	// Reconstruct

	@Autowired
	private Validator	validator;


	public Sponsor reconstruct(final Sponsor sponsor, final BindingResult binding) {
		Sponsor result;

		if (sponsor.getId() == 0)
			result = sponsor;
		else {
			final Sponsor retrieved = this.sponsorRepository.findOne(sponsor.getId());
			result = sponsor;

			result.setUserAccount(retrieved.getUserAccount());

		}

		this.validator.validate(result, binding);
		this.flush();
		return result;
	}

}
