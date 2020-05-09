package application.models;

public class ContainerAdvancedQuery {
	public int getDistance(Container container) {
		int distance = 0;
		if (!container.getJourneys().isEmpty()) {
			distance = container.getJourneys().stream().mapToInt(journey->journey.getDistance()).sum();
		}
		if (container.hasJourney()) {
			distance+=container.getJourney().getDistance();
		}
		return distance;

	}

	public int getNumberOfJourneys(Container container) {
		int numberOfJourneys = container.getJourneys().size();
		if (container.hasJourney()) {
			numberOfJourneys++;
		}
		return numberOfJourneys;
	}
	
	public int getNumberOfPorts(Container container) {
		int numberOfPorts = 0;
		if (!container.getJourneys().isEmpty()) {
			numberOfPorts = container.getJourneys().stream().mapToInt(journey->journey.getNumberOfPorts()).sum();
		}
		if (container.hasJourney()) {
			numberOfPorts+=container.getJourney().getNumberOfPorts();
		}
		return numberOfPorts;
	}	
}
