
public class Journey {
	
	Port portOfOrigin;
	Port destination;
	String content;
	Client client;
	
	
	public Journey(Client client, Port portOfOrigin, Port destination, String content) {
		super();
		this.portOfOrigin = portOfOrigin;
		this.destination = destination;
		this.content = content;
		this.client = client;
	}
	
	
	
	
	
}
