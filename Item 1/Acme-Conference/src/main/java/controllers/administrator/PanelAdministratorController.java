
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
import services.PanelService;
import controllers.AbstractController;
import domain.Conference;
import domain.Panel;
import forms.ActivityPanelForm;

@Controller
@RequestMapping("/panel/administrator")
public class PanelAdministratorController extends AbstractController {

	@Autowired
	private PanelService		panelService;

	@Autowired
	private ConferenceService	conferenceService;


	// Creating --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final int conferenceId) {
		ModelAndView result;
		Conference conference;
		ActivityPanelForm activityPanelForm;

		conference = this.conferenceService.findOne(conferenceId);

		activityPanelForm = new ActivityPanelForm();
		activityPanelForm.setConference(conference);

		result = new ModelAndView("activity/createPanel");
		result.addObject("activityForm", activityPanelForm);
		result.addObject("type", "panel");
		return result;
	}

	// Save --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("activityForm") @Valid final ActivityPanelForm activityPanelForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createCreateModelAndView(activityPanelForm);
		else
			try {
				this.panelService.register(activityPanelForm);
				result = new ModelAndView("redirect:/activity/administrator/list.do?conferenceId=" + activityPanelForm.getConference().getId());

			} catch (final Throwable oops) {
				result = this.createCreateModelAndView(activityPanelForm, "activity.commit.error");
			}
		return result;
	}

	// Edition --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int panelId) {
		ModelAndView result;
		Panel panel;

		panel = this.panelService.findOne(panelId);

		result = this.createEditModelAndView(panel);
		return result;
	}

	// Update --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView update(@ModelAttribute("activity") @Valid final Panel panel, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(panel);
		else
			try {
				this.panelService.save(panel);
				result = new ModelAndView("redirect:/activity/administrator/display.do?activityId=" + panel.getId());

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(panel, "activity.commit.error");
			}
		return result;
	}

	// Delete --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Panel panel, final BindingResult bindingResult) {
		ModelAndView result;

		try {
			this.panelService.delete(panel);
			result = new ModelAndView("redirect:/");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(panel, "activity.commit.error");
		}

		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createCreateModelAndView(final ActivityPanelForm activityPanelForm) {
		return this.createCreateModelAndView(activityPanelForm, null);
	}

	protected ModelAndView createCreateModelAndView(final ActivityPanelForm activityPanelForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("activity/createPanel");
		result.addObject("activityForm", activityPanelForm);
		result.addObject("type", "panel");
		result.addObject("message", message);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Panel panel) {
		return this.createEditModelAndView(panel, null);
	}

	protected ModelAndView createEditModelAndView(final Panel panel, final String message) {
		final ModelAndView result;

		result = new ModelAndView("activity/updatePanel");
		result.addObject("activity", panel);
		result.addObject("type", "panel");
		result.addObject("message", message);
		return result;
	}

}
