package website.model;

import javax.validation.constraints.NotBlank;



public class CredentialForm {
	
	@NotBlank(message = "password is mandatory")
	private String password;
	
	@NotBlank(message = "username is mandatory")
	private String clientName;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	
	
}
