package website.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class JourneyForm {
	
	@NotBlank
	@Column
	private String portOfOrigin;
	@NotBlank
	@Column
	private String destination;
	@Id
	@NotBlank
	@Column
	private String content;
	
	public String getPortOfOrigin() {
		return portOfOrigin;
	}
	public void setPortOfOrigin(String portOfOrigin) {
		this.portOfOrigin = portOfOrigin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
