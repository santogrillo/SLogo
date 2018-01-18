package gui;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Raphael Kim ck174
 * 
 * An abstract class that holds a border pane that can be placed on the IDE
 *
 */
public abstract class Window {
	public static final int PADDING = 5;
	protected BorderPane parent;
	protected BorderPane layout;
	
	public Window(BorderPane p) {
		parent = p;
		layout = new BorderPane();
		layout.setPrefSize(p.getPrefWidth(), p.getPrefHeight());
		layout.setPadding(new Insets(PADDING));
	}
	
	public void start() {
		parent.setCenter(layout);
	}
	
	public abstract void update();
	
	public BorderPane getRoot() {
		return parent;
	}
}
