
package controllers;

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

import services.ActivityService;
import services.CommentService;
import services.CommentableService;
import services.ConferenceService;
import domain.Activity;
import domain.Comment;
import domain.Commentable;
import domain.Conference;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	@Autowired
	private CommentService		commentService;

	@Autowired
	private ConferenceService	conferenceService;

	@Autowired
	private ActivityService		activityService;

	@Autowired
	private CommentableService	commentableService;


	// Listing (Conference) --------------------------------------------------------

	@RequestMapping(value = "/listFromConference", method = RequestMethod.GET)
	public ModelAndView listFromConference(@RequestParam final int conferenceId) {
		final ModelAndView result;
		final Collection<Comment> comments;
		final Conference conference;

		conference = this.conferenceService.findOne(conferenceId);

		comments = this.commentService.findByCommentable(conference);

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

		comments = this.commentService.findByCommentable(activity);

		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		result.addObject("requestURI", "comment/listFromActivity.do?activityId=" + activityId);

		return result;
	}

	// Creating --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final int commentableId) {
		ModelAndView result;
		final Comment comment;
		Commentable commentable;

		if (this.commentableService.findOne(commentableId) == null) {

			final Activity activity = this.activityService.findOne(commentableId);
			final int newid = activity.getId();
			commentable = this.commentableService.findOne(newid);

		} else
			commentable = this.commentableService.findOne(commentableId);

		comment = this.commentService.create();
		comment.setCommentable(commentable);

		result = new ModelAndView("comment/create");
		result.addObject("comment", comment);
		return result;
	}

	// Save --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("comment") @Valid final Comment comment, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(comment);
		else
			try {
				final Comment saved = this.commentService.save(comment);
				final int commentableId = saved.getCommentable().getId();

				if (this.conferenceService.findOne(commentableId) == null)
					result = new ModelAndView("redirect:listFromActivity.do?activityId=" + commentableId);
				else
					result = new ModelAndView("redirect:listFromConference.do?conferenceId=" + commentableId);

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(comment, "comment.commit.error");
			}
		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Comment comment) {
		return this.createEditModelAndView(comment, null);
	}

	protected ModelAndView createEditModelAndView(final Comment comment, final String message) {
		final ModelAndView result;

		result = new ModelAndView("comment/create");
		result.addObject("comment", comment);
		result.addObject("message", message);
		return result;
	}

}
