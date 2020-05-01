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
	
	
	private List<Client> clients = new ArrayList<Client>();
	public Set<Port> ports = new PortHashSet();
	public List<Container> containers = new ArrayList<Container>();
	private List<Journey> journeys = new ArrayList<Journey>();
	
	public void registerClient(String clientName, String address, String contactPerson, String email, String password) throws Exception {
		if (isClientRegistered(clientName)) {
			throw new Exception("Client already registered");
		}
		clients.add(new Client(clientName, address, contactPerson, email, password));
	}
	
	public Client loggedInClient(String clientName, String password) throws Exception {
		Client c = clients.stream().filter((client)->client.get("clientName").equals(clientName)).findFirst().orElse(null);
		if (c==null) {
			throw new Exception("Username is incorrect");
		}
		else if (!c.get("password").equals(password)) {
			throw new Exception("Password is incorrect");
		}
		else {
			return c;
		}
	}
	
	public boolean isClientRegistered(String clientName) {
		return clients.stream().anyMatch((client)->client.getClientName().equals(clientName));
	}
	
	public List<Client> findClient(String... keywords) throws Exception {
		List<Client> resultingClients = getClients(keywords);
		if (resultingClients.isEmpty()) {
			throw new Exception("No clients found");

		}
		return resultingClients;
	}

	private List<Client> getClients(String... keywords) {
		return clients.stream().filter((client)->clientHasKeyword(keywords, client)).collect(Collectors.toList());
	}

	private boolean clientHasKeyword(String[] keywords, Client client) {
		for(String keyword: keywords) {
			if( client.hasKeyword(keyword)) {
				return true;
			}
		}
		return false;
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
		for (Client client : clients) {
		}
		return clients.stream().noneMatch((client)->client.getClientName().equals(clientName));
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

	public void registerContainer(String portOfOrigin, String destination, String content, Client client) throws Exception {
		Port startport = findPort(portOfOrigin);
		Port finalport = findPort(destination);
		
		if (ports.contains(portOfOrigin)|| ports.contains(destination)) {
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
//			ArrayList containerMost = new ArrayList();
			Container container = Collections.max(containers,Comparator.comparing(c -> c.getDistance()));
			return new Pair<Container,Integer>(container,container.getDistance());
//			containerMost.add(container);
//			containerMost.add(container.getDistance());
//			return containerMost;
		}
		throw new Exception("No containers exist");
	}

	public Pair<Container,Integer> mostJourneys() throws Exception {
		if (!containers.isEmpty()) {
//			ArrayList containerMost = new ArrayList();
			Container container = Collections.max(containers,Comparator.comparing(c -> c.getNumberOfJourneys()));
			return new Pair<Container,Integer>(container,container.getDistance());
//			containerMost.add(container);
//			containerMost.add(container.getNumberOfJourneys());
//			return containerMost;
		}
		throw new Exception("No containers exist");
	}
	
	public Pair<Container,Integer> mostPorts() throws Exception {
		if (!containers.isEmpty()) {
//			ArrayList containerMost = new ArrayList();
			Container container = Collections.max(containers,Comparator.comparing(c -> c.getNumberOfPorts()));
			return new Pair<Container,Integer>(container,container.getDistance());
//			containerMost.add(container);
//			containerMost.add(container.getNumberOfPorts());
//			return containerMost;
		}
		throw new Exception("No containers exist");
	}

	public Pair<Container,Integer> leastKilometersTraveled() throws Exception {
		if (!containers.isEmpty()) {
//			ArrayList containerLeast = new ArrayList();
			Container container = Collections.min(containers,Comparator.comparing(c -> c.getDistance()));
			return new Pair<Container,Integer>(container,container.getDistance());
//			containerLeast.add(container);
//			containerLeast.add(container.getDistance());
//			return containerLeast;
		}
		throw new Exception("No containers exist");		
	}

	public Pair<Container,Integer> leastJourneys() throws Exception {
		if (!containers.isEmpty()) {
//			ArrayList containerLeast = new ArrayList();
			Container container = Collections.min(containers,Comparator.comparing(c -> c.getNumberOfJourneys()));
			return new Pair<Container,Integer>(container,container.getDistance());
//			containerLeast.add(container);
//			containerLeast.add(container.getNumberOfJourneys());
//			return containerLeast;
		}
		throw new Exception("No containers exist");			
	}

	public Pair<Container,Integer> leastPorts() throws Exception {
		if (!containers.isEmpty()) {
//			ArrayList containerLeast = new ArrayList();
			Container container = Collections.min(containers,Comparator.comparing(c -> c.getNumberOfPorts()));
			return new Pair<Container,Integer>(container,container.getDistance());
//			containerLeast.add(container);
//			containerLeast.add(container.getNumberOfPorts());
//			return containerLeast;
		}
		throw new Exception("No containers exist");			
	}

	public Pair<Journey,Integer> longestJourney() throws Exception {
		if (!journeys.isEmpty()) {
//			ArrayList longestJourney = new ArrayList();
			Journey journey = Collections.max(journeys,Comparator.comparing(j -> j.getDistance()));
			return new Pair<Journey,Integer>(journey,journey.getDistance());
			
//			longestJourney.add(journey);
//			longestJourney.add(journey.getDistance());
//			return longestJourney;
		}
		throw new Exception("No journeys exist");			
	}
	public Pair<Journey,Integer> shortestJourney() throws Exception {
		if (!journeys.isEmpty()) {
//			ArrayList shortestJourney = new ArrayList();
			Journey journey = Collections.min(journeys,Comparator.comparing(j -> j.getDistance()));
			return new Pair<Journey,Integer>(journey,journey.getDistance());
//			shortestJourney.add(journey);
//			shortestJourney.add(journey.getDistance());
//			return shortestJourney;
		}
		throw new Exception("No journeys exist");			
	}
	

	
}

