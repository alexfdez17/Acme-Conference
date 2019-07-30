
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ConferenceRepository;
import domain.Activity;
import domain.Author;
import domain.Conference;
import domain.Finder;

@Service
@Transactional
public class ConferenceService {

	// Managed Repository
	@Autowired
	private ConferenceRepository	conferenceRepository;

	// Supporting services
	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private AuthorService			authorService;

	@Autowired
	private Validator				validator;


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
		this.checkDatesOnSave(conference);

		final int conferenceId = conference.getId();

		if (this.exists(conferenceId)) {
			final Conference retrieved = this.findOne(conferenceId);

			Assert.isTrue(!retrieved.getIsFinal());
		}

		return this.conferenceRepository.save(conference);
	}

	public Collection<Conference> findAll() {
		this.administratorService.findByPrincipal();

		return this.conferenceRepository.findAll();
	}

	public Conference findOne(final int conferenceId) {
		final Conference result;

		result = this.conferenceRepository.findOne(conferenceId);

		return result;
	}
	public boolean exists(final int conferenceId) {
		return this.conferenceRepository.exists(conferenceId);
	}

	// Other business methods
	public Collection<Conference> findAllPrincipalIsRegistered() {
		final Author principal = this.authorService.findByPrincipal();
		final int principalId = principal.getId();

		return this.findAllAuthorIsRegistered(principalId);
	}

	public Collection<Conference> findByCategory(final Finder finder) {
		Assert.notNull(finder);
		Collection<Conference> result;

		if (finder.getCategory().equals(""))
			result = this.findFinals();
		else
			result = this.conferenceRepository.findByCategoryName(finder.getCategory());

		return result;
	}

	public Collection<Conference> findByMaximumFee(final Finder finder) {
		Assert.notNull(finder);
		Collection<Conference> result;

		if (finder.getMaximumFee() == 0.0)
			result = this.findFinals();
		else
			result = this.conferenceRepository.findByMaximumFee(finder.getMaximumFee());

		return result;
	}

	public Collection<Conference> findByStartDate(final Finder finder) {
		Assert.notNull(finder);
		Collection<Conference> result;

		if (finder.getStartDate() == null)
			result = this.findFinals();
		else
			result = this.conferenceRepository.findByStartDate(finder.getStartDate());

		return result;
	}

	public Collection<Conference> findByEndDate(final Finder finder) {
		Assert.notNull(finder);
		Collection<Conference> result;

		if (finder.getEndDate() == null)
			result = this.findFinals();
		else
			result = this.conferenceRepository.findByEndDate(finder.getEndDate());

		return result;
	}

	public Collection<Conference> findAllByKeyword(final String keyword) {
		Assert.notNull(keyword);
		Collection<Conference> result;

		if (keyword == "")
			result = this.findFinals();
		else
			result = this.conferenceRepository.findAllByKeyword(keyword);

		return result;
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
		return this.conferenceRepository.findAllRunning();
	}

	public Conference update(final Conference conference) {
		Assert.notNull(conference);

		this.administratorService.findByPrincipal();

		return this.conferenceRepository.save(conference);
	}

	public Conference reconstruct(final Conference conference, final BindingResult binding) {
		final Conference result = conference;
		final int conferenceId = conference.getId();

		final Collection<Activity> activities;

		if (!this.exists(conferenceId))
			activities = new ArrayList<>();
		else {
			final Conference retrieved = this.findOne(conferenceId);

			activities = retrieved.getActivities();
		}

		result.setActivities(activities);

		this.checkDates(conference, binding);
		this.validator.validate(result, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return result;
	}

	// Auxiliary methods
	private Collection<Conference> findAllAuthorIsRegistered(final int authorId) {
		return this.conferenceRepository.findAllAuthorIsRegistered(authorId);
	}

	private void checkDates(final Conference conference, final BindingResult binding) {
		final Date cameraReadyDeadline = conference.getCameraReadyDeadline();
		final Date notificationDeadline = conference.getNotificationDeadline();
		final Date submissionDeadline = conference.getSubmissionDeadline();

		final Date endDate = conference.getEndDate();
		final Date startDate = conference.getStartDate();

		if (startDate.after(endDate))
			binding.rejectValue("startDate", "conference.startDate.after.endDate.error");
		else if (cameraReadyDeadline.after(startDate))
			binding.rejectValue("cameraReadyDeadline", "conference.cameraReadyDeadline.after.startDate.error");
		else if (notificationDeadline.after(cameraReadyDeadline))
			binding.rejectValue("notificationDeadline", "conference.notificationDeadline.after.cameraReadyDeadline.error");
		else if (submissionDeadline.after(notificationDeadline))
			binding.rejectValue("submissionDeadline", "conference.submissionDeadline.after.notificationDeadline.error");

	}

	private void checkDatesOnSave(final Conference conference) {
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
