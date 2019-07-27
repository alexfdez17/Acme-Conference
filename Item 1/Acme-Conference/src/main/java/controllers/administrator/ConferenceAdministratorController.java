
package controllers.administrator;

import java.util.Collection;
import java.util.Date;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.ConferenceService;
import controllers.AbstractController;
import domain.Category;
import domain.Conference;

@Controller
@RequestMapping("/conference/administrator")
public class ConferenceAdministratorController extends AbstractController {

	@Autowired
	private ConferenceService	conferenceService;

	@Autowired
	private CategoryService		categoryService;


	// Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final String keyword) {
		ModelAndView result;

		try {
			if (keyword.equals("all"))
				result = this.listAll();
			else if (keyword.equals("cameraReadyElapses"))
				result = this.listCameradReadyElapasesLess5Days();
			else if (keyword.equals("notificationDeadlineElapses"))
				result = this.listNotificationDeadlineElapsesLess5Days();
			else if (keyword.equals("organised"))
				result = this.listOrganisedLess5Days();
			else if (keyword.equals("submissionDeadlineElapsed"))
				result = this.listSubmissionDeadlineElapsedLast5Days();
			else
				result = this.listByKeyword(keyword);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		try {
			final Conference conference = this.conferenceService.create();

			result = this.createEditModelAndView(conference, "create");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int conferenceId) {
		ModelAndView result;

		try {
			final Conference conference = this.conferenceService.findOne(conferenceId);

			Assert.notNull(conference);

			result = this.createEditModelAndView(conference, "edit");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Conference conference, final BindingResult binding) {
		final int conferenceId = conference.getId();
		final String action = this.conferenceService.exists(conferenceId) ? "edit" : "create";

		ModelAndView result;

		try {
			conference = this.conferenceService.reconstruct(conference, binding);

			this.conferenceService.save(conference);

			result = new ModelAndView("redirect:list.do?keyword=all");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(conference, action, "conference.save.error");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(conference, action, "conference.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Conference conference, final String action) {
		return this.createEditModelAndView(conference, action, null);
	}

	protected ModelAndView createEditModelAndView(final Conference conference, final String action, final String messageCode) {
		final ModelAndView result = new ModelAndView("conference/" + action);
		final Collection<Category> categories = this.categoryService.findAll();

		result.addObject("conference", conference);
		result.addObject("categories", categories);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "conference/administrator/edit.do");

		return result;
	}

	// Auxiliary methods

	private ModelAndView listAll() {
		final ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.findAll();

		result = this.setModelAndView(conferences);

		return result;
	}

	private ModelAndView listCameradReadyElapasesLess5Days() {
		final ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.findAllCameraReadyDeadlineElapsesLess5Days();

		result = this.setModelAndView(conferences);

		return result;
	}

	private ModelAndView listNotificationDeadlineElapsesLess5Days() {
		final ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.findAllNotificationDeadlineElapsesLess5Days();

		result = this.setModelAndView(conferences);

		return result;
	}

	private ModelAndView listOrganisedLess5Days() {
		final ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.findAllOrganisedLess5Days();

		result = this.setModelAndView(conferences);

		return result;
	}

	private ModelAndView listSubmissionDeadlineElapsedLast5Days() {
		final ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.findAllSubmissionDeadlineElapsedLast5Days();

		result = this.setModelAndView(conferences);

		return result;
	}

	private ModelAndView listByKeyword(final String keyword) {
		final ModelAndView result;
		final Collection<Conference> conferences = this.conferenceService.findAllByKeyword(keyword);

		result = this.setModelAndView(conferences);

		return result;
	}

	private ModelAndView setModelAndView(final Collection<Conference> conferences) {
		final ModelAndView result = new ModelAndView("conference/list");
		final Date today = new Date();

		result.addObject("conferences", conferences);
		result.addObject("requestURI", "conference/administrator/list.do");
		result.addObject("today", today);

		return result;
	}

}
