package application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;


public class Container {
	
	private long id;
	
	private Port port;
	
	private Journey journey;
	
	private List<Journey> journeys = new LinkedList<Journey>();
	

	public Container(Port port) {
		this.port = port;
	}
	
	public List<Journey> getJourneys() {
		return journeys;
	}
	
	public Journey getJourney() {
		return journey;
	}
	
	public void setJourney(Journey journey) {
		this.journey = journey;
		
	}

	public void setJourneys(List<Journey> journeys) {
		this.journeys = journeys;
	}

	public Port getPort() {
		return port;
	}
	
	private void setPort(Port port) {
		this.port = port;
		
	}

	public void setId(long id) {
		this.id = id;
	}
	

	
	public long getId() {
		return id;
	}
	
	public boolean isContainerAvailable(Port startport) {
		return startport == this.port && !hasJourney();

	}
	
	public boolean hasKeyword(String keyword) {
		if (!hasJourney()) {
			return keyword.equals(port.getPort());
		}
		return (keyword.equals(port.getPort()) | keyword.equals(journey.getPortOfOrigin().getPort()) | keyword.equals(journey.getDestination().getPort()) | keyword.equals(journey.getContent()) | keyword.equals(journey.getClient().get("clientName")));
	}

	public void updateJourney(List<String> times, List<Port> locations, List<Integer> temperatures, List<Integer> humidities, List<Integer> pressures) {
		journey.update(times, locations, temperatures, humidities, pressures);
		port.removeContainer(this);
		setPort(journey.getLastLocation());
		port.addContainer(this);
		if (isLocationDestination()) {
			endJourney();
		}		
	}

	private void endJourney() {
		journeys.add(journey);
		journey = null;

	}

	private boolean isLocationDestination() {
		return port == journey.getDestination();
	}

	public boolean hasJourney() {
		return journey != null;
	}

	public int getDistance() {
		int distance = 0;
		if (!journeys.isEmpty()) {
			distance = journeys.stream().mapToInt(journey->journey.getDistance()).sum();
		}
		if (hasJourney()) {
			distance+=journey.getDistance();
		}
		return distance;

	}

	public int getNumberOfJourneys() {
		int numberOfJourneys = journeys.size();
		if (hasJourney()) {
			numberOfJourneys++;
		}
		return numberOfJourneys;
	}
	
	public int getNumberOfPorts() {
		int numberOfPorts = 0;
		if (!journeys.isEmpty()) {
			numberOfPorts = journeys.stream().mapToInt(journey->journey.getNumberOfPorts()).sum();
		}
		if (hasJourney()) {
			numberOfPorts+=journey.getNumberOfPorts();
		}
		return numberOfPorts;
	}
}
