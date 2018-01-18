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
import var.DoubleVariable;
import var.Variable;

public abstract class BasicCommand {
	protected ObservableList<Variable> varList;
	protected ObservableList<UserCommand> methodList;
	protected List<Variable> savedVars;
	protected List<Variable> localVars;
	protected String name;
	private boolean cumalative;
	
	/**
	 * 
	 * @param name - the name of the command, usually literally what was written in the original input
	 * @param vars - the global variable list
	 * @param methods - the global user method list
	 */
	public BasicCommand(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		varList = vars;
		methodList = methods;
		this.name = name;
		savedVars = new ArrayList<Variable>();
		localVars = new ArrayList<Variable>();
		cumalative = false;
	}
	
	
	/**
	 * 
	 * @param td - the turtle display the command interacts with
	 * @param commands - the command queue remaining
	 * @param id - the id of the currently active turtle
	 * @return - A string which is the result of running the next command (and any subsequent commands that it uses to execute)
	 */
	public abstract String executeCommand(TurtleDisplay td, CommandList commands, Integer id)
			throws NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException;
	
	/**
	 * 
	 * @return - the name of the command
	 */
	public String getName() {
		return new String(name);
	}
	
	/**
	 * 
	 * @param commands - the remaining command list
	 * @return - returns the next command in the list
	 */
	protected BasicCommand nextCommand(CommandList commands) throws ArgumentCountException {
		return commands.nextCommand();
	}

	/**
	 * 
	 * @param commands - the remaining command list
	 * @return - returns the name of the next command in the list
	 */
	protected String nextCommandName(CommandList commands) throws ArgumentCountException {
		return nextCommand(commands).getName();
	}
	
	/**
	 * 
	 * @param td - the active turtle display
	 * @param commands - the remaining commands list
	 * @param id - the id of the currently active turtle
	 * @return - returns the result of executing the next command in the list
	 */
	protected String nextCommandValue(TurtleDisplay td, CommandList commands, Integer id) throws ArgumentCountException, MissingBracketException, NumberFormatException, InvalidArgumentException, InvalidCommandException {
		return nextCommand(commands).executeCommand(td, commands, id);
	}
	
	/**
	 * Parses the command list and returns the command sequence bounded by list start and end  '[' ']' 
	 * @param commands - the commands lisst
	 * @return - returns a commandList containing all commands bounded by [ and ], exclusive
	 */
	protected CommandList createSublist(CommandList commands) throws MissingBracketException, ArgumentCountException {
		if (!(nextCommand(commands) instanceof ListStart)) {
			throw new MissingBracketException();
		}
		CommandList sublist = new CommandList();
		BasicCommand command = commands.nextCommand();
		if(command instanceof ListEnd) { return sublist;}
		int depth = 1;
		while(depth > 0) {
			sublist.add(command);
			command = commands.nextCommand();
			if(command instanceof ListStart) { depth++; }
			if(command instanceof ListEnd) { depth--; }
		}
		return sublist;
	}
	
	/**
	 * Executes the given commandList
	 * @param td - the turtledisplay
	 * @param sublist - the commandList to be executed
	 * @return - the final result from executing the sublist
	 */
	protected String runSublist(TurtleDisplay td, CommandList sublist) throws ArgumentCountException, MissingBracketException, NumberFormatException, InvalidArgumentException, InvalidCommandException {
		String result = "0";
		while (sublist.size() > 0) {
			result = sublist.nextCommand().executeCommand(td, sublist, null);
		}
		return result;
	}
	
	/**
	 * Returns the Variable corresponding to the given name from the given list
	 * @param varName - the name of the variable to look for
	 * @param list - the list to search
	 * @return - returns the Variable object which matches the name, returns null if not found
	 */
	protected Variable lookupVariable(String varName, List<Variable> list) {
		
		for(Variable v : list) {
			if(v.getName().getValue().equals(varName)) {
				return v;
			}
		}
		return null;
	}
	
	protected UserCommand lookupCommand(String varName, List<UserCommand> list) {
		
		for(UserCommand uc : list) {
			if(uc.getName().equals(varName)) {
				return uc;
			}
		}
		return null;
	}
	
	/** 
	 *  used to ensure that "local" variables used by a command do not overwrite the global values at the end of command execution
	 */
	protected void restoreLocals() {
		//restore the global variables to their previous state
		for(Variable local : savedVars) {
			Variable global = lookupVariable(local.getName().getValue(), varList);
			if(global != null) {
				global.setValue(local.getValue().getValue());
			}
		}
		
		//remove local only variables from the list
		varList.removeAll(localVars);
	}
	
	/**
	 *  Checks if a certain variable is used globally, and stores the value so it can be restored later
	 *  If the variable is not in the global table, it is added
	 */
	
	protected void storeValue(String varName)
	{
		Variable v = lookupVariable(varName, varList);
		if(v != null) {
			savedVars.add(new DoubleVariable(v.getName().getValue(), v.getValue().getValue().doubleValue()));
		}
		else {
			Variable temp = new DoubleVariable(varName, 0);
			varList.add(temp);
			localVars.add(temp);
		}
	}
	
	/**
	 * 
	 * Sets the cumalative marker, for unlimited input methods
	 */
	protected void setCumalative(boolean b) {
		cumalative = b;
	}
	
	/**
	 * 
	 * @return - returns whether the command should be cumalative with itself for unlimited inputs
	 */
	protected boolean isCumalative() {
		return cumalative;
	}
	
	/**
	 * checks if the next command in the list is a list start, to begin a sublist
	 * @param commands - commandList
	 */
	protected void checkForBracket(CommandList commands) throws ArgumentCountException, MissingBracketException {
		if(!(commands.nextCommand() instanceof ListStart)) throw new MissingBracketException();
	}
	
	protected void flushOldCommandList(CommandList commands, CommandList temp) throws ArgumentCountException {
		while(commands.size()>temp.size()) {
			commands.nextCommand();
		}
	}

}
