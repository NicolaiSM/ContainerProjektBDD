package application.data;

public class StringAttribute implements Element{
	private String attribute;
	
	public StringAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	public void set(String attribute) {
		this.attribute = attribute;
	}
	@Override
	public boolean hasKeyword(String... keywords) {
		return attribute.equals(keywords);
	}
	
}
