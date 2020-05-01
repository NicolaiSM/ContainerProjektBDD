package application;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.*;


@Entity
public class Port {
	
	@Id
	@Column(name = "port")
	private final String port;
	
	@OneToMany
	private List<Container> containers = new ArrayList<Container>();
	
	@Column
	private final int xCoordinate;
	@Column
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
