
package controllers.administrator;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ReviewerService;
import services.SubmissionService;
import controllers.AbstractController;
import domain.Reviewer;
import domain.Submission;

@Controller
@RequestMapping("/submission/administrator")
public class SubmissiontAdministratorController extends AbstractController {

	// Managed service
	@Autowired
	private SubmissionService	submissionService;

	// ---------------

	// Supporting services
	@Autowired
	private ReviewerService		reviewerService;


	// -------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String status) {
		ModelAndView result;

		try {
			final Collection<Submission> submissions;

			if (status == null)
				submissions = this.submissionService.findAll();
			else
				submissions = this.submissionService.findAllByStatus(status);

			result = new ModelAndView("submission/list");

			result.addObject("requestURI", "submission/administrator/list.do");
			result.addObject("submissions", submissions);

		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/assignment", method = RequestMethod.GET)
	public ModelAndView createAssignment(@RequestParam final int submissionId) {
		ModelAndView result;

		try {
			final Submission submission = this.submissionService.findOne(submissionId);

			Assert.notNull(submission);

			result = this.createEditModelAndView(submission);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/assign", method = RequestMethod.POST, params = "assign")
	public ModelAndView saveAssignment(Submission submission, final BindingResult binding) {
		ModelAndView result;

		try {
			submission = this.submissionService.reconstruct(submission, binding);

			final Collection<Reviewer> reviewers = submission.getReviewers();

			this.submissionService.assignToReviewers(submission, reviewers);

			result = new ModelAndView("redirect:list.do");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(submission, "submission.save.error");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(submission, "submission.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Submission submission) {
		return this.createEditModelAndView(submission, null);
	}

	protected ModelAndView createEditModelAndView(final Submission submission, final String messageCode) {
		final ModelAndView result = new ModelAndView("submission/assign");
		final Collection<Reviewer> reviewers = this.reviewerService.findAllNotAssginedToSubmission(submission);
		final String lang = LocaleContextHolder.getLocale().getLanguage();

		result.addObject("submission", submission);

		result.addObject("lang", lang);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "submission/administrator/assign.do");
		result.addObject("reviewers", reviewers);

		return result;
	}

}
