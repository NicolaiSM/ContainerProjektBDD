package application;

import java.util.*;
import java.util.stream.*;

import website.model.Client;
import website.model.Container;
import website.model.Journey;
import website.model.Port;

public class ContainerApp {
	
	private static ContainerApp instance = null;
	
	public static ContainerApp getInstance() {
		if(instance == null) {
			instance = new ContainerApp();
		}
		return instance;
	}
	
	
	private List<Client> clients = new ArrayList<Client>();
	private Set<Port> ports = new HashSet<Port>();
	public List<Container> containers = new ArrayList<Container>();
	private List<Journey> journeys = new ArrayList<Journey>();
	
	public void registerClient(String clientName, String address, String contactPerson, String email) throws Exception {
		if (isClientRegistered(clientName)) {
			throw new Exception("Client already registered");
		}
		clients.add(new Client(clientName, address, contactPerson, email));
	}
	
	public boolean isClientRegistered(String clientName) {
		return clients.stream().anyMatch((client)->client.getClientName().equals(clientName));
	}
	
	public List<Client> findClient(List<String> keywords) throws Exception {
		List<Client> resultingClients = getClients(keywords);
		if (resultingClients.isEmpty()) {
			throw new Exception("No clients found");

		}
		return resultingClients;
	}

	private List<Client> getClients(List<String> keywords) {
		return clients.stream().filter((client)->clientHasKeyword(keywords, client)).collect(Collectors.toList());
	}

	private boolean clientHasKeyword(List<String> keywords, Client client) {
		return keywords.stream().anyMatch((keyword)->client.hasKeyword(keyword));
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
		return clients.stream().noneMatch((client)->client.getClientName().equals(clientName));
	}



	public void registerPort(String port) throws Exception {
		if (portIsRegistered(port)) {
			throw new Exception("Port is already registered");
		} 
		ports.add(new Port(port));
	}

	private boolean portIsRegistered(String port) {
		return ports.stream().anyMatch((Port)->Port.getPort().equals(port));
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

	public List<Container> findContainer(List<String> keywords) throws Exception {
		List<Container> resultingContainers = getContainers(keywords);
		if (resultingContainers.isEmpty()) {
			throw new Exception("No containers found");

		}
		return resultingContainers;
	}

	private List<Container> getContainers(List<String> keywords) {
		return containers.stream().filter((container)->containerHasKeyword(keywords, container)).collect(Collectors.toList());
	}

	private boolean containerHasKeyword(List<String> keywords, Container container) {
		return keywords.stream().anyMatch((keyword)->container.hasKeyword(keyword));
	}

	public List<Journey> findJourney(List<String> keywords) throws Exception {
		List<Journey> resultingJourneys = getJourneys(keywords);
		if (resultingJourneys.isEmpty()) {
			throw new Exception("No journeys found");
			
		}
		
		return resultingJourneys;
	}

	private List<Journey> getJourneys(List<String> keywords) {
		return journeys.stream().filter((journey)->journeyHasKeyword(keywords, journey)).collect(Collectors.toList());
	}

	private boolean journeyHasKeyword(List<String> keywords, Journey journey) {
		return keywords.stream().anyMatch((keyword)->journey.hasKeyword(keyword));
	}

	public void updateJourney(Container container, List<String> times, List<String> locations, List<Integer> temperatures, List<Integer> humidities, List<Integer> pressures) throws Exception {
		if (container.hasNoJourney()) {
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

		
}
