package application;

import website.ListenHandler;


public class Hullahop {
	public static void main(String[] args) {
		System.out.println("test");
		ListenHandler.getListenerByKey("test").increment();
		System.out.println(ListenHandler.getListenerByKey("test").getCounter());
	}
}
