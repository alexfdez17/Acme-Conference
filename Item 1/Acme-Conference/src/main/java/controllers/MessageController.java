
package controllers;

import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.MessageService;
import services.TopicService;
import domain.Actor;
import domain.Message;
import domain.Topic;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

	// Mangaged service
	@Autowired
	private MessageService	messageService;

	// ----------------
	@Autowired
	private ActorService	actorService;

	@Autowired
	private TopicService	topicService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final Integer recipientId, @RequestParam(required = false) final Integer senderId, @RequestParam(required = false) final String topic) {
		ModelAndView result;

		try {
			Collection<Message> messages;

			if (recipientId != null)
				messages = this.messageService.findAllByPrincipalAndRecipientId(recipientId);
			else if (senderId != null)
				messages = this.messageService.findAllByPrincipalAndSenderId(senderId);
			else if (topic != null)
				messages = this.messageService.findAllByPrincipalAndTopic(topic);
			else
				messages = this.messageService.findAllByPrincipal();

			final String lang = LocaleContextHolder.getLocale().getLanguage();
			final String dateFormatter = lang.equals("en") ? "MM/dd/yyyy HH:mm" : "dd/MM/yyyy HH:mm";

			result = new ModelAndView("message/list");

			result.addObject("dateFormatter", dateFormatter);
			result.addObject("lang", lang);
			result.addObject("requestURI", "message/list.do");
			result.addObject("mezzages", messages);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int messageId) {
		ModelAndView result;

		try {
			final Message message = this.messageService.findOne(messageId);

			Assert.notNull(message);

			result = this.createDisplayModelAndView(message);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

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

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "send")
	public ModelAndView send(@ModelAttribute("mezzage") Message message, final BindingResult binding) {
		ModelAndView result;

		try {
			message = this.messageService.reconstruct(message, binding);

			this.messageService.save(message);

			result = new ModelAndView("redirect:list.do");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(message, "message.save.error");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(message, "message.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute("mezzage") Message message, final BindingResult binding) {
		ModelAndView result;

		try {
			message = this.messageService.findOne(message.getId());

			this.messageService.delete(message);

			result = new ModelAndView("redirect:list.do");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final Throwable oops) {
			result = this.createDisplayModelAndView(message, "message.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Message message) {
		return this.createEditModelAndView(message, null);
	}

	protected ModelAndView createEditModelAndView(final Message message, final String messageCode) {
		final ModelAndView result = new ModelAndView("message/create");

		final Collection<Actor> recipients = this.actorService.findAll();

		final String lang = LocaleContextHolder.getLocale().getLanguage();
		final String topicName = lang.equals("en") ? "name" : "nameES";
		final Collection<Topic> topics = this.topicService.findAll();

		result.addObject("mezzage", message);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "message/create.do");
		result.addObject("recipients", recipients);
		result.addObject("topics", topics);
		result.addObject("topicName", topicName);

		return result;
	}

	protected ModelAndView createDisplayModelAndView(final Message message) {
		return this.createDisplayModelAndView(message, null);
	}

	protected ModelAndView createDisplayModelAndView(final Message message, final String messageCode) {
		final ModelAndView result = new ModelAndView("message/display");

		final String lang = LocaleContextHolder.getLocale().getLanguage();
		final SimpleDateFormat dateFormatter;
		final String topic;

		if (lang.equals("en")) {
			dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			topic = message.getTopic().getName();
		} else {
			dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			topic = message.getTopic().getNameES();
		}

		final String moment = dateFormatter.format(message.getMoment());

		result.addObject("mezzage", message);
		result.addObject("message", messageCode);
		result.addObject("moment", moment);
		result.addObject("requestURI", "message/display.do");
		result.addObject("topic", topic);

		return result;
	}
}
