package tp.pr5.control;

/**
 * Class that contains the exceptions generated when a command is being managed
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 3
 */

public class CommandException extends Exception{
	public CommandException () {

	}
	
	public CommandException (String msg) {
		super (msg);
	}

	public CommandException (String msg, Throwable cause) {
	    super(msg);
        cause.printStackTrace();
	}

	public CommandException (Throwable cause) {
        cause.printStackTrace();
	}
}
