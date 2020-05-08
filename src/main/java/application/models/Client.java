package application.models;

import java.util.*;

import javax.persistence.*;

import application.data.QueryLinkedList;

public class Client extends User {
	
	public Client() {
		
	}
	
	public Client(String clientName) {
		clientInfo.put("clientName",clientName);

	}
	
	public Client(String clientName, String address, String contactPerson, String email, String password) {
		super();
		clientInfo.put("clientName",clientName);
		clientInfo.put("address", address);
		clientInfo.put("contactPerson", contactPerson);
		clientInfo.put("email", email);
		clientInfo.put("password",password);
	}

	private List<Container> clientContainers = new QueryLinkedList<Container>();

	public List<Container> getClientContainers() {
		return clientContainers;
	}

	

	
	
	
}
