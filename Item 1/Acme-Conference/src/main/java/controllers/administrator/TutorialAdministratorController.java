
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.TutorialService;
import controllers.AbstractController;

@Controller
@RequestMapping("/tutorial/administrator")
public class TutorialAdministratorController extends AbstractController {

	@Autowired
	private TutorialService	tutorialService;

}
