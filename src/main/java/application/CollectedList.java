package application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectedList<A extends Elements>{
	private ArrayList<A> list = new ArrayList<A>();
	
	public A findElement(String...keywords) {
		
		return list.stream().filter((element)->element.hasKeyword(keywords)).findFirst().orElse(null);
		
		
	}
	
	public List<A> findElements(String...keywords) {
		
		return list.stream().filter((element)->element.hasKeyword(keywords)).collect(Collectors.toList());
		
	}
	
	public void addElement(A element) {
		list.add(element);
	}
	
	public boolean anyMatch(String...keywords) {
		return list.stream().anyMatch((element)->element.hasKeyword(keywords));
	}
	
	
	


}
