package commands;

import errors.ArgumentCountException;
import errors.InvalidArgumentException;
import errors.InvalidCommandException;
import errors.MissingBracketException;
import javafx.collections.ObservableList;
import var.Variable;

public class ClearStamps extends BasicCommand {

	public ClearStamps(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id) throws NumberFormatException,
			ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException {
		
		return (td.clearStamps() ? "1" : "0");
	}

}
