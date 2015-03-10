package tp.pr4.control;

import tp.pr4.Util.Misc;
import tp.pr4.logic.Board;
import tp.pr4.logic.ComplicaMove;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Move;

/**
 * Player that randomly plays Complica. It chooses a random column on the board. Any column will do since, if it is full, 
 * a space is created by displacing all the counters of the column downwards.
 * 
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 3
 * @see: tp.pr4.control.Player
 */

public class RandomComplicaPlayer implements Player {

	@Override
	public Move getMove(Board board, Counter colour) {
		ComplicaMove randomMove;
		int i = Misc.randInt(1, board.getWidth());

		randomMove = new ComplicaMove(i, colour);

		return randomMove;
	}
}