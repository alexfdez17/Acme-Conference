
package controllers.reviewer;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ReportService;
import controllers.AbstractController;
import domain.Report;

@Controller
@RequestMapping("/report/reviewer")
public class ReportReviewerController extends AbstractController {

	// Managed service
	@Autowired
	private ReportService	reportService;


	// ---------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		try {
			final Collection<Report> reports = this.reportService.findAllByPrincipal();

			result = new ModelAndView("report/list");

			result.addObject("reports", reports);
			result.addObject("requestURI", "report/reviewer/list.do");

		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int submissionId) {
		ModelAndView result;

		try {
			final Report report = this.reportService.create(submissionId);

			result = this.createEditModelAndView(report);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Report report, final BindingResult binding) {
		ModelAndView result;

		try {
			report = this.reportService.reconstruct(report, binding);

			this.reportService.save(report);

			result = new ModelAndView("redirect:list.do");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(report, "report.save.error");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(report, "report.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Report report) {
		return this.createEditModelAndView(report, null);
	}

	protected ModelAndView createEditModelAndView(final Report report, final String messageCode) {
		final ModelAndView result = new ModelAndView("report/create");
		final Collection<String> decisions = new ArrayList<>();

		decisions.add("ACCEPT");
		decisions.add("REJECT");
		decisions.add("BORDER-LINE");

		result.addObject("report", report);
		result.addObject("decisions", decisions);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "report/reviewer/edit.do");

		return result;
	}

}
