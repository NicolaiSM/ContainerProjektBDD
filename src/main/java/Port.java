import java.util.ArrayList;
import java.util.List;

public class Port {
	String port;
	List<Container> containers = new ArrayList<Container>();
	
	public Port(String port) {
		this.port = port;
	}

	public String getPort() {
		return port;
	}
	
	public void addContainer(Container container) {
		containers.add(container);
	}

	
}
