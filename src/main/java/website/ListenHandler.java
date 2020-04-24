package website;

import java.util.HashMap;

public class ListenHandler {
	private static HashMap<String, Listener> listeners = new HashMap<String, Listener>();
	
	public static Listener getListenerByKey(String key) {
		if(!(listeners.containsKey(key))) {
			createListener(key);
			
		}
		return listeners.get(key);
		
	}
	
	public static void createListener(String key) {
		listeners.put(key, new Listener());
		
	}
	
	
}
