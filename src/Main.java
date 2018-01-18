import java.util.ResourceBundle;

import gui.IDE;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * @author Raphael Kim
 *
 */
public class Main extends Application {

	private static final String LABELS = "resources.labels/labels";
	private static ResourceBundle Resources = ResourceBundle.getBundle(LABELS);
	
	public void start(Stage stage) {
		IDE ideWindow = new IDE(stage);
		stage.setTitle(Resources.getString("title"));
		stage.setScene(ideWindow.getScene());
		stage.show();
	}
	

	/**
	 * @param args
	 *            Main launch method
	 */
	public static void main(String[] args) {
		launch(args);
	}
}