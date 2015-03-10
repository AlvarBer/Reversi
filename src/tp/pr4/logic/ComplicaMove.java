package tp.pr4.logic;

import tp.pr4.Util.Misc;

/**
 * Class that implements the move for the Complica game, implementing the abstract methods of the parent class.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 08/01/2015
 * @see: tp.pr4.logic.Move
 * @since: Assignment 2
 */

public class ComplicaMove extends Move {

	//Attributes
	private Counter bottom;

	//Constructor

	/**
	 * Class constructor. Invokes the constructor of the superclass
	 *
	 * @param moveColumn Column in which to place the counter.
	 * @param moveColour Colour of the counter to be placed (also that of the player that places it).
	 */
	public ComplicaMove(int moveColumn, Counter moveColour) {
		super(moveColumn, moveColour);
		bottom = Counter.EMPTY;
	}

	//Methods (Defined in the superclass Move)

	@Override
	public void executeMove(Board board) throws InvalidMove {

		if (getColumn() >= 1 && getColumn() <= board.getWidth()) {
			if (Misc.topCounter(board, getColumn()) > 1) {  //The column is not full and set the counter
				board.setPosition(getColumn(), Misc.topCounter(board, getColumn()) - 1, getMoveColour());
			} else { //The column is full and push down the column and set the new counter
				bottom = pushColumn(board, getColumn(), getMoveColour());
			}
		} else {
			throw new InvalidMove("column number " + getColumn() + " is not on the board.");
		}
	}

	@Override
	public void undo(Board board) {
		if (bottom == Counter.EMPTY) { //The column wasn't push down in this move
			int undo_row = Misc.topCounter(board, getColumn());
			board.setPosition(getColumn(), undo_row, Counter.EMPTY);
		} else {
			pullColumn(board, getColumn(), bottom);
		}
	}
	
	/**
	 * Method that insert a counter on the bottom of a column and moves up the rest of the counters of the column.
	 *
	 * @param board      Board to be changed
	 * @param moveColumn Number of the column in which the counter will be inserted
	 * @param counter    Counter to be inserted
	 * @return The top counter of the board that has been overwritten
	 */
	private static Counter pushColumn(Board board, int moveColumn, Counter counter) {
		Counter bottom;
		bottom = board.getPosition(moveColumn, board.getHeight());
		//Copy the counter above to each counter starting from the bottom
		for (int i = board.getHeight(); i > 1; i--) {
			board.setPosition(moveColumn, i, board.getPosition(moveColumn, i - 1));
		}
		board.setPosition(moveColumn, 1, counter);
		return bottom;
	}

	/**
	 * Method that insert a counter on the top of a column and moves down the rest of the counters of the column.
	 *
	 * @param board      Board to be changed
	 * @param moveColumn Number of the column in which the counter will be inserted
	 * @param counter    Counter to be inserted
	 */
	private static void pullColumn(Board board, int moveColumn, Counter counter) {
		//Copy the counter below to each counter starting from the top
		for (int i = 1; i < board.getHeight(); i++) {
			board.setPosition(moveColumn, i, board.getPosition(moveColumn, i + 1));
		}
		board.setPosition(moveColumn, board.getHeight(), counter);
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