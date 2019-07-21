
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.SectionService;
import controllers.AbstractController;

@Controller
@RequestMapping("/section/administrator")
public class SectionAdministratorController extends AbstractController {

	@Autowired
	private SectionService	sectionService;

}
