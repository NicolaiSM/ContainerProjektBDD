package application.models;

import java.util.HashMap;
import java.util.Map;

import application.data.Elements;
import application.models.id.UserId;

public class User implements Elements {
	
	private  Long id;
	
	public User() {
		id = UserId.newUserId();
	}
	
	
	protected Map<String,String> clientInfo = new HashMap<>();

	public String get(String key) {
		return clientInfo.get(key);
	}
	
	public boolean hasKeyword(String key) {
		return clientInfo.containsValue(key);
	}
	
	@Override
	public boolean hasKeyword(String... keywords) {
		for (String keyword : keywords) {
			if (hasKeyword(keyword)) {
				return true;
			}
		}
		return false;
	}

	public void setClientInfo(String key, String value) {
		clientInfo.put(key, value);		
	}
	
	public Long getId() {
		return this.id;
	}


	
	
}
