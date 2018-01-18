package commands;

import java.util.ArrayList;
import java.util.List;

import errors.ArgumentCountException;
import errors.InvalidArgumentException;
import errors.InvalidCommandException;
import errors.MissingBracketException;
import javafx.collections.ObservableList;
import var.Variable;

/**
 * @author Santo Grillo, Raphael Kim
 */
public class UserCommand {

	private String definition;
	private CommandList commandList;
	private ArrayList<String> inputsList;
	
	public UserCommand(String name, ArrayList<String> inputs, CommandList commands) {
		this.definition = name;
		inputsList = inputs;
		this.commandList = commands;
	}
	
	public String getDefinition() {
		
		String result = "";
		CommandList temp = new CommandList();
		temp.addAll(commandList);
		while(temp.size() > 0) {
			try {
				result += temp.nextCommand().name;
				result += " ";
			} catch (ArgumentCountException e) {
				result = "";
				break;
			}
		}
		
		return result;
	}
	
	public String getName() {
		return definition;
	}
	
	protected CommandList getCommands() {
		CommandList temp = new CommandList();
		temp.addAll(commandList);
		return temp;
	}
	
	protected void setInputs(ArrayList<String> inputs) {
		inputsList = inputs;
	}
	
	protected ArrayList<String> getInputVariables() {
		return inputsList;
	}
	
	protected void setCommandList(CommandList newList) {
		commandList = newList;
	}
	
	public String getFormattedInputVariables() {
		return inputsList.toString();
	}
}
