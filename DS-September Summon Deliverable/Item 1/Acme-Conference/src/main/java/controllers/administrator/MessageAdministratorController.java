
package controllers.administrator;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AuthorService;
import services.MessageService;
import services.TopicService;
import controllers.AbstractController;
import domain.Actor;
import domain.Message;
import domain.Topic;

@Controller
@RequestMapping("/message/administrator")
public class MessageAdministratorController extends AbstractController {

	// Managed service
	@Autowired
	private MessageService	messageService;

	// ---------------

	@Autowired
	private ActorService	actorService;

	@Autowired
	private AuthorService	authorService;

	@Autowired
	private TopicService	topicService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		try {
			final Message message = this.messageService.create();

			result = this.createEditModelAndView(message);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/broadcast", method = RequestMethod.POST, params = "broadcastAll")
	public ModelAndView broadcastAll(@ModelAttribute("mezzage") final Message message, final BindingResult binding) {
		final Actor principal = this.actorService.findPrincipal();
		final Collection<Actor> recipients = this.actorService.findAll();

		recipients.remove(principal);
		message.setRecipients(recipients);

		return this.send(message, binding);
	}

	@RequestMapping(value = "/broadcast", method = RequestMethod.POST, params = "broadcastAllAuthors")
	public ModelAndView broadcastAllAuthors(@ModelAttribute("mezzage") final Message message, final BindingResult binding) {
		final Collection<Actor> recipients = this.authorService.findAllAsActor();

		message.setRecipients(recipients);

		return this.send(message, binding);
	}

	@RequestMapping(value = "/broadcast", method = RequestMethod.POST, params = "broadcastAuthorRegistration")
	public ModelAndView broadcastAuthorRegistration(@ModelAttribute("mezzage") final Message message, final BindingResult binding) {
		final Collection<Actor> recipients = this.authorService.findAllWithRegistration();

		message.setRecipients(recipients);

		return this.send(message, binding);
	}

	@RequestMapping(value = "/broadcast", method = RequestMethod.POST, params = "broadcastAuthorSubmission")
	public ModelAndView broadcastAuthorSubmission(@ModelAttribute("mezzage") final Message message, final BindingResult binding) {
		final Collection<Actor> recipients = this.authorService.findAllWithSubmission();

		message.setRecipients(recipients);

		return this.send(message, binding);
	}

	@RequestMapping(value = "/notify", method = RequestMethod.GET)
	public ModelAndView notifyDecisionOnSubmissions() {
		ModelAndView result;

		try {
			this.messageService.notifyDecisionOnSubmissions();

			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");

			result.addObject("message", "message.commit.error");
		}

		return result;
	}

	protected ModelAndView send(Message message, final BindingResult binding) {
		ModelAndView result;

		try {
			message.setIsBroadcast(true);

			message = this.messageService.reconstruct(message, binding);

			this.messageService.broadcast(message);

			result = new ModelAndView("redirect:/message/list.do");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(message, "message.save.error");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(message, "message.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Message message) {
		return this.createEditModelAndView(message, null);
	}

	protected ModelAndView createEditModelAndView(final Message message, final String messageCode) {
		final ModelAndView result = new ModelAndView("message/broadcast");

		final String lang = LocaleContextHolder.getLocale().getLanguage();
		final String topicName = lang.equals("en") ? "name" : "nameES";
		final Collection<Topic> topics = this.topicService.findAll();

		result.addObject("mezzage", message);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "message/administrator/broadcast.do");
		result.addObject("topics", topics);
		result.addObject("topicName", topicName);

		return result;
	}
}
