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
	private Position[] countersFlipped;
	private static final int NUMBER_OF_DIRECTIONS = 8;

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
		countersFlipped = new Position[NUMBER_OF_DIRECTIONS];
		for (int i = 0; i < NUMBER_OF_DIRECTIONS; ++i) {
			countersFlipped[i] = new Position ();
			countersFlipped[i].setPos(-1, -1);
		}
	}

	//Functions

	//Public Functions
	@Override
	public void executeMove(Board board) throws InvalidMove {
		if (Misc.validPosition(board, getColumn(), getRow())) {
			if (!board.getPosition(getColumn(), getRow()).equals(Counter.EMPTY.toString())) {
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
		//We change all the "End of the line" counters
		for (int i = 0; i < NUMBER_OF_DIRECTIONS; ++i) {
			if (countersFlipped[i].getPosX() != -1) {
				boa.setPosition(countersFlipped[i].getPosX(), countersFlipped[i].getPosY(),
					Misc.changeTurn(getPlayer()));
			}
		}

		//We change the Counter we put
		boa.setPosition(getColumn(), getRow(), Misc.changeTurn(getPlayer()));

		//We make the exact inverse move we want to undo
		try {
			flipIt(boa, Misc.changeTurn(getPlayer()));
		} catch (InvalidMove e) {
			//This call to flipIt is never going to fail
		}

		//We reverse the "End of the line" counters we flipped
		for (int i = 0; i < NUMBER_OF_DIRECTIONS; ++i) {
			if (countersFlipped[i].getPosX() != -1) {
				boa.setPosition(countersFlipped[i].getPosX(), countersFlipped[i].getPosY(),
						Misc.changeTurn(boa.getPosition(countersFlipped[i].getPosX(), countersFlipped[i].getPosY())));
			}
		}

		//And of course we eliminate the Counter the player put
		boa.setPosition(getColumn(), getRow(), Counter.EMPTY);
	}

	@Override
	public int getColumn() {
		return this.moveColumn;
	}

	@Override
	public int getRow() {
		return moveRow;
	}

	/**
	 * Checks if a move is legal (Counters are flipped)
	 *
	 * It assumes the counter is empty andi n a valid position, undefined behaviour otherwise
	 *
	 * @param board
	 * @param Player
	 * @return
	 */
	public boolean isLegal(ReadOnlyBoard board, Counter Player) {
		int verticalIncrement = 0, horizontalIncrement = 0;
		int currentColumn, currentRow, currentDirection = 0;
		boolean legal = false;

		//    Awesome Direction Scheme:
		//  Dir 0        Dir 1        Dir 2
		// (-1,-1)      (0,-1)       (1,-1)
		//                │
		//                │
		//  Dir 7         │           Dir 3
		// (-1,0) ────────┼────────── (1,0)
		//                │
		//                │
		//  Dir 6         │          Dir 4
		// (-1,1)       (0,1)        (1,1)
		//              Dir 5

		while (currentDirection < NUMBER_OF_DIRECTIONS && !legal) {
			if (currentDirection == 0) {
				verticalIncrement = -1;
				horizontalIncrement = -1;
			} else if (currentDirection == 1) {
				verticalIncrement = 0;
				horizontalIncrement = -1;
			}
			else if (currentDirection == 2) {
				verticalIncrement = 1;
				horizontalIncrement = -1;
			}
			else if (currentDirection == 3) {
				verticalIncrement = 1;
				horizontalIncrement = 0;
			}
			else if (currentDirection == 4) {
				verticalIncrement = 1;
				horizontalIncrement = 1;
			} else if (currentDirection == 5) {
				verticalIncrement = 0;
				horizontalIncrement = 1;
			} else if (currentDirection == 6) {
				verticalIncrement = -1;
				horizontalIncrement = 1;
			} else if (currentDirection == 7) {
				verticalIncrement = -1;
				horizontalIncrement = 0;
			}
			currentColumn = this.getColumn() + verticalIncrement;
			currentRow = this.getRow() + horizontalIncrement;
			while (Misc.validPosition(board, currentColumn, currentRow) && //While we don't hit a wall & counters are theirs
					board.getPosition(currentColumn, currentRow) == Misc.changeTurn(Player)) {
				currentColumn += verticalIncrement;
				currentRow += horizontalIncrement;
				if (Misc.validPosition(board, currentColumn, currentRow) && //If we hit a counter that is ours
						board.getPosition(currentColumn, currentRow) == Player) {
					legal = true;
				}
			}
			++currentDirection;
		}

		return legal;
	}

	/**
	 * Checks all possibles moves to see if any is legal
	 *
	 * @param board
	 * @param player
	 * @return
	 */
	public static boolean canMove(ReadOnlyBoard board, Counter player) {
		boolean valid = false;
		int i = 1, j;

		while (i <= board.getHeight() && !valid) {
			j = 1;
			while (j <= board.getWidth() && !valid) {
				if (board.getPosition(i, j) == Counter.EMPTY) {
					if (new ReversiMove(i, j, player).isLegal(board, player)){
						valid = true;
					}
				}
				++j;
			}
			++i;
		}

		return valid;
	}

	//Private Functions
	private void flipIt(Board board, Counter Player) throws InvalidMove {
		int verticalIncrement = 0, horizontalIncrement = 0, numberOfCounters;
		int currentColumn, currentRow;
		boolean throwAnException = true;

		for (int currentDirection = 0; currentDirection < NUMBER_OF_DIRECTIONS; ++currentDirection) {
			numberOfCounters = 0;
			if (currentDirection == 0) {
				verticalIncrement = -1;
				horizontalIncrement = -1;
			}
			else if (currentDirection == 1) {
				verticalIncrement = 0;
				horizontalIncrement = -1;
			}
			else if (currentDirection == 2) {
				verticalIncrement = 1;
				horizontalIncrement = -1;
			}
			else if (currentDirection == 3) {
				verticalIncrement = 1;
				horizontalIncrement = 0;
			}
			else if (currentDirection == 4) {
				verticalIncrement = 1;
				horizontalIncrement = 1;
			}
			else if (currentDirection == 5) {
				verticalIncrement = 0;
				horizontalIncrement = 1;
			}
			else if (currentDirection == 6) {
				verticalIncrement = -1;
				horizontalIncrement = 1;
			}
			else if (currentDirection == 7) {
				verticalIncrement = -1;
				horizontalIncrement = 0;
			}
			currentColumn = getColumn() + verticalIncrement;
			currentRow = getRow() + horizontalIncrement;
			while (Misc.validPosition(board, currentColumn, currentRow) && //While we don't hit a wall & counters are theirs
					board.getPosition(currentColumn, currentRow) == Misc.changeTurn(Player)) {
				++numberOfCounters;
				currentColumn += verticalIncrement;
				currentRow += horizontalIncrement;
				if (Misc.validPosition(board, currentColumn, currentRow) && //If we hit a counter that is ours
						board.getPosition(currentColumn, currentRow) == Player) {
					countersFlipped[currentDirection].setPos(currentColumn, currentRow); //Save "End of the line" position
					currentColumn -= verticalIncrement; //Go back one
					currentRow -= horizontalIncrement;
					for (int i = 0; i < numberOfCounters; ++i) { //Flip all the counters in between
						board.setPosition(currentColumn, currentRow, Player);
						currentColumn -= verticalIncrement;
						currentRow -= horizontalIncrement;
					}
				}
			}
		}

		//Now we check if we have done any flip at all, if the answer is no we throw an exception
		int i = 0;
		while (i < NUMBER_OF_DIRECTIONS && throwAnException) {
			if (countersFlipped[i].getPosX() != -1) {
				throwAnException = false;
				board.setPosition(this.getColumn(), this.getRow(), Player);
			}
			++i;
		}
		if (throwAnException) {
			throw new InvalidMove("No others counters were flipped");
		}
	}

	/**
	 * Class that facilitates the work with a position in the board
	 *
	 * @author: Alvaro Bermejo
	 * @author: Francisco Lozano
	 * @version: 25/04/2015
	 * @since: Assignment 5
	 */
	private class Position {
		//Attributes
		private int posX;
		private int posY;

		//Functions

		//Getters
		public int getPosX() {
			return posX;
		}

		public int getPosY() {
			return posY;
		}

		//Setters
		public void setPos(int posX, int posY) {
			this.posX = posX;
			this.posY = posY;
		}

	}
}