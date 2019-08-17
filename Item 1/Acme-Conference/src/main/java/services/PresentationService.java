
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PresentationRepository;
import domain.Activity;
import domain.Conference;
import domain.Paper;
import domain.Presentation;
import forms.ActivityPresentationForm;

@Service
@Transactional
public class PresentationService {

	// Managed Repository
	@Autowired
	private PresentationRepository	presentationRepository;

	// Supported Services
	@Autowired
	private ConferenceService		conferenceService;


	// CRUD

	public Presentation create() {
		Presentation result;

		result = new Presentation();

		return result;
	}

	public Presentation save(final Presentation presentation) {
		Assert.notNull(presentation);
		Presentation result;
		Conference conference = new Conference();

		final Collection<Conference> conferences = this.conferenceService.findAll();
		for (final Conference c : conferences)
			if (c.getActivities().contains(presentation))
				conference = c;

		Assert.isTrue(conference.getIsFinal());
		Assert.isTrue(presentation.getStartMoment().after(conference.getStartDate()));
		Assert.isTrue(presentation.getStartMoment().before(conference.getEndDate()));

		result = this.presentationRepository.save(presentation);
		this.presentationRepository.flush();
		return result;
	}

	public void delete(final Presentation presentation) {
		Assert.notNull(presentation);
		Assert.isTrue(presentation.getId() != 0);

		this.presentationRepository.delete(presentation);
	}

	public Collection<Presentation> findAll() {
		Collection<Presentation> result;

		result = this.presentationRepository.findAll();

		return result;
	}

	public Presentation findOne(final int presentationId) {
		Presentation result;

		result = this.presentationRepository.findOne(presentationId);

		return result;
	}

	public void flush() {
		this.presentationRepository.flush();
	}

	//Other business methods

	public Presentation register(final ActivityPresentationForm activityPresentationForm) {
		final Presentation result = this.create();
		final Conference conference = activityPresentationForm.getConference();

		result.setTitle(activityPresentationForm.getTitle());
		result.setSpeakers(activityPresentationForm.getSpeakers());
		result.setStartMoment(activityPresentationForm.getStartMoment());
		result.setDuration(activityPresentationForm.getDuration());
		result.setRoom(activityPresentationForm.getRoom());
		result.setSummary(activityPresentationForm.getSummary());
		result.setAttachments(activityPresentationForm.getAttachments());

		final Paper paper = new Paper();
		paper.setTitle(activityPresentationForm.getPaperTitle());
		paper.setAuthors(activityPresentationForm.getPaperAuthors());
		paper.setSummary(activityPresentationForm.getPaperSummary());
		paper.setDocument(activityPresentationForm.getPaperDocument());
		result.setCameraReadyPaper(paper);

		final Presentation saved = this.save(result);

		final Collection<Activity> activities = conference.getActivities();
		activities.add(saved);
		conference.setActivities(activities);
		this.conferenceService.update(conference);

		return result;
	}

}
