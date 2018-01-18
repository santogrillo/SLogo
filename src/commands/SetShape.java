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

public class SetShape extends BasicCommand{

	public SetShape(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id) 
			throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException{
		
		double result = 1;
		CommandList temp = new CommandList();
		
		for(SingleTurtle t:td.getTurtleManager().getActiveTurtles()) {
			temp = (CommandList) commands.clone();
			double index = Double.parseDouble(nextCommandValue(td, temp, t.getID()));
			td.setShapeToIndex((int) index, t.getID());
		}
		
		flushOldCommandList(commands, temp);
		return Double.toString(result);
	}
}
