package table;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import commands.UserCommand;
import controller.CommandController;
import gui.Factory;
import gui.Window;

/**
 * 
 * @author Raphael Kim ck174
 * 
 * Holds and displays a list of user defined methods
 *
 */
public class UserCommandTable extends Table<UserCommand> {
	
	CommandController controller;
	
	public UserCommandTable(BorderPane bp, CommandController c) {
		super(bp);
		controller = c;
		data = FXCollections.observableArrayList();
	}

	@SuppressWarnings("unchecked")
	public void createTable() {
		TableColumn<UserCommand, String> names = new TableColumn<UserCommand, String>("Name");
		TableColumn<UserCommand, String> param = new TableColumn<UserCommand, String>("Parameters");
		TableColumn<UserCommand, String> definitions = new TableColumn<UserCommand, String>("Definition");
		
		names.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
		param.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFormattedInputVariables()));
		definitions.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDefinition()));
		
		table.getColumns().addAll(names, param, definitions);
		table.setPrefHeight(0);
		table.setItems(data);
		table.setPlaceholder(new Label("No command has been declared"));
		
		Button detail = Factory.makeButton("details", e -> viewDetail());
		Button delete = Factory.makeButton("delete", e -> deleteRow());
		buttonRegion.getChildren().addAll(detail, delete);
		buttonRegion.setAlignment(Pos.BOTTOM_RIGHT);
		buttonRegion.setPadding(new Insets(Window.PADDING, 0, 0, 0));
		buttonRegion.setSpacing(Window.PADDING);
		
		border.setCenter(table);
		border.setBottom(buttonRegion);
	}
	
	private void viewDetail() {
		try {
			UserCommand comm = table.getSelectionModel().getSelectedItem();
			String description = comm.getDefinition();
			Label nameLabel = new Label(comm.getName());
			Label label = new Label(description);
			BorderPane pane = new BorderPane();
			pane.setCenter(label);
			BorderPane box = new BorderPane();
			box.setCenter(nameLabel);
			pane.setTop(box);
			pane.setPadding(new Insets(Window.PADDING));
			Scene newScene = new Scene(pane, label.getPrefWidth(), label.getPrefHeight());
			Stage screen = new Stage();
			screen.setScene(newScene);
		    screen.setTitle(comm.getName());
		    screen.show();
		}
		catch (NullPointerException e) {
			// Do nothing
		}
	}
	
	public ObservableList<UserCommand> getData() {
		return controller.getUDCommandList();
	}
}
