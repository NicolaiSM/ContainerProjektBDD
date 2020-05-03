package application;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.persistence.*;



public class Port {
	
	@Id
	@Column(name = "port")
	private final String port;
	
	private List<Container> containers = new LinkedList<Container>();
	
	private final int xCoordinate;

	private final int yCoordinate;
	
	@Override
	public boolean equals(Object object) {
		return this.port.equals(((Port) object).getPort());
		
	}
	
	@Override
	public int hashCode() {
		return port.hashCode();
	}
	

	
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

	public void removeContainer(Container container) {
		containers.remove(container);
	}

	public double getXCoordinate() {
		return (double) xCoordinate;
	}

	public double getYCoordinate() {
		return (double) yCoordinate;
	}
}
