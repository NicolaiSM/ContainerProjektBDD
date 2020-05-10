package application.data;

public class LongAttribute implements Element {

	private Long attribute;
	
	public LongAttribute(Long attribute) {
		this.attribute = attribute;
	}
	
	public Long getAttribute() {
		return attribute;
	}
	
	
	@Override
	public boolean hasKeyword(String... keywords) {
		String stringAttribute = attribute.toString();
		for (String keyword: keywords ) { 
			if (stringAttribute.equals(keyword)) {
				return true;
			}
		}
		return false;
		
	}
	
	public int hashCode() {
		return attribute.hashCode();
	}
	

}
