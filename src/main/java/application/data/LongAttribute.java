package application.data;

public class LongAttribute implements Element {

	private String attribute;
	
	public LongAttribute(Long attribute) {
		this.attribute = attribute.toString();
	}
	
	public String getAttribute() {
		return attribute;
	}
	
	
	@Override
	public boolean hasKeyword(String... keywords) {
		for (String keyword: keywords ) { 
			if (attribute.equals(keyword)) {
				return true;
			}
		}
		return false;
		
	}
	
	

}
