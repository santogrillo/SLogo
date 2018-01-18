package gui;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import commands.TurtleDisplay;
import controller.CommandController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Raphael Kim ck174
 * Creates an environment that holds all of the user interface together
 * 
 */
public class IDE {
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;
	
	private Scene myScene;
	private Timeline animation = new Timeline(); 
	private BorderPane layout;

	private BorderPane bottomPane;
	private BorderPane topPane;
	private BorderPane centerPane;
	
	private TurtleDisplay td;
	private CommandController controller;
	
	private List<Window> windows;
	private VBox toolbar;
	private VBox rightbar;
	private BorderPane turtlePane;
	private BorderPane consolePane;
	private BorderPane viewerPane;
	private WindowViewer viewer;
	private ConsoleWindow console;
	private Stage stage;
	private static final int FRAMES_PER_SECOND = 2;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	
	private static final String COLOR = "resources.IDE/colors";
	private static ResourceBundle styleResources = ResourceBundle.getBundle(COLOR);
	
	public IDE(Stage s) {
		controller = new CommandController();
		layout = new BorderPane();
		myScene = new Scene(layout, WIDTH, HEIGHT);
		windows = new ArrayList<Window>();
		stage = s;
		setUpPanes();
		animate();
	}
	
	public Scene getScene() {
		return myScene;
	}
	
	/**
	 * Adds console to bottom right corner
	 */
	private void addConsole() {
		ConsoleWindow w = new ConsoleWindow(consolePane, controller);
		windows.add(w);
		w.start();
		console = w;
	}
	
	/**
	 * Adds turtle display to top right corner
	 */
	private void addTurtleDisplay() {
		td = new TurtleDisplay(turtlePane);
		windows.add(td);
		controller.addDisplay(td);
		td.start();
		
	}
	
	/**
	 * Adds window viewer to bottom left corner
	 */
	private void addWindowViewer() {
		viewer = new WindowViewer(viewerPane, td, controller);
		viewer.createViewer();
		windows.add(viewer);
		viewer.start();
	}

	/**
	 * Adds toolbar to top left corner
	 */
	private void addToolBar() { 
		// TODO: Implement addToolBar()
		EditBar tools = new EditBar(toolbar,td,stage,console);
	    tools.start();
		
	}
	
	/**
	 * Sets up multiple panes so that the component panes can be added on
	 */
	private void setUpPanes() {
		bottomPane = new BorderPane();
		topPane = new BorderPane();
		layout.setBottom(bottomPane);
		layout.setTop(topPane);
		
		//new
		centerPane = new BorderPane();
		layout.setCenter(centerPane);
		
		toolbar = new VBox();
		rightbar = new VBox();
		
		//refactor
		toolbar.setStyle(styleResources.getString("verticalStyle"));
		toolbar.setPrefWidth(WIDTH * 1/6);
		
		rightbar.setStyle(styleResources.getString("verticalStyle"));
		rightbar.setPrefWidth(WIDTH * 1/6);
		
		turtlePane = new BorderPane();
		turtlePane.setPrefWidth(WIDTH*2/3);
		turtlePane.setStyle(styleResources.getString("initTurtleStyle"));
		centerPane.setLeft(toolbar);
		centerPane.setCenter(turtlePane);
		centerPane.setRight(rightbar);
		
		consolePane = new BorderPane();
		consolePane.setPrefWidth(WIDTH * 2/3);
		viewerPane = new BorderPane();
		bottomPane.setRight(consolePane);
		bottomPane.setLeft(viewerPane);
		
		addConsole();
		addTurtleDisplay();
		addToolBar();
		addWindowViewer();
	}
	
		
	private void animate() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	private void step(){
		for (Window w : windows) {
			w.update();
		}
	}
}

