
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	// Managed Repository ----------------------------------------------------

	@Autowired
	private ActorRepository	actorRepository;


	// Supported Services --------------------------------------------------

	//CRUD

	public Actor create() {
		Actor result;
		result = new Actor();
		return result;
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);
		Actor result;
		result = this.actorRepository.save(actor);
		this.actorRepository.flush();
		return result;
	}

	public void delete(final Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);
		Assert.isTrue(this.actorRepository.exists(actor.getId()));
		final int id = LoginService.getPrincipal().getId();
		final int actorid = actor.getId();
		Assert.isTrue(id == actorid || LoginService.getPrincipal().getAuthorities().contains(Authority.ADMIN));

		this.actorRepository.delete(actor);
	}

	public Collection<Actor> findAll() {
		Collection<Actor> result;

		result = this.actorRepository.findAll();

		return result;
	}

	public Actor findOne(final int actorId) {
		Actor result;

		result = this.actorRepository.findOne(actorId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.actorRepository.flush();
	}

	// Other Business Methods
	public boolean checkAuthority(final Actor actor, final String stringAuth) {
		final UserAccount userAccount = actor.getUserAccount();

		final Collection<Authority> auths = userAccount.getAuthorities();
		final Authority auth = new Authority();

		auth.setAuthority(stringAuth);

		return auths.contains(auth);
	}

	public <T> T findPrincipal() {
		final UserAccount userAccount = LoginService.getPrincipal();
		final T principal = this.findByUserAccount(userAccount);

		return principal;
	}

	private <T> T findByUserAccount(final UserAccount userAccount) {
		final T result = this.actorRepository.findByUserAccountID(userAccount.getId());

		return result;
	}

	public Actor findByUsername(final String username) {
		return this.actorRepository.findByUsername(username);
	}

}
