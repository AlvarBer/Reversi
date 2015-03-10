package tp.pr4.logic;

import tp.pr4.Util.Misc;

/**
 * Class that implements the move for the Connect-4 game, implementing the abstract methods of the parent class.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 08/01/2015
 * @since: Assignment 2
 * @see: tp.pr4.logic.Move
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
	@Override
	public void executeMove(Board board) throws InvalidMove {
		if (getColumn() >= 1 && getColumn() <= board.getWidth()) {
			if (Misc.topCounter(board, getColumn()) > 1)
				board.setPosition(getColumn(), Misc.topCounter(board, getColumn()) - 1, getMoveColour());
			else
				throw new InvalidMove("column number " + getColumn() + " is already full.");
		} else
			throw new InvalidMove("column number " + getColumn() + " is not on the board.");
	}

	public void undo(Board board) {
		int undo_row = Misc.topCounter(board, getColumn());
		board.setPosition(getColumn(), undo_row, Counter.EMPTY);
	}

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumn() {
		// TODO Auto-generated method stub
		return 0;
	}

}

