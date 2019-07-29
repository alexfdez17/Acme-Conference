
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.ConferenceService;
import domain.Activity;
import domain.Comment;
import domain.Conference;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	@Autowired
	private ConferenceService	conferenceService;

	@Autowired
	private ActivityService		activityService;


	// Listing (Conference) --------------------------------------------------------

	@RequestMapping(value = "/listFromConference", method = RequestMethod.GET)
	public ModelAndView listFromConference(@RequestParam final int conferenceId) {
		final ModelAndView result;
		final Collection<Comment> comments;
		final Conference conference;

		conference = this.conferenceService.findOne(conferenceId);

		comments = conference.getComments();

		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		result.addObject("requestURI", "comment/listFromConference.do?conferenceId=" + conferenceId);

		return result;
	}

	// Listing (Conference) --------------------------------------------------------

	@RequestMapping(value = "/listFromActivity", method = RequestMethod.GET)
	public ModelAndView listFromActivity(@RequestParam final int activityId) {
		final ModelAndView result;
		final Collection<Comment> comments;
		final Activity activity;

		activity = this.activityService.findOne(activityId);

		comments = activity.getComments();

		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		result.addObject("requestURI", "comment/listFromActivity.do?activityId=" + activityId);

		return result;
	}

}
