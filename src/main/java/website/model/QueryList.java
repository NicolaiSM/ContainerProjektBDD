package website.model;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.stereotype.Repository;

import application.data.Element;
import application.models.Port;

@Repository
public class QueryList extends LinkedList<Object> {
	
	public QueryList(Collection<?> collection) {
		super(collection);
	}
	
	
	
	

}
