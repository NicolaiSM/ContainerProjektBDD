
public class Container {
	Port port;
	Journey journey;
	
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
}
