
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import cz.jirutka.validator.collection.constraints.EachNotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Reviewer extends Actor {

	//Attributes
	private Collection<String>	keywords;


	//Attributes

	@EachNotBlank
	@NotEmpty
	public Collection<String> getKeywords() {
		return this.keywords;
	}

	public void setKeywords(final Collection<String> keywords) {
		this.keywords = keywords;
	}

}
