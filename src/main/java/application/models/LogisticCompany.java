package application.models;

import java.util.HashMap;
import java.util.Map;

public class LogisticCompany extends User {

	public LogisticCompany(String username, String password) {
		userInfo.put("clientName",username);
		userInfo.put("password",password);
	}
	

}
