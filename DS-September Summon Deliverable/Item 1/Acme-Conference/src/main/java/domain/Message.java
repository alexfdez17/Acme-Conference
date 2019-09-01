
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "mezzage")
public class Message extends DomainEntity {

	//Attributes
	private Date				moment;
	private String				subject;
	private String				body;
	private boolean				isBroadcast;

	//Relationships
	private Actor				owner;
	private Actor				sender;
	private Collection<Actor>	recipients;
	private Topic				topic;


	//Attributes

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

	@NotBlank
	@SafeHtml
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@NotBlank
	@SafeHtml
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@NotNull
	public boolean getIsBroadcast() {
		return this.isBroadcast;
	}

	public void setIsBroadcast(final boolean isBroadcast) {
		this.isBroadcast = isBroadcast;
	}

	//Relationships

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	public Actor getOwner() {
		return this.owner;
	}

	public void setOwner(final Actor owner) {
		this.owner = owner;
	}

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	public Actor getSender() {
		return this.sender;
	}

	public void setSender(final Actor sender) {
		this.sender = sender;
	}

	@ManyToMany
	@Valid
	@NotEmpty
	public Collection<Actor> getRecipients() {
		return this.recipients;
	}

	public void setRecipients(final Collection<Actor> recipients) {
		this.recipients = recipients;
	}

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(final Topic topic) {
		this.topic = topic;
	}

}
