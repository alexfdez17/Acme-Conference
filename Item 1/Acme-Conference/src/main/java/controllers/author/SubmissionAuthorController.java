
package controllers.author;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.ConferenceService;
import services.SubmissionService;
import controllers.AbstractController;
import domain.Author;
import domain.Conference;
import domain.Submission;
import forms.SubmissionPaperForm;

@Controller
@RequestMapping("/submission/author")
public class SubmissionAuthorController extends AbstractController {

	@Autowired
	private SubmissionService	submissionService;

	@Autowired
	private AuthorService		authorService;

	@Autowired
	private ConferenceService	conferenceService;


	// Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Submission> submissions;

		final Author principal = this.authorService.findByPrincipal();

		submissions = this.submissionService.findByAuthor(principal);

		result = new ModelAndView("submission/list");
		result.addObject("submissions", submissions);

		return result;
	}

	// Display --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int submissionId) {
		final ModelAndView result;
		Submission submission;
		boolean cameraReady = false;

		submission = this.submissionService.findOne(submissionId);
		if (submission.getCameraReadyPaper() != null)
			cameraReady = true;

		result = new ModelAndView("submission/display");
		result.addObject("submission", submission);
		result.addObject("cameraReady", cameraReady);

		return result;
	}

	// Creating --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final int conferenceId) {
		ModelAndView result;
		final Submission submission;
		Conference conference;
		final SubmissionPaperForm submissionPaperForm;

		conference = this.conferenceService.findOne(conferenceId);
		submission = this.submissionService.create();
		submission.setConference(conference);

		submissionPaperForm = new SubmissionPaperForm();
		submissionPaperForm.setSubmission(submission);

		result = new ModelAndView("submission/create");
		result.addObject("submissionForm", submissionPaperForm);
		return result;
	}

	// Save --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("submissionForm") @Valid final SubmissionPaperForm submissionForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createMakeSubmissionModelAndView(submissionForm);
		else
			try {
				this.submissionService.makeSubmission(submissionForm);
				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {
				result = this.createMakeSubmissionModelAndView(submissionForm, "submission.commit.error");
			}
		return result;
	}

	// Go to upload --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int submissionId) {
		ModelAndView result;
		Submission submission;
		SubmissionPaperForm submissionPaperForm;

		submission = this.submissionService.findOne(submissionId);

		submissionPaperForm = new SubmissionPaperForm();
		submissionPaperForm.setSubmission(submission);

		result = this.createUploadCameraReadyPaperModelAndView(submissionPaperForm);
		return result;
	}

	// Upload --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView upload(@ModelAttribute("submissionForm") @Valid final SubmissionPaperForm submissionForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createUploadCameraReadyPaperModelAndView(submissionForm);
		else
			try {
				this.submissionService.makeSubmission(submissionForm);
				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {
				result = this.createUploadCameraReadyPaperModelAndView(submissionForm, "submission.commit.error");
			}
		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createMakeSubmissionModelAndView(final SubmissionPaperForm submissionPaperForm) {
		return this.createMakeSubmissionModelAndView(submissionPaperForm, null);
	}

	protected ModelAndView createMakeSubmissionModelAndView(final SubmissionPaperForm submissionPaperForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("submission/create");
		result.addObject("submissionForm", submissionPaperForm);
		result.addObject("message", message);
		return result;
	}

	protected ModelAndView createUploadCameraReadyPaperModelAndView(final SubmissionPaperForm submissionPaperForm) {
		return this.createUploadCameraReadyPaperModelAndView(submissionPaperForm, null);
	}

	protected ModelAndView createUploadCameraReadyPaperModelAndView(final SubmissionPaperForm submissionPaperForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("submission/upload");
		result.addObject("submissionForm", submissionPaperForm);
		result.addObject("message", message);
		return result;
	}

}
