
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.PresentationService;
import controllers.AbstractController;

@Controller
@RequestMapping("/presentation/administrator")
public class PresentationAdministratorController extends AbstractController {

	@Autowired
	private PresentationService	presentationService;

}
