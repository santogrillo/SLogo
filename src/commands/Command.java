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

public class Command extends BasicCommand {

	public Command(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id)
			throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidCommandException, InvalidArgumentException {
		UserCommand command = lookupCommand(name, methodList);
		
		if(command != null)
		{
			
			//set the initial value of the variables to the values passed into the function
			ArrayList<String> localVars = command.getInputVariables();
			for(int i = 0; i < localVars.size(); i++) {
				storeValue(localVars.get(i));
				lookupVariable(localVars.get(i), varList).setValue(Double.parseDouble(nextCommandValue(td, commands, id)));
			}
			
			return runSublist(td, command.getCommands());
		}
		
		throw new InvalidCommandException(name);
	}

}
