
package controllers.administrator;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SystemConfigurationService;
import controllers.AbstractController;
import domain.SystemConfiguration;

@Controller
@RequestMapping("/sys-config/administrator")
public class SystemConfigurationAdministratorController extends AbstractController {

	// Managed service
	@Autowired
	private SystemConfigurationService	systemConfigurationService;


	// ---------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;

		try {
			final SystemConfiguration systemConfiguration = this.systemConfigurationService.find();

			Assert.notNull(systemConfiguration);

			result = this.createEditModelAndView(systemConfiguration);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(SystemConfiguration systemConfiguration, final BindingResult binding) {
		ModelAndView result;

		try {
			systemConfiguration = this.systemConfigurationService.reconstruct(systemConfiguration, binding);

			this.systemConfigurationService.save(systemConfiguration);

			result = new ModelAndView("redirect:edit.do");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(systemConfiguration, "systemConfiguration.save.error");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(systemConfiguration, "systemConfiguration.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final SystemConfiguration systemConfiguration) {
		return this.createEditModelAndView(systemConfiguration, null);
	}

	protected ModelAndView createEditModelAndView(final SystemConfiguration systemConfiguration, final String messageCode) {
		final ModelAndView result = new ModelAndView("systemConfiguration/edit");

		result.addObject("systemConfiguration", systemConfiguration);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "sys-config/administrator/edit.do");

		return result;
	}

}
