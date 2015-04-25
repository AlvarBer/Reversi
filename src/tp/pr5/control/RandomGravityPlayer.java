package tp.pr5.control;

import tp.pr5.Util.Misc;
import tp.pr5.logic.Board;
import tp.pr5.logic.Counter;
import tp.pr5.logic.GravityMove;
import tp.pr5.logic.Move;

/**
 * Player that randomly plays Gravity. It chooses a random position on the board (making sure it is empty).
 * 
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 3
 * @see: tp.pr5.control.Player
 */
public class RandomGravityPlayer implements Player {

	@Override
	public Move getMove(Board board, Counter colour) {
		int i = Misc.randInt(1, board.getWidth());
		int j = Misc.randInt(1, board.getHeight());
		GravityMove randomMove;

		while (board.getPosition(i, j) != Counter.EMPTY) { //While we can't find a valid position
			i = Misc.randInt(1, board.getWidth()); //Keep searching for one
			j = Misc.randInt(1, board.getHeight());
		}
		randomMove = new GravityMove(i, j, colour);

		return randomMove;
	}
}
