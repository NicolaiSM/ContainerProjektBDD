
public class Container {
	private Port port;
	private Journey journey;
	
	public Container(Port port) {
		this.port = port;
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
		return (keyword.equals(port.getPort()) | keyword.equals(journey.getPortOfOrigin().getPort()) | keyword.equals(journey.getDestination().getPort()) | keyword.equals(journey.getContent()));
	}
}
