/**
 * @author Raphael Kim
 */
package gui;

import controller.CommandController;
import javafx.scene.layout.BorderPane;
import table.CommandTable;

/**
 * @author Raphael Kim ck174
 * Creates a BorderPane where the list of commands is shown
 * 
 */
public class CommandWindow extends Window {

	private CommandTable table;
	
	public CommandWindow(BorderPane r, CommandController c) {
		super(r);
		table = new CommandTable(layout, c);
		table.createTable();
	}
	
	public void update() {
		table.update();
	}
}
