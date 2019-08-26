
package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import services.ActorService;
import services.ConferenceService;
import services.SponsorshipService;
import domain.Activity;
import domain.Actor;
import domain.Conference;
import domain.Sponsorship;

@Controller
@RequestMapping("/conference")
public class ConferenceController extends AbstractController {

	// Managed service
	@Autowired
	private ConferenceService	conferenceService;

	// Supporting services
	@Autowired
	private ActorService		actorService;

	@Autowired
	private SponsorshipService	sponsorshipService;


	// Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String keyword, @RequestParam(required = false) final String category) {
		final ModelAndView result;

		if (keyword != null) {
			if (keyword.equals("final"))
				result = this.listFinal();
			else if (keyword.equals("forthcoming"))
				result = this.listForthcoming();
			else if (keyword.equals("past"))
				result = this.listPast();
			else if (keyword.equals("running"))
				result = this.listRunning();
			else
				result = this.listByKeyword(keyword);
		} else
			result = this.listByCategory(category);

		return result;
	}
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int conferenceId) {
		ModelAndView result;

		try {
			final Conference conference = this.conferenceService.findOne(conferenceId);

			Assert.notNull(conference);

			result = this.createDisplayModelAndView(conference);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	protected ModelAndView createDisplayModelAndView(final Conference conference) {
		final ModelAndView result = new ModelAndView("conference/display");

		final Collection<Activity> activities = conference.getActivities();
		final Date submissionDeadline = conference.getSubmissionDeadline();
		final Date notificationDeadline = conference.getNotificationDeadline();
		final Date cameraReadyDeadline = conference.getCameraReadyDeadline();
		final Date startDate = conference.getStartDate();
		final Date endDate = conference.getEndDate();

		boolean hasSponsorships = false;

		final List<Sponsorship> sponsorships = new ArrayList<Sponsorship>(this.sponsorshipService.findByConference(conference));

		final String lang = LocaleContextHolder.getLocale().getLanguage();
		final SimpleDateFormat timeFormatter;
		final SimpleDateFormat dateFormatter;

		if (lang.equals("en")) {
			dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
			timeFormatter = new SimpleDateFormat("MM/dd/yyyy");
		} else {
			dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			timeFormatter = new SimpleDateFormat("MM/dd/yyyy");
		}

		final String formatedSubmissionDeadline = timeFormatter.format(submissionDeadline);
		final String formatedNotificationDeadline = timeFormatter.format(notificationDeadline);
		final String formatedCameraReadyDeadline = timeFormatter.format(cameraReadyDeadline);
		final String formatedStartDate = dateFormatter.format(startDate);
		final String formatedEndDate = dateFormatter.format(endDate);

		if (!sponsorships.isEmpty()) {
			hasSponsorships = true;
			final Random rand = new Random();
			final Sponsorship sponsorship = sponsorships.get(rand.nextInt(sponsorships.size()));
			result.addObject("sponsorship", sponsorship);
		}

		result.addObject("hasSponsorships", hasSponsorships);

		result.addObject("formatedSubmissionDeadline", formatedSubmissionDeadline);
		result.addObject("formatedNotificationDeadline", formatedNotificationDeadline);
		result.addObject("formatedCameraReadyDeadline", formatedCameraReadyDeadline);
		result.addObject("formatedStartDate", formatedStartDate);
		result.addObject("formatedEndDate", formatedEndDate);

		result.addObject("activities", activities);
		result.addObject("conference", conference);
		result.addObject("requestURI", "conference/display.do");

		return result;
	}
	// Auxiliary methods

	private ModelAndView listFinal() {
		final ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.findFinals();

		result = this.setModelAndView(conferences);

		return result;
	}

	private ModelAndView listForthcoming() {
		final ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.findAllForthcoming();

		result = this.setModelAndView(conferences);

		return result;
	}

	private ModelAndView listPast() {
		final ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.findAllPast();

		result = this.setModelAndView(conferences);

		return result;
	}

	private ModelAndView listRunning() {
		final ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.findAllRunning();

		result = this.setModelAndView(conferences);

		return result;
	}

	private ModelAndView listByKeyword(final String keyword) {
		final ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.findAllByKeyword(keyword);

		result = this.setModelAndView(conferences);

		return result;
	}

	private ModelAndView listByCategory(final String category) {
		final ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.findAllByCategory(category);

		result = this.setModelAndView(conferences);

		return result;
	}

	private ModelAndView setModelAndView(final Collection<Conference> conferences) {
		final ModelAndView result = new ModelAndView("conference/list");
		final Date today = new Date();

		final String lang = LocaleContextHolder.getLocale().getLanguage();
		final String timeFormatter = lang.equals("en") ? "MM/dd/yyyy HH:mm" : "dd/MM/yyyy HH:mm";
		final String dateFormatter = lang.equals("en") ? "MM/dd/yyyy" : "dd/MM/yyyy";

		result.addObject("conferences", conferences);
		result.addObject("dateFormatter", dateFormatter);
		result.addObject("timeFormatter", timeFormatter);
		result.addObject("requestURI", "conference/list.do");
		result.addObject("today", today);

		this.addPrincipalRegisteredAndSubmittedConferences(result);

		return result;
	}

	private void addPrincipalRegisteredAndSubmittedConferences(final ModelAndView modelAndView) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
			final Actor principal = this.actorService.findPrincipal();
			final boolean isAuthor = this.actorService.checkAuthority(principal, Authority.AUTHOR);

			if (isAuthor) {
				final Collection<Conference> principalRegisteredConferences = this.conferenceService.findAllByRegisteredPrincipal();
				final Collection<Conference> principalSubmittedConferences = this.conferenceService.findAllByPrincipalWhoSubmitted();

				modelAndView.addObject("principalRegisteredConferences", principalRegisteredConferences);
				modelAndView.addObject("principalSubmittedConferences", principalSubmittedConferences);
			}
		}
	}
}
