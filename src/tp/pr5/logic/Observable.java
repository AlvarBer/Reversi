package tp.pr5.logic;

/**
 * Generic interface for the Observable pattern.
 * 
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 4
 * @param <T>
 */
public interface Observable<T> {

	/**
	 * Adds an observer
	 * 
	 * @param o
	 */
	public void addObserver(T o);
	
	/**
	 * Removes an observer
	 * 
	 * @param o
	 */
	public void removeObserver (T o);
	
}