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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.CommentService;
import services.ConferenceService;
import services.RegistrationService;
import services.SubmissionService;
import domain.Administrator;
import domain.Conference;
import domain.Submission;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private SubmissionService		submissionService;

	@Autowired
	private RegistrationService		registrationService;

	@Autowired
	private ConferenceService		conferenceService;

	@Autowired
	private CommentService			commentService;


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

	// Make decision on submissions --------------------------------------------------------

	@RequestMapping(value = "/decide", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int conferenceId) {
		final ModelAndView result;
		final Conference conference;
		final Collection<Submission> submissions;

		conference = this.conferenceService.findOne(conferenceId);
		submissions = this.submissionService.makeDecision(conference);

		result = new ModelAndView("submission/list");
		result.addObject("submissions", submissions);

		return result;
	}

	// Dashboard --------------------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		final ModelAndView result;
		result = new ModelAndView("administrator/dashboard");

		// Submissions per conference
		Double[] spc = new Double[4];
		spc = this.submissionService.minMaxAvgStddevPerConference();

		if (spc == null) {
			spc = new Double[4];
			spc[0] = 0.;
			spc[1] = 0.;
			spc[2] = 0.;
			spc[3] = 0.;
		}

		result.addObject("minimumspc", spc[0]);
		result.addObject("maximumspc", spc[1]);
		result.addObject("averagespc", spc[2]);
		result.addObject("stdevspc", spc[3]);

		// Registrations per conference
		Double[] rpc = new Double[4];
		rpc = this.registrationService.minMaxAvgStddevPerConference();

		if (rpc == null) {
			rpc = new Double[4];
			rpc[0] = 0.;
			rpc[1] = 0.;
			rpc[2] = 0.;
			rpc[3] = 0.;
		}

		result.addObject("minimumrpc", rpc[0]);
		result.addObject("maximumrpc", rpc[1]);
		result.addObject("averagerpc", rpc[2]);
		result.addObject("stdevrpc", rpc[3]);

		// Conference fees
		Double[] cf = new Double[4];
		cf = this.conferenceService.minMaxAvgStddevFee();

		if (cf == null) {
			cf = new Double[4];
			cf[0] = 0.;
			cf[1] = 0.;
			cf[2] = 0.;
			cf[3] = 0.;
		}

		result.addObject("minimumcf", cf[0]);
		result.addObject("maximumcf", cf[1]);
		result.addObject("averagecf", cf[2]);
		result.addObject("stdevcf", cf[3]);

		// Days per conference
		Double[] dpc = new Double[4];
		dpc = this.conferenceService.minMaxAvgStddevDays();

		if (dpc == null) {
			dpc = new Double[4];
			dpc[0] = 0.;
			dpc[1] = 0.;
			dpc[2] = 0.;
			dpc[3] = 0.;
		}

		result.addObject("minimumdpc", dpc[0]);
		result.addObject("maximumdpc", dpc[1]);
		result.addObject("averagedpc", dpc[2]);
		result.addObject("stdevdpc", dpc[3]);

		// Conferences per category
		Double[] cpc = new Double[4];
		cpc = this.conferenceService.conferencesPerCategoryStats();

		if (cpc == null) {
			cpc = new Double[4];
			cpc[0] = 0.;
			cpc[1] = 0.;
			cpc[2] = 0.;
			cpc[3] = 0.;
		}

		result.addObject("minimumcpc", cpc[1]);
		result.addObject("maximumcpc", cpc[2]);
		result.addObject("averagecpc", cpc[0]);
		result.addObject("stdevcpc", cpc[3]);

		// Comments per conference
		Double[] cmpc = new Double[4];
		cmpc = this.commentService.minMaxAvgStddevPerConference();

		if (cmpc == null) {
			cmpc = new Double[4];
			cmpc[0] = 0.;
			cmpc[1] = 0.;
			cmpc[2] = 0.;
			cmpc[3] = 0.;
		}

		result.addObject("minimumcmpc", cmpc[0]);
		result.addObject("maximumcmpc", cmpc[1]);
		result.addObject("averagecmpc", cmpc[2]);
		result.addObject("stdevcmpc", cmpc[3]);

		// Comments per activity
		Double[] cmpa = new Double[4];
		cmpa = this.commentService.minMaxAvgStddevPerActivity();

		if (cmpa == null) {
			cmpa = new Double[4];
			cmpa[0] = 0.;
			cmpa[1] = 0.;
			cmpa[2] = 0.;
			cmpa[3] = 0.;
		}

		result.addObject("minimumcmpa", cmpa[0]);
		result.addObject("maximumcmpa", cmpa[1]);
		result.addObject("averagecmpa", cmpa[2]);
		result.addObject("stdevcmpa", cmpa[3]);

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
