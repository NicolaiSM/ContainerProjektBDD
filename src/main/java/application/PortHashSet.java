package application;

import java.util.HashSet;

public class PortHashSet extends HashSet<Port>{

	
	public boolean contains(String string) {
		return contains(new Port(string));
		
	}
	
}
