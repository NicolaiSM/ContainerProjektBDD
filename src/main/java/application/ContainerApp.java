package application;

import java.util.*;
import java.util.stream.*;

import application.data.Element;
import application.data.QueryHashSet;
import application.data.QueryLinkedList;
import application.models.Client;
import application.models.Container;
import application.models.ContainerAdvancedQuery;
import application.models.Journey;
import application.models.LogisticCompany;
import application.models.Pair;
import application.models.Port;
import application.models.User;

public class ContainerApp {
	
	public ContainerApp() {
		users.add(new LogisticCompany("admin", "admin"));	
	}
	
	
	private static ContainerApp instance = null;
	
	public static ContainerApp getInstance() {
		if(instance == null) {
			instance = new ContainerApp();
		}
		return instance;
	}
	
	private QueryHashSet<Port> ports = new QueryHashSet<Port>();
	private QueryHashSet<User> users = new QueryHashSet<User>();
	private QueryLinkedList<Journey> journeys = new QueryLinkedList<Journey>();
	private QueryLinkedList<Container> containers = new QueryLinkedList<Container>();
	
	public Client registerClient(String clientName, String address, String contactPerson, String email, String password) throws Exception {
		Client a = new Client(clientName, address, contactPerson, email, password);
		if (!users.add(a)) {
			
			throw new Exception("Client already registered");
		}
		return a;
	}
	
	public User loginUser(String username, String password) throws Exception {
		User user = users.findElement(username);
		if (user == null || !user.get("clientName").equals(username)) {
			throw new Exception("Username is incorrect");
		}
		else if (!user.get("password").equals(password)) {
			throw new Exception("Password is incorrect");
		}
		else {
			return user;
		}
	}
	
	public List<User> findClient(String... keywords) throws Exception {
		List<User> resultingClients =  users.findElements(keywords);
		if (resultingClients.isEmpty()) {
			throw new Exception("No clients found");

		}
		return resultingClients;
	}


	public void updateClient(Client client, String key, String value) throws Exception {
		if (key.equals("clientName")) {
			if (isClientAvailable(value)) {
				client.setClientInfo(key, value);
			}
			else {
				throw new Exception("New client name already exists");
			}
		} else {
			client.setClientInfo(key, value);
		}
	}

	private boolean isClientAvailable(String clientName) {
		return !users.contains(new Client(clientName));
	}

	public List<Port> findPorts(String... keywords) {
		return ports.findElements(keywords);
	}

	public void registerPort(String port) throws Exception {
		if (!ports.add(new Port(port))) {
			throw new Exception("Port is already registered");
		}
	}

	private boolean isPortRegistered(String port) {
		return ports.contains(new Port(port));
		
	}
	
	public void createContainer(String port) throws Exception {
		if (!isPortRegistered(port)) {
			throw new Exception("Port is not registered");
		}
		Port p = ports.findElement(port);
		Container container = new Container(p);
		containers.add(container);
		p.addContainer(container);
		
	}

	public void registerContainer(String portOfOrigin, String destination, String content, Client client) throws Exception {
		if (!isPortRegistered(portOfOrigin) || !isPortRegistered(destination) || portOfOrigin.equals(destination)) {
			throw new Exception ("Ports are not valid");
		}
		Port startport = ports.findElement(portOfOrigin);
		Port finalport = ports.findElement(destination);
		Container availableContainer = getAvailableContainer(startport);
		if(availableContainer == null) {
			throw new Exception ("No available containers in port");
		}
		
		Journey journey = new Journey (startport, finalport, content, client);
		((Client) client).addJourney(journey);
		journeys.add(journey);
		availableContainer.setJourney(journey);
		availableContainer.getJourneys().add(journey);
		((Client) client).getClientContainers().add(availableContainer);
				
	}

	private Container getAvailableContainer(Port startport) {
		return containers.stream().filter((container)->container.isContainerAvailable(startport)).findFirst().orElse(null);
	}

	public List<Container> findContainer(String... keywords) throws Exception {
		List<Container> resultingContainers = containers.findElements(keywords);
		if (resultingContainers.isEmpty()) {
			throw new Exception("No containers found");

		}
		return resultingContainers;
	}


	public List<Journey> findJourney(String... keywords) throws Exception {
		List<Journey> resultingJourneys = journeys.findElements(keywords);
		if (resultingJourneys.isEmpty()) {
			throw new Exception("No journeys found");
			
		}
		
		return resultingJourneys;
	}

	public void updateJourney(Container container, List<String> times, List<String> locations, List<Integer> temperatures, List<Integer> humidities, List<Integer> pressures) throws Exception {
		if (!container.hasJourney()) {
			throw new Exception ("Container is not on a journey");
		}
		if (isLocationNotValid(locations)) {
			throw new Exception ("Location is not valid");
		}
		container.updateJourney(times, convertLocations(locations), temperatures, humidities, pressures);
	}

	private List<Port> convertLocations(List<String> locations) {
		return locations.stream().map((location)->ports.findElement(location)).collect(Collectors.toList());
	}

	private boolean isLocationNotValid(List<String> locations) {
		return locations.stream().anyMatch((location)->!isPortRegistered(location));
	}
	
	
	
	
	
	

	public Pair<Container,Integer> mostKilometersTraveled() throws Exception {
		if (!containers.isEmpty()) {
			ContainerAdvancedQuery containerAdvancedQuery = new ContainerAdvancedQuery();
			Container container = Collections.max(containers,Comparator.comparing(c -> containerAdvancedQuery.getDistance(c)));
			return new Pair<Container,Integer>(container,containerAdvancedQuery.getDistance(container));
		}
		throw new Exception("No containers exist");
	}

	public Pair<Container,Integer> mostJourneys() throws Exception {
		if (!containers.isEmpty()) {
			ContainerAdvancedQuery containerAdvancedQuery = new ContainerAdvancedQuery();
			Container container = Collections.max(containers,Comparator.comparing(c -> containerAdvancedQuery.getNumberOfJourneys(c)));
			return new Pair<Container,Integer>(container,containerAdvancedQuery.getNumberOfJourneys(container));
		}
		throw new Exception("No containers exist");
	}
	
	public Pair<Container,Integer> mostPorts() throws Exception {
		if (!containers.isEmpty()) {
			ContainerAdvancedQuery containerAdvancedQuery = new ContainerAdvancedQuery();
			Container container = Collections.max(containers,Comparator.comparing(c -> containerAdvancedQuery.getNumberOfPorts(c)));
			return new Pair<Container,Integer>(container,containerAdvancedQuery.getNumberOfPorts(container));
		}
		throw new Exception("No containers exist");
	}

	public Pair<Container,Integer> leastKilometersTraveled() throws Exception {
		if (!containers.isEmpty()) {
			ContainerAdvancedQuery containerAdvancedQuery = new ContainerAdvancedQuery();
			Container container = Collections.min(containers,Comparator.comparing(c -> containerAdvancedQuery.getDistance(c)));
			return new Pair<Container,Integer>(container,containerAdvancedQuery.getDistance(container));
		}
		throw new Exception("No containers exist");		
	}

	public Pair<Container,Integer> leastJourneys() throws Exception {
		if (!containers.isEmpty()) {
			ContainerAdvancedQuery containerAdvancedQuery = new ContainerAdvancedQuery();
			Container container = Collections.min(containers,Comparator.comparing(c -> containerAdvancedQuery.getNumberOfJourneys(c)));
			return new Pair<Container,Integer>(container,containerAdvancedQuery.getNumberOfJourneys(container));
		}
		throw new Exception("No containers exist");			
	}

	public Pair<Container,Integer> leastPorts() throws Exception {
		if (!containers.isEmpty()) {
			ContainerAdvancedQuery containerAdvancedQuery = new ContainerAdvancedQuery();
			Container container = Collections.min(containers,Comparator.comparing(c -> containerAdvancedQuery.getNumberOfPorts(c)));
			return new Pair<Container,Integer>(container,containerAdvancedQuery.getNumberOfPorts(container));
		}
		throw new Exception("No containers exist");			
	}

	public Pair<Journey,Integer> longestJourney() throws Exception {
		if (!journeys.isEmpty()) {
			Journey journey = Collections.max(journeys,Comparator.comparing(j -> j.getDistance()));
			return new Pair<Journey,Integer>(journey,journey.getDistance());
			
		}
		throw new Exception("No journeys exist");			
	}
	public Pair<Journey,Integer> shortestJourney() throws Exception {
		if (!journeys.isEmpty()) {
			Journey journey = Collections.min(journeys,Comparator.comparing(j -> j.getDistance()));
			return new Pair<Journey,Integer>(journey,journey.getDistance());
		}
		throw new Exception("No journeys exist");			
	}

	
	public QueryLinkedList<Container> getContainers() {
		return containers;
	}

	public Collection<? extends Object> getPorts() {
		return ports;
	}

	public Collection<? extends Object> getUsers() {
		return users;
	}
	
	
}

