package application;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
public class Port {
	
	@Id
	@Column(name = "port")
	private String port;
	
	@OneToMany
	private List<Container> containers = new ArrayList<Container>();

	
	public Port(String port) {
		this.port = port;
	}
	
	public Port() {
	}

	public String getPort() {
		return port;
	}
	
	public void addContainer(Container container) {
		containers.add(container);
	}

	public void removeContainer(Container container) {
		containers.remove(container);
	}

	
}
