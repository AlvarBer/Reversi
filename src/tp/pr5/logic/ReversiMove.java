package tp.pr5.logic;


import tp.pr5.Util.Misc;

/**
 * Class that implements the move for the Reversi game, implementing the abstract methods of the parent class.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 21/04/2015
 * @since: Assignment 5
 * @see: tp.pr5.logic.Move
 */
public class ReversiMove extends Move {

	//Attributes
	private int moveRow;
	private int moveColumn;
	private Counter moveColour;

	//Constructor

	/**
	 * Constructor of the class.
	 *
	 * @param moveColumn Number of the column which will be modified by the movement
	 * @param moveRow Number of the row which will be modified by the movement
	 * @param moveColour Colour of the player who has made the movement
	 */
	public ReversiMove(int moveColumn, int moveRow, Counter moveColour) {
		this.moveColumn = moveColumn;
		this.moveColour = moveColour;
		this.moveRow = moveRow;
	}

	//Functions
	@Override
	public void executeMove(Board board) throws InvalidMove {
		if (Misc.validPosition(board, getColumn(), getRow())) {
			if (board.getPosition(getColumn(), getRow()) != Counter.EMPTY) {
				flipIt(board, this.getPlayer());
			} else
				throw new InvalidMove("position (" + getColumn() + ", " + getRow() + ") is not empty.");
		} else
			throw new InvalidMove("position (" + getColumn() + ", " + getRow() + ") is not on the board.");
	}

	@Override
	public Counter getPlayer() {
		return this.moveColour;
	}

	@Override
	public void undo(Board boa) {

		flipIt(boa,Misc.changeTurn(this.getPlayer()));

		boa.setPosition(this.getColumn(), this.getRow(), Counter.EMPTY);
	}

	@Override
	public int getColumn() {
		return this.moveColumn;
	}

	@Override
	public int getRow() {
		return moveRow;
	}

	private void flipIt(Board board, Counter Player) {
		int verticalIncrement = 0, horizontalIncrement = 0, numberOfCounters;

		for (int k = 0; k < 4; ++k) {
			numberOfCounters = 0;
			if (k == 0) {
				verticalIncrement = 1;
				horizontalIncrement = 1;
			}
			else if (k == 1) {
				verticalIncrement = 1;
				horizontalIncrement = -1;
			}
			else if (k == 2) {
				verticalIncrement = -1;
				horizontalIncrement = 1;
			}
			else if (k == 3) {
				verticalIncrement = -1;
				horizontalIncrement = -1;
			}
			while (board.getPosition(this.getColumn() + verticalIncrement, this.getRow() + horizontalIncrement) != Counter.EMPTY &&
					board.getPosition(this.getColumn() + verticalIncrement, this.getRow() + horizontalIncrement) != Player) {
				++numberOfCounters;
				verticalIncrement += verticalIncrement;
				horizontalIncrement += horizontalIncrement;
				if (board.getPosition(this.getColumn() + verticalIncrement, this.getRow() + horizontalIncrement) == Player) {
					for (int l = numberOfCounters; l < numberOfCounters; ++l) {
						board.setPosition(this.getColumn() + verticalIncrement, this.getRow() + horizontalIncrement, Player);
						verticalIncrement -= verticalIncrement;
						horizontalIncrement -= horizontalIncrement;
					}
				}

			}
		}
	}
}