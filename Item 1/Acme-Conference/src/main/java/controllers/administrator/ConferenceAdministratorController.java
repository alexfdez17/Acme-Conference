
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ConferenceService;
import controllers.AbstractController;

@Controller
@RequestMapping("/conference/administrator")
public class ConferenceAdministratorController extends AbstractController {

	@Autowired
	private ConferenceService	conferenceService;

	// Listing --------------------------------------------------------

	//	@RequestMapping(value = "/list", method = RequestMethod.GET)
	//	public ModelAndView list() {
	//		final ModelAndView result;
	//		final Collection<Conference> conferences;
	//
	//		conferences = this.conferenceService.findAll();
	//		final Date today = new Date();
	//
	//		result = new ModelAndView("conference/list");
	//		result.addObject("conferences", conferences);
	//		result.addObject("requestURI", "conference/administrator/list.do");
	//		result.addObject("today", today);
	//
	//		return result;
	//	}

}
