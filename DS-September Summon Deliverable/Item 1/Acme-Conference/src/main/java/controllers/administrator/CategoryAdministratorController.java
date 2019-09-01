
package controllers.administrator;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import controllers.AbstractController;
import domain.Category;

@Controller
@RequestMapping("/category/administrator")
public class CategoryAdministratorController extends AbstractController {

	// Managed service
	@Autowired
	private CategoryService	categoryService;


	// ---------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final Integer parentId) {
		ModelAndView result;

		try {
			final Collection<Category> categories;

			if (parentId != null)
				categories = this.categoryService.findAllByParentId(parentId);
			else
				categories = this.categoryService.findAllRootChildrenAndRoot();

			result = new ModelAndView("category/list");

			result.addObject("requestURI", "category/administrator/list.do");
			result.addObject("categories", categories);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(required = false) final Integer parentId) {
		ModelAndView result;

		try {
			final Category category;

			if (parentId != null)
				category = this.categoryService.create(parentId);
			else
				category = this.categoryService.create();

			result = this.createEditModelAndView(category, "create");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int categoryId) {
		ModelAndView result;

		try {
			final Category category = this.categoryService.findOne(categoryId);

			Assert.notNull(category);

			result = this.createEditModelAndView(category, "edit");
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Category category, final BindingResult binding) {
		final int categoryId = category.getId();
		final String action = this.categoryService.exists(categoryId) ? "edit" : "create";

		ModelAndView result;

		try {
			category = this.categoryService.reconstruct(category, binding);

			this.categoryService.save(category);

			final int parentId = category.getParent().getId();

			result = new ModelAndView("redirect:list.do?parentId=" + parentId);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(category, action, "category.save.error");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(category, action, "category.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Category category, final BindingResult binding) {
		ModelAndView result;

		try {
			category = this.categoryService.findOne(category.getId());

			final int parentId = category.getParent().getId();

			this.categoryService.delete(category);

			result = new ModelAndView("redirect:list.do?parentId=" + parentId);
		} catch (final IllegalArgumentException oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(category, "category.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Category category, final String action) {
		return this.createEditModelAndView(category, action, null);
	}

	protected ModelAndView createEditModelAndView(final Category category, final String action, final String messageCode) {
		final ModelAndView result = new ModelAndView("category/" + action);
		final Collection<Category> categories = this.categoryService.findAll();

		result.addObject("category", category);
		result.addObject("categories", categories);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "category/administrator/edit.do");

		return result;
	}
}
