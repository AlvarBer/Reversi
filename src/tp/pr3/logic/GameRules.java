package tp.pr3.logic;

/**
 * Interface that represents the rules of the game. The game delegates in an object of a class that implements this interface the progression of the game.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 08/01/2015
 * @since: Assignment 2
 */

public interface GameRules {

	//Methods

	/**
	 * Build a board that is to be used in the game, according to the rules of that game.
	 *
	 * @return Board to be used. The state of the board is that at the start of the game.
	 */
	Board newBoard();

	/**
	 * Return the colour of the player that starts the game.
	 *
	 * @return Colour the first player to place a counter.
	 */
	Counter initialPlayer();

	/**
	 * Returns true if, with the current board, the game has finished in a draw.
	 *
	 * @param lastPlayer Last player to make a move.
	 * @param board      The current board.
	 * @return True if the game has finished in a draw.
	 */
	boolean isDraw(Counter lastPlayer, Board board);

	/**
	 * Returns the colour of the player whose turn it is.
	 *
	 * @param lastPlayer Last player to make a move.
	 * @param board      The current board.
	 * @return Next player to make a move.
	 */
	Counter nextTurn(Counter lastPlayer, Board board);

	/**
	 * Checks whether or not, with the current board, one of the players has won and, if so, returns the colour of the winner
	 *
	 * @param lastMove The last move performed. This parameter can be used in derived classes to optimise the calculation of whether one of the players has won.
	 * @param board    The current board.
	 * @return Colour of the winner, if there is one. If not, returns Counter.EMPTY (this does not necessarily mean that the game has ended in a draw).
	 */
	Counter winningMove(Move lastMove, Board board);
}
