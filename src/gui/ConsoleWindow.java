package gui;

import controller.CommandController;
import controller.Workspace;
import javafx.scene.layout.BorderPane;

/**
 * @author Madhavi, Raphael
 * This class extends Window to create a pane especially for the console.
 */

public class ConsoleWindow extends Window {

	private Console console;
	private CommandController control;
	
	public ConsoleWindow(BorderPane r, CommandController cc) {
		super(r);
		console = new Console(layout, cc);
		console.createConsole();
		control=cc;
	}
	
	/**
	 * Clears the text in the input console
	 */
	public void clearInput() {
		console.clearInput();
	}
	
	/**
	 * @param s
	 * Adds text s to the input textArea
	 */
	public void addInput(String s) {
		console.addInput(s);
	}
	
	public void update() {
		// DO NOTHING
	}

	/**
	 * @param newWS
	 * Adds command history to a new workspace and then sends it to backend 
	 */
	public void editAndSendWorkspace(Workspace newWS) {
		newWS.setCommandHistory(console.getCommandHistory());
		control.saveWorkspace(newWS);
	}

	/**
	 * @param ws
	 * Sets the command history to one saved in a workspace
	 */
	public void setWorkspace(Workspace ws) {
		console.setConsoleOutput(ws.getCommandHistory());
		control.loadWorkspace(ws);	
	}
	
	/**
	 * @return Returns the controller
	 */
	public CommandController getController() {
		return control;
	}
	
		
	/**
	 * @param s Have the controller to execute a command s
	 */
	public void executeCommand(String s) {
		control.executeCommand(s);
	}
}
