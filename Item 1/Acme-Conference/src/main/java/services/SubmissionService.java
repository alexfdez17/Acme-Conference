
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SubmissionRepository;
import domain.Author;
import domain.Submission;

@Service
@Transactional
public class SubmissionService {

	// Managed repository
	@Autowired
	private SubmissionRepository	submissionRepository;

	// Supporting services
	@Autowired
	private AuthorService			authorService;


	// Simple CRUD methods

	public Submission create() {
		Submission result;
		Author author;
		String initials, letters, middleName;

		result = new Submission();
		author = this.authorService.findByPrincipal();

		middleName = "X";
		if (!author.getMiddleName().equals(""))
			middleName = author.getMiddleName().substring(0, 1);

		initials = author.getName().substring(0, 1) + middleName + author.getSurname().substring(0, 1);
		letters = RandomStringUtils.randomAlphanumeric(4).toUpperCase();

		result.setMoment(new Date(System.currentTimeMillis() - 1000));
		result.setAuthor(author);
		result.setStatus("UNDER-REVIEW");
		result.setTicker(initials.toUpperCase() + "-" + letters);

		return result;
	}

	public Submission save(final Submission submission) {
		Assert.notNull(submission);
		Submission result;

		final Author principal = this.authorService.findByPrincipal();
		final Author owner = submission.getAuthor();
		Assert.isTrue(principal.equals(owner));

		Assert.isTrue(submission.getConference().getSubmissionDeadline().after(Calendar.getInstance().getTime()));

		if (submission.getCameraReadyPaper() != null) {
			Assert.isTrue(submission.getStatus().equals("ACCEPTED"));
			Assert.isTrue(submission.getConference().getCameraReadyDeadline().after(Calendar.getInstance().getTime()));
		}

		result = this.submissionRepository.save(submission);
		return result;
	}

	public void delete(final Submission submission) {
		Assert.notNull(submission);
		Assert.isTrue(submission.getId() != 0);

		this.submissionRepository.delete(submission);
	}

	public Collection<Submission> findAll() {
		Collection<Submission> result;

		result = this.submissionRepository.findAll();

		return result;
	}

	public Submission findOne(final int submissionId) {
		Submission result;

		result = this.submissionRepository.findOne(submissionId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.submissionRepository.flush();
	}

	// Other business methods

	// Dashboard

	public List<Double> minMaxAvgStddevPerConference() {
		return this.submissionRepository.minMaxAvgStddevPerConference();
	}


	// Auxiliary methods

	@Autowired
	private Validator	validator;


	public Submission reconstruct(final Submission submission, final BindingResult binding) {
		Submission result;

		if (submission.getId() == 0)
			result = submission;
		else {
			final Submission retrieved = this.submissionRepository.findOne(submission.getId());
			result = submission;

			result.setAuthor(retrieved.getAuthor());
			result.setConference(retrieved.getConference());
			result.setMoment(retrieved.getMoment());
			result.setTicker(retrieved.getTicker());
			result.setStatus(retrieved.getStatus());

		}

		this.validator.validate(result, binding);
		this.flush();
		return result;
	}

}
