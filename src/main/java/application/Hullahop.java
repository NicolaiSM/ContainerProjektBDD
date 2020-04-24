package application;

import website.ListenHandler;


public class Hullahop {
	public static void main(String[] args) {
		System.out.println("test");
		ListenHandler.createListener("login");
		ListenHandler.getListener("login").increment();
		
		System.out.println(ListenHandler.getListener("login").getCounter());
	}
}
