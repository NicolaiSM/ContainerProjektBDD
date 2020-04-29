package application;

public class Hulahop {

	public static void main(String[] args) {
		SingletonLists.getClientList().add(new Client());
		SingletonLists.getClientList().add(new Client("a","a","a","a","a"));
		SingletonLists.getClientList().add(new Client());
		SingletonLists.getClientList().add(new Client());
		SingletonLists.getClientList().add(new Client());
		System.out.println(SingletonLists.getClientList().size());
		System.out.println(SingletonLists.getClientList().anyMatch("a"));
	}

}
