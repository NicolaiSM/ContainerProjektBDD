package application.models.id;

public class JourneyID {
	
	private static Long id = 1L;
	
	public static Long newJourneyId() {
		return id++;
	}

}
