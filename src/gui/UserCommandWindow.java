package gui;

import controller.CommandController;
import javafx.scene.layout.BorderPane;
import table.UserCommandTable;

/**
 * @author Raphael Kim ck174
 * Creates a BorderPane on which a list of user defined methods is displayed
 */
public class UserCommandWindow extends Window {

private UserCommandTable coms;
	
	public UserCommandWindow(BorderPane p, CommandController c) {
		super(p);
		coms = new UserCommandTable(layout, c);
		coms.createTable();
	}
	
	public void update() {
		coms.update();
	}
	
}
