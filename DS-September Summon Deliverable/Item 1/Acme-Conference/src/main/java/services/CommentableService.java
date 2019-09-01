
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentableRepository;
import domain.Commentable;

@Service
@Transactional
public class CommentableService {

	// Managed repository
	@Autowired
	private CommentableRepository	commentableRepository;


	// Supporting services

	// Simple CRUD methods

	public Commentable create() {
		Commentable result;
		result = new Commentable();
		return result;
	}

	public Commentable save(final Commentable commentable) {
		Assert.notNull(commentable);
		Commentable result;
		result = this.commentableRepository.save(commentable);
		this.commentableRepository.flush();
		return result;
	}

	public void delete(final Commentable commentable) {
		Assert.notNull(commentable);
		Assert.isTrue(commentable.getId() != 0);

		this.commentableRepository.delete(commentable);
	}

	public Collection<Commentable> findAll() {
		Collection<Commentable> result;

		result = this.commentableRepository.findAll();

		return result;
	}

	public Commentable findOne(final int commentableId) {
		Commentable result;

		result = this.commentableRepository.findOne(commentableId);

		return result;
	}

	public void flush() {
		this.commentableRepository.flush();
	}

	// Other business methods

	// Auxiliary methods

}
