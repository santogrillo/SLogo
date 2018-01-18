package gui;

import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Madhavi
 * Creates errors pop ups
 */
public class Errors {
	
	private static final String LABELS = "resources.labels/labels";
	private static ResourceBundle labelResources = ResourceBundle.getBundle(LABELS);
	
	public static void createErrorPopup(String error) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(labelResources.getString("error"));
		alert.setContentText(error);
		alert.showAndWait();
	}
}
