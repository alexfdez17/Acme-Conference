
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import controllers.AbstractController;
import domain.Author;

@Controller
@RequestMapping("/author/administrator")
public class AuthorAdministratorController extends AbstractController {

	@Autowired
	private AuthorService	authorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		try {
			final Collection<Author> authors = this.authorService.findAll();

			result = new ModelAndView("author/list");

			result.addObject("requestURI", "author/administrator/list.do");
			result.addObject("authors", authors);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/scores", method = RequestMethod.GET)
	public ModelAndView computeScores() {
		ModelAndView result;

		try {
			this.authorService.computeScores();

			result = new ModelAndView("redirect:list.do");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:list.do");

			result.addObject("message", "message.commit.error");
		}

		return result;
	}

}
