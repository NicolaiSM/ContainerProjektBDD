package application;

public class Hulahop {

	public static void main(String[] args ) {
		
		try {
			ContainerApp.getInstance().registerClient("b", "b", "b", "b", "b");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ContainerApp.getInstance().registerClient("c", "c", "c", "c", "c");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ContainerApp.getInstance().registerClient("c", "c", "c", "c", "c");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
