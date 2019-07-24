
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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


	// Simple CRUD methods

	public Report create(final int submissionId) {
		final Reviewer principal = this.reviewerService.findByPrincipal();
		final Submission submission = this.submissionService.findOne(submissionId);

		final Report result = new Report();
		final Collection<String> comments = new ArrayList<>();

		result.setComments(comments);
		result.setReviewer(principal);
		result.setSubmission(submission);

		return result;
	}

	public Report save(final Report report) {
		Assert.notNull(report);

		this.reviewerService.findByPrincipal();

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
	//
	//	public Collection<Report> findBorderLine(final Submission submission) {
	//		return this.reportRepository.findBorderLine(submission.getId());
	//	}
	//
	//	public Collection<Report> findBySubmission(final Submission submission) {
	//		return this.reportRepository.findBySubmission(submission.getId());
	//	}

	// Auxiliary methods
	private Collection<Report> findAllByReviewerId(final int reviewerId) {
		return this.reportRepository.findAllByReviewerId(reviewerId);
	}
}
