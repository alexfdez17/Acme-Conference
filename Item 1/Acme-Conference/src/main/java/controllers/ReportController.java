
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ReportService;
import domain.Report;

@Controller
@RequestMapping("/report")
public class ReportController extends AbstractController {

	// Managed service
	@Autowired
	private ReportService	reportService;


	// ---------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int reportId) {
		ModelAndView result;

		try {
			final Report report = this.reportService.findOne(reportId);

			Assert.notNull(report);

			result = this.createDisplayModelAndView(report);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	protected ModelAndView createDisplayModelAndView(final Report report) {
		final ModelAndView result = new ModelAndView("report/display");

		result.addObject("report", report);
		result.addObject("requestURI", "report/display.do");

		return result;
	}
}
