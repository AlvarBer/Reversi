package tp.pr5.logic;

import tp.pr5.Util.Check;
import tp.pr5.Util.Misc;


/**
 * Class that implements the rules for Reversi, 
 * implementing all the methods of the interface, as well as the constructor.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 21/04/2015
 * @see: tp.pr5.logic.GameRules
 * @since: Assignment 5
 */
public class ReversiRules implements GameRules {
	
	//Constants
	private static final byte WIDTH = 8;
	private static final byte HEIGHT = 8;

	@Override
	public Board newBoard() {
		Board board = new Board(WIDTH, HEIGHT);
		return board;
	}

	@Override
	public Counter initialPlayer() {
		return Counter.BLACK;
	}

	@Override
	public boolean isDraw(Counter lastPlayer, Board board) {
		boolean draw = false;
		if (Check.checkFullGravity(board)) {
			if(Check.checkReversiWinner(board) == Counter.EMPTY)
			draw = true;
		}
		return draw;
	}

	@Override
	public Counter nextTurn(Counter lastPlayer, Board board) {
		return Misc.changeTurn(lastPlayer);
	}

	@Override
	public Counter winningMove(Move lastMove, Board board) {
		// TODO Auto-generated method stub
		return null;
	}

}
