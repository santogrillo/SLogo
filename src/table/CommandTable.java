package table;

import java.util.Enumeration;
import java.util.ResourceBundle;

import controller.CommandController;
import gui.Factory;
import gui.Window;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * @author Raphael Kim ck174
 * 
 * Creates a table of pre-defined methods for users to use as reference.
 * Updates automatically to match the language being used.
 *
 */
public class CommandTable extends Table<String> {

	private static final String LANGUAGE_DIRECTORY = "resources/languages/";
	
	private static final String DESCRIPTIONS = "resources/commandDescription";
	private static ResourceBundle desc = ResourceBundle.getBundle(DESCRIPTIONS);
	
	private ResourceBundle langProp;
	private String language;
	private CommandController controller;
	
	public CommandTable(BorderPane bp, CommandController c) {
		super(bp);
		controller = c;
		language = c.getLanguage();
		langProp = ResourceBundle.getBundle(LANGUAGE_DIRECTORY + language);
	}
	
	@Override
	public void createTable() {
		data = FXCollections.observableArrayList();
		Enumeration<String> iter = desc.getKeys();
		while (iter.hasMoreElements()) {
			String name = iter.nextElement();
			data.add(name);
		}
		
		setUpColumns();		
		
		table.setPrefHeight(0);
		table.setItems(data);
		table.setPlaceholder(new Label("There is no predefined command"));
		
		Button detail = Factory.makeButton("details", e -> viewDetail());
		buttonRegion.getChildren().add(detail);
		buttonRegion.setAlignment(Pos.BOTTOM_RIGHT);
		buttonRegion.setPadding(new Insets(Window.PADDING, 0, 0, 0));
		buttonRegion.setSpacing(Window.PADDING);
		
		border.setCenter(table);
		border.setBottom(buttonRegion);
	}
	
	private void setUpColumns() {
		TableColumn<String, String> names = new TableColumn<String, String>("Name");
		TableColumn<String, String> primary = new TableColumn<String, String>("Primary");
		TableColumn<String, String> secondary = new TableColumn<String, String>("Secondary");
		
		names.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue()));
		primary.setCellValueFactory(cell -> new SimpleStringProperty(getCommands(cell.getValue())[0]));
		secondary.setCellValueFactory(cell -> new SimpleStringProperty(getCommands(cell.getValue())[1]));
		
		table.getColumns().clear();
		table.getColumns().addAll(names, primary, secondary);
	}
	
	private String[] getCommands(String name) {
		try {
			String[] commands = new String[2];
			String combined = langProp.getString(name);
			String[] spliced = combined.split("\\|");
			if (spliced.length == 2) {
				commands = spliced;
			}
			else {
				commands[0] = spliced[0];
			}
			return commands;
		}
		catch (Exception e) {
			//System.out.println(name);
			return null;
		}
	}
	
	private void viewDetail() {
		try {
			String name = table.getSelectionModel().getSelectedItem();
			String description = desc.getString(name);
			Label label = new Label(description);
			BorderPane pane = new BorderPane();
			pane.setCenter(label);
			pane.setPadding(new Insets(Window.PADDING));
			Scene newScene = new Scene(pane, label.getPrefWidth(), label.getPrefHeight());
			Stage screen = new Stage();
			screen.setScene(newScene);
		    screen.setTitle(name);
		    screen.show();
		}
		catch (NullPointerException e) {
			// Do nothing
		}
	}
	
	public String getLanguage() {
		return controller.getLanguage();
	}
	
	public void setLanguage(String newLanguage) {
		language = newLanguage;
		langProp = ResourceBundle.getBundle(LANGUAGE_DIRECTORY + language);
		setUpColumns();
	}
	
	public void update() {
		if (!language.equals(controller.getLanguage())) {
			setLanguage(controller.getLanguage());
		}
	}	
	
}
