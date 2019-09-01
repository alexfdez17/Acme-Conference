
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "title", column = @Column(name = "cameraReadyPaperTitle")), @AttributeOverride(name = "summary", column = @Column(name = "cameraReadyPaperSummary"))
	})
	public Paper getCameraReadyPaper() {
		return this.cameraReadyPaper;
	}

	public void setCameraReadyPaper(final Paper cameraReadyPaper) {
		this.cameraReadyPaper = cameraReadyPaper;
	}

}
