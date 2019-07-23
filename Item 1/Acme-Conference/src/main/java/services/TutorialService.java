
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TutorialRepository;
import domain.Activity;
import domain.Conference;
import domain.Section;
import domain.Tutorial;
import forms.ActivityTutorialForm;

@Service
@Transactional
public class TutorialService {

	// Managed Repository
	@Autowired
	private TutorialRepository	tutorialRepository;

	// Supported Services
	@Autowired
	private ConferenceService	conferenceService;

	@Autowired
	private SectionService		sectionService;


	// CRUD

	public Tutorial create() {
		Tutorial result;

		result = new Tutorial();
		final Collection<Section> sections = new ArrayList<Section>();
		result.setSections(sections);

		return result;
	}

	public Tutorial save(final Tutorial tutorial) {
		Assert.notNull(tutorial);
		Tutorial result;
		result = this.tutorialRepository.save(tutorial);
		this.tutorialRepository.flush();
		return result;
	}

	public void delete(final Tutorial tutorial) {
		Assert.notNull(tutorial);
		Assert.isTrue(tutorial.getId() != 0);

		this.tutorialRepository.delete(tutorial);
	}

	public Collection<Tutorial> findAll() {
		Collection<Tutorial> result;

		result = this.tutorialRepository.findAll();

		return result;
	}

	public Tutorial findOne(final int tutorialId) {
		Tutorial result;

		result = this.tutorialRepository.findOne(tutorialId);

		return result;
	}

	public void flush() {
		this.tutorialRepository.flush();
	}

	//Other business methods

	public Tutorial register(final ActivityTutorialForm activityTutorialForm) {
		final Tutorial result = this.create();
		final Conference conference = activityTutorialForm.getConference();

		result.setTitle(activityTutorialForm.getTitle());
		result.setSpeakers(activityTutorialForm.getSpeakers());
		result.setStartMoment(activityTutorialForm.getStartMoment());
		result.setDuration(activityTutorialForm.getDuration());
		result.setRoom(activityTutorialForm.getRoom());
		result.setSummary(activityTutorialForm.getSummary());
		result.setAttachments(activityTutorialForm.getAttachments());

		final Section section = this.sectionService.create();
		section.setTitle(activityTutorialForm.getSectionTitle());
		section.setSummary(activityTutorialForm.getSectionSummary());
		section.setPictures(activityTutorialForm.getSectionPictures());
		final Section sectionSaved = this.sectionService.save(section);

		final Collection<Section> sections = result.getSections();
		sections.add(sectionSaved);
		result.setSections(sections);

		final Tutorial saved = this.save(result);

		final Collection<Activity> activities = conference.getActivities();
		activities.add(saved);
		conference.setActivities(activities);
		this.conferenceService.update(conference);

		return result;
	}
}
