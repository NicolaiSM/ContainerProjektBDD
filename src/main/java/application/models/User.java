package application.models;

import java.util.HashMap;
import java.util.Map;

import application.data.Element;
import application.models.id.UserId;

public class User implements Element {
	
	private  Long id;
	
	public User() {
		id = UserId.newUserId();
	}
	
	
	protected Map<String,String> userInfo = new HashMap<>();

	public String get(String key) {
		return userInfo.get(key);
	}
	
	@Override
	public boolean hasKeyword(String... keywords) {
		for (String keyword : keywords) {
			if (userInfo.containsValue(keyword)) {
				return true;
			}
		}
		return false;
	}
	
	public Long getId() {
		return this.id;
	}
	
}
