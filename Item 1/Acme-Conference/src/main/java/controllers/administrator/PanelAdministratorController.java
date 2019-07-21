
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.PanelService;
import controllers.AbstractController;

@Controller
@RequestMapping("/panel/administrator")
public class PanelAdministratorController extends AbstractController {

	@Autowired
	private PanelService	panelService;

}
