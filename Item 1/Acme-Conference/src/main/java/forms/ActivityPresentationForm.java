
package forms;

import java.util.Collection;
import java.util.Date;

import javax.persistence.ElementCollection;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import cz.jirutka.validator.collection.constraints.EachNotBlank;
import cz.jirutka.validator.collection.constraints.EachURL;
import domain.Conference;

public class ActivityPresentationForm {

	private Conference			conference;
	private String				title;
	private Collection<String>	speakers;
	private Date				startMoment;
	private int					duration;
	private String				room;
	private String				summary;
	private Collection<String>	attachments;
	private String				paperTitle;
	private String				paperAuthors;
	private String				paperSummary;
	private String				paperDocument;


	@NotNull
	public Conference getConference() {
		return this.conference;
	}

	public void setConference(final Conference conference) {
		this.conference = conference;
	}

	@NotBlank
	@SafeHtml
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@ElementCollection
	@NotEmpty
	@EachNotBlank
	public Collection<String> getSpeakers() {
		return this.speakers;
	}

	public void setSpeakers(final Collection<String> speakers) {
		this.speakers = speakers;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	public Date getStartMoment() {
		return this.startMoment;
	}

	public void setStartMoment(final Date startMoment) {
		this.startMoment = startMoment;
	}

	@Min(0)
	public int getDuration() {
		return this.duration;
	}

	public void setDuration(final int duration) {
		this.duration = duration;
	}

	@NotBlank
	@SafeHtml
	public String getRoom() {
		return this.room;
	}

	public void setRoom(final String room) {
		this.room = room;
	}

	@NotBlank
	@SafeHtml
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	@ElementCollection
	@EachURL
	@NotNull
	public Collection<String> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(final Collection<String> attachments) {
		this.attachments = attachments;
	}

	@NotBlank
	public String getPaperTitle() {
		return this.paperTitle;
	}

	public void setPaperTitle(final String paperTitle) {
		this.paperTitle = paperTitle;
	}

	@NotBlank
	public String getPaperAuthors() {
		return this.paperAuthors;
	}

	public void setPaperAuthors(final String paperAuthors) {
		this.paperAuthors = paperAuthors;
	}

	@NotBlank
	public String getPaperSummary() {
		return this.paperSummary;
	}

	public void setPaperSummary(final String paperSummary) {
		this.paperSummary = paperSummary;
	}

	@NotBlank
	@URL
	public String getPaperDocument() {
		return this.paperDocument;
	}

	public void setPaperDocument(final String paperDocument) {
		this.paperDocument = paperDocument;
	}

}
