package tp.pr5.logic;

/**
 * Stores the information about the board. The size is fixed at the moment of construction.
 * It contains methods to access the information of each cell and to place counters
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 08/01/2015
 * @since: Assignment 1
 */

public class Board implements ReadOnlyBoard {

	//Attributes
	private Counter[][] board;
	private int width;
	private int height;

	//Constructor

	/**
	 * Constructs an empty board.
	 *
	 * @param tx Number of columns (max. x coordinate).
	 * @param ty Number of rows (max. y coordinate).
	 */
	public Board(int tx, int ty) {
		if (tx < 1 || ty < 1) {
			tx = 1;
			ty = 1;
		}
		this.width = tx;
		this.height = ty;
		this.board = new Counter[tx][ty];
		reset();
	}

	//Methods

	/**
	 * Observer method which returns the height of the board
	 *
	 * @return Number of rows of the board.
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Observer method which returns the width of the board.
	 *
	 * @return Number of columns of the board.
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Observer method which returns the state of a particular position on the board (i.e. the value of the counter at that position).
	 *
	 * @param x Column number (From 1 to width)
	 * @param y Row number (From 1 to height)
	 * @return State of the position on the board. If the position is invalid, returns Counter.EMPTY
	 */
	public Counter getPosition(int x, int y) {
		if (x < 1 || y < 1 || x > this.width || y > this.height)
			return Counter.EMPTY;
		else
			return this.board[x - 1][y - 1];
	}

	/**
	 * Mutator method to place a counter at a particular position on the board.
	 * Can also be used to remove a counter from the position provided (by changing it to Counter.EMPTY).
	 *
	 * @param x      Column number (From 1 to width)
	 * @param y      Row number (From 1 to height)
	 * @param colour Colour of the counter. A value is Counter.EMPTY is used to indicate leaving the position without a counter.
	 */
	public void setPosition(int x, int y, Counter colour) {
		try {
			this.board[x - 1][y - 1] = colour;
		} catch (java.lang.ArrayIndexOutOfBoundsException ex) {
			//Maybe we should do something with the exception like
			//ex.printStackTrace();
		}
	}

	/**
	 * Method which removes all the counters of the board (by changing them to Counter.EMPTY).
	 */
	public void reset() {
		for (int j = 1; j <= this.width; j++) {
			for (int i = 1; i <= this.height; i++)
				setPosition(j, i, Counter.EMPTY);
		}
	}

	/**
	 * Method which converts the board to a String to be displayed on the console.
	 * "X" as Black counters, "O" as White counters and empty spaces as Empty counters.
	 *
	 * @return Text representation of the board
	 */
	@Override
	public String toString() {
		String boardStr = "";
		for (int i = 1; i <= this.height; i++) {
			boardStr = boardStr + "|";
			for (int j = 1; j <= this.width; j++) {
				if (getPosition(j, i) == Counter.BLACK) {
					boardStr = boardStr + "X";
				} else if (getPosition(j, i) == Counter.WHITE) {
					boardStr = boardStr + "O";
				} else {
					boardStr = boardStr + " ";
				}
			}
			boardStr = boardStr + "|";
			boardStr = boardStr + "\n";
		}

		boardStr = boardStr + "+";
		for (int i = 1; i <= this.width; i++)
			boardStr = boardStr + "-";
		boardStr = boardStr + "+\n";

		boardStr = boardStr + " ";

		for (int j = 1; j <= this.width; j++)
			boardStr = boardStr + j % 10;

		boardStr = boardStr + "\n";
		return boardStr;
	}
}