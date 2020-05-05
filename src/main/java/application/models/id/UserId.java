package application.models.id;

public class UserId {
	
	private static Long id = 0L;
	
	public static Long newUserId() {
		return id++;
	}
	
}
