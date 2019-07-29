
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import services.PanelService;
import services.PresentationService;
import services.TutorialService;
import domain.Activity;
import domain.Conference;
import domain.Panel;
import domain.Presentation;
import domain.Section;
import domain.Tutorial;

@Controller
@RequestMapping("/activity")
public class ActivityController extends AbstractController {

	@Autowired
	private ConferenceService	conferenceService;

	@Autowired
	private TutorialService		tutorialService;

	@Autowired
	private PanelService		panelService;

	@Autowired
	private PresentationService	presentationService;


	// Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int conferenceId) {
		final ModelAndView result;
		final Collection<Activity> activities;
		final Conference conference;

		conference = this.conferenceService.findOne(conferenceId);

		activities = conference.getActivities();

		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("conferenceId", conferenceId);
		result.addObject("conference", conference);

		return result;
	}

	// Display --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int activityId) {
		final ModelAndView result;

		if (this.tutorialService.findOne(activityId) != null) {
			final Tutorial tutorial = this.tutorialService.findOne(activityId);
			final Collection<Section> sections = tutorial.getSections();
			result = new ModelAndView("activity/displayTutorial");
			result.addObject("type", "tutorial");
			result.addObject("activity", tutorial);
			result.addObject("sections", sections);
		} else if (this.panelService.findOne(activityId) != null) {
			final Panel panel = this.panelService.findOne(activityId);
			result = new ModelAndView("activity/displayPanel");
			result.addObject("type", "panel");
			result.addObject("activity", panel);
		} else {
			final Presentation presentation = this.presentationService.findOne(activityId);
			result = new ModelAndView("activity/displayPresentation");
			result.addObject("type", "presentation");
			result.addObject("activity", presentation);
		}

		return result;
	}

}
