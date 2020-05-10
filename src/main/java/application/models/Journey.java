package application.models;
import java.util.*;

import javax.persistence.*;

import application.data.Element;
import application.data.LongAttribute;
import application.data.StringAttribute;
import application.models.id.ContainerId;
import application.models.id.JourneyID;



public class Journey implements Element {
	

	private List<String> times = new ArrayList<String>();
	
	private List<Port> locations = new ArrayList<Port>();
	
	private List<Integer> temperatures = new ArrayList<Integer>();
	
	private List<Integer> humidities = new ArrayList<Integer>();
	 
	private List<Integer> pressures = new ArrayList<Integer>();
	
	private Map<String, Element> attributes = new HashMap<>();
	
	
	public Journey(Port portOfOrigin, Port destination, String content, User user) {
		attributes.put("portOfOrigin",portOfOrigin);
		attributes.put("destination",destination);
		attributes.put("content",new StringAttribute(content));
		attributes.put("user", user);
//		attributes.compute("id", id)
		attributes.put("id", new LongAttribute(JourneyID.newJourneyId()));
		
	}
	
	public Map<String, Element> getAttributes() {
		return attributes;
	}

	@Override
	public boolean hasKeyword(String... keywords) {
		for (Element e : attributes.values()) {
			if (e.hasKeyword(keywords)) {
				return true;
			}
		}
		return false;
	}
	
	public Element get(String key) {
		return attributes.get(key);
	}
	
	public List<String> getTimes() {
		return times;
	}


	public List<Port> getLocations() {
		return locations;
	}

	public List<Integer> getTemperatures() {
		return temperatures;
	}

	public List<Integer> getHumidities() {
		return humidities;
	}

	public List<Integer> getPressures() {
		return pressures;
	}

	public Element getId() {
		return attributes.get("id");
	}
	
	public void update(List<String> times, List<Port> locations, List<Integer> temperatures, List<Integer> humidities, List<Integer> pressures) {
		this.times.addAll(times);
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
			double x = locations.get(0).getXCoordinate() - ((Port) attributes.get("portOfOrigin")).getXCoordinate();
			double y = locations.get(0).getYCoordinate() - ((Port) attributes.get("portOfOrigin")).getYCoordinate();
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
	
	public boolean equals(Object object) {
		return getId().equals(((Journey) object).getId());
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
}
