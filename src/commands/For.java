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

public class For extends BasicCommand {

	public For(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id)
			throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidCommandException, InvalidArgumentException {

		CommandList inputs = createSublist(commands);

		// get the local iterator, and save the global value to prevent overwriting it
		Var var = (Var) nextCommand(inputs);
		storeValue(var.name);
		// loop parameters
		double start = Double.parseDouble(nextCommandValue(td, inputs, id));
		double end = Double.parseDouble(nextCommandValue(td, inputs, id));
		double increment = Double.parseDouble(nextCommandValue(td, inputs, id));

		// build the loop sublist
		CommandList sublist = createSublist(commands);

		if (sublist.size() <= 0)
			return "0";

		// run the loop
		String result = "0";
		Variable v = lookupVariable(var.name, varList);
		for (v.setValue(start); v.getValue().getValue().doubleValue() < end; v
				.setValue(v.getValue().getValue().doubleValue() + increment)) {
			CommandList temp = new CommandList();
			temp.addAll(sublist);
			result = runSublist(td, temp);
		}

		restoreLocals();

		return result;
	}

}
