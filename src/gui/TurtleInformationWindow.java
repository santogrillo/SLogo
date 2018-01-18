package gui;

import commands.TurtleDisplay;
import javafx.scene.layout.BorderPane;

public class TurtleInformationWindow extends Window{

	public TurtleInformationWindow(BorderPane p, TurtleDisplay td) {
		super(p);
		TurtleInformation data = new TurtleInformation(layout,td);
		data.getData();
	}
	
	

	@Override
	public void update() {
		// do nothing
		
	}

}

