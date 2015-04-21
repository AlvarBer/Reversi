 package tp.pr5;

import tp.pr5.control.ArgumentException;
import tp.pr5.control.Arguments;
import tp.pr5.control.Controller;

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
		Arguments arguments;
		try {
			arguments = new Arguments(args);
			Controller ctrl  = arguments.getController();
			ctrl.run();				
		} catch (ArgumentException e) {
			System.err.println("Incorrect use: " + e.getMessage());
			System.err.println("For more details, use -h|--help.");
			System.exit(1);
		}
		
	}
}
