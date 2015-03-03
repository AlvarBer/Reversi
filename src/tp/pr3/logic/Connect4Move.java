package tp.pr3.logic;

import tp.pr3.Util.Misc;

/**
 * Class that implements the move for the Connect-4 game, implementing the abstract methods of the parent class.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 08/01/2015
 * @since: Assignment 2
 * @see: tp.pr3.logic.Move
 */

public class Connect4Move extends Move {
	//Constructor

	/**
	 * Class constructor. Invokes the constructor of the superclass
	 *
	 * @param moveColumn Column in which to place the counter.
	 * @param moveColour Colour of the counter to be placed (also that of the player that places it).
	 */
	public Connect4Move(int moveColumn, Counter moveColour) {
		super(moveColumn, moveColour);
	}

	//Methods (Defined in the superclass Move)
	public void executeMove(Board board) throws InvalidMove {
		if (getMoveColumn() >= 1 && getMoveColumn() <= board.getWidth()) {
			if (Misc.topCounter(board, getMoveColumn()) > 1)
				board.setPosition(getMoveColumn(), Misc.topCounter(board, getMoveColumn()) - 1, getMoveColour());
			else
				throw new InvalidMove("column number " + getMoveColumn() + " is already full.");
		} else
			throw new InvalidMove("column number " + getMoveColumn() + " is not on the board.");
	}

	public void undo(Board board) {
		int undo_row = Misc.topCounter(board, getMoveColumn());
		board.setPosition(getMoveColumn(), undo_row, Counter.EMPTY);
	}

}

