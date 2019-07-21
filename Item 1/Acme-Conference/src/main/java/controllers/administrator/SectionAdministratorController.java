
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SectionService;
import services.TutorialService;
import controllers.AbstractController;
import domain.Tutorial;
import forms.SectionForm;

@Controller
@RequestMapping("/section/administrator")
public class SectionAdministratorController extends AbstractController {

	@Autowired
	private SectionService	sectionService;

	@Autowired
	private TutorialService	tutorialService;


	// Creating --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final int tutorialId) {
		ModelAndView result;
		Tutorial tutorial;
		final SectionForm sectionForm;

		tutorial = this.tutorialService.findOne(tutorialId);

		sectionForm = new SectionForm();
		sectionForm.setTutorial(tutorial);

		result = new ModelAndView("section/create");
		result.addObject("sectionForm", sectionForm);
		return result;
	}

	// Save --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("sectionForm") @Valid final SectionForm sectionForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createCreateModelAndView(sectionForm);
		else
			try {
				this.sectionService.register(sectionForm);
				result = new ModelAndView("redirect:/activity/administrator/display.do?activityId=" + sectionForm.getTutorial().getId());

			} catch (final Throwable oops) {
				result = this.createCreateModelAndView(sectionForm, "activity.commit.error");
			}
		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createCreateModelAndView(final SectionForm sectionForm) {
		return this.createCreateModelAndView(sectionForm, null);
	}

	protected ModelAndView createCreateModelAndView(final SectionForm sectionForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("section/create");
		result.addObject("sectionForm", sectionForm);
		result.addObject("message", message);
		return result;
	}

}
