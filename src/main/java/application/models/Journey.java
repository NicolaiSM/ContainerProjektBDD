package application.models;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import application.data.Elements;



public class Journey implements Elements {
	

	private long id;
	
	@OneToOne
	private final Port portOfOrigin;
	
	@OneToOne
	private final Port destination;
	
	@Column
	private final String content;
	
	@ManyToOne
	private final User client;
	
	private List<String> times = new LinkedList<String>();
	
	private List<Port> locations = new LinkedList<Port>();
	
	private List<Integer> temperatures = new LinkedList<Integer>();
	
	private List<Integer> humidities = new LinkedList<Integer>();
	
	private List<Integer> pressures = new LinkedList<Integer>();
	
	public Journey(Port portOfOrigin, Port destination, String content, User user) {
		this.portOfOrigin = portOfOrigin;
		this.destination = destination;
		this.content = content;
		this.client = user;
	}

	
	public List<String> getTimes() {
		return times;
	}


	public void setTimes(List<String> times) {
		this.times = times;
	}


	public List<Port> getLocations() {
		return locations;
	}


	public void setLocations(List<Port> locations) {
		this.locations = locations;
	}


	public List<Integer> getTemperatures() {
		return temperatures;
	}


	public void setTemperatures(List<Integer> temperatures) {
		this.temperatures = temperatures;
	}


	public List<Integer> getHumidities() {
		return humidities;
	}


	public void setHumidities(List<Integer> humidities) {
		this.humidities = humidities;
	}


	public List<Integer> getPressures() {
		return pressures;
	}


	public void setPressures(List<Integer> pressures) {
		this.pressures = pressures;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getId() {
		return id;
	}
	
	public Port getPortOfOrigin() {
		return portOfOrigin;
	}

	public Port getDestination() {
		return destination;
	}

	public String getContent() {
		return content;
	}
	
	public User getClient() {
		return client;
	}

	public boolean hasKeyword(String... keywords) {
		for (String keyword : keywords) {
			if(portOfOrigin.equals(keyword) | destination.equals(keyword) | content.equals(keyword) | client.hasKeyword(keyword)) {
				return true;
			}
		}
		return false;
	}

	public void update(List<String> times, List<Port> locations, List<Integer> temperatures, List<Integer> humidities, List<Integer> pressures) {
		this.times.addAll(0,times);
		this.locations.addAll(locations);
		this.temperatures.addAll(temperatures);
		this.humidities.addAll(humidities);
		this.pressures.addAll(pressures);
	}

	public Port getLastLocation() {	
		return locations.get(locations.size()-1);
	}

	public int getDistance() {
		int numberOfLocations = locations.size();
		double distance = 0;
		if (!(numberOfLocations<1)) {
			double x = locations.get(0).getXCoordinate()-portOfOrigin.getXCoordinate();
			double y = locations.get(0).getYCoordinate()-portOfOrigin.getYCoordinate();
			distance+=Math.sqrt(x*x+y*y);
			for (int i=0; i<numberOfLocations-1; i++) {
				x = locations.get(i+1).getXCoordinate()-locations.get(i).getXCoordinate();
				y = locations.get(i+1).getYCoordinate()-locations.get(i).getYCoordinate();
				distance+=Math.sqrt(x*x+y*y);
			}
		}
		return (int) distance;
	}

	public int getNumberOfPorts() {
		return locations.size()+1;
	}
}
