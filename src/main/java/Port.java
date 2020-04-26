import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Port {
	private final String port;
	private List<Container> containers = new ArrayList<Container>();
	private final int xCoordinate;
	private final int yCoordinate;
	
	public Port(String port) {
		this.port = port;
		Random rand = new Random();
		xCoordinate = rand.nextInt((20000-(-20000))+1)+(-20000);
		yCoordinate = rand.nextInt((20000-(-20000))+1)+(-20000);
	}

	public String getPort() {
		return port;
	}
	
	public void addContainer(Container container) {
		containers.add(container);
	}

	public double getXCoordinate() {
		return (double) xCoordinate;
	}

	public double getYCoordinate() {
		return (double) yCoordinate;
	}

	
}
