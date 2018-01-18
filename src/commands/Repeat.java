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

public class Repeat extends BasicCommand {

	public Repeat(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id)
			throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidCommandException, InvalidArgumentException {

		double max = Double.parseDouble(nextCommandValue(td, commands, id));

		CommandList sublist = createSublist(commands);
		
		if (sublist.size() <= 0)
			return "0";

		String result = "0";

		for (double i = 0; i < max; i++) {
			CommandList temp = new CommandList();
			temp.addAll(sublist);
			result = runSublist(td, temp);
		}

		return result;
	}

}
