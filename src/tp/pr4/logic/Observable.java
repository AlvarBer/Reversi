package tp.pr4.logic;

public interface Observable<T> {

	public void addObserver(T o);
	
	public void removeObserver (T o);
	
}