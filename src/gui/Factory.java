package gui;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author Madhavi
 * This is a factory to create buttons, textfields, menuitems, and to get Imageviews from
 * an image name.
 *
 */
public class Factory {
	
	public static final int PADDING = 5;
	
	public static Button makeButton(String name, EventHandler<ActionEvent> handler) {
		Button b = new Button(name);
		b.setOnAction(handler);
		b.setPadding(new Insets(PADDING));
		return b;
	}
	
	public static Button makeButtonInPane(String name, EventHandler<ActionEvent> handler, Pane pane) {
		Button b = new Button(name);
		b.setOnAction(handler);
		b.setPadding(new Insets(PADDING));
		pane.getChildren().add(b);
		return b;
	}
	
	public static TextField makeTextField(String prompt, Pane pane) {
		TextField newField = new TextField();
		newField.setPadding(new Insets(PADDING));
		newField.setPromptText(prompt);
		pane.getChildren().add(newField);
		return newField;
	}

	public static MenuItem createMenuItem(String title, EventHandler<ActionEvent> handler) {
		MenuItem item = new MenuItem(title);
		item.setOnAction(handler);
		return item;
	}
	
	public static void createMenuItemInMenu(String title, EventHandler<ActionEvent> handler, MenuButton menu) {
		MenuItem item = new MenuItem(title);
		item.setOnAction(handler);
		menu.getItems().add(item);
	}
	
	public ImageView getImageView(String image) {
		Image icon = new Image(getClass().getClassLoader().getResourceAsStream(image));
        ImageView view = new ImageView(icon);
        view.setFitWidth(25);
        view.setFitHeight(25);
        return view;
	}
	
}
