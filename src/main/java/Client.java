
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
	public String getClientName() {
		return this.clientName;
	}
	
}
