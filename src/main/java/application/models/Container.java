package application.models;


import java.util.LinkedList;
import java.util.List;

import application.data.Element;
import application.models.id.ContainerId;


public class Container implements Element {
	
	private Long id;
	
	private Port port;
	
	private Journey journey;
	
	private List<Journey> journeys = new LinkedList<Journey>();
	

	public Container(Port port) {
		this.port = port;
		id = ContainerId.newContainerId();
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
	

	
	public long getId() {
		return id;
	}
	
	public boolean isContainerAvailable(Port startport) {
		return startport == this.port && !hasJourney();

	}
	
	@Override
	public boolean hasKeyword(String... keywords) {
		for (String keyword : keywords) {
			if (port.equals(keyword) | (hasJourney() && journey.hasKeyword(keyword))) {
				return true;
			}
		}
		return false;
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
		((Client) journey.get("user")).getClientContainers().remove(this);
		journey = null;

	}

	private boolean isLocationDestination() {
		return port == journey.get("destination");
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
	
	
	@Override
	public boolean equals(Object object) {
		return id.equals(((Container) object).getId());
		
	}
	
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	

	
}
