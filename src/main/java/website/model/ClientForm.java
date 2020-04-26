package website.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class ClientForm extends UserForm{

	public ClientForm(@NotBlank(message = "The Clientname is mandatory") String clientName,
			@NotBlank(message = "The address is mandatory") String address,
			@Email(message = "The email is not valid") @NotBlank(message = "The email is mandatory") String email,
			@NotBlank(message = "The Contact person is mandatory") String contactPerson, String password) {
		super(clientName, address, email, contactPerson, password);
		// TODO Auto-generated constructor stub
	}

	public ClientForm() {
		super();
	}

	
}
