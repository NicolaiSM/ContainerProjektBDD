package application;

public class Hulahop {
//
//	public static void main(String[] args) {
//		SingletonLists.getClientList().add(new Client());
//		SingletonLists.getClientList().add(new Client("a","a","a","a","a"));
//		SingletonLists.getClientList().add(new Client());
//		SingletonLists.getClientList().add(new Client());
//		SingletonLists.getClientList().add(new Client());
//		System.out.println(SingletonLists.getClientList().size());
//		System.out.println(SingletonLists.getClientList().anyMatch("a"));
//	}

	public static void main(String[] args) {

		ContainerApp.getInstance().ports.add(new Port("test"));
		ContainerApp.getInstance().ports.add(new Port("test2"));
		ContainerApp.getInstance().ports.add(new Port("test"));
		ContainerApp.getInstance().ports.contains("hulahop");
		
		System.out.println(ContainerApp.getInstance().ports);
		System.out.println(ContainerApp.getInstance().ports.contains(new Port("test")));
		
		
		

	}

}
