package tp.pr5.logic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Stack implementation done with ArrayDeque
 * java recommends this implementation better than Stack
 * Limited to LIFO by only using part of Dequeue functions
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 08/01/2015
 * @since: Assignment 1
 */

public class DequeStack {

	private Deque<Move> deque;

	/**
	 * Constructor using ArrayDeque
	 * 
	 */
	public DequeStack() {
		this.deque = new ArrayDeque<>();
	}

	/**
	 * We push a Move into the stack
	 * 
	 * @param mov
	 */
	public void push(Move mov) {
		deque.addFirst(mov);
	}

	/**
	 * We pop the last move in the Stack
	 * 
	 * @return
	 */
	public Move pop() {
		return deque.removeFirst();
	}

	/**
	 * We check if the Dequeue is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return deque.isEmpty();
	}

}