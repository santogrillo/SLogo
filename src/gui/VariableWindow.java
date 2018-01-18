package gui;

import controller.CommandController;
import javafx.scene.layout.BorderPane;
import table.VariableTable;

/**
 * 
 * @author Raphael Kim ck174
 * Creates a window on which a list of user declared variables is displayed
 */
public class VariableWindow extends Window {

	private VariableTable vars;
	
	public VariableWindow(BorderPane p, CommandController c) {
		super(p);
		vars = new VariableTable(layout, c);
		vars.createTable();
	}
	
	public void update() {
		vars.update();
	}
	
}
