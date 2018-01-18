/**
 * @author Ben Welton, Santo Grillo
 */

package controller;

import java.util.Scanner;

import commands.CommandList;
import commands.TurtleDisplay;
import commands.UserCommand;
import errors.ArgumentCountException;
import errors.InvalidArgumentException;
import errors.InvalidCommandException;
import errors.MissingBracketException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import var.Variable;


public class Parser {
	private final String DEFAULT_LANGUAGE = "English";
	private final String DEFAULT_SYNTAX = "Syntax";
	ObservableList<Variable> variables;
	ObservableList<UserCommand> commands;
	private String language;
	private CommandFactory cf;

	protected Parser(ObservableList<Variable> vars, ObservableList<UserCommand> commands) {
		variables = vars;
		this.commands = commands;
		language = DEFAULT_LANGUAGE;
		cf = new CommandFactory(DEFAULT_LANGUAGE, DEFAULT_SYNTAX, vars, commands);
	}
	
	protected String parseCommandList(String s, TurtleDisplay td)
			throws InvalidCommandException, NumberFormatException, ArgumentCountException, MissingBracketException, InvalidArgumentException {
		
		CommandList commandList = new CommandList();
		Scanner scan = new Scanner(s);
		scan.useDelimiter("\\s+");
		while (scan.hasNext()) {
			String next = scan.next().trim();
			if (next.equals("#")) {
				scan.nextLine();
			} else {
				commandList.add(cf.buildCommand(next));
			}
		}
		
		scan.close();
		
		String result = new String();
		while (commandList.size() > 0) {
			result = commandList.nextCommand().executeCommand(td, commandList, null);
		}
		return result;
	}
	
	protected void setLanguage(String lang) {
		language = lang;
		cf = new CommandFactory(language, DEFAULT_SYNTAX, variables, commands);
	}
	
	protected String getLanguage() {
		return language;
	}
	
	protected void updateUDValues(ObservableList<Variable> vars, ObservableList<UserCommand> commands) {
		cf.updateUDValues(vars, commands);
	}
}

