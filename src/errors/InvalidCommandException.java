/**
 * @author Ben Welton, Santo Grillo
 */
package errors;

public class InvalidCommandException extends Exception{

	public InvalidCommandException(String command) {
		super("ERROR: " + command + " is not a valid command");
	}
	
}
