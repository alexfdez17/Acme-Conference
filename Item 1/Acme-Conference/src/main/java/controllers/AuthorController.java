
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import forms.RegisterAuthorForm;

@Controller
@RequestMapping("/author")
public class AuthorController extends AbstractController {

	@Autowired
	private AuthorService	authorService;


	// Go to registration --------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView createAuthor() {
		RegisterAuthorForm registerAuthorForm;
		final ModelAndView result;

		registerAuthorForm = new RegisterAuthorForm();
		result = new ModelAndView("actor/registerAuthor");

		result.addObject("registerForm", registerAuthorForm);
		result.addObject("role", "author");

		return result;
	}

	// Save --------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final RegisterAuthorForm registerAuthorForm, final BindingResult binding) {
		ModelAndView result;
		//SystemConfig systemConfig;

		if (binding.hasErrors())
			result = this.createEditModelAndView(registerAuthorForm);
		else
			try {
				if (registerAuthorForm.getPhone().matches("\\d{4,99}")) {
					/*
					 * systemConfig = this.systemConfigService.findSystemConfiguration();
					 * String newPhone = systemConfig.getPhonePrefix();
					 * newPhone += " " + registerAuthorForm.getPhone();
					 * registerAuthorForm.setPhone(newPhone);
					 */
				}
				this.authorService.registerAuthor(registerAuthorForm);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(registerAuthorForm, "actor.commit.error");
			}
		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final RegisterAuthorForm registerAuthorForm) {
		return this.createEditModelAndView(registerAuthorForm, null);
	}

	protected ModelAndView createEditModelAndView(final RegisterAuthorForm registerAuthorForm, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/registerAuthor");
		result.addObject("registerAuthorForm", registerAuthorForm);
		result.addObject("message", messageCode);

		return result;
	}

}
