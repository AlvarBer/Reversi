package tp.pr4.control;

/**
 * Class that contains the exceptions generated in the execution arguments handling process
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 3
 */

public class ArgumentException extends Exception {
	public ArgumentException() {

	}

	public ArgumentException(String msg) {
		super(msg);
	}

	public ArgumentException(String msg, Throwable cause) {
		super(msg);
		cause.printStackTrace();
	}

	public ArgumentException(Throwable cause) {
		cause.printStackTrace();
	}

}
