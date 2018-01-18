/**
 * @author Ben Welton, Santo Grillo
 */
package commands;

import java.util.ArrayList;
import java.util.List;

import errors.ArgumentCountException;
import errors.InvalidArgumentException;
import errors.InvalidCommandException;
import errors.MissingBracketException;
import javafx.collections.ObservableList;
import var.Variable;

public class Tell extends BasicCommand{

	public Tell(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id)
			throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException {
		
		CommandList turtles = createSublist(commands);
		List<Integer> indices = new ArrayList<>();
		String result = "0";
		
		while(turtles.size()>0) {
			result = nextCommandValue(td, turtles, id);
			indices.add((int) Double.parseDouble(result));
		}
		td.getTurtleManager().setActive(indices);
		return result;
	}

}
