
package controllers.author;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.FinderService;
import controllers.AbstractController;
import domain.Author;
import domain.Conference;
import domain.Finder;

@Controller
@RequestMapping("/finder/author")
public class FinderAuthorController extends AbstractController {

	@Autowired
	private AuthorService	authorService;

	@Autowired
	private FinderService	finderService;


	// Creating --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		final Author author = this.authorService.findByPrincipal();

		Finder finder = this.finderService.findByAuthor(author);
		if (finder == null) {
			finder = this.finderService.create();
			finder.setAuthor(author);
		}

		result = new ModelAndView("finder/edit");
		result.addObject("finder", finder);
		result.addObject("requestURI", "finder/author/edit.do");
		result.addObject("conferences", finder.getConferences());
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("finder") @Valid final Finder finder, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(finder);
		else
			try {
				this.finderService.save(finder);
				this.finderService.flush();
				result = new ModelAndView("redirect:edit.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(finder, "finder.commit.error");
				oops.printStackTrace();
			}
		return result;
	}

	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public ModelAndView clear() {
		final Author author = this.authorService.findByPrincipal();

		final Finder finder = this.finderService.findByAuthor(author);
		if (finder != null)
			this.finderService.delete(finder);
		return new ModelAndView("redirect:edit.do");

	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Finder finder) {
		ModelAndView result;

		result = this.createEditModelAndView(finder, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(final Finder finder, final String messageCode) {
		ModelAndView result;
		Collection<Conference> conferences;
		conferences = finder.getConferences();
		result = new ModelAndView("finder/edit");
		result.addObject("finder", finder);
		result.addObject("message", messageCode);
		result.addObject("conferences", conferences);

		return result;
	}

}
