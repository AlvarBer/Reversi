package tp.pr5.control;

import tp.pr5.Util.Misc;
import tp.pr5.logic.Board;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Move;
import tp.pr5.logic.ReversiMove;

/**
 * Player that randomly plays Reversi. 
 *  
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 21/04/2015
 * @since: Assignment 5
 * @see: tp.pr5.control.Player
 */
public class RandomReversiPlayer implements Player {

	@Override
	public Move getMove(Board board, Counter colour) {
		int i = Misc.randInt(1, board.getWidth());
		int j = Misc.randInt(1, board.getHeight());
		ReversiMove randomMove = new ReversiMove(i, j, colour);

		while (board.getPosition(i, j) != Counter.EMPTY) { //While we can't find a valid position
			i = Misc.randInt(1, board.getWidth()); //Keep searching for one
			j = Misc.randInt(1, board.getHeight());
			randomMove = new ReversiMove(i, j, colour);
		}
		while (!randomMove.isLegal(board,colour)) { //While the move is not legal
			while (board.getPosition(i, j) != Counter.EMPTY) {
				i = Misc.randInt(1, board.getWidth());
				j = Misc.randInt(1, board.getHeight());
				randomMove = new ReversiMove(i, j, colour);
			}
		}

		return randomMove;
	}

}
