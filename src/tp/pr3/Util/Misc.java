package tp.pr3.Util;

import tp.pr3.logic.Board;
import tp.pr3.logic.Counter;
import tp.pr3.logic.GravityMove;
import java.util.Random;

/**
 * Class which collects several static methods that serve as utilities in the rest of the classes of the program.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 08/01/2015
 * @since: Assignment 2
 */

public class Misc {

	//Methods

	/**
	 * Method which returns the upper counter of a given column that is not empty. If the column is full, it returns 1
	 *
	 * @param board  The board of the game
	 * @param column The column we are checking
	 * @return The corresponding row with the upper non-empty counter of a column
	 */
	public static int topCounter(Board board, int column) {
		int row = 1;
		while (row <= board.getHeight() && (board.getPosition(column, row) == Counter.EMPTY)) {
			++row;
		}

		return row; //row = 1 means the column it's full
	}

	/**
	 * Method that returns the inverse counter of another one (Either Black or White)
	 *
	 * @param turn Counter to be changed
	 * @return Inverse of the counter
	 */
	public static Counter changeTurn(Counter turn) {
		Counter newTurn;
		if (turn == Counter.WHITE) {
			newTurn = Counter.BLACK;
		} else {
			newTurn = Counter.WHITE;
		}
		return newTurn;
	}

	/**
	 * Method which converts a Counter to a String to be displayed on the console. (Either White or Black)
	 *
	 * @param turn Counter to be converted
	 * @return Text representation of the Counter
	 */
	public static String strTurn(Counter turn) {
		String turnStr;
		if (turn == Counter.WHITE) {
			turnStr = "White";
		} else {
			turnStr = "Black";
		}
		return turnStr;
	}

	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 */
	public static int randInt(int min, int max) {

		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	/**
	 * Given a board and a position returns a boolean telling if it's a valid position or not
	 *
	 * @param board The board we want to check
	 * @param x     Column we want to check
	 * @param x     Row we want to check
	 * @return Boolean The validity of the position
	 */
	public static boolean validPosition(Board board, int x, int y) { // We could pass only the board height & height
		boolean valid = false;                                       // instead of the whole board
		if (x > 0 && x <= board.getWidth() && y > 0 && y <= board.getHeight()) {
			valid = true;
		}

		return valid;
	}

}