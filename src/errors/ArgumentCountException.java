/**
 * @author Ben Welton, Santo Grillo
 */
package errors;

public class ArgumentCountException extends Exception{

	public ArgumentCountException() {
		super("ERROR: The number of arguments passed into this command is incorrect");
	}
}
