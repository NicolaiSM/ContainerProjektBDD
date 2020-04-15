import java.util.*;

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
}
