
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Author;
import domain.Conference;
import domain.Finder;

@Service
@Transactional
public class FinderService {

	// Managed Repository
	@Autowired
	private FinderRepository	finderRepository;

	//Supporting services
	@Autowired
	private AuthorService		authorService;

	@Autowired
	private ConferenceService	conferenceService;


	// CRUD

	public Finder create() {
		Finder result;

		result = new Finder();
		result.setConferences(new ArrayList<Conference>());

		return result;

	}

	public Finder save(final Finder finder) {
		Finder result;
		Author author;
		Assert.notNull(finder);

		author = this.authorService.findByPrincipal();

		final Collection<Conference> results = this.conferenceService.findFinals();
		final Collection<Conference> byKeyword = this.conferenceService.findAllByKeyword(finder.getKeyword());
		final Collection<Conference> byCategory = this.conferenceService.findAllByCategory(finder);
		final Collection<Conference> byStartDate = this.conferenceService.findByStartDate(finder);
		final Collection<Conference> byEndDate = this.conferenceService.findByEndDate(finder);
		final Collection<Conference> byMaximumFee = this.conferenceService.findByMaximumFee(finder);

		results.retainAll(byKeyword);
		results.retainAll(byCategory);
		results.retainAll(byStartDate);
		results.retainAll(byEndDate);
		results.retainAll(byMaximumFee);

		finder.setConferences(results);
		finder.setAuthor(author);

		result = this.finderRepository.saveAndFlush(finder);
		return result;
	}

	public void delete(final Finder finder) {
		Assert.notNull(finder);
		Assert.isTrue(finder.getId() != 0);
		Assert.isTrue(this.finderRepository.exists(finder.getId()));

		this.finderRepository.delete(finder);
	}

	public Collection<Finder> findAll() {
		Collection<Finder> result;

		result = this.finderRepository.findAll();

		return result;
	}

	public Finder findOne(final int finderId) {
		Finder result;

		result = this.finderRepository.findOne(finderId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.finderRepository.flush();
	}

	//Other business methods

	public Finder findByAuthor(final Author author) {
		return this.finderRepository.findByAuthorId(author.getId());
	}

}
