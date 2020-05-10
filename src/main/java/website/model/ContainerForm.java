package website.model;

import javax.validation.constraints.NotBlank;

public class ContainerForm {
	
	@NotBlank(message = "Port is mandatory")
	private String port;

	public String getPort() {
		return port;
	}
	
	public String setPort(String string) {
		return port = string;
	}
	
}
