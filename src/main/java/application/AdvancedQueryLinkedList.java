package application;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AdvancedQueryLinkedList<A extends Elements> extends LinkedList<A> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7699425566372948024L;


	public A findElement(String...keywords) {
		
		return this.stream().filter((element)->element.hasKeyword(keywords)).findFirst().orElse(null);
		
	}
	
	public List<A> findElements(String...keywords) {
		
		return this.stream().filter((element)->element.hasKeyword(keywords)).collect(Collectors.toList());
		
	}
	
	
	public boolean anyMatch(String...keywords) {
		return this.stream().anyMatch((element)->element.hasKeyword(keywords));
	}




}
