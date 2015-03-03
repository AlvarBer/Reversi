package tp.pr4;

import tp.pr4.control.ArgumentException;
import tp.pr4.control.Arguments;
import tp.pr4.control.Controller;
import tp.pr4.control.GameTypeFactory;
import tp.pr4.logic.Game;

import java.util.Scanner;

/**
 * Class that contains the entry point to the application.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 08/01/2015
 */

public class Main {

	//Methods	

	/**
	 * Main method of the application
	 *
	 * @param args Arguments passed to the application. Not used.
	 */
	public static void main(String[] args) {
		//Pending of accepting execution arguments. Till then, Connect4 is the game by default
		Arguments arguments;
		try {
			arguments = new Arguments(args);
			GameTypeFactory factory = arguments.getFactory();
			if (factory != null) {
				Game game = arguments.getGame();
				Scanner in = new Scanner(System.in);
				Controller control = new Controller(factory, game, in);
				control.run();
				System.exit(0);
			}
		} catch (ArgumentException e) {
			System.err.println("Incorrect use: " + e.getMessage());
			System.err.println("For more details, use -h|--help.");
			System.exit(1);
		}
	}
}
