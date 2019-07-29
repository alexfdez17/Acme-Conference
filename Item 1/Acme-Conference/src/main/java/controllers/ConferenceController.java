
package controllers;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import domain.Activity;
import domain.Conference;

@Controller
@RequestMapping("/conference")
public class ConferenceController extends AbstractController {

	// Managed service
	@Autowired
	private ConferenceService	conferenceService;


	// Supporting services

	// Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final String keyword) {
		final ModelAndView result;

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

	private ModelAndView setModelAndView(final Collection<Conference> conferences) {
		final ModelAndView result = new ModelAndView("conference/list");
		final Date today = new Date();

		result.addObject("conferences", conferences);
		result.addObject("requestURI", "conference/list.do");
		result.addObject("today", today);

		return result;
	}
}
