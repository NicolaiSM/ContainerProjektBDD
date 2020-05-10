package application.data;

public class StringAttribute implements Element{
	private String attribute;
	
	public StringAttribute(String attribute) {
		this.attribute = attribute;
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
