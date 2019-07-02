
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Presentation extends Activity {

	//Attributes
	private Paper	cameraReadyPaper;


	//Attributes

	@Valid
	@NotNull
	public Paper getCameraReadyPaper() {
		return this.cameraReadyPaper;
	}

	public void setCameraReadyPaper(final Paper cameraReadyPaper) {
		this.cameraReadyPaper = cameraReadyPaper;
	}

}
