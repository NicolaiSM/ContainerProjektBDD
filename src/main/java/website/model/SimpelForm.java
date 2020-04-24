package website.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class SimpelForm {
	
	@Id
	@Column
	@NotBlank(message = "Mandatory" )
	private String simpelString;

	public String getSimpelString() {
		return simpelString;
	}

	public void setSimpelString(String simpelString) {
		this.simpelString = simpelString;
	}
	
	
	
}
