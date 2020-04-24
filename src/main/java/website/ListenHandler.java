package website;

import java.util.HashMap;

public class ListenHandler {
	private static HashMap<String, Listener> listeners = new HashMap<String, Listener>();
	
	public static Listener getListenerByKey(String key) {
		return listeners.get(key);
		
	}
	
	public static void createListener(String key) {
		listeners.put(key, new Listener());
		
	}
}
