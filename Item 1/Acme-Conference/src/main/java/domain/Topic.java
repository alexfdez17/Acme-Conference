
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Topic {

	//Attributes
	private String	name;
	private String	nameES;

	//Relationships
	private Message	message;


	//Attributes

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getNameES() {
		return this.nameES;
	}

	public void setNameES(final String nameES) {
		this.nameES = nameES;
	}

	//Relationships

	@ManyToOne(optional = false)
	public Message getMessage() {
		return this.message;
	}

	public void setMessage(final Message message) {
		this.message = message;
	}

}
