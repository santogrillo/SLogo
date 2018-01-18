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

public class SetPalette extends BasicCommand{

	public SetPalette(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id) 
			throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException{
		double index = Double.parseDouble(nextCommandValue(td, commands, id));
		double r = Double.parseDouble(nextCommandValue(td, commands, id));
		double g = Double.parseDouble(nextCommandValue(td, commands, id));
		double b = Double.parseDouble(nextCommandValue(td, commands, id));
		td.setColorIndex((int) index, (int) r, (int) g, (int) b);
		return Double.toString(index);
	}
}
