import java.util.*;
import java.util.stream.*;

public class ContainerApp {
	List<Client> clients = new ArrayList<Client>();
	List<Port> ports = new ArrayList<Port>();
	
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
	
	private Port isPort(String port) {
		return ports.stream().filter((Port)->Port.getPort().equals(port)).findFirst().orElse(null);
	}

	public Journey createJourney(Client client, String portOfOrigin, String destination, String content) {
		Port portOfOriginNew = isPort(portOfOrigin);
		Port destinationNew = isPort(destination);
				
		if (!(portOfOriginNew == null) && !(destinationNew == null)) {
			return new Journey(client, portOfOriginNew, destinationNew, content);
		}
		
		return null;
		
	}
	
	
	
	
	
	
	
	
}
