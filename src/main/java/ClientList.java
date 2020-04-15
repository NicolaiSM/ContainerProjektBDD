import java.util.*;

public class ClientList {
	ArrayList<Client> clientList = new ArrayList<Client>();
	
	public boolean addUniqueClient(String clientName, String address, String contactPerson, String email) {
		if (isUniqueClient(clientName)) {
			clientList.add(new Client(clientName, address, contactPerson, email));
			return true;
		}
		return false;
	}
	public boolean isUniqueClient(String clientName) {
		return !clientList.stream().anyMatch((Client)->Client.getClientName().equals(clientName));
	}
}
