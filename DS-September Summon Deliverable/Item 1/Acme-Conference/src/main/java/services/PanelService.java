
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PanelRepository;
import domain.Activity;
import domain.Conference;
import domain.Panel;
import forms.ActivityPanelForm;

@Service
@Transactional
public class PanelService {

	// Managed Repository
	@Autowired
	private PanelRepository		panelRepository;

	// Supported Services
	@Autowired
	private ConferenceService	conferenceService;


	// CRUD

	public Panel create() {
		Panel result;

		result = new Panel();

		return result;
	}

	public Panel save(final Panel panel) {
		Assert.notNull(panel);
		Panel result;
		Conference conference = new Conference();

		if (panel.getId() != 0) {
			final Collection<Conference> conferences = this.conferenceService.findAll();
			for (final Conference c : conferences)
				if (c.getActivities().contains(panel))
					conference = c;

			Assert.isTrue(!conference.getIsFinal());
			Assert.isTrue(panel.getStartMoment().after(conference.getStartDate()));
			Assert.isTrue(panel.getStartMoment().before(conference.getEndDate()));
		}

		result = this.panelRepository.save(panel);
		this.panelRepository.flush();
		return result;
	}

	public void delete(final Panel panel) {
		Assert.notNull(panel);
		Assert.isTrue(panel.getId() != 0);
		Conference conference = new Conference();

		final Collection<Conference> conferences = this.conferenceService.findAll();
		for (final Conference c : conferences)
			if (c.getActivities().contains(panel))
				conference = c;

		Assert.isTrue(!conference.getIsFinal());

		this.panelRepository.delete(panel);
	}

	public Collection<Panel> findAll() {
		Collection<Panel> result;

		result = this.panelRepository.findAll();

		return result;
	}

	public Panel findOne(final int panelId) {
		Panel result;

		result = this.panelRepository.findOne(panelId);

		return result;
	}

	public void flush() {
		this.panelRepository.flush();
	}

	//Other business methods

	public Panel register(final ActivityPanelForm activityPanelForm) {
		final Panel result = this.create();
		final Conference conference = activityPanelForm.getConference();

		Assert.isTrue(activityPanelForm.getStartMoment().after(conference.getStartDate()));
		Assert.isTrue(activityPanelForm.getStartMoment().before(conference.getEndDate()));

		result.setTitle(activityPanelForm.getTitle());
		result.setSpeakers(activityPanelForm.getSpeakers());
		result.setStartMoment(activityPanelForm.getStartMoment());
		result.setDuration(activityPanelForm.getDuration());
		result.setRoom(activityPanelForm.getRoom());
		result.setSummary(activityPanelForm.getSummary());
		result.setAttachments(activityPanelForm.getAttachments());

		final Panel saved = this.save(result);

		final Collection<Activity> activities = conference.getActivities();
		activities.add(saved);
		conference.setActivities(activities);
		this.conferenceService.update(conference);

		return result;
	}
}
