package application;

import application.data.Element;
import application.data.QueryLinkedList;
import application.data.StringAttribute;
import application.models.Client;

public class Hulahop {

	public static void main(String[] args ) {
		
		try {
			Client test = ContainerApp.getInstance().registerClient("a","a" ,"a","a@a.com","a");
			ContainerApp.getInstance().registerPort("a");
			ContainerApp.getInstance().registerPort("b");
			ContainerApp.getInstance().createContainer("a");
			ContainerApp.getInstance().createContainer("b");
			
			ContainerApp.getInstance().registerContainer("a", "b" , "lego", test );
			
			System.out.println(((QueryLinkedList<Element>) test.getClientContainers()).findElements("lego"));
			StringAttribute test2 = new StringAttribute("kål");
			
			System.out.println(test.hasKeyword("kål"));
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
