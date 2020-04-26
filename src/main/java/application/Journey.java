package application;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
public class Journey {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;
	
	@OneToOne
	private Port portOfOrigin;
	
	@OneToOne
	private Port destination;
	
	@Column
	private String content;
	
	@ManyToOne
	private Client client;
	
	@ElementCollection
	private List<String> times = new ArrayList<String>();
	
	@OneToMany
	private List<Port> locations = new ArrayList<Port>();
	
	@ElementCollection
	private List<Integer> temperatures = new ArrayList<Integer>();
	
	@ElementCollection
	private List<Integer> humidities = new ArrayList<Integer>();
	
	@ElementCollection
	private List<Integer> pressures = new ArrayList<Integer>();
	
	public Journey(Port portOfOrigin, Port destination, String content, Client client) {
		this.portOfOrigin = portOfOrigin;
		this.destination = destination;
		this.content = content;
		this.client = client;
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

	public boolean hasKeyword(String keyword) {
		return (keyword.equals(portOfOrigin.getPort()) | keyword.equals(destination.getPort()) | keyword.equals(content) | keyword.equals(client.getClientName()));
	}

	public Client getClient() {
		return client;
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
		int numberOfPorts = locations.size();
		if (portOfOrigin!=null) {
			numberOfPorts++;
		}
		return numberOfPorts;
	}
}
