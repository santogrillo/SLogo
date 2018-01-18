/**
 * @author Madhavi Rajiv, Ben Whelton
 */
package commands;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import errors.InvalidArgumentException;
import gui.Factory;
import gui.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Displays the turtle and its movement in live time. Keeps track of the current turtle's state.
 * The model can command the turtle to move or behave in a specific manner through this interface.
 *
 */
public class TurtleDisplay extends Window {
	private static final String COLOR = "editBarResources/Color";
	private static final String TURTLES = "editBarResources/turtles";
	
	private ResourceBundle colorResources = ResourceBundle.getBundle(COLOR);
	private ResourceBundle shapeResources = ResourceBundle.getBundle(TURTLES);
	
	private Pane grid;
	private TurtleManager turtles;
	private ObservableMap<Integer, Color> colors;
	private ObservableMap<Integer, String> colorStrings;
	private ObservableMap<Integer, ImageView> shapes;
	private ObservableMap<Integer, String> shapeNames;
	private int colorIndex = 1;
	private int shapeIndex = 1;
	private List<ImageView> stamps = new ArrayList<ImageView>();

	public TurtleDisplay(BorderPane r) {
		super(r);
		grid = new Pane();
		grid.setPadding(new Insets(PADDING));
		grid.setPrefSize(getRoot().getPrefWidth(), getRoot().getPrefHeight());
		turtles = new TurtleManager(grid);
		initializeColorMap();
		initializeShapeMap();
	}

	/**
	 * Creates the initial hardcoded color palette mapping
	 */
	private void initializeColorMap() {
		Map<Integer, Color> colorMap = new HashMap<>();
		Map<Integer, String> colorStringMap = new HashMap<>();
		int i = 1;
		
		Enumeration<String> iter = colorResources.getKeys();
		while(iter.hasMoreElements()) {
			String key = iter.nextElement();
			Color color = Color.web(colorResources.getString(key));
			colorMap.put(i, color);
			colorStringMap.put(i, key);
			i++;
		}
		
		colors = FXCollections.observableMap(colorMap);
		colorStrings = FXCollections.observableMap(colorStringMap);
	}
	
	/**
	 * Creates the initial hardcoded shape index mapping
	 */
	private void initializeShapeMap() {
		Map<Integer, ImageView> shapeMap = new HashMap<>();
		Map<Integer, String> shapeNameMap = new HashMap<>();
		int i = 1;
		
		Enumeration<String> iter = shapeResources.getKeys();
		Factory f = new Factory();
		while(iter.hasMoreElements()) {
			String key = iter.nextElement();
			ImageView shape = f.getImageView(key);
			shapeMap.put(i, shape);
			shapeNameMap.put(i, key);
			i++;
		}
		
		shapes = FXCollections.observableMap(shapeMap);
		shapeNames = FXCollections.observableMap(shapeNameMap);
	}

	@Override
	public void start() {
		getRoot().setCenter(grid);
	}
	
	/**
	 * @param colorStyle
	 * Sets the color of the background based on a css styling input colorStyle
	 */
	public void setColor(String colorStyle) {
		grid.setStyle(colorStyle);
	}
	
	/**
	 * @param col
	 * @param id
	 * Takes in a Color col and a Turtle ID id and sets the turtle's pen color to col
	 */
	public void setColorPen(Color col, Integer id) {
		SingleTurtle turtle = turtles.getID(id);
		turtle.setPenColor(col);
	}
	
	/**
	 * @param image
	 * @param imageName
	 * @param id
	 * Takes in an image, it's name, and a turtle ID to set that turtle's image to the input image.
	 */
	public void setTurtleImage(ImageView image, String imageName, Integer id) {
		SingleTurtle turtle = turtles.getID(id);
		int imageID = 1;
		for(Integer key : shapes.keySet()) {
			if(shapes.get(key).equals(image)) {imageID = key;}
		}
		turtle.setImage(image.getImage(), imageName, imageID);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//do nothing;
		
	}
	
	/**
	 * @param id
	 * @return Returns the SingleTurtle with the input id
	 */
	public SingleTurtle getTurtle(Integer id) {
		return turtles.getID(id);
	}
	
	/**
	 * @return Returns the total number of created turtles
	 */
	public int getNumTurtles() {
		return turtles.getNumTurtles();
	}
	
	/**
	 * @param pixels
	 * @param id
	 * @return Sets the pen size of a turtle of a certain ID
	 */
	protected double setPenSize(double pixels, Integer id) {
		SingleTurtle turtle = turtles.getID(id);
		turtle.setPenSize(pixels);
		return pixels;
	}
	
	/**
	 * @param index
	 * @param r
	 * @param g
	 * @param b
	 * @return Maps an rgb color to an index and returns the index
	 * @throws InvalidArgumentException
	 * 
	 */
	protected int setColorIndex(int index, int r, int g, int b) throws InvalidArgumentException {
		if(r > 255 | g > 255 | b >255 | !colors.containsKey(index)) throw new InvalidArgumentException();
		Color color = Color.rgb(r, g, b);
		String colorString = "rgb(" + r + ", " + g + ", " + b + ");";
		colors.put(index, color);
		colorStrings.put(index, colorString);
		return index;
	}
	
	/**
	 * @param index
	 * @return Returns the index of the color, which is used to set the background to the 
	 * color mapped to the index
	 * @throws InvalidArgumentException
	 */
	protected int setColorToIndex(int index) throws InvalidArgumentException {
		if(!colors.containsKey(index)) throw new InvalidArgumentException();
		String background = "-fx-background-color: " + colorStrings.get(index);
		setColor(background);
		return index;
	}
	
	/**
	 * @param index
	 * @param id
	 * @return Sets shape to shape mapped to an index and returns the index
	 * @throws InvalidArgumentException
	 */
	protected int setShapeToIndex(int index, Integer id) throws InvalidArgumentException {
		if(!shapes.containsKey(index)) throw new InvalidArgumentException();
		setTurtleImage(shapes.get(index), shapeNames.get(index), id);
		shapeIndex = index;
		return index;
	}
	
	/**
	 * @param index
	 * @param id
	 * @return Returns index; sets a turtle with the given id's pen color to color mapped to index
	 * @throws InvalidArgumentException
	 */
	protected int setColorPenToIndex(int index, Integer id) throws InvalidArgumentException {
		if(!colors.containsKey(index)) throw new InvalidArgumentException();
		setColorPen(colors.get(index), id);
		colorIndex = index;
		return index;
	}
	
	/**
	 * @param ID
	 * @return Returns the pen color index of turtle with given ID
	 */
	public Integer getColorPen(Integer ID) {
		Color c = getTurtle(ID).getPenColor();
		for(Integer i : colors.keySet()) {
			if(colors.get(i).equals(c)) { return i; }
		}
		return 0;
	}
	
	/**
	 * @param ID
	 * @return Returns the shape index of turtle with given ID
	 */
	protected double getShape(Integer ID) {
		return this.getTurtle(ID).getShape();
	}
	
	/**
	 * @return Returns an observable map mapping indices to colors
	 */
	public ObservableMap<Integer, Color> getColorMap(){
		return colors;
	}
	
	/**
	 * @param colorMap
	 * Sets the color mapping
	 */
	public void setColorMap(ObservableMap<Integer, Color> colorMap) {
		colors.putAll(colorMap);
	}
	
	/**
	 * @return Returns the shape mapping
	 */
	public ObservableMap<Integer, ImageView> getShapeMap(){
		return shapes;
	}
	
	/**
	 * @return Returns the TurtleManager 
	 */
	public TurtleManager getTurtleManager() {
		return turtles;
	}

	public void addStamp(ImageView stamp)
	{
		stamps.add(stamp);
		grid.getChildren().add(stamp);
	}
	
	public boolean clearStamps() {
		boolean result = stamps.size() > 0;
		grid.getChildren().removeAll(stamps);
		stamps.clear();
		return result;
	}
	
}

