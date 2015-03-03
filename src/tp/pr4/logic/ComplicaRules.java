package tp.pr4.logic;

import tp.pr4.Util.Check;
import tp.pr4.Util.Misc;

/**
 * Class that implements the rules for the Complica game, implementing all the methods of the interface, as well as the constructor.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 08/01/2015
 * @since: Assignment 2
 * @see: tp.pr4.logic.GameRules
 */

public class ComplicaRules implements GameRules {

	//Constants
	private static final byte WIDTH = 4;
	private static final byte HEIGHT = 7;

	//Methods (Defined in the interface class GameRules)
	public Counter initialPlayer() {
		return Counter.WHITE;
	}

	public boolean isDraw(Counter lastPlayer, Board board) {
		return false;
	}

	public Board newBoard() {
		Board board = new Board(WIDTH, HEIGHT);
		return board;
	}

	public Counter winningMove(Move lastMove, Board board) {
		Counter winningMove = Check.checkComplicaWin(board);

		return winningMove;
	}

	public Counter nextTurn(Counter lastPlayer, Board board) {
		return Misc.changeTurn(lastPlayer);
	}
}