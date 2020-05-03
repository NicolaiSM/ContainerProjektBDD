package application;

import java.util.*;
import java.util.stream.*;

public class ContainerApp {
	
	private static ContainerApp instance = null;
	
	public static ContainerApp getInstance() {
		if(instance == null) {
			instance = new ContainerApp();
		}
		return instance;
	}
	private Set<User> users = new HashSet<User>(Arrays.asList(new LogisticCompany("Mærsk","1234")));
	private Set<Port> ports = new HashSet<Port>();
	private List<Container> containers = new ArrayList<Container>();
	private List<Journey> journeys = new ArrayList<Journey>();
	
	public void registerClient(String clientName, String address, String contactPerson, String email, String password) throws Exception {
		if (isClientRegistered(clientName)) {
			throw new Exception("Client already registered");
		}
		users.add(new Client(clientName, address, contactPerson, email, password));
	}
	public User loggedInUser(String username, String password) throws Exception {
		Optional<User> u = users.stream().filter((user)->user.get("clientName").equals(username)).findFirst();
		if (u.isEmpty()) {
			throw new Exception("Username is incorrect");
		}
		else if (!u.get().get("password").equals(password)) {
			throw new Exception("Password is incorrect");
		}
		else {
			return u.get();
		}
	}
	
	private boolean isClientRegistered(String clientName) {
		return users.stream().anyMatch((client)->client.get("clientName").equals(clientName));
	}
	
	public List<User> findClient(String... keywords) throws Exception {
		List<User> resultingClients = getClients(keywords);
		if (resultingClients.isEmpty()) {
			throw new Exception("No clients found");

		}
		return resultingClients;
	}

	private List<User> getClients(String... keywords) {
		return users.stream().filter((client)->client.hasKeyword(keywords)).collect(Collectors.toList());
	}

	public void updateClient(User client, String key, String value) throws Exception {
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
		return users.stream().noneMatch((client)->client.get("clientName").equals(clientName));
	}



	public void registerPort(String port) throws Exception {
		if (portIsRegistered(port)) {
			throw new Exception("Port is already registered");
		}
		else {
			ports.add(new Port(port));
		}
		
	}

	private boolean portIsRegistered(String port) {
		return ports.contains(new Port(port));
		
	}
	

	public Port findPort(String port){
		
		return ports.stream().filter((Port)->Port.getPort().equals(port)).findFirst().orElse(null);
	}
	
	public void createContainer(String port) throws Exception {
		if (!portIsRegistered(port)) {
			throw new Exception("Port is not registered");
		}
		Port p = findPort(port);
		Container container = new Container(p);
		containers.add(container);
		p.addContainer(container);
		
	}

	public void registerContainer(String portOfOrigin, String destination, String content, User client) throws Exception {
		Port startport = findPort(portOfOrigin);
		Port finalport = findPort(destination);
		
		if (startport == null || finalport == null) {
			throw new Exception ("No valid ports");
		}
		
		Container availableContainer = getAvailableContainer(startport);
		if(availableContainer == null) {
			throw new Exception ("No available containers in port");
		}
		Journey journey = new Journey (startport, finalport, content, client);
		journeys.add(journey);
		availableContainer.setJourney(journey);
		
				
	}

	private Container getAvailableContainer(Port startport) {
		
		return containers.stream().filter((container)->container.isContainerAvailable(startport)).findFirst().orElse(null);
	}

	public List<Container> findContainer(String... keywords) throws Exception {
		List<Container> resultingContainers = getContainers(keywords);
		if (resultingContainers.isEmpty()) {
			throw new Exception("No containers found");

		}
		return resultingContainers;
	}

	private List<Container> getContainers(String[] keywords) {
		return containers.stream().filter((container)->containerHasKeyword(keywords, container)).collect(Collectors.toList());
	}

	private boolean containerHasKeyword(String[] keywords, Container container) {
		for(String keyword: keywords) {
			if( container.hasKeyword(keyword)) {
			 return true;	
			}
		}
		
		return false;
	}

	public List<Journey> findJourney(String... keywords) throws Exception {
		List<Journey> resultingJourneys = getJourneys(keywords);
		if (resultingJourneys.isEmpty()) {
			throw new Exception("No journeys found");
			
		}
		
		return resultingJourneys;
	}

	private List<Journey> getJourneys(String[] keywords) {
		return journeys.stream().filter((journey)->journeyHasKeyword(keywords, journey)).collect(Collectors.toList());
	}

	private boolean journeyHasKeyword(String[] keywords, Journey journey) {
		for(String keyword : keywords) {
			if(journey.hasKeyword(keyword)) {
				return true;
			}
			
		}
		return false;
		
	}

	public void updateJourney(Container container, List<String> times, List<String> locations, List<Integer> temperatures, List<Integer> humidities, List<Integer> pressures) throws Exception {
		if (!container.hasJourney()) {
			throw new Exception ("Container is not on a journey");
		}
		if (isLocationNotValid(locations)) {
			throw new Exception ("Location is not valid");
		}
		List<Port> locationsAsPorts = convertLocations(locations);
		container.updateJourney(times, locationsAsPorts, temperatures, humidities, pressures);
	}

	private List<Port> convertLocations(List<String> locations) {
		
		return locations.stream().map((location)->findPort(location)).collect(Collectors.toList());
	}

	private boolean isLocationNotValid(List<String> locations) {
		return locations.stream().anyMatch((location)->!portIsRegistered(location));
	}

	public Pair<Container,Integer> mostKilometersTraveled() throws Exception {
		if (!containers.isEmpty()) {
			Container container = Collections.max(containers,Comparator.comparing(c -> c.getDistance()));
			return new Pair<Container,Integer>(container,container.getDistance());
		}
		throw new Exception("No containers exist");
	}

	public Pair<Container,Integer> mostJourneys() throws Exception {
		if (!containers.isEmpty()) {
			Container container = Collections.max(containers,Comparator.comparing(c -> c.getNumberOfJourneys()));
			return new Pair<Container,Integer>(container,container.getDistance());
		}
		throw new Exception("No containers exist");
	}
	
	public Pair<Container,Integer> mostPorts() throws Exception {
		if (!containers.isEmpty()) {
			Container container = Collections.max(containers,Comparator.comparing(c -> c.getNumberOfPorts()));
			return new Pair<Container,Integer>(container,container.getDistance());
		}
		throw new Exception("No containers exist");
	}

	public Pair<Container,Integer> leastKilometersTraveled() throws Exception {
		if (!containers.isEmpty()) {
			Container container = Collections.min(containers,Comparator.comparing(c -> c.getDistance()));
			return new Pair<Container,Integer>(container,container.getDistance());
		}
		throw new Exception("No containers exist");		
	}

	public Pair<Container,Integer> leastJourneys() throws Exception {
		if (!containers.isEmpty()) {
			Container container = Collections.min(containers,Comparator.comparing(c -> c.getNumberOfJourneys()));
			return new Pair<Container,Integer>(container,container.getDistance());
		}
		throw new Exception("No containers exist");			
	}

	public Pair<Container,Integer> leastPorts() throws Exception {
		if (!containers.isEmpty()) {
			Container container = Collections.min(containers,Comparator.comparing(c -> c.getNumberOfPorts()));
			return new Pair<Container,Integer>(container,container.getDistance());
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
	public Set<Port> getPorts() {
		return ports;
	}
	public List<Container> getContainers() {
		return containers;
	}
	
	container.journey.content;
	

	
}

