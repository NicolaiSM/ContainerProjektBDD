import java.util.*;
import java.util.stream.*;

public class ContainerApp {
	List<Client> clients = new ArrayList<Client>();
	Set<Port> ports = new HashSet<Port>();
	List<Container> containers = new ArrayList<Container>();
	List<Journey> journeys = new ArrayList<Journey>();
	
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
		List<Client> resultingClients = clients.stream().filter((client)->clientHasKeyword(keywords, client)).collect(Collectors.toList());
		if (resultingClients.isEmpty()) {
			throw new Exception("No clients found");

		}
		return resultingClients;
	}

	private boolean clientHasKeyword(List<String> keywords, Client client) {
		return keywords.stream().anyMatch((keyword)->client.hasKeyword(keyword));
	}

	public void updateClient(Client client, String clientName, String address, String contactPerson, String email) throws Exception {
		if (changeInClientName(client, clientName) & isClientRegistered(clientName)) {
			throw new Exception("New client name already exists");
		}
		setClientInformation(client, clientName, address, contactPerson, email);
	}

	private void setClientInformation(Client client, String clientName, String address, String contactPerson, String email) {
		client.setClientName(clientName);
		client.setAddress(address);
		client.setContactPerson(contactPerson);
		client.setEmail(email);
	}

	private boolean changeInClientName(Client client, String clientName) {
		return !client.getClientName().equals(clientName);
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
		List <Container> availableContainers = getAvailableContainers(startport);
		if(availableContainers.isEmpty()) {
			throw new Exception ("No available containers in port");
		}
		Journey journey = new Journey (portOfOrigin, destination, content, client);
		journeys.add(journey);
		availableContainers.get(0).setJourney(journey);
		
				
	}

	private List<Container> getAvailableContainers(Port startport) {
		
		return containers.stream().filter((container)->container.isContainerAvailable(startport)).collect(Collectors.toList());
	}
}
