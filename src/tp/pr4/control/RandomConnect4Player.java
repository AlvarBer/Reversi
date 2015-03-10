package tp.pr4.control;

import tp.pr4.Util.Misc;
import tp.pr4.logic.Board;
import tp.pr4.logic.Connect4Move;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Move;

/**
 * Player that randomly plays Connect 4. It chooses a random column on the board (making sure it's not full).
 * 
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 3
 * @see: tp.pr4.control.Player
 */
public class RandomConnect4Player implements Player {

	@Override
	public Move getMove(Board board, Counter colour) {
		Connect4Move randomMove;

		int i = Misc.randInt(1, board.getWidth());
		while (Misc.topCounter(board, i) == 1) { //While we can't find a valid column
			i = Misc.randInt(1, board.getWidth()); //Keep searching for one
		}
		randomMove = new Connect4Move(i, colour);

		return randomMove;
	}
}