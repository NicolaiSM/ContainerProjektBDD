package application;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ListOfInformation implements Elements{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;
	
	@ElementCollection
	private Map<String,String> info = new HashMap<>();

	@Override
	public boolean hasKeyword(String... keywords) {
		for (String keyword:keywords) {
			if (info.containsValue(keyword)) {
				return true;
			}
		}
		return false;
	}
	
	public ListOfInformation() {
		
	}
	
	
	
	
}
