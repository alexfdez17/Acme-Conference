
package controllers.author;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RegistrationService;
import controllers.AbstractController;
import domain.Registration;

@Controller
@RequestMapping("/registration/author")
public class RegistrationAuthorController extends AbstractController {

	// Managed service
	@Autowired
	private RegistrationService	registrationService;


	// ---------------

	// Supporting services
	//@Autowired
	//private SystemConfigurationService	systemConfigurationService;

	// -------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result = new ModelAndView("registration/list");

		try {
			final Collection<Registration> registrations = this.registrationService.findAllByPrincipal();

			result.addObject("registrations", registrations);
			result.addObject("requestURI", "registration/author/list.do");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int registrationId) {
		ModelAndView result;

		try {
			final Registration registration = this.registrationService.findOne(registrationId);

			Assert.notNull(registration);

			result = this.createDisplayModelAndView(registration);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int conferenceId) {
		ModelAndView result;

		try {
			final Registration registration = this.registrationService.create(conferenceId);

			result = this.createEditModelAndView(registration, "create");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	protected ModelAndView createDisplayModelAndView(final Registration registration) {
		final ModelAndView result = new ModelAndView("registration/display");

		result.addObject("registration", registration);
		result.addObject("requestURI", "registration/display.do");

		return result;
	}

	protected ModelAndView createEditModelAndView(final Registration registration, final String action) {
		return this.createEditModelAndView(registration, action, null);
	}

	protected ModelAndView createEditModelAndView(final Registration registration, final String action, final String messageCode) {
		final ModelAndView result = new ModelAndView("registration/" + action);

		result.addObject("registration", registration);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "registration/author/edit.do");

		return result;
	}
}
