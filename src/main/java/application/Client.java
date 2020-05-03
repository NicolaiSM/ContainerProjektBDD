package application;

import java.util.*;

import javax.persistence.*;

public class Client extends User implements Elements{
		
	private long id;
	
	

	public Client()  {
		
	}
	public Client(String clientName, String address, String contactPerson, String email, String password) {
		clientInfo.put("clientName",clientName);
		clientInfo.put("address", address);
		clientInfo.put("contactPerson", contactPerson);
		clientInfo.put("email", email);
		clientInfo.put("password",password);
	}

//	public boolean hasKeyword(String key) {
//		return clientInfo.containsValue(key);
//	}
//	
//	//Setters
//	public void setClientInfo(String key, String value) {
//		clientInfo.put(key, value);
//	}
//	
//
//	
//	@Override
//	public boolean hasKeyword(String... keywords) {
//		for (String keyword : keywords) {
//			if (hasKeyword(keyword)) {
//				return true;
//			}
//		}
//		return false;
//	}
	

	
}
