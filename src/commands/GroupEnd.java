/**
 * @author Santo Grillo, Ben Welton
 */
package commands;

import errors.ArgumentCountException;
import errors.InvalidArgumentException;
import errors.InvalidCommandException;
import javafx.collections.ObservableList;
import var.Variable;

public class GroupEnd extends BasicCommand {

	public GroupEnd(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id)
			throws NumberFormatException, ArgumentCountException, InvalidArgumentException, InvalidCommandException {
		throw new InvalidArgumentException();
	}

}
