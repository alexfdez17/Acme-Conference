
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SponsorService;
import forms.RegisterSponsorForm;

@Controller
@RequestMapping("/sponsor")
public class SponsorController extends AbstractController {

	@Autowired
	private SponsorService	sponsorService;


	// Go to registration --------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView createSponsor() {
		RegisterSponsorForm registerSponsorForm;
		final ModelAndView result;

		registerSponsorForm = new RegisterSponsorForm();
		result = new ModelAndView("actor/registerSponsor");

		result.addObject("registerForm", registerSponsorForm);
		result.addObject("role", "sponsor");

		return result;
	}

	// Save --------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("registerForm") @Valid final RegisterSponsorForm registerForm, final BindingResult binding) {
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
					 * newPhone += " " + registerSponsorForm.getPhone();
					 * registerSponsorForm.setPhone(newPhone);
					 */
				}
				this.sponsorService.registerSponsor(registerForm);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(registerForm, "actor.commit.error");
			}
		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final RegisterSponsorForm registerSponsorForm) {
		return this.createEditModelAndView(registerSponsorForm, null);
	}

	protected ModelAndView createEditModelAndView(final RegisterSponsorForm registerSponsorForm, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/registerSponsor");
		result.addObject("registerForm", registerSponsorForm);
		result.addObject("message", messageCode);
		result.addObject("role", "sponsor");

		return result;
	}

}
