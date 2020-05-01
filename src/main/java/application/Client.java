package application;

import java.util.*;

import javax.persistence.*;

public class Client implements Elements {
	
	private long id;
	
	private Map<String,String> clientInfo = new HashMap<>();


	public Client()  {
		
	}
	public Client(String clientName, String address, String contactPerson, String email, String password) {
		clientInfo.put("clientName",clientName);
		clientInfo.put("address", address);
		clientInfo.put("contactPerson", contactPerson);
		clientInfo.put("email", email);
		clientInfo.put("password",password);
	}

	public boolean hasKeyword(String key) {
		return clientInfo.containsValue(key);
	}
	
	//Setters
	public void setClientInfo(String key, String value) {
		clientInfo.put(key, value);
	}
	
	//Getters
	public String getClientName() {
		return clientInfo.get("clientName");
	}
	public String get(String key) {
		return clientInfo.get(key);
	}
	
	@Override
	public boolean hasKeyword(String... keywords) {
		// TODO Auto-generated method stub
		return false;
	}
	

	
}
