
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
import domain.Commentable;

@Service
@Transactional
public class CommentService {

	// Managed repository
	@Autowired
	private CommentRepository	commentRepository;


	// Supporting services

	// Simple CRUD methods

	public Comment create() {
		Comment result;
		result = new Comment();
		result.setMoment(new Date(System.currentTimeMillis() - 1000));
		return result;
	}

	public Comment save(final Comment comment) {
		Assert.notNull(comment);
		Comment result;
		result = this.commentRepository.save(comment);
		this.commentRepository.flush();
		return result;
	}

	public void delete(final Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);

		this.commentRepository.delete(comment);
	}

	public Collection<Comment> findAll() {
		Collection<Comment> result;

		result = this.commentRepository.findAll();

		return result;
	}

	public Comment findOne(final int commentId) {
		Comment result;

		result = this.commentRepository.findOne(commentId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.commentRepository.flush();
	}

	// Other business methods

	public Collection<Comment> findByCommentable(final Commentable commentable) {
		return this.commentRepository.findByCommentable(commentable.getId());
	}

	// Dashboard

	public Double[] minMaxAvgStddevPerActivity() {
		return this.commentRepository.minMaxAvgStddevPerActivity();
	}

	public Double[] minMaxAvgStddevPerConference() {
		return this.commentRepository.minMaxAvgStddevPerConference();
	}

	// ---------

	// Auxiliary methods

	// -----------------

}
