/**
 * @author Ben Welton, Santo Grillo
 */

package controller;


import java.util.HashMap;

import commands.TurtleDisplay;
import commands.UserCommand;
import errors.ArgumentCountException;
import errors.InvalidCommandException;
import errors.MissingBracketException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;

import errors.InvalidArgumentException;
import var.Variable;

public class CommandController implements Model {
	private Parser parser;
	private TurtleDisplay display;
	
	private ObservableList<Variable> variableList;
	private ObservableList<UserCommand> UDCommandList;
	
	public CommandController() {
		variableList = FXCollections.observableArrayList();
		UDCommandList = FXCollections.observableArrayList();
		parser = new Parser(variableList, UDCommandList);
	}

	@Override
	public String executeCommand(String s) {
		String commandResult = new String();
		try {
			commandResult = parser.parseCommandList(s, display);
		} catch (InvalidCommandException | ArgumentCountException | MissingBracketException 
				| NumberFormatException | InvalidArgumentException e) {
			commandResult = e.getMessage();
		}
		return commandResult;
	}

	public ObservableList<Variable> getVariableList() {
		return variableList;
	}

	@Override
	public ObservableList<UserCommand> getUDCommandList() {
		return UDCommandList;
	}

	@Override
	public void setLanguage(String lang) {
		parser.setLanguage(lang);
	}
	
	public String getLanguage() {
		return parser.getLanguage();
	}
	
	public void addDisplay(TurtleDisplay td) {
		display = td;
	}

	public void loadWorkspace(Workspace ws) {
		variableList = ws.getVariableList();
		UDCommandList = ws.getUDCommandList();
		display.setColorMap(ws.getColorMap());
		parser.updateUDValues(variableList, UDCommandList);
	}
	
	public void saveWorkspace(Workspace ws) {
		ws.setVariableList(FXCollections.observableArrayList(variableList));
		ws.setUDCommandList(FXCollections.observableArrayList(UDCommandList));
		
		ObservableMap<Integer, Color> colorMap = FXCollections.observableMap(new HashMap<Integer, Color>());
		colorMap.putAll(display.getColorMap());
		ws.setColorMap(FXCollections.observableMap(colorMap));
	}

}

