/*
 * WelcomeController.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.SystemConfigurationService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	@Autowired
	private SystemConfigurationService	systemConfigurationService;


	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		final ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		String welcomeMessage;

		final String lan = LocaleContextHolder.getLocale().getLanguage();

		if (lan == "en") {
			formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			welcomeMessage = this.systemConfigurationService.findWelcomeMessage();
		} else {
			formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			welcomeMessage = this.systemConfigurationService.findWelcomeMessageES();
		}

		moment = formatter.format(new Date());

		result = new ModelAndView("welcome/index");
		result.addObject("welcomeMessage", welcomeMessage);
		result.addObject("moment", moment);

		return result;
	}
}
