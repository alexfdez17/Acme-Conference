
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SubmissionRepository;
import domain.Author;
import domain.Conference;
import domain.Paper;
import domain.Report;
import domain.Reviewer;
import domain.Submission;
import forms.SubmissionCameraReadyPaperForm;
import forms.SubmissionPaperForm;

@Service
@Transactional
public class SubmissionService {

	// Managed repository
	@Autowired
	private SubmissionRepository	submissionRepository;

	// Supporting services
	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private AuthorService			authorService;

	@Autowired
	private ReportService			reportService;

	@Autowired
	private ReviewerService			reviewerService;


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

		final Collection<Reviewer> reviewers = new ArrayList<Reviewer>();

		result.setMoment(new Date(System.currentTimeMillis() - 1000));
		result.setAuthor(author);
		result.setStatus("UNDER-REVIEW");
		result.setTicker(initials.toUpperCase() + "-" + letters);
		result.setReviewers(reviewers);
		result.setReportsAvailable(false);

		return result;
	}

	public Submission save(final Submission submission) {
		Assert.notNull(submission);
		Submission result;

		final Author principal = this.authorService.findByPrincipal();
		final Author owner = submission.getAuthor();
		Assert.isTrue(principal.equals(owner));

		if (submission.getId() == 0)
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
		Assert.isTrue(this.exists(submissionId));

		return this.submissionRepository.findOne(submissionId);
	}

	public boolean exists(final int submissionId) {
		return this.submissionRepository.exists(submissionId);
	}

	public void flush() {
		this.submissionRepository.flush();
	}

	// Other business methods
	public Submission assignToReviewers(final Submission submission, final Collection<Reviewer> reviewers) {
		Assert.isTrue(reviewers.size() <= 3, "submission.exceded.assignments.error");
		Assert.isTrue(submission.getStatus().equals("UNDER-REVIEW"));

		this.administratorService.findByPrincipal();

		submission.setReviewers(reviewers);

		return this.submissionRepository.save(submission);
	}

	public void autoAssignAll() {
		final Collection<Submission> allUnderReview = this.findAllUnderReview();

		for (final Submission submission : allUnderReview) {
			final String conferenceTitle = submission.getConference().getTitle();
			final String conferenceSummary = submission.getConference().getSummary();
			final int submissionId = submission.getId();

			final Collection<Reviewer> reviewers = this.reviewerService.findAllByConferenceTitleAndSummaryNotAssignedToSubmission(conferenceTitle, conferenceSummary, submissionId);

			this.assignToReviewers(submission, reviewers);
		}
	}

	public Collection<Submission> findAllByReviewerAsPrincipal() {
		final Reviewer principal = this.reviewerService.findByPrincipal();
		final int principalId = principal.getId();

		return this.findAllByReviewerId(principalId);
	}

	public Collection<Submission> findAllWithReportWrittenByPrincipal() {
		final Reviewer principal = this.reviewerService.findByPrincipal();
		final int principalId = principal.getId();

		return this.findAllWithReportWrittenByReviewer(principalId);
	}

	public Collection<Submission> findAllByStatus(final String status) {
		this.administratorService.findByPrincipal();
		this.checkStatus(status);

		return this.submissionRepository.findAllByStatus(status);
	}

	public Collection<Submission> findAllNotUnderReview() {
		return this.submissionRepository.findAllNotUnderReview();
	}

	public Collection<Submission> findByAuthor(final Author author) {
		return this.submissionRepository.findByAuthorId(author.getId());
	}

	public Collection<Submission> findByConference(final Conference conference) {
		return this.submissionRepository.findByConference(conference.getId());
	}

	public Submission makeSubmission(final SubmissionPaperForm submissionPaperForm) {
		final Submission result = this.create();
		result.setConference(submissionPaperForm.getConference());

		final Paper paper = new Paper();

		paper.setTitle(submissionPaperForm.getTitle());
		paper.setAuthors(submissionPaperForm.getAuthors());
		paper.setSummary(submissionPaperForm.getSummary());
		paper.setDocument(submissionPaperForm.getDocument());

		result.setPaper(paper);
		this.save(result);

		return result;
	}

	public Submission update(final Submission submission) {
		Assert.notNull(submission);

		return this.submissionRepository.save(submission);
	}

	public Submission uploadCameraReadyPaper(final SubmissionCameraReadyPaperForm submissionCameraReadyPaperForm) {
		final Submission result = submissionCameraReadyPaperForm.getSubmission();
		final Paper cameraReady = new Paper();

		cameraReady.setTitle(submissionCameraReadyPaperForm.getTitle());
		cameraReady.setAuthors(submissionCameraReadyPaperForm.getAuthors());
		cameraReady.setSummary(submissionCameraReadyPaperForm.getSummary());
		cameraReady.setDocument(submissionCameraReadyPaperForm.getDocument());

		result.setCameraReadyPaper(cameraReady);
		this.save(result);

		return result;
	}

	public Collection<Submission> makeDecision(final Conference conference) {
		final Collection<Submission> result;
		Collection<Submission> submissions;

		submissions = this.findByConference(conference);

		if (!submissions.isEmpty())
			for (final Submission s : submissions) {
				final Collection<Report> accept = this.reportService.findAllAcceptBySubmission(s);
				final Collection<Report> reject = this.reportService.findAllRejectBySubmission(s);

				if (accept.size() < reject.size())
					s.setStatus("REJECTED");
				else
					s.setStatus("ACCEPTED");

				this.submissionRepository.save(s);
			}

		result = this.findByConference(conference);
		return result;

	}

	// Dashboard

	public Double[] minMaxAvgStddevPerConference() {
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
			result.setPaper(retrieved.getPaper());
			result.setCameraReadyPaper(retrieved.getCameraReadyPaper());
			result.setReportsAvailable(retrieved.getReportsAvailable());

		}

		if (result.getReviewers() != null && result.getReviewers().size() > 3)
			binding.rejectValue("reviewers", "submission.reviewers.size.error");
		else if (result.getReviewers() == null || result.getReviewers().isEmpty())
			binding.rejectValue("reviewers", "submission.reviewers.empty.error");

		this.validator.validate(result, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return result;
	}

	private void checkStatus(final String status) {
		Assert.notNull(status);
		Assert.isTrue(status.equals("ACCEPTED") || status.equals("REJECTED") || status.equals("UNDER-REVIEW"));
	}

	private Collection<Submission> findAllUnderReview() {
		return this.submissionRepository.findAllUnderReview();
	}

	private Collection<Submission> findAllByReviewerId(final int reviewerId) {
		return this.submissionRepository.findAllByReviewerId(reviewerId);
	}

	private Collection<Submission> findAllWithReportWrittenByReviewer(final int reviewerId) {
		return this.submissionRepository.findAllWithReportWrittenByReviewer(reviewerId);
	}
}
