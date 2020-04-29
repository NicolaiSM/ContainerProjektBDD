import io.cucumber.java.en.*;

import static org.junit.Assert.*;

import java.util.*;

import application.Client;
import application.Container;
import application.ContainerApp;



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
	
	@Given("information about a client; name {string}, address {string}, contactperson with name {string}, email of contactperson {string}, and password {string}")
	public void information_about_a_client_name_address_contactperson_with_name_email_of_contactperson_and_password(String clientName, String address, String contactPerson, String email, String password) {
		this.clientName = clientName;
	    this.address = address;
	    this.contactPerson = contactPerson;
	    this.email = email;
	    this.password = password;
	}

	@Given("a list of clients with attributes; name: {string}, address: {string}, contactperson name {string}, contactperson email {string}, password: {string} and name: {string}, address: {string}, contactperson name: {string}, contactperson email: {string}, password: {string}")
	public void a_list_of_clients_with_attributes_name_address_contactperson_name_contactperson_email_password_and_name_address_contactperson_name_contactperson_email_password(String clientName1, String address1, String contactPerson1, String email1, String password1, String clientName2, String address2, String contactPerson2, String email2, String password2) throws Exception {
		containerApp.registerClient(clientName1, address1, contactPerson1, email1, password1);
	    containerApp.registerClient(clientName2, address2, contactPerson2, email2, password2);
	}
	
	@When("registering the client")
	public void registering_the_client() {
	    try {
			containerApp.registerClient(clientName, address, contactPerson, email, password);
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
	String[] keywords;
	
	@Given("a keyword {string} and a keyword {string}")
	public void a_keyword_and_a_keyword(String keyword1, String keyword2) {
		keywords = new String[] { keyword1, keyword2};
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
	
	@Given("a client with name: {string}, address: {string}, contactperson name: {string}, contactperson email: {string}, password {string}")
	public void a_client_with_name_address_contactperson_name_and_contactperson_email_password(String clientName, String address, String contactPerson, String email, String password) throws Exception {
		containerApp.registerClient(clientName, address, contactPerson, email, password);
		client = containerApp.findClient(clientName).get(0);
	}

	@Given("Client wants to update the client information {string} to {string} and {string} to {string}")
	public void Client_wants_to_update_the_client_information_to_(String key1, String value1, String key2, String value2) {
	    this.key1 = key1;
	    this.value1 = value1;
	    this.key2 = key2;
	    this.value2 = value2;
	}

	@When("Change previous client information to the given information")
	public void change_previous_client_information_to_the_given_information()  {
		try {
			containerApp.updateClient(client, key1, value1);
			containerApp.updateClient(client, key2, value2);
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
	

	@Given("a client: name {string}, address {string}, contact person {string}, email {string}, password {string}")
	public void a_client_name_address_contact_person_email_password(String name, String address, String contactPerson, String email, String password) {
		client  = new Client(name, address, contactPerson, email, password); 
		
		
	}
	
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

	@Given("a list of existing journeys: port of origin {string}, destination {string}, content {string} with the client: client name {string}, address {string}, contact person {string}, email {string}, password {string}")
	public void a_list_of_existing_journeys_port_of_origin_destination_content_with_the_client_client_name_address_contact_person_email_password(String portOfOrigin, String destination, String content, String clientName, String clientAddress, String contactPerson, String email, String password) throws Exception {
	    containerApp.registerPort(portOfOrigin);
	    containerApp.registerPort(destination);
	    containerApp.createContainer(portOfOrigin);
	    client = new Client(clientName, clientAddress, contactPerson, email, password);
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
	@Given("a container: port {string} with journey: port of origin {string}, destination {string}, content {string} with the client: client name {string}, address {string}, contact person {string}, email {string}, password {string}")
	public void a_container_port_with_journey_port_of_origin_destination_content_with_the_client_client_name_address_contact_person_email_password(String port, String portOfOrigin, String destination, String content, String name, String address, String contactPerson, String email, String password) throws Exception {
		containerApp.createContainer(port);
		container = containerApp.findContainer(port).get(0);
		client = new Client(name, address, contactPerson, email, password);
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
		container = containerApp.findContainer(port).get(0);
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
	
	//
	//
	//
	
	// Most Kilometers Traveled
	
	@Given("a new journey with port of origin {string}, destination {string}, content {string} with the same client")
	public void a_new_journey_with_port_of_origin_destination_content_with_the_same_client(String portOfOrigin, String destination, String content) throws Exception {
		containerApp.registerContainer(portOfOrigin, destination, content, client);
	}

	@Given("internal information: timestamp {string}, location {string}, temperature {int}, humidity {int}, pressure {int}")
	public void internal_information_timestamp_location_temperature_humidity_pressure(String time, String location, Integer temperature, Integer humidity, Integer pressure) throws Exception {
	    this.times.add(time);
	    this.locations.add(location);
	    this.temperatures.add(temperature);
	    this.humidities.add(humidity);
	    this.pressures.add(pressure);
		containerApp.updateJourney(container, times, locations, temperatures, humidities, pressures);

	}

	@Given("a second container with port {string}")
	public void a_second_container_with_port(String port) throws Exception {
	    containerApp.createContainer(port);
	}
	
	@When("determining the container that traveled the most")
	public void determining_the_container_that_traveled_the_most() {
		try {
			containerApp.mostKilometersTraveled();
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("the container that traveled the most is found")
	public void the_container_that_traveled_the_most_is_found() {
	    assertNull(exception);
	}
	
	@Then("no container found")
	public void no_container_found() {
	    assertNotNull(exception);
	}
	
	//
	//
	//
	// Most Journeys
	
	@When("determining the container that went on the most journeys")
	public void determining_the_container_that_went_on_the_most_journeys() {
	    try {
	    	containerApp.mostJourneys();
	    } catch (Exception e) {
	    	exception = e;
	    }
	}

	@Then("the container that went on the most journeys is found")
	public void the_container_that_went_on_the_most_journeys_is_found() {
	    assertNull(exception);
	}
	//
	//
	//
	// Most Ports
	@When("determining the container that visited the most ports")
	public void determining_the_container_that_visited_the_most_ports() {
		try {
	    	containerApp.mostPorts();
	    } catch (Exception e) {
	    	exception = e;
	    }
	}

	@Then("the container that visited the most ports is found")
	public void the_container_that_visited_the_most_ports_is_found() {
	    assertNull(exception);

	}
	
	//
	//
	//
	
	// Least Kilometers traveled
	
	@When("determining the container that traveled the least")
	public void determining_the_container_that_traveled_the_least() {
		try {
			containerApp.leastKilometersTraveled();
		} catch (Exception e) {
			exception = e;
		}
	}
	
	@Then("the container that traveled the least is found")
	public void the_container_that_traveled_the_least_is_found() {
	    assertNull(exception);

	}
	
	//
	//
	//
	// Least Journeys

	@When("determining the container that went on the least journeys")
	public void determining_the_container_that_went_on_the_least_journeys() {
		try {
			containerApp.leastJourneys();
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("the container that went on the least journeys is found")
	public void the_container_that_went_on_the_least_journeys_is_found() {
	    assertNull(exception);
	}
	
	//
	//
	//
	// Least Ports
	
	@When("determining the container that visited the least ports")
	public void determining_the_container_that_visited_the_least_ports() {
		try {
			containerApp.leastPorts();
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("the container that visited the least ports is found")
	public void the_container_that_visited_the_least_ports_is_found() {
	    assertNull(exception);
	}

	//
	//
	//
	// Longest Journey

	@When("determining the longest journey")
	public void determining_the_longest_journey() {
	    try {
			containerApp.longestJourney();
		} catch (Exception e) {
			exception = e;
		}

	}

	@Then("the longest journey is found")
	public void the_longest_journey_is_found() {
	    assertNull(exception);

	}

	//
	//
	//
	// Shortest
	
	@When("determining the shortest journey")
	public void determining_the_shortest_journey() {
	    try {
			containerApp.shortestJourney();
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("the shortest journey is found")
	public void the_shortest_journey_is_found() {
	    assertNull(exception);

	}

	@Then("no journey found")
	public void no_journey_found() {
	    assertNotNull(exception);
	}
	
	//
	//
	//
	
	// Client login
	private String username;
	private String password;
	
	@Given("clients")
	public void clients(io.cucumber.datatable.DataTable dataTable) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		containerApp.registerClient(dataTable.cell(2, 0),dataTable.cell(2, 1),dataTable.cell(2, 2),dataTable.cell(2, 3),dataTable.cell(2, 4));
		containerApp.registerClient(dataTable.cell(1, 0),dataTable.cell(1, 1),dataTable.cell(1, 2),dataTable.cell(1, 3),dataTable.cell(1, 4));
	}


	@Given("a username {string} and a password {string}")
	public void a_username_and_a_password(String username, String password) {
	    this.username = username;
	    this.password = password;
	}

	@When("logging in")
	public void logging_in() {
	    try {
			containerApp.loggedInClient(username, password);
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("client is logged in")
	public void client_is_logged_in() {
	    assertNull(exception);
	}
	
	@Then("client is not logged in")
	public void client_is_not_logged_in() {
	    assertNotNull(exception);
	}

}