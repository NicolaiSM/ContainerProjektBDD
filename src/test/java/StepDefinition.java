import io.cucumber.java.en.*;

import static org.junit.Assert.*;



public class StepDefinition {
	private String clientName;
	private String address;
	private String contactPerson;
	private String email;
	private boolean bool;
	private ClientList clientList = new ClientList();
	
	
	
	// REGISTER CLIENT
	@Given("information about a client; name {string}, address {string}, contactperson with name {string}, email of contactperson {string}")
	public void information_about_a_client_name_address_contactperson_with_name_email_of_contactperson(String clientName, String clientAddress, String contactPerson, String email) {
	    this.clientName = clientName;
	    this.address = clientAddress;
	    this.contactPerson = contactPerson;
	    this.email = email;
	}
	
	@Given("a list of clients with attributes; name: {string}, address: {string}, contactperson name {string}, contactperson email {string} and name: {string}, address: {string}, contactperson name: {string}, contactperson email: {string}")
	public void a_list_of_clients_with_attributes_name_address_contactperson_name_contactperson_email_and_name_address_contactperson_name_contactperson_email(String clientName1, String clientAddress1, String contactPerson1, String email1, String clientName2, String clientAddress2, String contactPerson2, String email2) {
	    clientList.addUniqueClient(clientName1, clientAddress1, contactPerson1, email1);
	    clientList.addUniqueClient(clientName2, clientAddress2, contactPerson2, email2);
	}

	@When("registering a new client")
	public void registering_a_new_client() {
	    this.bool = clientList.addUniqueClient(this.clientName, this.address, this.contactPerson, this.email);
	}

	@Then("Client is registered")
	public void client_is_registered() {
	    assertTrue(bool);
	}

	@Then("Client is not registered")
	public void client_is_not_registered() {
	    assertFalse(bool);

	}
}