import io.cucumber.java.en.*;

import static org.junit.Assert.*;

import java.util.*;

import application.Client;
import application.Container;
import application.ContainerApp;
import application.User;



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
	
	@Given("Information about a client")
	public void information_about_a_client(io.cucumber.datatable.DataTable dataTable) throws Exception {
		clientName = dataTable.cell(1, 0);
		address = dataTable.cell(1, 1);
		contactPerson = dataTable.cell(1, 2);
		email = dataTable.cell(1, 3);
		password = dataTable.cell(1, 4);
	}
	
	@When("Registering the client")
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

	@Then("Client could not be registered")
	public void client_could_not_be_registered() {
	    assertNotNull(exception);
	}
	
	//
	//
	//
	
	// FIND CLIENT
	String[] keywords;
	

	@Given("Keywords")
	public void keywords(io.cucumber.datatable.DataTable dataTable) {
		keywords = new String[] {dataTable.cell(1, 0), dataTable.cell(2, 0)};

	}
	@When("Finding clients that matches keyword")
	public void finding_clients_that_matches_keyword() {

		try {
			containerApp.findClient(keywords);
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("One or more clients found")
	public void one_or_more_clients_found() {
	    assertNull(exception);
	}

	@Then("No clients are found")
	public void no_clients_are_found() {
	    assertNotNull(exception);

	}
	
	//
	//
	//
	
	// UPDATE CLIENT INFORMATION
	private User client;
	
	@Given("A client")
	public void a_client(io.cucumber.datatable.DataTable dataTable) throws Exception {
		containerApp.registerClient(dataTable.cell(1, 0),dataTable.cell(1, 1),dataTable.cell(1, 2),dataTable.cell(1, 3),dataTable.cell(1, 4));
		client = containerApp.findClient(dataTable.cell(1, 0)).get(0);
	}
	@Given("Information that should be changed")
	public void information_that_should_be_changed(io.cucumber.datatable.DataTable dataTable) {
		key1 = dataTable.cell(1, 0);
		value1 = dataTable.cell(1, 1);
		key2 = dataTable.cell(2, 0);
		value2 = dataTable.cell(2, 1);
	}

	@When("Updating the client")
	public void updating_the_client()  {
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

	@Given("A port to be registered {string}")
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
	
	@Given("A port {string}")
	public void a_port(String port) {
	    this.port = port;
	}

	@Given("Existing ports")
	public void existing_ports(io.cucumber.datatable.DataTable dataTable) throws Exception {
		containerApp.registerPort(dataTable.cell(1, 0));
	}
	

	@When("Creating a new container")
	public void creating_a_new_container() {
		try {
			containerApp.createContainer(port);
		} catch (Exception e){
			exception = e;
		}
	}

	@Then("The container is created")
	public void the_container_is_created() {
	    assertNull(exception);
	}

	@Then("The container could not be created")
	public void the_container_could_not_be_created() {
		assertNotNull(exception);
	}
	
	
	//
	//
	//
	
	// Register Container
	
	String portOfOrigin;
	String destination;
	String content;

	@Given("Containers")
	public void containers(io.cucumber.datatable.DataTable dataTable) throws Exception {
		for (int i=1; i<dataTable.height(); i++) {
			containerApp.createContainer(dataTable.cell(i, 0));
		}
	}
	
	@Given("Information about the journey")
	public void information_about_the_journey(io.cucumber.datatable.DataTable dataTable) {
		portOfOrigin = dataTable.cell(1, 0);
		destination = dataTable.cell(1, 1);
		content = dataTable.cell(1, 2);
	}
	
	@Given("A journey")
	public void a_journey(io.cucumber.datatable.DataTable dataTable) throws Exception {
		containerApp.registerContainer(dataTable.cell(1, 0), dataTable.cell(1, 1), dataTable.cell(1, 2), client);
	}

	@When("Registrering a container")
	public void registrering_a_container() {
		try {
			containerApp.registerContainer(portOfOrigin, destination, content, client);
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("Container is registred")
	public void container_is_registred() {
	    assertNull(exception);
	}
	
	@Then("Container is not registred")
	public void container_is_not_registred() {
	    assertNotNull(exception);
	}
	
	
	//
	//
	//
	
	// Find Container
	
	
	@When("Searching for a container")
	public void searching_for_a_container() {
		try {
			containerApp.findContainer(keywords);
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("A container has been found")
	public void a_container_has_been_found() {
	    assertNull(exception);
	}

	@Then("No containers found")
	public void no_containers_found() {
		assertNotNull(exception);
		
	}
	
	//
	//
	//
	
	// Find Journey
	
	@Given("Journeys")
	public void journeys(io.cucumber.datatable.DataTable dataTable) throws Exception {
	    containerApp.registerContainer(dataTable.cell(1, 0), dataTable.cell(1, 1), dataTable.cell(1, 2), client);
	    containerApp.registerContainer(dataTable.cell(2, 0), dataTable.cell(2, 1), dataTable.cell(2, 2), client);
	}
	
	@When("Searching for a journey")
	public void searching_for_a_journey() {
		try {
			containerApp.findJourney(keywords);
		} catch (Exception e) {
			exception = e;
		}
		
	}

	@Then("A journey has been found")
	public void a_journey_has_been_found() {
		assertNull(exception);
		
	}

	@Then("The keyword does not match any journey")
	public void the_keyword_does_not_match_any_journey() {
	    assertNotNull(exception);
	}
	
	
	
	
	
	//
	//
	//
	
	// Update Journey
	List<String> times = new ArrayList<String>();
	List<String> locations = new ArrayList<String>();
	List<Integer> temperatures = new ArrayList<Integer>();
	List<Integer> humidities = new ArrayList<Integer>();
	List<Integer> pressures = new ArrayList<Integer>();
	Container container;

	@Given("New internal information")
	public void new_internal_information(io.cucumber.datatable.DataTable dataTable) {
		this.times.add(dataTable.cell(1, 0));
	    this.locations.add(dataTable.cell(1, 1));
	    this.temperatures.add(Integer.parseInt(dataTable.cell(1, 2)));
	    this.humidities.add(Integer.parseInt(dataTable.cell(1, 3)));
	    this.pressures.add(Integer.parseInt(dataTable.cell(1, 4)));
	}

	@Given("A container")
	public void a_container(io.cucumber.datatable.DataTable dataTable) throws Exception {
		containerApp.createContainer(dataTable.cell(1, 0));
		container = containerApp.findContainer(dataTable.cell(1, 0)).get(0);
	}
	@Given("Ports")
	public void ports(io.cucumber.datatable.DataTable dataTable) throws Exception {
	    for (int i=1; i<dataTable.height(); i++) {
			containerApp.registerPort(dataTable.cell(i, 0));
	    }
	}

	@When("Updating a journey")
	public void updating_a_journey() {
	    try {
			containerApp.updateJourney(container, times, locations, temperatures, humidities, pressures);
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("The journey is updated")
	public void the_journey_is_updated() {
	    assertNull(exception);
	}

	@Then("The journey has ended")
	public void the_journey_has_ended() {
		assertNull(container.getJourney());
		assertNull(exception);
	}

	@Then("The journey is not updated")
	public void the_journey_is_not_updated() {
		assertNotNull(exception);
	}
	
	//
	//
	//
	
	// Most Kilometers Traveled
		
	@When("Determining the container that traveled the most")
	public void determining_the_container_that_traveled_the_most() {
		try {
			containerApp.mostKilometersTraveled();
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("The container that traveled the most is found")
	public void the_container_that_traveled_the_most_is_found() {
	    assertNull(exception);
	}
	
	@Then("No container found")
	public void no_container_found() {
	    assertNotNull(exception);
	}
	
	//
	//
	//
	// Most Journeys
	
	@When("Determining the container that went on the most journeys")
	public void determining_the_container_that_went_on_the_most_journeys() {
	    try {
	    	containerApp.mostJourneys();
	    } catch (Exception e) {
	    	exception = e;
	    }
	}

	@Then("The container that went on the most journeys is found")
	public void the_container_that_went_on_the_most_journeys_is_found() {
	    assertNull(exception);
	}
	//
	//
	//
	// Most Ports
	@When("Determining the container that visited the most ports")
	public void determining_the_container_that_visited_the_most_ports() {
		try {
	    	containerApp.mostPorts();
	    } catch (Exception e) {
	    	exception = e;
	    }
	}

	@Then("The container that visited the most ports is found")
	public void the_container_that_visited_the_most_ports_is_found() {
	    assertNull(exception);

	}
	
	//
	//
	//
	
	// Least Kilometers traveled
	
	@When("Determining the container that traveled the least")
	public void determining_the_container_that_traveled_the_least() {
		try {
			containerApp.leastKilometersTraveled();
		} catch (Exception e) {
			exception = e;
		}
	}
	
	@Then("The container that traveled the least is found")
	public void the_container_that_traveled_the_least_is_found() {
	    assertNull(exception);

	}
	
	//
	//
	//
	// Least Journeys

	@When("Determining the container that went on the least journeys")
	public void determining_the_container_that_went_on_the_least_journeys() {
		try {
			containerApp.leastJourneys();
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("The container that went on the least journeys is found")
	public void the_container_that_went_on_the_least_journeys_is_found() {
	    assertNull(exception);
	}
	
	//
	//
	//
	// Least Ports
	
	@When("Determining the container that visited the least ports")
	public void determining_the_container_that_visited_the_least_ports() {
		try {
			containerApp.leastPorts();
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("The container that visited the least ports is found")
	public void the_container_that_visited_the_least_ports_is_found() {
	    assertNull(exception);
	}

	//
	//
	//
	// Longest Journey

	@When("Determining the longest journey")
	public void determining_the_longest_journey() {
	    try {
			containerApp.longestJourney();
		} catch (Exception e) {
			exception = e;
		}

	}

	@Then("The longest journey is found")
	public void the_longest_journey_is_found() {
	    assertNull(exception);

	}

	//
	//
	//
	// Shortest Journey
	
	@Given("Internal information")
	public void internal_information(io.cucumber.datatable.DataTable dataTable) throws Exception {
		for (int i=1; i<dataTable.height(); i++) {
			this.times.add(dataTable.cell(i, 0));
		    this.locations.add(dataTable.cell(i, 1));
		    this.temperatures.add(Integer.parseInt(dataTable.cell(i, 2)));
		    this.humidities.add(Integer.parseInt(dataTable.cell(i, 3)));
		    this.pressures.add(Integer.parseInt(dataTable.cell(i, 4)));
		}
	    containerApp.updateJourney(container, times, locations, temperatures, humidities, pressures);
	}
	
	@When("Determining the shortest journey")
	public void determining_the_shortest_journey() {
	    try {
			containerApp.shortestJourney();
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("The shortest journey is found")
	public void the_shortest_journey_is_found() {
	    assertNull(exception);

	}

	@Then("No journey found")
	public void no_journey_found() {
	    assertNotNull(exception);
	}
	
	//
	//
	//
	
	// Client login
	private String username;
	private String password;
	
	@Given("Clients")
	public void clients(io.cucumber.datatable.DataTable dataTable) throws Exception {
		for (int i=1; i<dataTable.height(); i++) {
			containerApp.registerClient(dataTable.cell(i, 0),dataTable.cell(i, 1),dataTable.cell(i, 2),dataTable.cell(i, 3),dataTable.cell(i, 4));
		}
	}

	@Given("A username {string} and a password {string}")
	public void a_username_and_a_password(String username, String password) {
	    this.username = username;
	    this.password = password;
	}

	@When("Logging in")
	public void logging_in() {
	    try {
			containerApp.loggedInUser(username, password);
		} catch (Exception e) {
			exception = e;
		}
	}

	@Then("User is logged in")
	public void client_is_logged_in() {
	    assertNull(exception);
	}
	
	@Then("User is not logged in")
	public void client_is_not_logged_in() {
	    assertNotNull(exception);
	}

}