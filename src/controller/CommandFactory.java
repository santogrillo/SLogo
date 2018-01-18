/**
 * @author Ben Welton, Santo Grillo
 */

package controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


import commands.BasicCommand;
import commands.UserCommand;
import errors.InvalidCommandException;
import javafx.collections.ObservableList;
import var.Variable;

public class CommandFactory {
	private static final String RESOURCE_DIR = "resources/languages/";
	
	private ResourceBundle commands;
	private ResourceBundle syntax;
	private Map<String, Pattern> myCommands;
	private Map<String, Pattern> mySyntax;
	private ObservableList<Variable> variables;
	private ObservableList<UserCommand> methods;
	
	public CommandFactory(String lang, String syn, ObservableList<Variable> vars, ObservableList<UserCommand> uclist) {
		commands = ResourceBundle.getBundle(RESOURCE_DIR + lang);
		syntax = ResourceBundle.getBundle(RESOURCE_DIR + syn);
		mySyntax = new HashMap<>();
		myCommands = new HashMap<>();
		addPatterns(commands, myCommands);
		addPatterns(syntax, mySyntax);
		variables = vars;
		methods = uclist;
	}
	
	//credit to Duvall
	private void addPatterns(ResourceBundle rb, Map<String,Pattern> map) {
        Enumeration<String> iter = rb.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = rb.getString(key);
            map.put(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
        }
    }
	
	protected BasicCommand buildCommand(String text) throws InvalidCommandException {
		String type = new String();
		for (String command:myCommands.keySet()) {
            if (match(text, myCommands.get(command))) {
                type = command;
            }
        }
		
		if(type.isEmpty()) {
			for(String syntax:mySyntax.keySet()) {
				if(match(text, mySyntax.get(syntax))) {
					type = syntax;
				}
			}
		}
		
		if(type.isEmpty()) {
			throw new InvalidCommandException(text);
		}else {
			try {
				Class<?> clazz = Class.forName("commands." + type);
				Constructor<?> ctor = clazz.getDeclaredConstructor(String.class, ObservableList.class, ObservableList.class);
				return (BasicCommand) ctor.newInstance(text, variables, methods);
			} catch (InstantiationException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				e1.printStackTrace();
				return null;
			}
		}

	}
	
	protected void updateUDValues(ObservableList<Variable> vars, ObservableList<UserCommand> commands) {
		variables = vars;
		commands = methods;
	}
	
	//credit to Duvall
	private boolean match (String text, Pattern regex) {
        return regex.matcher(text).matches();
    }
}
