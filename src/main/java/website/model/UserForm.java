package website.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class UserForm {
	
	
	@Id
	@Column(unique = true)
	@NotBlank(message = "The Clientname is mandatory")
	private String clientName;
	
	@Column
	@NotBlank(message = "The address is mandatory")
	private String address;
	
	@Column
	@Email(message = "The email is not valid")
	@NotBlank(message = "The email is mandatory")
	private String email;
	
	@Column
	@NotBlank(message = "The Contact person is mandatory")
	private String contactPerson;
	
	@Column
	private String password;


	public String getPassword() {
		return password;
	}

	
	
	public UserForm(@NotBlank(message = "The Clientname is mandatory") String clientName,
			@NotBlank(message = "The address is mandatory") String address,
			@Email(message = "The email is not valid") @NotBlank(message = "The email is mandatory") String email,
			@NotBlank(message = "The Contact person is mandatory") String contactPerson, String password) {
		super();
		this.clientName = clientName;
		this.address = address;
		this.email = email;
		this.contactPerson = contactPerson;
		this.password = password;
	}
	
	public UserForm() {
		
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	
	
}
