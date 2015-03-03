package tp.pr3.logic;

/**
 * Class that represents a move made by a player of the game. It has a method to execute the move on a given board and another to undo the move on a given board.
 * It is an abstract class; there will be a derived concrete class for each type of game supported.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 08/01/2015
 * @since: Assingment 2
 */

public abstract class Move extends java.lang.Object {

	//Constructor

	//Attributes
	private int moveColumn;

	//Methods
	private Counter moveColour;

	/**
	 * Construct a move,
	 *
	 * @param moveColumn Number of the column which will be modified by the movement
	 * @param moveColour Colour of the player who has made the movement
	 */
	public Move(int moveColumn, Counter moveColour) {
		this.moveColumn = moveColumn;
		this.moveColour = moveColour;
	}

	/**
	 * Executes the move on the board passed as a parameter. It is assumed that the board passed as a parameter is consistent with the rules of the game for which the move was created.
	 * If not, the behaviour is undefined.
	 *
	 * @param board Board on which to execute the move.
	 * @return True if the move was successfully executed and false otherwise.
	 */
	public abstract void executeMove(Board board) throws InvalidMove;

	/**
	 * Returns the colour of the player who made the move (may be abstract)
	 *
	 * @return Colour of the counter to be placed in this move (coincides with that passed to the constructor).
	 */
	public Counter getPlayer() {
		return moveColour;
	}

	/**
	 * Undoes the move on the board passed as a parameter. It is assumed that the board passed as a parameter is consistent with the rules of the game for which the move was created and that, furthermore, the move was the last one executed on the board.
	 * If not, the behaviour is undefined. The method can therefore be expected to always work correctly.
	 *
	 * @param boa Board on which to undo the move.
	 */
	public abstract void undo(Board boa);

	/**
	 * Accessor method to get the column to be modified by the movement
	 *
	 * @return Number of the column which will be modified by the movement
	 */
	public int getMoveColumn() {
		return moveColumn;
	}

	/**
	 * Mutator method to set the column to be modified by the movement
	 *
	 * @param moveColumn Number of the column which will be modified by the movement
	 */
	public void setMoveColumn(int moveColumn) {
		this.moveColumn = moveColumn;
	}

	/**
	 * Accessor method to get the colour of the player who has made the movement
	 *
	 * @return Colour of the player who has made the movement
	 */
	public Counter getMoveColour() {
		return moveColour;
	}

	/**
	 * Mutator method to set the colour of the player who has made the movement
	 *
	 * @param moveColour Colour of the player who has made the movement
	 */
	public void setMoveColour(Counter moveColour) {
		this.moveColour = moveColour;
	}
}
