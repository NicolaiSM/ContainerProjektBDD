import io.cucumber.java.en.*;

import static org.junit.Assert.*;

import java.util.*;



public class StepDefinition {
	private String clientName;
	private String address;
	private String contactPerson;
	private String email;
	private Exception exception;
	private ContainerApp containerApp = new ContainerApp();	
	
	// REGISTER CLIENT
	
	@Given("information about a client; name {string}, address {string}, contactperson with name {string}, email of contactperson {string}")
	public void information_about_a_client_name_address_contactperson_with_name_email_of_contactperson(String clientName, String clientAddress, String contactPerson, String email) {
	    this.clientName = clientName;
	    this.address = clientAddress;
	    this.contactPerson = contactPerson;
	    this.email = email;
	}

	@Given("a list of clients with attributes; name: {string}, address: {string}, contactperson name {string}, contactperson email {string} and name: {string}, address: {string}, contactperson name: {string}, contactperson email: {string}")
	public void a_list_of_clients_with_attributes_name_address_contactperson_name_contactperson_email_and_name_address_contactperson_name_contactperson_email(String clientName1, String clientAddress1, String contactPerson1, String email1, String clientName2, String clientAddress2, String contactPerson2, String email2) throws Exception {
	    containerApp.registerClient(clientName1, clientAddress1, contactPerson1, email1);
	    containerApp.registerClient(clientName2, clientAddress2, contactPerson2, email2);
	}

	@When("registering the client")
	public void registering_the_client() {
	    try {
			containerApp.registerClient(clientName, address, contactPerson, email);
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("Client is registered")
	public void client_is_registered() {
	    assertTrue(containerApp.isClientRegistered(clientName));
	}

	@Then("Client registration failed")
	public void client_registration_failed() {
	    assertNotNull(exception);
	}
	
	//
	//
	//
	
	// FIND CLIENT

	@Given("a name for a client {string} and a contactperson name {string}")
	public void a_name_for_a_client_and_a_contactperson_name(String clientName, String contactPerson) {
		this.clientName = clientName;
		this.contactPerson = contactPerson;
	}

	@When("Finding clients that matches keyword")
	public void finding_clients_that_matches_keyword() {
		List<String> keywords = new ArrayList<String>();
		keywords.add(clientName);
		keywords.add(contactPerson);
		try {
			containerApp.findClient(keywords);
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("Check if any client is found")
	public void check_if_any_client_is_found() {
	    assertNull(exception);
	}

	@Then("Check that no result is found")
	public void check_that_no_result_is_found() {
	    assertNotNull(exception);

	}
	
	//
	//
	//
	
	// UPDATE CLIENT INFORMATION
	private Client client;
	
	@Given("a client with; name: {string}, address: {string}, contactperson name: {string} and contactperson email: {string}")
	public void a_client_with_name_address_contactperson_name_and_contactperson_email(String clientName, String address, String contactPerson, String email) {
	    client = new Client(clientName,address,contactPerson,email);
	}

	@Given("Client wants to update client name to {string}, address to {string}, contact person to {string} and email to {string}")
	public void client_wants_to_update_client_name_to_address_to_contact_person_to_and_email_to(String clientName, String clientAddress, String contactPerson, String email) {
	    this.clientName = clientName;
	    this.address = clientAddress;
	    this.contactPerson = contactPerson;
	    this.email = email;
	}

	@When("Change previous client information to the given information")
	public void change_previous_client_information_to_the_given_information() {
		try {
			containerApp.updateClient(client,clientName,address,contactPerson,email);
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("Client has been updated")
	public void client_has_been_updated() {
	    assertNull(exception);
	}
	
	@Then("Client has not been updated")
	public void client_has_not_been_updated() {
	    assertNotNull(exception);
	}
}