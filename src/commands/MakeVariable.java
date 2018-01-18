/**
 * @author Santo Grillo, Ben Welton
 */
package commands;

import errors.ArgumentCountException;
import errors.InvalidArgumentException;
import errors.InvalidCommandException;
import errors.MissingBracketException;
import javafx.collections.ObservableList;
import var.DoubleVariable;
import var.Variable;

public class MakeVariable extends BasicCommand {

	public MakeVariable(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id) throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException {
		String varName = nextCommandName(commands);
		System.out.println(commands.size());
		Double value = Double.parseDouble(this.nextCommandValue(td, commands, id));
		for(Variable v : varList)
		{
			if(v.getName().getValue().equals(varName)) {
				v.setValue(value);
				return value.toString();
			}
		}
		varList.add(new DoubleVariable(varName, value));
		System.out.println(varList.get(0).toString());
		return value.toString();
	}
}
