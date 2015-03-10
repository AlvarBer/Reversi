package tp.pr4.control;

import tp.pr4.logic.Game;

/**
 * Class which controls the execution of the game in a console mode, presenting the user with a set of alternatives and asking which he/she wishes to choose until the game finishes. 
 * This, in principle, is the class Controller of the previous assignments, with some refactoring to use MVC.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 4
 */

public class ConsoleController extends Controller {
	
    /**
     * Class constructor.
     * 
     * @param factory The gameType factory to be used.
     * @param g The game which is to be played.
     */
	public ConsoleController(GameTypeFactory factory, Game g) {
		
	}
	
	
	/**
	 * It create the console view, and executes the game until it finishes or the user request to exit.
	 * It is assumed that this method is called just once. If it is called again, the behaviour is undefined.
	 */
	public void run() {
		
	}

}
