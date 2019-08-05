
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ReviewerService;
import domain.Reviewer;
import forms.RegisterReviewerForm;

@Controller
@RequestMapping("/reviewer")
public class ReviewerController extends AbstractController {

	@Autowired
	private ReviewerService	reviewerService;


	// Display --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		final ModelAndView result;
		Reviewer actor;

		actor = this.reviewerService.findByPrincipal();

		result = new ModelAndView("actor/display");
		result.addObject("actor", actor);

		return result;
	}

	// Go to registration --------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView createReviewer() {
		RegisterReviewerForm registerReviewerForm;
		final ModelAndView result;

		registerReviewerForm = new RegisterReviewerForm();
		result = new ModelAndView("actor/registerReviewer");

		result.addObject("registerForm", registerReviewerForm);
		result.addObject("role", "reviewer");

		return result;
	}

	// Save --------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("registerForm") @Valid final RegisterReviewerForm registerForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createRegisterModelAndView(registerForm);
		else
			try {
				this.reviewerService.registerReviewer(registerForm);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createRegisterModelAndView(registerForm, "actor.commit.error");
			}
		return result;
	}

	// Edit profile --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		Reviewer reviewer;
		final ModelAndView result;

		reviewer = this.reviewerService.findByPrincipal();
		result = new ModelAndView("actor/edit");

		result.addObject("actor", reviewer);
		result.addObject("role", "reviewer");

		return result;
	}

	// Save edit profile --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@ModelAttribute("actor") Reviewer actor, final BindingResult binding) {
		ModelAndView result;

		actor = this.reviewerService.reconstruct(actor, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(actor);
		else
			try {
				this.reviewerService.save(actor);
				result = new ModelAndView("redirect:display.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(actor, "actor.commit.error");
			}
		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createRegisterModelAndView(final RegisterReviewerForm registerReviewerForm) {
		return this.createRegisterModelAndView(registerReviewerForm, null);
	}

	protected ModelAndView createRegisterModelAndView(final RegisterReviewerForm registerReviewerForm, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/registerReviewer");
		result.addObject("registerForm", registerReviewerForm);
		result.addObject("message", messageCode);
		result.addObject("role", "reviewer");

		return result;
	}

	protected ModelAndView createEditModelAndView(final Reviewer reviewer) {
		return this.createEditModelAndView(reviewer, null);
	}

	protected ModelAndView createEditModelAndView(final Reviewer reviewer, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("actor", reviewer);
		result.addObject("message", messageCode);
		result.addObject("role", "reviewer");

		return result;
	}

}
