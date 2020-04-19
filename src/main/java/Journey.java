import java.util.List;

public class Journey {

	private Port portOfOrigin;
	private Port destination;
	private String content;
	private Client client;
	private List<String> times;
	private List<Port> locations;
	private List<Integer> temperatures;
	private List<Integer> humidities;
	private List<Integer> pressures;
	
	public Journey(Port portOfOrigin, Port destination, String content, Client client) {
		this.portOfOrigin = portOfOrigin;
		this.destination = destination;
		this.content = content;
		this.client = client;
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
		this.times.addAll(times);
		this.locations.addAll(locations);
		this.temperatures.addAll(temperatures);
		this.humidities.addAll(humidities);
		this.pressures.addAll(pressures);
		
				

	}

	public Port getLastLocation() {
		
		return locations.get(locations.size()-1);
	}
}
