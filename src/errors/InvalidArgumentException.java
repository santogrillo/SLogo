/**
 * @author Ben Welton, Santo Grillo
 */
package errors;

public class InvalidArgumentException extends Exception{

	public InvalidArgumentException() {
		super("ERROR: One of the arguments passed in is invalid for this command");
	}
}