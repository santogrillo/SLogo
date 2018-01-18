/**
 * @author Santo Grillo
 */
package commands;

import errors.ArgumentCountException;
import errors.InvalidArgumentException;
import errors.InvalidCommandException;
import errors.MissingBracketException;
import javafx.collections.ObservableList;
import var.DoubleVariable;
import var.Variable;

public class Var extends BasicCommand {

	public Var(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id)
			throws NumberFormatException, ArgumentCountException, InvalidArgumentException, InvalidCommandException {
		Variable newVar = lookupVariable(name, varList);
		if(newVar != null) return newVar.getValue().getValue().toString();
		newVar = new DoubleVariable(name, 0);
		varList.add(newVar);
		return newVar.getValue().getValue().toString();
	}

}
