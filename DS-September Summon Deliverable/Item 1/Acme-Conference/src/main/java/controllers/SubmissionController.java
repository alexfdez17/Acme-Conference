
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SubmissionService;
import domain.Submission;

@Controller
@RequestMapping("/submission")
public class SubmissionController extends AbstractController {

	// Managed repository
	@Autowired
	private SubmissionService	submissionService;


	// ------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int submissionId) {
		ModelAndView result;
		Submission submission;

		try {
			submission = this.submissionService.findOne(submissionId);

			Assert.notNull(submission);

			result = this.createDisplayModelAndView(submission);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	protected ModelAndView createDisplayModelAndView(final Submission submission) {
		final ModelAndView result = new ModelAndView("submission/display");
		boolean cameraReady = false;

		if (submission.getCameraReadyPaper() != null)
			cameraReady = true;

		result.addObject("cameraReady", cameraReady);
		result.addObject("submission", submission);
		result.addObject("requestURI", "submission/administrator/display.do");

		return result;
	}

}
