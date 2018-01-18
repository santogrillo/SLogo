/**
 * @author Ben Welton, Santo Grillo
 */
package commands;

import errors.ArgumentCountException;
import errors.InvalidArgumentException;
import errors.InvalidCommandException;
import errors.MissingBracketException;
import javafx.collections.ObservableList;
import var.Variable;

public class Tangent extends BasicCommand {
	public Tangent(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id) throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException {
		double degrees = Double.parseDouble(nextCommandValue(td, commands, id));
		double radians = Math.toRadians(degrees);
		double tangent = Math.tan(radians);
		return Double.toString(tangent);
	}	
}