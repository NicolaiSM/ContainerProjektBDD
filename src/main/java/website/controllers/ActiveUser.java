package website.controllers;

import application.models.User;

public class ActiveUser {

	private static User user = null;

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		ActiveUser.user = user;
	}
	
	
}
