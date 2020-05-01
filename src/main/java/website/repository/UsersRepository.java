package website.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import application.AdvancedQueryLinkedList;
import application.Client;
import website.model.UserForm;


public class UsersRepository {
	
	private static AdvancedQueryLinkedList<Client> clientListInstance = null;
	
	public static AdvancedQueryLinkedList<Client> getClientList() {
		if (clientListInstance == null) {
			clientListInstance = new AdvancedQueryLinkedList<Client>();
		}

		return clientListInstance;
	}
 
}
