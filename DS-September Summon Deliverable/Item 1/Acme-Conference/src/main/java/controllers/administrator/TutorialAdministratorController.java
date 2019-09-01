
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import services.TutorialService;
import controllers.AbstractController;
import domain.Conference;
import domain.Tutorial;
import forms.ActivityTutorialForm;

@Controller
@RequestMapping("/tutorial/administrator")
public class TutorialAdministratorController extends AbstractController {

	@Autowired
	private TutorialService		tutorialService;

	@Autowired
	private ConferenceService	conferenceService;


	// Creating --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final int conferenceId) {
		ModelAndView result;
		Conference conference;
		ActivityTutorialForm activityTutorialForm;

		conference = this.conferenceService.findOne(conferenceId);

		activityTutorialForm = new ActivityTutorialForm();
		activityTutorialForm.setConference(conference);

		result = new ModelAndView("activity/createTutorial");
		result.addObject("activityForm", activityTutorialForm);
		result.addObject("type", "tutorial");
		return result;
	}

	// Save --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("activityForm") @Valid final ActivityTutorialForm activityTutorialForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createCreateModelAndView(activityTutorialForm);
		else
			try {
				this.tutorialService.register(activityTutorialForm);
				result = new ModelAndView("redirect:/activity/administrator/list.do?conferenceId=" + activityTutorialForm.getConference().getId());

			} catch (final Throwable oops) {
				result = this.createCreateModelAndView(activityTutorialForm, "activity.commit.error");
			}
		return result;
	}

	// Edition --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tutorialId) {
		ModelAndView result;
		Tutorial tutorial;

		tutorial = this.tutorialService.findOne(tutorialId);

		result = this.createEditModelAndView(tutorial);
		return result;
	}

	// Update --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView update(@ModelAttribute("activity") @Valid final Tutorial tutorial, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(tutorial);
		else
			try {
				this.tutorialService.save(tutorial);
				result = new ModelAndView("redirect:/activity/administrator/display.do?activityId=" + tutorial.getId());

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(tutorial, "activity.commit.error");
			}
		return result;
	}

	// Delete --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Tutorial tutorial, final BindingResult bindingResult) {
		ModelAndView result;

		try {
			this.tutorialService.delete(tutorial);
			result = new ModelAndView("redirect:/");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(tutorial, "activity.commit.error");
		}

		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createCreateModelAndView(final ActivityTutorialForm activityTutorialForm) {
		return this.createCreateModelAndView(activityTutorialForm, null);
	}

	protected ModelAndView createCreateModelAndView(final ActivityTutorialForm activityTutorialForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("activity/createTutorial");
		result.addObject("activityForm", activityTutorialForm);
		result.addObject("type", "tutorial");
		result.addObject("message", message);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Tutorial tutorial) {
		return this.createEditModelAndView(tutorial, null);
	}

	protected ModelAndView createEditModelAndView(final Tutorial tutorial, final String message) {
		final ModelAndView result;

		result = new ModelAndView("activity/updateTutorial");
		result.addObject("activity", tutorial);
		result.addObject("type", "tutorial");
		result.addObject("message", message);
		return result;
	}

}
