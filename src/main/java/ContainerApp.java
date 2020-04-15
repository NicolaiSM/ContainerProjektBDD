import java.util.*;
import java.util.stream.*;

public class ContainerApp {
	List<Client> clients = new ArrayList<Client>();
	
	public void registerClient(String clientName, String address, String contactPerson, String email) throws Exception {
		if (isClientRegistered(clientName)) {
			throw new Exception("Client already registered");
		}
		clients.add(new Client(clientName, address, contactPerson, email));
		
	}
	
	public boolean isClientRegistered(String clientName) {
		return clients.stream().anyMatch((Client)->Client.getClientName().equals(clientName));
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
}
