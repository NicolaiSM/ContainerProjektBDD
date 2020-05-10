package application;

import application.data.Element;
import application.data.QueryLinkedList;
import application.data.StringAttribute;
import application.models.Client;

public class Hulahop {

	public static void main(String[] args ) {
		
		System.out.println(ContainerApp.getInstance().getContainers());
		
		System.out.println(ContainerApp.getInstance().getContainers().findElements(new String[] {"a","b"}));
		
	}

}
