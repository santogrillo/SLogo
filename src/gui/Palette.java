package gui;

import java.util.ResourceBundle;

import commands.TurtleDisplay;
import controller.CommandController;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * @author Madhavi
 * Creates the view for a palette showing users which indices match with which colors and shapes
 */

public class Palette {
	
	private static final String SPACE = "      ";
	private static final int FONT_SIZE = 20;
	private BorderPane border;
	private ObservableMap<Integer, ImageView> observableShapeMap;
	private ObservableMap<Integer, Color> observableColorMap;
	private GridPane colorGrid = new GridPane();
	private GridPane turtleGrid = new GridPane();
	private CommandController controller;
	
	private static final String LABELS = "resources.labels/labels";
	private static ResourceBundle labelResources = ResourceBundle.getBundle(LABELS);

	
	public Palette(BorderPane bp, TurtleDisplay td, CommandController cc) {
		border = bp;
		border.setLeft(colorGrid);
		border.setRight(turtleGrid);
		observableColorMap = td.getColorMap();
		observableShapeMap = td.getShapeMap();
		controller = cc;
	}

	/**
	 * Displays the mapping for colors and turtle shapes, listens to
	 * the observable color map to see if the palette needs to be updated
	 */
	public void createPalettes() {
		createColorPalette();
        observableColorMap.addListener(new MapChangeListener<Integer,Color>() {

			@Override
			public void onChanged(Change<? extends Integer, ? extends Color> change) {
				createColorPalette();			
			}
        });
		createTurtlePalette();
	}

	private void createColorPalette() {
		int i = 0;
		for(Integer key: observableColorMap.keySet()) {
			Label index = new Label(key.toString() + SPACE);
			index.setFont(new Font(FONT_SIZE));
			colorGrid.add(index, 0, i);
			Rectangle paint = new Rectangle(60,20);
			paint.setFill(observableColorMap.get(key));
			colorGrid.add(paint, 1,i);
			MenuButton mb = new MenuButton(labelResources.getString("set"), null);
			Factory.createMenuItemInMenu(labelResources.getString("backgroundcolor"), e->controller.executeCommand("setbg "+key),mb);
			Factory.createMenuItemInMenu(labelResources.getString("pencolor"), e->controller.executeCommand("setpc "+key),mb);
			colorGrid.add(mb, 2, i);
			i++;
		}
	}
	
	private void createTurtlePalette() {
        for(Integer key: observableShapeMap.keySet()) {
            String index = SPACE + key.toString() +SPACE;
            ImageView turtle = observableShapeMap.get(key);
            Label indx = new Label(index);
            indx.setFont(new Font(FONT_SIZE));
            turtleGrid.add(indx, 0, Integer.parseInt(key.toString()+""));  
            turtleGrid.add(turtle, 1, Integer.parseInt(key.toString()+""));
        }
	}

}

