package application;

public class SingletonLists {
	
	

	
	private static AdvancedQueryLinkedList<Container> containerListInstance = null;
	
	public static AdvancedQueryLinkedList<Container> getContainerList() {
		if (containerListInstance == null) {
			containerListInstance = new AdvancedQueryLinkedList<Container>();
		}
		
		return containerListInstance;
	}
	
	private static AdvancedQueryLinkedList<Port> portListInstance = null;
	
	public static AdvancedQueryLinkedList<Port> getPortList() {
		if (portListInstance == null) {
			portListInstance = new AdvancedQueryLinkedList<Port>();
		}
		
		return portListInstance;
	}
	
}
