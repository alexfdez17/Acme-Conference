
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SubmissionService;
import controllers.AbstractController;
import domain.Submission;

@Controller
@RequestMapping("/submission/administrator")
public class SubmissiontAdministratorController extends AbstractController {

	// Managed service
	@Autowired
	private SubmissionService	submissionService;


	// ---------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final String status) {
		ModelAndView result;

		try {
			final Collection<Submission> submissions = this.submissionService.findAllByStatus(status);

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
		return new ModelAndView();
	}

	@RequestMapping(value = "/assign", method = RequestMethod.GET)
	public ModelAndView saveAssignment(final Submission submission, final BindingResult binding) {
		return new ModelAndView();
	}
}
