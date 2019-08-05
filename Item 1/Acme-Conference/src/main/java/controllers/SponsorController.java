
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
import domain.Sponsor;
import forms.RegisterSponsorForm;

@Controller
@RequestMapping("/sponsor")
public class SponsorController extends AbstractController {

	@Autowired
	private SponsorService	sponsorService;


	// Display --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		final ModelAndView result;
		Sponsor actor;

		actor = this.sponsorService.findByPrincipal();

		result = new ModelAndView("actor/display");
		result.addObject("actor", actor);

		return result;
	}

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
			result = this.createRegisterModelAndView(registerForm);
		else
			try {
				this.sponsorService.registerSponsor(registerForm);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createRegisterModelAndView(registerForm, "actor.commit.error");
			}
		return result;
	}

	// Edit profile --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		Sponsor sponsor;
		final ModelAndView result;

		sponsor = this.sponsorService.findByPrincipal();
		result = new ModelAndView("actor/edit");

		result.addObject("actor", sponsor);
		result.addObject("role", "sponsor");

		return result;
	}

	// Save edit profile --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@ModelAttribute("actor") Sponsor actor, final BindingResult binding) {
		ModelAndView result;
		//SystemConfig systemConfig;

		actor = this.sponsorService.reconstruct(actor, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(actor);
		else
			try {
				this.sponsorService.save(actor);
				result = new ModelAndView("redirect:display.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(actor, "actor.commit.error");
			}
		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createRegisterModelAndView(final RegisterSponsorForm registerSponsorForm) {
		return this.createRegisterModelAndView(registerSponsorForm, null);
	}

	protected ModelAndView createRegisterModelAndView(final RegisterSponsorForm registerSponsorForm, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/registerSponsor");
		result.addObject("registerForm", registerSponsorForm);
		result.addObject("message", messageCode);
		result.addObject("role", "sponsor");

		return result;
	}

	protected ModelAndView createEditModelAndView(final Sponsor sponsor) {
		return this.createEditModelAndView(sponsor, null);
	}

	protected ModelAndView createEditModelAndView(final Sponsor sponsor, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("actor", sponsor);
		result.addObject("message", messageCode);
		result.addObject("role", "sponsor");

		return result;
	}

}
