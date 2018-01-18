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

public class PenDown extends BasicCommand{
	public PenDown(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id) throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException {
		for(SingleTurtle t:td.getTurtleManager().getActiveTurtles()) {	
			t.penDown();
		}
		return "1";
	}
}
