
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
import services.PresentationService;
import controllers.AbstractController;
import domain.Conference;
import domain.Presentation;
import forms.ActivityPresentationForm;

@Controller
@RequestMapping("/presentation/administrator")
public class PresentationAdministratorController extends AbstractController {

	@Autowired
	private PresentationService	presentationService;

	@Autowired
	private ConferenceService	conferenceService;


	// Creating --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final int conferenceId) {
		ModelAndView result;
		Conference conference;
		ActivityPresentationForm activityPresentationForm;

		conference = this.conferenceService.findOne(conferenceId);

		activityPresentationForm = new ActivityPresentationForm();
		activityPresentationForm.setConference(conference);

		result = new ModelAndView("activity/createPresentation");
		result.addObject("activityForm", activityPresentationForm);
		result.addObject("type", "presentation");
		return result;
	}

	// Save --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("activityForm") @Valid final ActivityPresentationForm activityPresentationForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createCreateModelAndView(activityPresentationForm);
		else
			try {
				this.presentationService.register(activityPresentationForm);
				result = new ModelAndView("redirect:/activity/administrator/list.do?conferenceId=" + activityPresentationForm.getConference().getId());

			} catch (final Throwable oops) {
				result = this.createCreateModelAndView(activityPresentationForm, "activity.commit.error");
			}
		return result;
	}

	// Edition --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int presentationId) {
		ModelAndView result;
		Presentation presentation;

		presentation = this.presentationService.findOne(presentationId);

		result = this.createEditModelAndView(presentation);
		return result;
	}

	// Update --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView update(@ModelAttribute("activity") @Valid final Presentation presentation, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(presentation);
		else
			try {
				this.presentationService.save(presentation);
				result = new ModelAndView("redirect:/activity/administrator/display.do?activityId=" + presentation.getId());

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(presentation, "activity.commit.error");
			}
		return result;
	}

	// Delete --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Presentation presentation, final BindingResult bindingResult) {
		ModelAndView result;

		try {
			this.presentationService.delete(presentation);
			result = new ModelAndView("redirect:/");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(presentation, "activity.commit.error");
		}

		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createCreateModelAndView(final ActivityPresentationForm activityPresentationForm) {
		return this.createCreateModelAndView(activityPresentationForm, null);
	}

	protected ModelAndView createCreateModelAndView(final ActivityPresentationForm activityPresentationForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("activity/createPresentation");
		result.addObject("activityForm", activityPresentationForm);
		result.addObject("type", "presentation");
		result.addObject("message", message);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Presentation presentation) {
		return this.createEditModelAndView(presentation, null);
	}

	protected ModelAndView createEditModelAndView(final Presentation presentation, final String message) {
		final ModelAndView result;

		result = new ModelAndView("activity/updatePresentation");
		result.addObject("activity", presentation);
		result.addObject("type", "presentation");
		result.addObject("message", message);
		return result;
	}

}
