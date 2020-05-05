package website.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import application.data.QueryLinkedList;
import application.models.Client;
import website.model.UserForm;


public class UsersRepository {
	
	private static QueryLinkedList<Client> clientListInstance = null;
	
	public static QueryLinkedList<Client> getClientList() {
		if (clientListInstance == null) {
			clientListInstance = new QueryLinkedList<Client>();
		}

		return clientListInstance;
	}
 
}
