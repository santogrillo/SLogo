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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import var.*;

/**
 * 
 * @author Raphael Kim ck174
 *
 * A table that Window class can use to tabulate relevant data and update accordingly
 * 
 * @param <T> the type of variable table holds
 */
public abstract class Table<T> {

	protected BorderPane border;
	protected TableView<T> table;
	protected HBox buttonRegion;
	protected ObservableList<T> data;
	
	public Table(BorderPane bp) {
		border = bp;
		table = new TableView<T>();
		buttonRegion = new HBox();
	}
	
	public abstract void createTable(); 
	
	protected Button makeButton(String name, EventHandler<ActionEvent> handler) {
		Button b = new Button(name);
		b.setOnAction(handler);
		return b;
	}
	
	public void clearData() {
		data.clear();
	}

	public void deleteRow()
	{
		try {
			ObservableList<T> row , allRows;
			allRows = table.getItems();
			row = table.getSelectionModel().getSelectedItems();         
			row.forEach(allRows::remove);
		}
		catch (NullPointerException e) {
			// Do nothing
		}
	}	
	
	public ObservableList<T> getData() {
		// TODO: Implement getData()
		return data;
	}
	
	public void update() {
		data = getData();
		table.setItems(data);
	}
	
}
