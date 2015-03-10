package tp.pr4.control;

import tp.pr4.logic.Board;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Move;

/**
 * Interface that represents a player; when the controller wants to know which move to execute next, 
 * it asks the player whose turn it is to provide a move. 
 * This is not a concept of the game (which receives the instructions of which move to execute), 
 * but rather of the control of the execution flow of the application, which is why it is in the control package rather than the logic package.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 3
 */
public interface Player {
	/**
	 * Returns the next move that this player is ready to execute. Different implementations can exhibit 
	 * different behaviours if passed a board as first argument on which it is not possible to place a counter
	 * of the colour passed as second argument, such as return an incorrect movement or return null, etc.
	 * 
	 * @param board The board on which to place the counter.
	 * @param colour Colour of the counter to be placed on the board. This parameter facilitates the implementation of some derived classes but others, those for which the objects have been configured on construction, will not need to use it.
	 * @return Move to be executed. Depending on the implementation, the move may, or may not, be assumed correct.
	 */
	Move getMove(Board board, Counter colour);
}