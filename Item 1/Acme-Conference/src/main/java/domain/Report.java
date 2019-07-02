
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {

	//Attributes
	private int			originality;
	private int			quality;
	private int			readability;
	private String		decision;
	private String		comments;

	//Relationships
	private Reviewer	reviewer;
	private Submission	submission;


	//Attributes

	@Range(min = 0, max = 10)
	public int getOriginality() {
		return this.originality;
	}

	public void setOriginality(final int originality) {
		this.originality = originality;
	}

	@Range(min = 0, max = 10)
	public int getQuality() {
		return this.quality;
	}

	public void setQuality(final int quality) {
		this.quality = quality;
	}

	@Range(min = 0, max = 10)
	public int getReadability() {
		return this.readability;
	}

	public void setReadability(final int readability) {
		this.readability = readability;
	}

	@NotBlank
	public String getDecision() {
		return this.decision;
	}

	public void setDecision(final String decision) {
		this.decision = decision;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	//Relationships

	@ManyToOne(optional = false)
	public Reviewer getReviewer() {
		return this.reviewer;
	}

	public void setReviewer(final Reviewer reviewer) {
		this.reviewer = reviewer;
	}

	@ManyToOne(optional = false)
	public Submission getSubmission() {
		return this.submission;
	}

	public void setSubmission(final Submission submission) {
		this.submission = submission;
	}

}
