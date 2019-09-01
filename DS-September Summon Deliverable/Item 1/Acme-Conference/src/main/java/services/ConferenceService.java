
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

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
	private ConferenceRepository		conferenceRepository;

	// Supporting services
	@Autowired
	private AdministratorService		administratorService;

	@Autowired
	private AuthorService				authorService;

	@Autowired
	private CategoryService				categoryService;

	@Autowired
	private SystemConfigurationService	systemConfigurationService;

	@Autowired
	private Validator					validator;


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
	public Collection<Conference> findAllByCategoryId(final int categoryId) {
		Assert.isTrue(this.categoryService.exists(categoryId));

		return this.conferenceRepository.findAllByCategoryId(categoryId);
	}

	public Collection<Conference> findAllByCategory(final String categoryName) {
		return this.conferenceRepository.findAllByCategoryName(categoryName);
	}

	public Collection<Conference> findAllByRegisteredPrincipal() {
		final Author principal = this.authorService.findByPrincipal();
		final int principalId = principal.getId();

		return this.findAllAuthorIsRegistered(principalId);
	}

	public Collection<Conference> findAllByPrincipalWhoSubmitted() {
		final Author principal = this.authorService.findByPrincipal();
		final int principalId = principal.getId();

		return this.findAllAuthorHasSubmitted(principalId);
	}

	public Collection<Conference> findAllByCategory(final Finder finder) {
		Assert.notNull(finder);
		Collection<Conference> result;

		if (finder.getCategory().equals(""))
			result = this.findFinals();
		else
			result = this.conferenceRepository.findAllByCategoryName(finder.getCategory());

		return result;
	}

	public Collection<Conference> findByMaximumFee(final Finder finder) {
		Assert.notNull(finder);
		Collection<Conference> result;

		if (finder.getMaximumFee() == null)
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

	public Collection<String> findBuzzWords() {
		final Collection<String> buzzWords = new ArrayList<>();

		final Collection<String> words = this.removeVoidWords();
		final double maxFreq = this.findMaxFreq(words) * 0.8;

		for (final String word : words) {
			int cont = 0;

			for (final String wurd : words)
				if (word.equals(wurd))
					cont++;

			if (cont > maxFreq)
				buzzWords.add(word);
		}

		return new HashSet<>(buzzWords);
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

		//this.administratorService.findByPrincipal();

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
	private int findMaxFreq(final Collection<String> words) {
		int maxFreq = 0;

		for (final String word : words) {
			int cont = 0;

			for (final String wurd : words)
				if (word.equals(wurd))
					cont++;

			if (cont > maxFreq)
				maxFreq = cont;
		}

		return maxFreq;
	}

	private Collection<String> removeVoidWords() {
		final List<String> words = new ArrayList<>();
		final Collection<String> voidWords = this.systemConfigurationService.findAllVoidWords();

		final Collection<Conference> conferences = this.findAllOrganisedLastYearOrInFuture();

		for (final Conference conference : conferences) {
			String title = conference.getTitle().toLowerCase();
			String summary = conference.getSummary().toLowerCase();

			for (final String voidWord : voidWords) {
				final String regex = "\\b" + voidWord + "\\b";

				title = title.replaceAll(regex, "");
				title = title.replaceAll("\\d", "");
				summary = summary.replaceAll(regex, "");
				summary = summary.replaceAll("\\d", "");
			}

			title.trim();
			summary.trim();

			final List<String> splitedTitle = Arrays.asList(title.split("\\W+"));
			final List<String> splittedSummary = Arrays.asList(summary.split("\\W+"));

			words.addAll(splitedTitle);
			words.addAll(splittedSummary);
		}

		return words;
	}

	private Collection<Conference> findAllAuthorHasSubmitted(final int authorId) {
		return this.conferenceRepository.findAllAuthorHasSubmitted(authorId);
	}

	private Collection<Conference> findAllAuthorIsRegistered(final int authorId) {
		return this.conferenceRepository.findAllAuthorIsRegistered(authorId);
	}

	private Collection<Conference> findAllOrganisedLastYearOrInFuture() {
		return this.conferenceRepository.findAllOrganisedLastYearOrInFuture();
	}

	private void checkDates(final Conference conference, final BindingResult binding) {
		final Date cameraReadyDeadline = conference.getCameraReadyDeadline();
		final Date notificationDeadline = conference.getNotificationDeadline();
		final Date submissionDeadline = conference.getSubmissionDeadline();

		final Date endDate = conference.getEndDate();
		final Date startDate = conference.getStartDate();

		if (startDate != null && endDate != null && startDate.after(endDate))
			binding.rejectValue("startDate", "conference.startDate.after.endDate.error");
		else if (cameraReadyDeadline != null && startDate != null && cameraReadyDeadline.after(startDate))
			binding.rejectValue("cameraReadyDeadline", "conference.cameraReadyDeadline.after.startDate.error");
		else if (notificationDeadline != null && cameraReadyDeadline != null && notificationDeadline.after(cameraReadyDeadline))
			binding.rejectValue("notificationDeadline", "conference.notificationDeadline.after.cameraReadyDeadline.error");
		else if (submissionDeadline != null && notificationDeadline != null && submissionDeadline.after(notificationDeadline))
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

	public Double[] conferencesPerCategoryStats() {

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
