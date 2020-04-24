package website.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Container {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;
	
	@ManyToOne
	@NotBlank()
	private Port port;
	
	@ManyToOne
	private Journey journey;
	
	@OneToMany
	private List<Journey> journeys = new ArrayList<Journey>();
	

	
	public Container(Port port) {
		this.port = port;
	}
	
	public long getId() {
		return id;
	}
	
	public boolean isContainerAvailable(Port startport) {
		
		if(startport == this.port && journey == null )  {
			return true;
		}
		return false;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
		
	}

	public boolean hasKeyword(String keyword) {
		if (journey == null) {
			return keyword.equals(port.getPort());
		}
		return (keyword.equals(port.getPort()) | keyword.equals(journey.getPortOfOrigin().getPort()) | keyword.equals(journey.getDestination().getPort()) | keyword.equals(journey.getContent()) | keyword.equals(journey.getClient().getClientName()));
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
		if (port == journey.getDestination()) {
			return true;
		}
		return false;
	}

	private void setPort(Port port) {
		this.port = port;
		
	}

	public Journey getJourney() {
		return journey;
	}

	

}
