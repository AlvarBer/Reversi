package tp.pr4.logic;

public class InvalidMove extends Exception {
	//Constructors
	public InvalidMove() {

	}

	public InvalidMove(String msg) {
		super(msg);
	}

	public InvalidMove(String msg, Throwable cause) {
		super(msg);
		cause.printStackTrace();
	}

	public InvalidMove(Throwable cause) {
		cause.printStackTrace();
	}
}