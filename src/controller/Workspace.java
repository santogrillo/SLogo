/**
 * @author Ben Welton, Madhavi Rajiv
 */

package controller;

import commands.TurtleManager;
import commands.UserCommand;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import var.Variable;

public class Workspace {

	private String commandHistory;
	private ObservableMap<Integer, Color> colorMap;
	private ObservableMap<Integer, ImageView> shapesMap;
	private ObservableList<Variable> variableList;
	private ObservableList<UserCommand> UDCommandList;
	private TurtleManager tm;
	
	public void setCommandHistory(String history) {
		commandHistory=history;
	}
	
	public String getCommandHistory() {
		return commandHistory;
	}
	
	protected void setColorMap(ObservableMap<Integer, Color> map) {
		colorMap = map;
	}
	
	protected ObservableMap<Integer,Color> getColorMap(){
		return colorMap;
	}
	
	protected void setVariableList(ObservableList<Variable> variables) {
		variableList = variables;
	}
	
	protected ObservableList<Variable> getVariableList(){
		return variableList;
	}
	
	protected void setUDCommandList(ObservableList<UserCommand> UDCommands) {
		UDCommandList = UDCommands;
	}
	
	protected ObservableList<UserCommand> getUDCommandList(){
		return UDCommandList;
	}
	
	public void setTurtleManager(TurtleManager t) {
		tm = t;
	}
	
	public TurtleManager getTurtleManager() {
		return tm;
	}
	
}
