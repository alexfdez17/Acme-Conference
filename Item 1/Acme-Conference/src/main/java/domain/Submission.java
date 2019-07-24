
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "status")
}, uniqueConstraints = {
	@UniqueConstraint(columnNames = {
		"ticker"
	})
})
public class Submission extends DomainEntity {

	//Attributes
	private String		ticker;
	private Date		moment;
	private Paper		paper;
	private Paper		cameraReadyPaper;
	private String		status;
	private boolean		reportsAvailable;

	//Relationships
	private Author		author;
	private Conference	conference;


	//Attributes

	@NotBlank
	@SafeHtml
	@Pattern(regexp = "^[A-Z]{3}-[A-Z0-9]{4}$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "MM/dd/yyyy hh:MM")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@Valid
	@NotNull
	@Embedded
	public Paper getPaper() {
		return this.paper;
	}

	public void setPaper(final Paper paper) {
		this.paper = paper;
	}

	@Valid
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "title", column = @Column(name = "cameraReadyTitle")), @AttributeOverride(name = "authors", column = @Column(name = "cameraReadyAuthors")),
		@AttributeOverride(name = "summary", column = @Column(name = "cameraReadySummary")), @AttributeOverride(name = "document", column = @Column(name = "cameraReadyDocument")),
	})
	public Paper getCameraReadyPaper() {
		return this.cameraReadyPaper;
	}

	public void setCameraReadyPaper(final Paper cameraReadypaper) {
		this.cameraReadyPaper = cameraReadypaper;
	}

	@NotBlank
	@SafeHtml
	@Pattern(regexp = "UNDER-REVIEW|ACCEPTED|REJECTED")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@NotNull
	public boolean getReportsAvailable() {
		return this.reportsAvailable;
	}

	public void setReportsAvailable(final boolean reportsAvailable) {
		this.reportsAvailable = reportsAvailable;
	}

	//Relationships

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(final Author author) {
		this.author = author;
	}

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	public Conference getConference() {
		return this.conference;
	}

	public void setConference(final Conference conference) {
		this.conference = conference;
	}

}
