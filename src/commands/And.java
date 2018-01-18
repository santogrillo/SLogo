/**
 * @author Santo Grillo, Ben Welton
 */
package commands;

import errors.ArgumentCountException;
import errors.InvalidArgumentException;
import errors.InvalidCommandException;
import errors.MissingBracketException;
import javafx.collections.ObservableList;
import var.Variable;

public class And extends BasicCommand{
	
	public And(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}
	
	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id) throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException {
		double val1 = Double.parseDouble(nextCommandValue(td, commands, id));
		double val2 = Double.parseDouble(nextCommandValue(td, commands, id));
		return (val1 != 0 && val2 != 0) ? "1" : "0";
	}
}
