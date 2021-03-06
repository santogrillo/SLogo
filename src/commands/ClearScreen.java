/**
 * @author Santo Grillo, Ben Welton
 */
package commands;

import java.util.Set;

import errors.ArgumentCountException;
import errors.InvalidArgumentException;
import errors.InvalidCommandException;
import errors.MissingBracketException;
import javafx.collections.ObservableList;
import var.Variable;

public class ClearScreen extends BasicCommand{
	public ClearScreen(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id) throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException {
		
		Set<Integer> idSet = td.getTurtleManager().getIDSet();
		String result = "";
		
		for(Integer ID : idSet) {
			Turtle turtle = td.getTurtle(ID);
			result = Double.toString(turtle.clearScreen());
		}
		return result;
	}
}