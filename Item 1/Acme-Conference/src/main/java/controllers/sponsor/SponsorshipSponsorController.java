
package controllers.sponsor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import services.SponsorService;
import services.SponsorshipService;
import controllers.AbstractController;
import domain.Conference;
import domain.Sponsor;
import domain.Sponsorship;

@Controller
@RequestMapping("/sponsorship/sponsor")
public class SponsorshipSponsorController extends AbstractController {

	@Autowired
	private SponsorshipService	sponsorshipService;

	@Autowired
	private SponsorService		sponsorService;

	@Autowired
	private ConferenceService	conferenceService;


	// Listing --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Sponsorship> sponsorships;

		final Sponsor principal = this.sponsorService.findByPrincipal();

		sponsorships = this.sponsorshipService.findBySponsor(principal);

		result = new ModelAndView("sponsorship/list");
		result.addObject("sponsorships", sponsorships);

		return result;
	}

	// Creating --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final int conferenceId) {
		ModelAndView result;
		final Sponsorship sponsorship;
		Conference conference;

		conference = this.conferenceService.findOne(conferenceId);
		sponsorship = this.sponsorshipService.create();
		sponsorship.setConference(conference);

		result = new ModelAndView("sponsorship/create");
		result.addObject("sponsorship", sponsorship);
		result.addObject("id", sponsorship.getId());
		return result;
	}

	// Edition --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int sponsorshipId) {
		ModelAndView result;
		Sponsorship sponsorship;

		sponsorship = this.sponsorshipService.findOne(sponsorshipId);

		result = this.createEditModelAndView(sponsorship);
		return result;
	}

	// Save --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("sponsorship") Sponsorship sponsorship, final BindingResult binding) {
		ModelAndView result;

		sponsorship = this.sponsorshipService.reconstruct(sponsorship, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(sponsorship);
		else
			try {
				this.sponsorshipService.save(sponsorship);
				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(sponsorship, "sponsorship.commit.error");
			}
		return result;
	}

	// Delete --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Sponsorship sponsorship, final BindingResult bindingResult) {
		ModelAndView result;

		try {
			this.sponsorshipService.delete(sponsorship);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(sponsorship, "socialProfile.commit.error");
		}

		return result;
	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Sponsorship sponsorship) {
		return this.createEditModelAndView(sponsorship, null);
	}

	protected ModelAndView createEditModelAndView(final Sponsorship sponsorship, final String message) {
		final ModelAndView result;

		if (sponsorship.getId() == 0)
			result = new ModelAndView("sponsorship/create");
		else
			result = new ModelAndView("sponsorship/edit");

		result.addObject("sponsorship", sponsorship);
		result.addObject("id", sponsorship.getId());
		result.addObject("message", message);
		return result;
	}

}
