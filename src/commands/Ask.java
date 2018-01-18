/**
 * @author Santo Grillo, Ben Welton
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

public class Ask extends BasicCommand {

	public Ask(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id)
			throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException {
		
		setRequestedTurtles(td, commands);
		String result = executeSublist(td, commands);
		td.getTurtleManager().restoreActive();
		return result;
	}

	private void setRequestedTurtles(TurtleDisplay td, CommandList commands)
			throws ArgumentCountException, MissingBracketException, NumberFormatException, InvalidArgumentException, InvalidCommandException {
		CommandList turtles = createSublist(commands);
		List<Integer> indices = new ArrayList<>();
		
		while(turtles.size()>0) {
			indices.add(Integer.parseInt(nextCommandValue(td, turtles, null)));
		}
		td.getTurtleManager().setTempActive(indices);
	}
	
	private String executeSublist(TurtleDisplay td, CommandList commands)
			throws ArgumentCountException, MissingBracketException, NumberFormatException, InvalidArgumentException, InvalidCommandException {
		CommandList sublist = createSublist(commands);
		String result = runSublist(td, sublist);
		return result;
	}

}
