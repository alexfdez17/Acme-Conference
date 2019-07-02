
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Reviewer extends Actor {

	//Attributes
	private String	keywords;


	//Attributes

	@NotBlank
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(final String keywords) {
		this.keywords = keywords;
	}

}
