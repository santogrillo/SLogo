/**
 * @author Ben Welton, Santo Grillo
 */
package errors;

public class MissingBracketException extends Exception{

	public MissingBracketException() {
		super("ERROR: This set of commands is missing a bracket.");
	}
}
