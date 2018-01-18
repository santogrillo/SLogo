package table;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.converter.NumberStringConverter;
import controller.CommandController;
import gui.Factory;
import gui.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import var.*;

/**
 * 
 * @author Raphael Kim ck174
 * 
 * Holds and displays a list of user defined variables and allows it to be edited.
 *
 */
public class VariableTable extends Table<Variable> {
	
	private CommandController controller;
	
	public VariableTable(BorderPane bp, CommandController c) {
		super(bp);
		controller = c;
	}
	
	@SuppressWarnings("unchecked")
	public void createTable() {
		table.setEditable(true);
		
		TableColumn<Variable, String> names = new TableColumn<Variable, String>("Name");
		TableColumn<Variable, Number> values = new TableColumn<Variable, Number>("Value");
		
		names.setCellValueFactory(cell -> cell.getValue().getName());
		values.setCellValueFactory(cell -> cell.getValue().getValue());
		values.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		values.setOnEditCommit(e -> editValue(e.getNewValue()));
		
		table.getColumns().addAll(names, values);
		table.setPrefHeight(0);
		table.setItems(data);
		table.setPlaceholder(new Label("No variable has been declared"));
		
		Button clear = Factory.makeButton("clear all", e -> clearData());
		Button delete = Factory.makeButton("delete", e -> deleteRow());
		buttonRegion.getChildren().addAll(clear, delete);
		buttonRegion.setAlignment(Pos.BOTTOM_RIGHT);
		buttonRegion.setPadding(new Insets(Window.PADDING, 0, 0, 0));
		buttonRegion.setSpacing(Window.PADDING);
		
		border.setCenter(table);
		border.setBottom(buttonRegion);
	}
	
	public void editValue(Number newValue) {
		try {
			table.getSelectionModel().getSelectedItem().setValue(newValue);
			table.refresh();
		}
		catch (NullPointerException e) {
			// Do nothing
		}
	}
	
	public ObservableList<Variable> getData() {
		return controller.getVariableList();
	}
}
