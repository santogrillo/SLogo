package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import commands.TurtleDisplay;
import commands.TurtleTracker;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

/**
 * @author Madhavi Rajiv, Raphael Kim
 * This class keeps track of turtle information and displays it for users to see
 *
 */
public class TurtleInformation {

	private static final String SPACE = "   ";
	private TurtleTracker tracker;
	private ObservableList<Integer> idList;
	private BorderPane border;
	private GridPane table = new GridPane();
	private List<Label> tableContents = new ArrayList<Label>();

	private static final String LABELS = "resources.labels/labels";
	private static ResourceBundle labelResources = ResourceBundle.getBundle(LABELS);
	
	public TurtleInformation(BorderPane bp, TurtleDisplay td) {
		tracker = td.getTurtleManager().getTracker();
		idList = tracker.getObservableIds();
		border = bp;
		border.setCenter(table);
		idList.addListener(new ListChangeListener<Integer>() {
			@Override
			public void onChanged(Change<? extends Integer> c) {
				getData();
			}
        });
		addTitles();	
		getData();
	}
	
	/**
	 * Adds listeners to all the data lists so that whenever turtle information changes,
	 * the displayed data is updated
	 */
	public void getData() {

		for(Integer ID: idList) {
			ObservableList<Double> data = tracker.getObservableData(ID);
			
	        data.addListener(new ListChangeListener<Double>() {
				@Override
				public void onChanged(Change<? extends Double> c) {
					for(Label l:tableContents) {
						table.getChildren().remove(l);
					}
					tableContents.clear();
					updateData();
				}
	        });
		}
		updateData();
	}

	/**
	 * Updates the displayed data
	 */
	private void updateData() {
		int i = 2;
		for(Integer ID : idList) {
			ObservableList<Double> data = tracker.getObservableData(ID);
			if(data.size()>=5) {
				for(int j = 0; j<4; j++) {
					String value;
					if (j == 0) {
						value = String.format("%d", data.get(j).intValue());
					}
					else {
						value = String.format("%.3f", data.get(j));
					}
					Label l = new Label(value);
					tableContents.add(l);
					table.add(l,j,i);
				}
				String penState = (data.get(4) == 0) ? "Up" : "Down";
				Label l = new Label(penState);
				table.add(l,4, i);
				tableContents.add(l);
			}
			i++;
		}
		
	}

	/**
	 * Generates the titles for the data columns
	 */
	private void addTitles() {
		table.add(new Label(labelResources.getString("id") + SPACE),0,0);
		table.add(new Label(labelResources.getString("x") + SPACE), 1, 0);
		table.add(new Label(labelResources.getString("y") +SPACE), 2, 0);
		table.add(new Label(labelResources.getString("heading")+SPACE) , 3, 0);
		table.add(new Label(labelResources.getString("penstate")+SPACE),4, 0);
	}
}