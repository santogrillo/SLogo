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

public class AskWith extends BasicCommand{

	public AskWith(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
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
		CommandList condition = createSublist(commands);
		CommandList copy = (CommandList) condition.clone();
		List<Integer> indices = new ArrayList<>();
		List<Integer> currentTurtle = new ArrayList<>();
		
		for(Integer id:td.getTurtleManager().getIDSet()) {
			currentTurtle.add(id);
			td.getTurtleManager().setTempActive(currentTurtle);
			if(Double.parseDouble(runSublist(td, condition)) != 0) indices.add(id);
			td.getTurtleManager().restoreActive();
			currentTurtle.remove(0);
			condition = (CommandList) copy.clone();
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
