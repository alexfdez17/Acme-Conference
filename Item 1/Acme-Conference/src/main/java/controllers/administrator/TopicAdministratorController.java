
package controllers.administrator;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TopicService;
import controllers.AbstractController;
import domain.Topic;

@Controller
@RequestMapping("/topic/administrator")
public class TopicAdministratorController extends AbstractController {

	// Managed service
	@Autowired
	private TopicService	topicService;


	// ---------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		try {
			final Collection<Topic> topics = this.topicService.findAll();
			final String lang = LocaleContextHolder.getLocale().getLanguage();

			result = new ModelAndView("topic/list");

			result.addObject("lang", lang);
			result.addObject("requestURI", "topic/administrator/list.do");
			result.addObject("topics", topics);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		try {
			final Topic topic = this.topicService.create();

			result = this.createEditModelAndView(topic, "create");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int topicId) {
		ModelAndView result;

		try {
			final Topic topic = this.topicService.findOne(topicId);

			Assert.notNull(topic);

			result = this.createEditModelAndView(topic, "edit");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Topic topic, final BindingResult binding) {
		final int topicId = topic.getId();
		final String action = this.topicService.exists(topicId) ? "edit" : "create";

		ModelAndView result;

		try {
			topic = this.topicService.reconstruct(topic, binding);

			this.topicService.save(topic);

			result = new ModelAndView("redirect:list.do");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(topic, action, "topic.save.error");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(topic, action, "topic.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Topic topic, final String action) {
		return this.createEditModelAndView(topic, action, null);
	}

	protected ModelAndView createEditModelAndView(final Topic topic, final String action, final String messageCode) {
		final ModelAndView result = new ModelAndView("topic/" + action);
		final String lang = LocaleContextHolder.getLocale().getLanguage();

		result.addObject("topic", topic);
		result.addObject("lang", lang);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "topic/administrator/edit.do");

		return result;
	}
}
