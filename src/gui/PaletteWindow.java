package gui;

import commands.TurtleDisplay;
import controller.CommandController;
import javafx.scene.layout.BorderPane;

/**
 * @author Madhavi
 * Sets up the view for a palette showing users which indices match with which colors and shapes
 */
public class PaletteWindow extends Window {

	private Palette palette;
	
	public PaletteWindow(BorderPane r, TurtleDisplay td, CommandController cc) {
		super(r);
		palette = new Palette(layout, td, cc);
		//cc.executeCommand("fd 100");
		palette.createPalettes();
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
