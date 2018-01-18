/**
 * @author Ben Welton, Santo Grillo Madhavi, Madhavi Rajiv, Raphael Kim
 */
package commands;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * @author Madhavi
 * This class keeps track of the Pen which is controlled by the Turtle class.
 */
public class Pen {

private boolean on;
private Pane root;
private static final int IMAGE_SIZE = 50;

private Color color;
private double size = 1;

private List<Line> lines = new ArrayList<Line>();

	protected Pen() {
		on = true;
		color = Color.LIGHTGRAY;
	}
	
	protected void setPane(Pane r) {
		root = r;
	}
	
	
	protected void clear() {
		for(Line line:lines) {
			root.getChildren().remove(line);
		}
		lines = new ArrayList<Line>();
	}
	
	protected void switchOn() {
		on = true;
	}
	
	protected void switchOff() {
		on = false;
	}
	
	protected Line draw(double startX, double startY, double endX, double endY, DoubleBinding centerX, DoubleBinding centerY, double heading) {
		Line line = new Line();
		if (on){		
			line.startXProperty().bind(centerX.add(startX));
			line.startYProperty().bind(centerY.add(startY));
			
			line.endXProperty().bind(centerX.add(endX));
			line.endYProperty().bind(centerY.add(endY));

			line.setStroke(color);
			line.setStrokeWidth(size);
			root.getChildren().add(line);
			lines.add(line);	
		}
		return line;

	}
	
	private List<Integer> getDirections(double heading){
		List<Integer> dir = new ArrayList<Integer>();
		dir.add(0); dir.add(0);
		while(heading>360) {
			heading = heading - 360;
		}
		while(heading<0) {
			heading = heading + 360;
		}
		
		if(heading<90) {
			dir.add(0,1);
			dir.add(1,1);
		}
		
		if(heading == 0) {
			dir.add(0,1);
			dir.add(1,2);
		}
		if(heading==90) {
			dir.add(0,0);
			dir.add(1,0);
		}
		
		if(heading==270) {
			dir.add(0,-2);
			dir.add(1,0);
		}
		
		if(heading==180) {
			dir.add(0,1);
			dir.add(1,-1);
		}
		
		if(heading>90 & heading<180) {
			dir.add(0,1);
			dir.add(0,-1);
		}
		
		if(heading>180 & heading<270) {
			dir.add(0,-1);
			dir.add(0,-1);
		}
		
		if(heading>270 & heading<360) {
			dir.add(0,-1);
			dir.add(0,1);
		}
		return dir;
	}

	
	
	protected boolean isOn() {
		return on;
	}
	
	protected void setColor(Color c) {
		color = c;
	}
	
	protected void setSize(double pixels) {
		size = pixels;
	}

	public Color getColor() {
		return color;
	}

	
}
