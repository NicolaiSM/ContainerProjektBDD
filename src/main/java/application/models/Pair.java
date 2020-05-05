package application;

public class Pair<T,D> {
	
	private final T first;
	private final D second;
	
	
	public T getFirst() {
		return first;
	}


	public D getSecond() {
		return second;
	}


	public Pair(T first, D second) {
		super();
		this.first = first;
		this.second = second;
	}
	
	
	
	
	
}
