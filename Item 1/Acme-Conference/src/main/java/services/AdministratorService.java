
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

import repositories.AdministratorRepository;
import security.Authority;
import security.UserAccount;
import domain.Administrator;
import forms.RegisterAdministratorForm;

@Service
@Transactional
public class AdministratorService {

	//Managed Repository
	@Autowired
	private AdministratorRepository	administratorRepository;

	// Supporting Services
	@Autowired
	private ActorService			actorService;


	// CRUD

	public Administrator create() {
		Administrator result;

		result = new Administrator();

		return result;
	}

	public Administrator save(final Administrator administrator) {
		Assert.notNull(administrator);
		Administrator result;
		result = this.administratorRepository.save(administrator);
		this.administratorRepository.flush();
		return result;
	}

	public void delete(final Administrator administrator) {
		Assert.notNull(administrator);
		Assert.isTrue(administrator.getId() != 0);
		Assert.isTrue(this.administratorRepository.exists(administrator.getId()));

		this.administratorRepository.delete(administrator);
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> result;

		result = this.administratorRepository.findAll();

		return result;
	}

	public Administrator findOne(final int administratorId) {
		Administrator result;

		result = this.administratorRepository.findOne(administratorId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.administratorRepository.flush();
	}

	//Other business methods

	public Administrator findByPrincipal() {
		return this.actorService.findPrincipal();
	}

	public Administrator registerAdministrator(final RegisterAdministratorForm registerAdministratorForm) {
		final Administrator result = this.create();

		final UserAccount userAccount = new UserAccount();
		final String password = registerAdministratorForm.getPassword();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String hashedPassword = encoder.encodePassword(password, null);
		Assert.isTrue(registerAdministratorForm.getPassword().equals(registerAdministratorForm.getPassword2()));
		userAccount.setPassword(hashedPassword);
		userAccount.setUsername(registerAdministratorForm.getUsername());

		final Authority authority = new Authority();
		authority.setAuthority(Authority.ADMIN);
		final List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		result.setUserAccount(userAccount);
		result.setAddress(registerAdministratorForm.getAddress());
		result.setEmail(registerAdministratorForm.getEmail());
		result.setName(registerAdministratorForm.getName());
		result.setMiddleName(registerAdministratorForm.getMiddleName());
		result.setPhone(registerAdministratorForm.getPhone());
		result.setPhoto(registerAdministratorForm.getPhoto());
		result.setSurname(registerAdministratorForm.getSurname());
		this.save(result);

		return result;
	}


	// Reconstruct

	@Autowired
	private Validator	validator;


	public Administrator reconstruct(final Administrator administrator, final BindingResult binding) {
		Administrator result;

		if (administrator.getId() == 0)
			result = administrator;
		else {
			final Administrator retrieved = this.administratorRepository.findOne(administrator.getId());
			result = administrator;

			result.setUserAccount(retrieved.getUserAccount());

		}

		this.validator.validate(result, binding);
		this.flush();
		return result;
	}

}
