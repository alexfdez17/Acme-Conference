
package controllers.reviewer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SubmissionService;
import controllers.AbstractController;
import domain.Submission;

@Controller
@RequestMapping("/submission/reviewer")
public class SubmissionReviewerController extends AbstractController {

	// Managed repository
	@Autowired
	private SubmissionService	submissionService;


	// ------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		try {
			final Collection<Submission> submissions = this.submissionService.findAllByReviewerAsPrincipal();

			result = new ModelAndView("submission/list");

			final Collection<Submission> submissionsWithReportWritten = this.submissionService.findAllWithReportWrittenByPrincipal();

			result.addObject("requestURI", "submission/reviewer/list.do");
			result.addObject("submissions", submissions);
			result.addObject("submissionsWithReportWritten", submissionsWithReportWritten);

		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}
}
