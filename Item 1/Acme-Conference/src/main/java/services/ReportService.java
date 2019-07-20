
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import domain.Report;
import domain.Submission;

@Service
@Transactional
public class ReportService {

	// Managed repository
	@Autowired
	private ReportRepository	reportRepository;


	// Supporting services

	// Simple CRUD methods

	public Report create() {
		Report result;

		result = new Report();

		return result;
	}

	public Report save(final Report report) {
		Assert.notNull(report);
		Report result;

		result = this.reportRepository.save(report);
		return result;
	}

	public void delete(final Report report) {
		Assert.notNull(report);
		Assert.isTrue(report.getId() != 0);

		this.reportRepository.delete(report);
	}

	public Collection<Report> findAll() {
		Collection<Report> result;

		result = this.reportRepository.findAll();

		return result;
	}

	public Report findOne(final int reportId) {
		Report result;

		result = this.reportRepository.findOne(reportId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.reportRepository.flush();
	}

	// Other business methods

	public Collection<Report> findAccept(final Submission submission) {
		return this.reportRepository.findAccept(submission.getId());
	}

	public Collection<Report> findReject(final Submission submission) {
		return this.reportRepository.findReject(submission.getId());
	}

	public Collection<Report> findBorderLine(final Submission submission) {
		return this.reportRepository.findBorderLine(submission.getId());
	}

	public Collection<Report> findBySubmission(final Submission submission) {
		return this.reportRepository.findBySubmission(submission.getId());
	}

	// Auxiliary methods

}
