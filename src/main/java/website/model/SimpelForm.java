package website.model;

import javax.persistence.*;

@Entity
public class SimpelForm {
	
	
	@Id
	@GeneratedValue
	@Column
	private long id;
	
	@Column
	private String simpelString1;
	
	@Column
	private String simpelString2;

	@Column
	private String simpelString3;
	
	@Column
	private String simpelString4;
	
	public long setId() {
		return id;
	}

	public String getSimpelString1() {
		return simpelString1;
	}

	public void setSimpelString1(String simpelString1) {
		this.simpelString1 = simpelString1;
	}

	public String getSimpelString2() {
		return simpelString2;
	}

	public void setSimpelString2(String simpelString2) {
		this.simpelString2 = simpelString2;
	}

	public String getSimpelString3() {
		return simpelString3;
	}

	public void setSimpelString3(String simpelString3) {
		this.simpelString3 = simpelString3;
	}

	public String getSimpelString4() {
		return simpelString4;
	}

	public void setSimpelString4(String simpelString4) {
		this.simpelString4 = simpelString4;
	}
	
	
	
	
	
}
