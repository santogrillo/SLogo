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

public class Sum extends BasicCommand {
	public Sum(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
		setCumalative(true);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id) throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException {
		double val1 = Double.parseDouble(nextCommandValue(td, commands, id));
		double val2 = Double.parseDouble(nextCommandValue(td, commands, id));
		return Double.toString(val1 + val2);
	}

}
