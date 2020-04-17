
public class Client {
	private String clientName;
	private String address;
	private String contactPerson;
	private String email;
	
	Client(String clientName, String address, String contactPerson, String email) {
	    this.clientName = clientName;
	    this.address = address;
	    this.contactPerson = contactPerson;
	    this.email = email;
	}

	public boolean hasKeyword(String keyword) {
		return (keyword.equals(clientName) || keyword.equals(address) || keyword.equals(contactPerson) || keyword.equals(email));
	}
	
	//Setters
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	//Getters
	public String getClientName() {
		return this.clientName;
	}
}
