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
	private String key1;
	private String value1;
	private String key2;
	private String value2;
	
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
	List<String> keywords = new ArrayList<String>();
	
	@Given("a keyword {string} and a keyword {string}")
	public void a_keyword_and_a_keyword(String keyword1, String keyword2) {
		keywords.add(keyword1);
		keywords.add(keyword2);
	}

	@When("Finding clients that matches keyword")
	public void finding_clients_that_matches_keyword() {

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
	
	@Given("a client with name: {string}, address: {string}, contactperson name: {string} and contactperson email: {string}")
	public void a_client_with_name_address_contactperson_name_and_contactperson_email(String clientName, String address, String contactPerson, String email) {
	    client = new Client(clientName,address,contactPerson,email);
	}

	@Given("Client wants to update the client information {string} to {string} and {string} to {string}")
	public void Client_wants_to_update_the_client_information_to_(String key1, String value1, String key2, String value2) {
	    this.key1 = key1;
	    this.value1 = value1;
	    this.key2 = key2;
	    this.value2 = value2;
	}

	@When("Change previous client information to the given information")
	public void change_previous_client_information_to_the_given_information() throws Exception {
		try {
			containerApp.updateClient(client, key1, value1);
		} catch (Exception e) {
			exception = e;
		}
		containerApp.updateClient(client, key2, value2);
	}

	@Then("Client has been updated")
	public void client_has_been_updated() {
	    assertNull(exception);
	}
	
	@Then("Client has not been updated")
	public void client_has_not_been_updated() {
	    assertNotNull(exception);
	}
	
	//
	//
	//
	
	// Register Port
	
	String port;
	
	@Given("a list of ports containing {string}")
	public void a_list_of_ports_containing(String port) throws Exception {
			containerApp.registerPort(port);
	}

	@Given("a port to be registered {string}")
	public void a_port_to_be_registered(String port) {
	    this.port = port;
	}

	@When("Registering the port")
	public void registering_the_port() {
		try {
			containerApp.registerPort(port);
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("Port is registered")
	public void port_is_registered() {
	    assertNull(exception);
	}

	@Then("Port is not registered")
	public void port_is_not_registered() {
	    assertNotNull(exception);
	}
	
	
	//
	//
	//
	
	// Create Container
	

	@Given("a port of origin {string}")
	public void a_port_of_origin(String port) {
	    this.port = port;
	}

	@Given("a list of ports containing the port {string}")
	public void a_list_of_ports_containing_the_port(String port) throws Exception {
	    containerApp.registerPort(port);
	}
	

	@When("creating a new container")
	public void creating_a_new_container() {
		try {
			containerApp.createContainer(port);
		} catch (Exception e){
			exception = e;
		}
	}

	@Then("a new container has been added to the existing containers")
	public void a_new_container_has_been_added_to_the_existing_containers() {
	    assertNull(exception);
	}

	@Then("the container could not be created since the port was not a validport")
	public void the_container_could_not_be_created_since_the_port_was_not_a_validport() {
		assertNotNull(exception);
	}
	
	
	//
	//
	//
	
	// Register Container
	
	String portOfOrigin;
	String destination;
	String content;

	@Given("a list of existing containers: port {string}, port {string}")
	public void a_list_of_existing_containers_port_port(String port1, String port2) throws Exception {
		containerApp.registerPort(port1);
		containerApp.registerPort(port2);
		containerApp.createContainer(port1);
		containerApp.createContainer(port2);

	}

	@Given("information about the journey: port of origin {string}, destination {string}, content {string}")
	public void information_about_the_journey_port_of_origin_destination_content(String portOfOrigin, String destination, String content) {
		this.portOfOrigin = portOfOrigin;
		this.destination = destination;
		this.content = content;
	}

	@Given("a list of existing containers: port {string}, port {string} with journey: port of origin {string}, destination {string}, content {string} with the client in the system")
	public void a_list_of_existing_containers_port_port_with_journey_port_of_origin_destination_content_with_the_client_in_the_system(String port1, String port2, String portOfOrigin, String destination, String content) throws Exception {
		containerApp.registerPort(port1);
		containerApp.registerPort(port2);
		containerApp.createContainer(port1);
		containerApp.createContainer(port2);
		containerApp.registerContainer(portOfOrigin, destination, content, client);
	}
	
	@Given("a client: name {string}, address {string}, contact person {string}, email {string}")
	public void a_client_name_address_contact_person_email(String name, String address, String contactPerson, String email) {
		client  = new Client(name, address, contactPerson, email); 
		
		
	}

	@Given("a list of existing containers: port {string}")
	public void a_list_of_existing_containers_port(String port) throws Exception {
		containerApp.registerPort(port);
		containerApp.createContainer(port);
	}

	@When("registrering a container")
	public void registrering_a_container() {
		try {
			containerApp.registerContainer(portOfOrigin, destination, content, client);
		} catch (Exception e) {
			exception = e;
		}
		
	}

	@Then("container is not registred")
	public void container_is_not_registred() {
	    assertNotNull(exception);
	}

	@Then("container is registred")
	public void container_is_registred() {
	    assertNull(exception);

	}
	

	
	
	//
	//
	//
	
	// Find Container
	
	@When("searching for a container")
	public void searching_for_a_container() {
		try {
			containerApp.findContainer(keywords);
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("a container has been found")
	public void a_container_has_been_found() {
	    assertNull(exception);
	}

	@Then("the keyword does not match any container")
	public void the_keyword_does_not_match_any_container() {
		assertNotNull(exception);
		
	}
	
	//
	//
	//
	
	// Find Journey


	@Given("a list of existing journeys: port of origin {string}, destination {string}, content {string} with the client: client name {string}, address {string}, contact person {string}, email {string}")
	public void a_list_of_existing_journeys_port_of_origin_destination_content_with_the_client_client_name_address_contact_person_email(String portOfOrigin, String destination, String content, String clientName, String clientAddress, String contactPerson, String email) throws Exception {
	    containerApp.registerPort(portOfOrigin);
	    containerApp.registerPort(destination);
	    containerApp.createContainer(portOfOrigin);
	    client = new Client(clientName, clientAddress, contactPerson, email);
	    containerApp.registerContainer(portOfOrigin, destination, content, client);
	    
	}

	@When("searching for a journey")
	public void searching_for_a_journey() {
		try {
			containerApp.findJourney(keywords);
		} catch (Exception e) {
			exception = e;
		}
		
	}

	@Then("a journey has been found")
	public void a_journey_has_been_found() {
		assertNull(exception);
		
	}

	@Then("the keyword does not match any journey")
	public void the_keyword_does_not_match_any_journey() {
	    assertNotNull(exception);
	}
	
	
	
	
	
	//
	//
	//
	
	// Update Container
	List<String> times = new ArrayList<String>();
	List<String> locations = new ArrayList<String>();
	List<Integer> temperatures = new ArrayList<Integer>();
	List<Integer> humidities = new ArrayList<Integer>();
	List<Integer> pressures = new ArrayList<Integer>();
	Container container;
	
	
	@Given("a list of existing ports {string}, {string} and {string}")
	public void a_list_of_existing_ports_and(String port1, String port2, String port3) throws Exception {
	    containerApp.registerPort(port1);
	    containerApp.registerPort(port2);
	    containerApp.registerPort(port3);
	}
	
	@Given("a container: port {string} with journey: port of origin {string}, destination {string}, content {string} with the client: client name {string}, address {string}, contact person {string}, email {string}")
	public void a_container_port_with_journey_port_of_origin_destination_content_with_the_client_client_name_address_contact_person_email(String port, String portOfOrigin, String destination, String content, String name, String address, String contactPerson, String email) throws Exception {
		containerApp.createContainer(port);
		keywords.add(port);
		container = containerApp.findContainer(keywords).get(0);
		client = new Client(name, address, contactPerson, email);
		containerApp.registerContainer(portOfOrigin, destination, content, client);
	}

	@Given("new internal information: timestamp {string}, location {string}, temperature {int}, humidity {int}, pressure {int}")
	public void new_internal_information_timestamp_location_temperature_humidity_pressure(String times, String locations, Integer temperatures, Integer humidities, Integer pressures) {
	    this.times.add(times);
	    this.locations.add(locations);
	    this.temperatures.add(temperatures);
	    this.humidities.add(humidities);
	    this.pressures.add(pressures);
	}
	
	@Given("a container: port {string} with no journey")
	public void a_container_port_with_no_journey(String port) throws Exception {
		containerApp.createContainer(port);
		keywords.add(port);
		container = containerApp.findContainer(keywords).get(0);
	}
	
	@When("updating a journey")
	public void updating_a_journey() {
	    try {
			containerApp.updateJourney(container, times, locations, temperatures, humidities, pressures);
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("the journey is updated")
	public void the_journey_is_updated() {
	    assertNull(exception);
	}

	@Then("the journey has ended")
	public void the_journey_has_ended() {
		assertNull(container.getJourney());
		assertNull(exception);
	}

	@Then("the journey is not updated")
	public void the_journey_is_not_updated() {
		assertNotNull(exception);
	}
	
}