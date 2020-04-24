package website.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Credentials {
	
	@Id
	@Column
	@NotBlank()
	private String username;
	
	@Column
	@NotBlank()
	private String password;
	
	Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
