
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SectionRepository;
import domain.Section;
import domain.Tutorial;
import forms.SectionForm;

@Service
@Transactional
public class SectionService {

	// Managed Repository
	@Autowired
	private SectionRepository	sectionRepository;

	// Supported Services
	@Autowired
	private TutorialService		tutorialService;


	// CRUD

	public Section create() {
		Section result;

		result = new Section();

		return result;
	}

	public Section save(final Section section) {
		Assert.notNull(section);
		Section result;
		result = this.sectionRepository.save(section);
		this.sectionRepository.flush();
		return result;
	}

	public void delete(final Section section) {
		Assert.notNull(section);
		Assert.isTrue(section.getId() != 0);

		this.sectionRepository.delete(section);
	}

	public Collection<Section> findAll() {
		Collection<Section> result;

		result = this.sectionRepository.findAll();

		return result;
	}

	public Section findOne(final int sectionId) {
		Section result;

		result = this.sectionRepository.findOne(sectionId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.sectionRepository.flush();
	}

	//Other business methods

	public Section register(final SectionForm sectionForm) {
		final Section result = this.create();
		final Tutorial tutorial = sectionForm.getTutorial();

		result.setTitle(sectionForm.getTitle());
		result.setSummary(sectionForm.getSummary());
		result.setPictures(sectionForm.getPictures());

		final Section saved = this.save(result);

		final Collection<Section> sections = tutorial.getSections();
		sections.add(saved);
		tutorial.setSections(sections);
		this.tutorialService.save(tutorial);

		return result;
	}

}
