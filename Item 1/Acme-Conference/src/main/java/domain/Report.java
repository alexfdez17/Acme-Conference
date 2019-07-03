
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import cz.jirutka.validator.collection.constraints.EachNotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {

	//Attributes
	private double		originality;
	private double		quality;
	private double		readability;
	private String		decision;
	private String		comments;

	//Relationships
	private Reviewer	reviewer;
	private Submission	submission;


	//Attributes

	@NotNull
	@DecimalMax("0.0")
	@DecimalMin("10.0")
	@Digits(fraction = 2, integer = 2)
	public double getOriginality() {
		return this.originality;
	}

	public void setOriginality(final double originality) {
		this.originality = originality;
	}

	@NotNull
	@DecimalMax("0.0")
	@DecimalMin("10.0")
	@Digits(fraction = 2, integer = 2)
	public double getQuality() {
		return this.quality;
	}

	public void setQuality(final double quality) {
		this.quality = quality;
	}

	@NotNull
	@DecimalMax("0.0")
	@DecimalMin("10.0")
	@Digits(fraction = 2, integer = 2)
	public double getReadability() {
		return this.readability;
	}

	public void setReadability(final double readability) {
		this.readability = readability;
	}

	@NotBlank
	@SafeHtml
	public String getDecision() {
		return this.decision;
	}

	public void setDecision(final String decision) {
		this.decision = decision;
	}

	@EachNotBlank
	@NotEmpty
	public String getComments() {
		return this.comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	//Relationships

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	public Reviewer getReviewer() {
		return this.reviewer;
	}

	public void setReviewer(final Reviewer reviewer) {
		this.reviewer = reviewer;
	}

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	public Submission getSubmission() {
		return this.submission;
	}

	public void setSubmission(final Submission submission) {
		this.submission = submission;
	}

}
