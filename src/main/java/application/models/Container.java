package application.models;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import application.data.Element;
import application.data.LongAttribute;
import application.models.id.ContainerId;


public class Container implements Element {
	
//	private Port port;
//	
//	private Journey journey;
//	
	private List<Journey> journeys = new LinkedList<Journey>();
	private Map<String, Element> attributes = new HashMap<>();
	

	public Container(Port port) {
		attributes.put("port", port);
		attributes.put("id", new LongAttribute(ContainerId.newContainerId()));
	}
	
	public List<Journey> getJourneys() {
		return journeys;
	}
	
	public Journey getJourney() {
		return (Journey) attributes.get("journey");
	}

	public void setJourney(Journey journey) {
		attributes.put("journey", journey);
	}

	public Port getPort() {
		return (Port) attributes.get("port");
	}
	

	
	public Element getId() {
		return attributes.get("id");
	}
	
	public boolean isContainerAvailable(Port startport) {
		return startport == getPort() && !hasJourney();

	}
	
//	@Override
//	public boolean hasKeyword(String... keywords) {
//		for (String keyword : keywords) {
//			if (port.equals(keyword) | (hasJourney() && journey.hasKeyword(keyword))) {
//				return true;
//			}
//		}
//		return false;
//	}
	@Override
	public boolean hasKeyword(String... keywords) {
		for (Element e : attributes.values()) {
			if (e.hasKeyword(keywords)) {
				return true;
			}
		}
		return false;
	}
	
	public void updateJourney(List<String> times, List<Port> locations, List<Integer> temperatures, List<Integer> humidities, List<Integer> pressures) {
		getJourney().update(times, locations, temperatures, humidities, pressures);
		getPort().removeContainer(this);
		attributes.put("port",getJourney().getLastLocation());
		getPort().addContainer(this);
		if (isLocationDestination()) {
			endJourney();
		}		
		
	}

	private void endJourney() {
		journeys.add(getJourney());
		((Client) getJourney().get("user")).getClientContainers().remove(this);
		attributes.remove("journey");

	}

	private boolean isLocationDestination() {
		return getPort() == getJourney().get("destination");
	}

	public boolean hasJourney() {
		return getJourney() != null;
	}

	public int getDistance() {
		int distance = 0;
		if (!journeys.isEmpty()) {
			distance = journeys.stream().mapToInt(journey->journey.getDistance()).sum();
		}
		if (hasJourney()) {
			distance+=getJourney().getDistance();
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
			numberOfPorts+=getJourney().getNumberOfPorts();
		}
		return numberOfPorts;
	}
	
	
	@Override
	public boolean equals(Object object) {
		return getId().equals(((Container) object).getId());
		
	}
	
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
	

	
}
