
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import controllers.AbstractController;
import forms.RegisterAdministratorForm;

@Controller
@RequestMapping("/administrator/administrator")
public class AdministratorAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;


	// Go to registration --------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView createAdministrator() {
		RegisterAdministratorForm registerAdministratorForm;
		final ModelAndView result;

		registerAdministratorForm = new RegisterAdministratorForm();
		result = new ModelAndView("actor/registerAdministrator");

		result.addObject("registerForm", registerAdministratorForm);
		result.addObject("role", "administrator/administrator");

		return result;
	}

	// Save register --------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("registerForm") @Valid final RegisterAdministratorForm registerForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createRegisterModelAndView(registerForm);
		else
			try {
				this.administratorService.registerAdministrator(registerForm);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createRegisterModelAndView(registerForm, "actor.commit.error");
			}
		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createRegisterModelAndView(final RegisterAdministratorForm registerAdministratorForm) {
		return this.createRegisterModelAndView(registerAdministratorForm, null);
	}

	protected ModelAndView createRegisterModelAndView(final RegisterAdministratorForm registerAdministratorForm, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/registerAdministrator");
		result.addObject("registerForm", registerAdministratorForm);
		result.addObject("message", messageCode);
		result.addObject("role", "administrator/administrator");

		return result;
	}

}
