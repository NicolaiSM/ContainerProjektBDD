package website.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import application.models.Port;

public class JourneyInformationForm {
	
	private List<String> times;

	private List<String> locations;
	
	private List<Integer> temperatures;
	
	private List<Integer> humidities;
	 
	private List<Integer> pressures;
	
	private List<Integer> ListStringToListInteger(List<String> listString)  {
		return listString.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
	}
	
	
	public List<String> getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = Arrays.asList(times.split(" "));
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(String locations) {
		
		this.locations =  Arrays.asList(locations.split(" "));
	}

	public List<Integer> getTemperatures() {
		return temperatures;
	}

	public void setTemperatures(String temperatures) {
		try {
			this.temperatures = ListStringToListInteger(Arrays.asList(temperatures.split(" ")));
		} catch(Exception e) {
			this.temperatures = null;
		}
	}

	public List<Integer> getHumidities() {
		return humidities;
	}

	public void setHumidities(String humidities) {
		this.humidities = ListStringToListInteger(Arrays.asList(humidities.split(" ")));
	}

	public List<Integer> getPressures() {
		return pressures;
	}

	public void setPressures(String pressures) {
		this.pressures = ListStringToListInteger(Arrays.asList(pressures.split(" ")));
	}
	
	public boolean hasError() {
		if ( times != null & locations != null & temperatures != null & humidities != null & pressures != null && times.size() != locations.size() & locations.size() != temperatures.size() &  temperatures.size() != humidities.size() & humidities.size() != pressures.size() ) {
			return true;
		} 
		return false;
	}
	
	
	
	
}
