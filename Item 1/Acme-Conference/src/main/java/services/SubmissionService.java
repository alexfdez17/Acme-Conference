
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.SubmissionRepository;

@Service
@Transactional
public class SubmissionService {

	// Managed repository
	@Autowired
	private SubmissionRepository	submissionRepository;


	// ------------------

	// Supporting services

	// -------------------

	// Simple CRUD methods

	// -------------------

	// Other business methods
	// ----------------------

	// Dashboard

	public List<Double> minMaxAvgStddevPerConference() {
		return this.submissionRepository.minMaxAvgStddevPerConference();
	}

	// ---------

	// Auxiliary methods

	// -----------------

}
