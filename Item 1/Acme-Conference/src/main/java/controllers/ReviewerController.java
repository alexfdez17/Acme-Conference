
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
import forms.RegisterReviewerForm;

@Controller
@RequestMapping("/reviewer")
public class ReviewerController extends AbstractController {

	@Autowired
	private ReviewerService	reviewerService;


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
		//SystemConfig systemConfig;

		if (binding.hasErrors())
			result = this.createEditModelAndView(registerForm);
		else
			try {
				if (registerForm.getPhone().matches("\\d{4,99}")) {
					/*
					 * systemConfig = this.systemConfigService.findSystemConfiguration();
					 * String newPhone = systemConfig.getPhonePrefix();
					 * newPhone += " " + registerReviewerForm.getPhone();
					 * registerReviewerForm.setPhone(newPhone);
					 */
				}
				this.reviewerService.registerReviewer(registerForm);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(registerForm, "actor.commit.error");
			}
		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final RegisterReviewerForm registerReviewerForm) {
		return this.createEditModelAndView(registerReviewerForm, null);
	}

	protected ModelAndView createEditModelAndView(final RegisterReviewerForm registerReviewerForm, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/registerReviewer");
		result.addObject("registerForm", registerReviewerForm);
		result.addObject("message", messageCode);
		result.addObject("role", "reviewer");

		return result;
	}

}
