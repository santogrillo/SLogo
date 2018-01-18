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

public class SetHeading extends BasicCommand{
	public SetHeading(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id) throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException {
		double result = 0;
		CommandList temp = new CommandList();
		
		for(SingleTurtle t:td.getTurtleManager().getActiveTurtles()) {
			temp = (CommandList) commands.clone();
			double heading = Double.parseDouble(nextCommandValue(td, temp, t.getID()));
			result = t.setHeading(heading);
		}
		
		flushOldCommandList(commands, temp);
		return Double.toString(result);
	}
}
