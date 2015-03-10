package tp.pr4.logic;

import tp.pr4.Util.Check;
import tp.pr4.Util.Misc;

/**
 * Class that implements the rules for the Connect-4 game, implementing all the methods of the interface, as well as the constructor.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 08/01/2015
 * @see: tp.pr4.logic.GameRules
 * @since: Assignment 2
 */


public class Connect4Rules implements GameRules {

	//Constants
	private static final byte WIDTH = 7;
	private static final byte HEIGHT = 6;

	//Methods (Defined in the interface class GameRules)
	@Override
	public Board newBoard() {
		Board board = new Board(WIDTH, HEIGHT);
		return board;
	}
	
	@Override
	public Counter initialPlayer() {
		return Counter.WHITE;
	}

	@Override
	public boolean isDraw(Counter lastPlayer, Board board) {
		boolean draw = false;
		if (Check.checkFull(board) && !Check.checkConnect4Win(board)) {
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
		Counter winningMove = Counter.EMPTY;
		if (Check.checkConnect4Win(board)) {
			winningMove = lastMove.getMoveColour();
		}
		return winningMove;
	}

}
