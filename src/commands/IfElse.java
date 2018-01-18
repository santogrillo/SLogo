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

public class IfElse extends BasicCommand{

	public IfElse(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id)
			throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException {
		
		String condition = this.nextCommandValue(td, commands, id);
		
		CommandList ifSublist = createSublist(commands);
		CommandList elseSublist = createSublist(commands);
		
		if(Double.parseDouble(condition) != 0) {
			return runSublist(td, ifSublist);
		}else {
			return runSublist(td, elseSublist);
		}
	}

}

