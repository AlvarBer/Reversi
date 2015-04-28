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
		
		//Set initial counters
		board.setPosition(4, 4, Counter.BLACK);
		board.setPosition(5, 5, Counter.BLACK);
		board.setPosition(4, 5, Counter.WHITE);
		board.setPosition(5, 4, Counter.WHITE);	
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
		Counter nextPlayer = Misc.changeTurn(lastPlayer);
		if (ReversiMove.canMove(board, nextPlayer))
			return nextPlayer;
		else
			return lastPlayer;	
	}

	@Override
	public Counter winningMove(Move lastMove, Board board) {
		return Counter.EMPTY;
	}

}
