package tp.pr3.logic;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeStack {

	private Deque<Move> deque;

	public DequeStack() {
		this.deque = new ArrayDeque<>();
	}

	public void push(Move mov) {
		deque.addFirst(mov);
	}

	public Move pop() {
		return deque.removeFirst();
	}

	public boolean isEmpty() {
		return deque.isEmpty();
	}

}