
public class Journey {

	String portOfOrigin;
	String destination;
	String content;
	Client client;
	
	public Journey(String portOfOrigin, String destination, String content, Client client) {
		this.portOfOrigin = portOfOrigin;
		this.destination = destination;
		this.content = content;
		this.client = client;
	}
}
