
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ReportRepository;
import domain.Report;
import domain.Reviewer;
import domain.Submission;

@Service
@Transactional
public class ReportService {

	// Managed repository
	@Autowired
	private ReportRepository	reportRepository;

	// Supporting services
	@Autowired
	private ReviewerService		reviewerService;

	@Autowired
	private SubmissionService	submissionService;

	@Autowired
	private Validator			validator;


	// Simple CRUD methods

	public Report create(final int submissionId) {
		final Submission submission = this.submissionService.findOne(submissionId);
		final Reviewer principal = this.middleware(submission);

		final Report result = new Report();
		final Collection<String> comments = new ArrayList<>();

		result.setComments(comments);
		result.setReviewer(principal);
		result.setSubmission(submission);

		return result;
	}

	public Report save(final Report report) {
		Assert.notNull(report);

		final Submission submission = report.getSubmission();

		this.middleware(submission);

		return this.reportRepository.save(report);
	}

	public Report findOne(final int reportId) {
		Assert.isTrue(this.exists(reportId));

		return this.reportRepository.findOne(reportId);
	}

	public boolean exists(final int reportId) {
		return this.reportRepository.exists(reportId);
	}

	// Other business methods
	public Collection<Report> findAllAvailableBySubmissionId(final int submissionId) {
		Assert.isTrue(this.submissionService.exists(submissionId));

		final Submission submission = this.submissionService.findOne(submissionId);

		Assert.isTrue(submission.getReportsAvailable());

		return this.reportRepository.findAllAvailableBySubmissionId(submissionId);
	}

	public Collection<Report> findAllByPrincipal() {
		final Reviewer principal = this.reviewerService.findByPrincipal();
		final int principalId = principal.getId();

		return this.findAllByReviewerId(principalId);
	}

	public Collection<Report> findAllAcceptBySubmission(final Submission submission) {
		return this.reportRepository.findAllAcceptBySubmissionId(submission.getId());
	}

	public Collection<Report> findAllRejectBySubmission(final Submission submission) {
		return this.reportRepository.findAllRejectBySubmissionId(submission.getId());
	}

	public Report reconstruct(final Report report, final BindingResult binding) {
		final Report result = report;
		final Reviewer principal = this.reviewerService.findByPrincipal();

		result.setReviewer(principal);

		this.validator.validate(result, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return result;
	}

	// Auxiliary methods
	private Collection<Report> findAllByReviewerId(final int reviewerId) {
		return this.reportRepository.findAllByReviewerId(reviewerId);
	}

	private Reviewer middleware(final Submission submission) {
		final Reviewer principal = this.reviewerService.findByPrincipal();
		final Collection<Reviewer> assignedReviewers = submission.getReviewers();

		Assert.isTrue(assignedReviewers.contains(principal));

		return principal;
	}
}
