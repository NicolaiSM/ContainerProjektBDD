package application;

public class Hulahop {

	public static void main(String[] args) {

		ContainerApp.getInstance().ports.add(new Port("test"));
		ContainerApp.getInstance().ports.add(new Port("test2"));
		ContainerApp.getInstance().ports.add(new Port("test"));
		ContainerApp.getInstance().ports.contains("hulahop");
		
		System.out.println(ContainerApp.getInstance().ports);
		System.out.println(ContainerApp.getInstance().ports.contains(new Port("test")));
		
		
		

	}

}
