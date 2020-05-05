package application.data;

import java.util.HashSet;
import java.util.Set;

import application.models.Port;

public class SingletonPortsHashSet {
	
	private static QueryHashSet<Port> ports = null;
	
	public static QueryHashSet<Port> getInstance() {
		if(ports == null) {
			ports = new QueryHashSet<Port>();
		}
		return ports;
	}
	
	
}
