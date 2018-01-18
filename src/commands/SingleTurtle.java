/**
 * @author Ben Welton, Madhavi Rajiv
 */
package commands;

import java.util.ResourceBundle;

import javafx.beans.binding.DoubleBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * @author Madhavi
 * This class takes care of the movement and other behaviors of the turtle as controlled by backend.
 */
public class SingleTurtle implements Turtle{

private static final int IMAGE_SIZE = 50;

private Image visual;
private Image invisible = new Image(getClass().getClassLoader().getResourceAsStream("invisible.png"));
private boolean visible;
private ImageView turtle;
private Pen pen = new Pen();

private DoubleBinding centerX;
private DoubleBinding centerY;

private double absoluteRotateZero;
private double absoluteRotateLocation;

private ObservableList<Integer> dataTracker;

private double x;
private double y;

private int imageIndex = 1;

private Pane root;

private int turtleID;

private boolean active;
private ObservableList<Integer> activeTracker;

private static final String TURTLE_ROTATE = "resources/turtleRotate";
private static ResourceBundle myResources = ResourceBundle.getBundle(TURTLE_ROTATE);

	public SingleTurtle(ImageView image, int ind) {
		sizeTurtle(image);
		turtle = image;
		visual = image.getImage();
		absoluteRotateZero = 0;
		absoluteRotateLocation = 0;
		visible = true;
		turtleID = ind;
		active = true;
		activeTracker = FXCollections.observableArrayList();
		turtle.setOnMouseClicked(e->changeActiveState());
		dataTracker = FXCollections.observableArrayList();
	}
	
	protected ObservableList<Integer> getTracker(){
		return activeTracker;
	}
	
	private void changeActiveState() {
		if(active) {
			makeInactive();
		}
		else {
			makeActive();
		}
		activeTracker.add(1);
	}
	
	public void makeActive() {
		active = true;
		turtle.setOpacity(1);	
	}

	public void makeInactive() {
		active = false;
		turtle.setOpacity(0.3);	
	}
	
	public boolean isActive() {
		return active;
	}
	
	@Override
	public int getID() {
		return turtleID;
	}
	
	@Override
	public void setImage(Image image, String imageName, int index) {
		turtle.setImage(image);
		visual = image;
		turtle.setRotate(absoluteRotateZero + absoluteRotateLocation);
		imageIndex = index;
	}

	private void sizeTurtle(ImageView image) {
		image.setFitWidth(IMAGE_SIZE);
		image.setFitHeight(IMAGE_SIZE);
	}
	
	protected void initialize(Pane r) {
		root = r;
		pen.setPane(root);
		root.getChildren().add(turtle);
		
		centerX = (root.widthProperty().divide(2));
		centerY = (root.heightProperty().divide(2));
		
		turtle.translateXProperty().bind(centerX.subtract(IMAGE_SIZE/2));
		turtle.translateYProperty().bind(centerY.subtract(IMAGE_SIZE/2));
		
		x=0;
		y=0;
		
		dataTracker.add(1);
	}
	
	@Override
	public void makeInvisible() {
		turtle.setVisible(false);
		visible = false;
	}
	
	@Override
	public void makeVisible() {
		turtle.setVisible(true);
		visible = true;
	}
	
	@Override
	public void turnLeft(double degrees) {
		turtle.setRotate(turtle.getRotate()-degrees);
		absoluteRotateLocation = absoluteRotateLocation - degrees;
		dataTracker.add(1);
	}
	@Override
	public void turnRight(double degrees) {
		turtle.setRotate(turtle.getRotate()+degrees);
		absoluteRotateLocation = absoluteRotateLocation + degrees;
		dataTracker.add(1);
	}
	@Override
	public double setHeading(double degrees) {
		double prevLoc = turtle.getRotate();
		turtle.setRotate(absoluteRotateZero + degrees);
		absoluteRotateLocation = degrees; dataTracker.add(1);
		return prevLoc-turtle.getRotate();
		
	}
	@Override
	public void penDown() {
		pen.switchOn(); dataTracker.add(1);
	}
	@Override
	public void penUp() {
		pen.switchOff(); dataTracker.add(1);
	}
	@Override
	public double goHome() {
		setHeading(0);
		boolean penDown = this.isPenDown();
		this.penUp();
		double distance = setLoc(0,0);
		if(penDown) this.penDown();
		return distance;
	}
	
	@Override
	public double getX() {
		return x;
	}
	@Override
	public double getY() {
		return -y;
	}
	@Override
	public double setLoc(double x1, double y1) {
		double prevX = x;
		double prevY = y;
		double dist = Math.sqrt(Math.pow(x-x1, 2)+Math.pow(y-y1, 2));
		x = x1; y = -1*y1;
		turtle.translateXProperty().bind(centerX.add(x).subtract(IMAGE_SIZE/2));
		turtle.translateYProperty().bind(centerY.add(y).subtract(IMAGE_SIZE/2));
		pen.draw(prevX,prevY,x,y,centerX,centerY,absoluteRotateLocation);
		checkBoundaryConditions();
		dataTracker.add(1);
		return dist;
	}
	
	@Override
	public double setTowards(double x1, double y1) {
		//TODO: Fill in method to turn towards location, return degrees turned
		double vecx = x1-x;
		double vecy = y1-y;
		double theta = 0;
		if(vecx>=0 & vecy>=0) {
			theta = Math.atan(vecx/vecy);
		}
		if(vecx>=0 & vecy<=0) {
			theta = Math.PI/2 + Math.atan(-vecy/vecx);
		}
		if(vecx<=0 & vecy<=0) {
			theta = Math.PI + Math.atan(-vecx/-vecy);
		}
		if(vecx<=0 & vecy>=0) {
			theta = -Math.atan(-vecx/vecy);
		}	
		dataTracker.add(1);
		return setHeading(theta*(180/(2*Math.PI)));

	}
	@Override
	public void goForward(double dist) {		
		move(dist,1);	
	}

	private void move(double dist, int direction) {
		double prevX = x;
		double prevY = y;
		
		double theta = Math.toRadians(absoluteRotateZero + absoluteRotateLocation);

		x = x + direction*dist*Math.sin(theta);
		y = y - direction*dist*Math.cos(theta);
		
		checkBoundaryConditions();
		
		turtle.translateXProperty().bind(centerX.add(x).subtract(IMAGE_SIZE/2));
		turtle.translateYProperty().bind(centerY.add(y).subtract(IMAGE_SIZE/2));
		
		dataTracker.add(1);
		pen.draw(prevX,prevY,x,y,centerX,centerY,absoluteRotateLocation);

	}

	private void checkBoundaryConditions() {
		if(x>centerX.doubleValue())	{
			x = centerX.doubleValue();
		}
		if(x<-centerX.doubleValue()) {
			x = -centerX.doubleValue();
		}
		
		if(y>centerY.doubleValue())	{
			y = centerY.doubleValue();
		}
		if(y<-centerY.doubleValue()) {
			y = -centerY.doubleValue();
		}
	}

	@Override
	public void goBack(double dist) {
		move(dist,-1);
	}
	@Override
	public boolean isPenDown() {
		return pen.isOn();
	}
	@Override
	public boolean isShowing() {
		return visible;
	}
	@Override
	public double getHeading() {
		return absoluteRotateLocation % 360.0;
	}
	@Override
	public double clearScreen() {
		pen.clear();	
		return goHome();
	}
	@Override
	public void setPenColor(Color col) {
		pen.setColor(col);
	}
	@Override
	public void setPenSize(double pixels) {
		pen.setSize(pixels);
	}

	public Color getPenColor() {
		return pen.getColor();
	}

	public double getShape() {
		return imageIndex;
	}

	public ObservableList<Integer> getDataTracker() {
		return dataTracker;
	}
	
	/*
	 * Get image of turtle for stamp command
	 */
	
	public ImageView getStamp() {
		ImageView stamp = new ImageView(turtle.getImage());
		stamp.setTranslateX(turtle.getTranslateX());
		stamp.setTranslateY(turtle.getTranslateY());
		stamp.setFitHeight(turtle.getFitHeight());
		stamp.setFitWidth(turtle.getFitWidth());
		stamp.setRotate(turtle.getRotate());
		return stamp;
	}

}
