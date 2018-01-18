/**
 * @author Santo Grillo, Ben Welton
 */
package commands;

import java.util.ArrayList;

import errors.ArgumentCountException;
import errors.MissingBracketException;
import javafx.collections.ObservableList;
import var.Variable;

public class MakeUserCommand extends BasicCommand {

	public MakeUserCommand(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id)
			throws NumberFormatException, ArgumentCountException, MissingBracketException {
		String commandName = this.nextCommandName(commands);
		CommandList inputs = createSublist(commands);
		CommandList instructions = createSublist(commands);
		
		//store all the input variables
		ArrayList<String> inputList = new ArrayList();
		for(int i=0;i<inputs.size();i++) {
			inputList.add(inputs.get(i).name);
		}
		
		UserCommand newCommand = lookupCommand(commandName, methodList);
		if(newCommand == null)
		{
			methodList.add(new UserCommand(commandName, inputList, instructions));
		}
		else
		{
			newCommand.setCommandList(instructions);
			newCommand.setInputs(inputList);
		}
		
		return "1.0";
	}

}
