package application.data;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class QueryHashSet<A extends Element> extends HashSet<A> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public A findElement(String...keywords) {
		return this.stream().filter((element)->element.hasKeyword(keywords)).findFirst().orElse(null);
		
	}
	
	public List<A> findElements(String...keywords) {
		return this.stream().filter((element)->element.hasKeyword(keywords)).collect(Collectors.toList());
		
	}
	

}
