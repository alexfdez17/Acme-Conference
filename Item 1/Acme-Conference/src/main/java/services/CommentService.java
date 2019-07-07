
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import repositories.CommentRepository;

public class CommentService {

	// Managed repository
	@Autowired
	private CommentRepository	commentRepository;


	// ------------------

	// Supporting services

	// -------------------

	// Simple CRUD methods

	// -------------------

	// Other business methods
	// ----------------------

	// Dashboard

	public List<Double> minMaxAvgStddevPerActivity() {
		return this.commentRepository.minMaxAvgStddevPerActivity();
	}

	public List<Double> minMaxAvgStddevPerConference() {
		return this.commentRepository.minMaxAvgStddevPerConference();
	}

	// ---------

	// Auxiliary methods

	// -----------------

}
