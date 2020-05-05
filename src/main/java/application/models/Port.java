package application.models;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.*;

import application.data.Elements;



public class Port implements Elements {
	
	@Id
	@Column(name = "port")
	private final String port;
	
	private Set<Container> containers = new HashSet<Container>();
	
	private final int xCoordinate;

	private final int yCoordinate;
	
	@Override
	public boolean equals(Object port) {
		return this.port.equals(((Port) port).getPort());
		
	}
	
	public boolean equals(String port) {
		return this.port.equals(port);
		
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

	@Override
	public boolean hasKeyword(String... keywords) {
		for (String keyword : keywords) {
			if (equals(keyword)) {
				return true;
			}
		}
		return false;
	}
}
