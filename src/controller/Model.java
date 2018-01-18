/**
 * @author Ben Welton, Santo Grillo
 */

package controller;

import commands.UserCommand;
import javafx.collections.ObservableList;
import var.Variable;

/**
 * The back-end interface that will take in user commands in slogo syntax and compile them into java commands.
 * It also stores all of the information regarding variables and user defined methods.
 */
public interface Model {

	/**
	 * Takes in user command, compiles, and executes it onto turtle display
	 * @param s command being compiled
	 * @param d turtle display on which outcome will be rendered
	 * @return return statement of the command (either return value or error message)
	 */
	public String executeCommand(String s);
	
	/**
	 * Gets a map where the key is variable name, and the value is what's stored in the variable
	 * @return a map of variables
	 */
	public ObservableList<Variable> getVariableList();
	
	/**
	 * Gets a map where the key is user defined method name, and the value is the set of commands in that method
	 * @return a map of user defined methods
	 */
	public ObservableList<UserCommand> getUDCommandList();
	
	/**
	 * Configures the model's back-end to compile in provided language
	 * @param lang the language to compile in
	 */
	public void setLanguage(String lang);
	
}
