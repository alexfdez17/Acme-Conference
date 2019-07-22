
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ConferenceRepository;
import domain.Activity;
import domain.Conference;

@Service
@Transactional
public class ConferenceService {

	// Managed Repository
	@Autowired
	private ConferenceRepository	conferenceRepository;

	// Supporting services
	@Autowired
	private AdministratorService	administratorService;


	// CRUD

	public Conference create() {
		this.administratorService.findByPrincipal();

		final Conference result = new Conference();
		final Collection<Activity> activities = new ArrayList<>();

		result.setActivities(activities);

		return result;
	}

	public Conference save(final Conference conference) {
		Assert.notNull(conference);

		this.administratorService.findByPrincipal();
		this.checkDates(conference);

		final int conferenceId = conference.getId();

		if (this.exists(conferenceId)) {
			final Conference retrieved = this.findOne(conferenceId);

			Assert.isTrue(!retrieved.getIsFinal());
		}

		return this.conferenceRepository.save(conference);
	}

	public void delete(final Conference conference) {
		Assert.notNull(conference);
		Assert.isTrue(conference.getId() != 0);

		this.conferenceRepository.delete(conference);
	}

	public Collection<Conference> findAll() {
		Collection<Conference> result;

		result = this.conferenceRepository.findAll();

		return result;
	}

	public Conference findOne(final int conferenceId) {
		final Conference result = this.conferenceRepository.findOne(conferenceId);

		Assert.notNull(this.exists(conferenceId));

		return result;
	}

	public boolean exists(final int conferenceId) {
		return this.conferenceRepository.exists(conferenceId);
	}

	public void flush() {
		this.conferenceRepository.flush();
	}

	// Other business methods

	public Collection<Conference> findAllByKeyword(final String keyword) {
		Assert.notNull(keyword);

		return this.conferenceRepository.findAllByKeyword(keyword);
	}

	public Collection<Conference> findAllCameraReadyDeadlineElapsesLess5Days() {
		this.administratorService.findByPrincipal();

		return this.conferenceRepository.findAllCameraReadyDeadlineElapsesLess5Days();
	}

	public Collection<Conference> findAllNotificationDeadlineElapsesLess5Days() {
		this.administratorService.findByPrincipal();

		return this.conferenceRepository.findAllNotificationDeadlineElapsesLess5Days();
	}

	public Collection<Conference> findAllOrganisedLess5Days() {
		this.administratorService.findByPrincipal();

		return this.conferenceRepository.findAllOrganisedLess5Days();
	}

	public Collection<Conference> findAllSubmissionDeadlineElapsedLast5Days() {
		this.administratorService.findByPrincipal();

		return this.conferenceRepository.findAllSubmissionDeadlineElapsedLast5Days();
	}

	public Collection<Conference> findFinals() {
		Collection<Conference> result;

		result = this.conferenceRepository.findFinals();

		return result;
	}

	public Collection<Conference> findAllForthcoming() {
		return this.conferenceRepository.findAllForthcoming();
	}

	public Collection<Conference> findAllPast() {
		return this.conferenceRepository.findAllPast();
	}

	public Collection<Conference> findAllRunning() {
		return this.findAllRunning();
	}

	// Auxiliary methods
	private void checkDates(final Conference conference) {
		final Date cameraReadyDeadline = conference.getCameraReadyDeadline();
		final Date notificationDeadline = conference.getNotificationDeadline();
		final Date submissionDeadline = conference.getSubmissionDeadline();

		final Date endDate = conference.getEndDate();
		final Date startDate = conference.getStartDate();

		Assert.isTrue(startDate.before(endDate));
		Assert.isTrue(cameraReadyDeadline.before(startDate));
		Assert.isTrue(notificationDeadline.before(cameraReadyDeadline));
		Assert.isTrue(submissionDeadline.before(notificationDeadline));
	}

	//Stats for dashboard

	public Double[] minMaxAvgStddevFee() {
		return this.conferenceRepository.minMaxAvgStddevFee();
	}

	public Double[] minMaxAvgStddevDays() {
		return this.conferenceRepository.minMaxAvgStddevDays();
	}

	public Double[] applicationsPerRookieStats() {

		//this.administratorService.findByPrincipal();

		final Double[] res = new Double[4];
		final Collection<Long> stats = this.conferenceRepository.getConferencesPerCategory();

		if (!stats.isEmpty()) {
			Double sum = 0.0;
			for (final Long n : stats)
				sum += n;
			res[0] = sum / stats.size(); // Average

			res[1] = Collections.min(stats).doubleValue(); //Minimum

			res[2] = Collections.max(stats).doubleValue(); //Maximum

			Double num = 0.0;
			Double numi = 0.0;
			for (final Long n : stats) {
				numi = Math.pow(n - res[0], 2);
				num += numi;
			}
			if (num != 0)
				res[3] = Math.sqrt(num / (stats.size() - 1)); // Standard
																// deviation
			else
				res[3] = 0.;
		} else {
			res[0] = 0.;
			res[1] = 0.;
			res[2] = 0.;
			res[3] = 0.;
		}

		return res;
	}

}
