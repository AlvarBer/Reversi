package tp.pr4.control;

import tp.pr4.logic.Game;
import tp.pr4.logic.Counter;


/**
 * Class which controls the execution of the game in a windows mode.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 4
 */

public class WindowController extends Controller {
	
	/**
	 * Class constructor.
	 * 
	 * @param factory The gameType factory to be used.
	 * @param g The game which is to be played.
	 */
	public WindowController(GameTypeFactory factory, Game g) {
	
	}
	
	/**
	 * 	Creates the window view. It is assumed that this method is called just once. If it is called again, the behaviour is undefined.
	 */
	@Override
	public void run(){
		
	}
	
	public void makeMove(int col, int row, Counter turn) {
		
	}
	
	/**
	 * Undo the last move of the currently played game.
	 */
	public void undo() {
		
	}
	
	/**
	 * Reset the current game.
	 */
	public void reset() {
		
	}
	
	/**
	 * Change to a new game.
	 * 
	 * @param gameType The game to be played.
	 * @param dimX The width of the board (necessary only for Gravity).
	 * @param dimY The height of the board (necessary only for Gravity)
	 */
	public void changeGame(tp.pr4.logic.GameType gameType, int dimX, int dimY) {
		
	}
	
	/**
	 * Make a random move.
	 * 
	 * @param player The player who should make the random move.
	 */
	public void randomMove(Counter player) {
		
	}
	
	/**
	 * Quit the application.
	 */
	public void requestQuit() {
		
	}

}
