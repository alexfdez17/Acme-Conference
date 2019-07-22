
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

import services.SectionService;
import services.TutorialService;
import controllers.AbstractController;
import domain.Section;
import domain.Tutorial;
import forms.SectionForm;

@Controller
@RequestMapping("/section/administrator")
public class SectionAdministratorController extends AbstractController {

	@Autowired
	private SectionService	sectionService;

	@Autowired
	private TutorialService	tutorialService;


	// Display --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int sectionId) {
		final ModelAndView result;
		Section section;

		section = this.sectionService.findOne(sectionId);

		result = new ModelAndView("section/display");
		result.addObject("section", section);

		return result;
	}

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

	// Edition --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int sectionId) {
		ModelAndView result;
		Section section;

		section = this.sectionService.findOne(sectionId);

		result = this.createEditModelAndView(section);
		return result;
	}

	// Update --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView update(@ModelAttribute("section") @Valid final Section section, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(section);
		else
			try {
				this.sectionService.save(section);
				result = new ModelAndView("redirect:/section/administrator/display.do?sectionId=" + section.getId());

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(section, "section.commit.error");
			}
		return result;
	}

	// Delete --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Section section, final BindingResult bindingResult) {
		ModelAndView result;

		try {
			this.sectionService.delete(section);
			result = new ModelAndView("redirect:/");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(section, "activity.commit.error");
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

	protected ModelAndView createEditModelAndView(final Section section) {
		return this.createEditModelAndView(section, null);
	}

	protected ModelAndView createEditModelAndView(final Section section, final String message) {
		final ModelAndView result;

		result = new ModelAndView("section/edit");
		result.addObject("section", section);
		result.addObject("message", message);
		return result;
	}

}
