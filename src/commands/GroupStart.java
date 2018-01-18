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

public class GroupStart extends BasicCommand {

	public GroupStart(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id)
			throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException {
		BasicCommand command = nextCommand(commands);
		
		CommandList inputs = new CommandList();
		BasicCommand next = nextCommand(commands);
		
		//build inputs list
		
		int depth = 1;
		while(depth > 0) {
			inputs.add(next);
			if(next instanceof GroupStart) { depth++; }
			next = nextCommand(commands);
			if(next instanceof GroupEnd) { depth--; }
		}
		
		//iterate over the list
		
		String result = "0";
		
		while(inputs.size() > 0) {
			result = command.executeCommand(td, inputs, id);
			
			if(command.isCumalative() && inputs.size() > 0) {
				inputs.add(0, new Constant(result, varList, methodList));
			}
		}
		
		return result;
	}

}
