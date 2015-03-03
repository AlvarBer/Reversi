package tp.pr3.control;

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
