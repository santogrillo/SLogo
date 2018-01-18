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

public class DoTimes extends BasicCommand {

	public DoTimes(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id)
			throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException {
		
		CommandList inputs = createSublist(commands);

		// get the local iterator, and save the global value to prevent overwriting it
		Var var = (Var) nextCommand(inputs);
		storeValue(var.name);
		double max = Double.parseDouble(nextCommandValue(td, inputs, id));

		// build the loop sublist
		CommandList sublist = createSublist(commands);
		
		if (sublist.size() <= 0)
			return "0";

		// run the loop
		String result = "0";
		Variable v = lookupVariable(var.name, varList);
		for (v.setValue(1); v.getValue().getValue().doubleValue() < max; 
				v.setValue(v.getValue().getValue().doubleValue() + 1)) {
			CommandList temp = new CommandList();
			temp.addAll(sublist);
			result = runSublist(td, temp);
		}
		
		restoreLocals();

		return result;
	}

}
