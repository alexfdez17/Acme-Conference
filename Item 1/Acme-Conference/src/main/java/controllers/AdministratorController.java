/*
 * AdministratorController.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import domain.Administrator;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;


	// Edit profile --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		Administrator administrator;
		final ModelAndView result;

		administrator = this.administratorService.findByPrincipal();
		result = new ModelAndView("actor/edit");

		result.addObject("actor", administrator);
		result.addObject("role", "administrator");

		return result;
	}

	// Save edit profile --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@ModelAttribute("actor") Administrator actor, final BindingResult binding) {
		ModelAndView result;
		//SystemConfig systemConfig;

		actor = this.administratorService.reconstruct(actor, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(actor);
		else
			try {
				if (actor.getPhone().matches("\\d{4,99}")) {
					/*
					 * systemConfig = this.systemConfigService.findSystemConfiguration();
					 * String newPhone = systemConfig.getPhonePrefix();
					 * newPhone += " " + registerAdministratorForm.getPhone();
					 * registerAdministratorForm.setPhone(newPhone);
					 */
				}
				this.administratorService.save(actor);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(actor, "actor.commit.error");
			}
		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Administrator administrator) {
		return this.createEditModelAndView(administrator, null);
	}

	protected ModelAndView createEditModelAndView(final Administrator administrator, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("actor", administrator);
		result.addObject("message", messageCode);
		result.addObject("role", "administrator");

		return result;
	}

}
