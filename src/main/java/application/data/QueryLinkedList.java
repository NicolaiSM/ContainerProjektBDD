package application.data;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class QueryLinkedList <A extends Element> extends LinkedList<A> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7699425566372948024L;
	
	public List<A> findElements(String...keywords) {
		return this.stream().filter((element)->element.hasKeyword(keywords)).collect(Collectors.toList());
		
	}
}
