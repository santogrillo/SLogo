/**
 * @author Madhavi Rajiv, Ben Whelton
 */
package commands;

import java.util.HashMap;
import java.util.Map;

import commands.SingleTurtle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;

/**
 * Keeps track of the changes in each turtle's state (ID, x, y, heading, penUp) using listeners. This is used to
 * create a view with this information in front end.
 *
 */
public class TurtleTracker {

	
	private Map<Integer, ObservableList<Double>> infoMap = new HashMap<Integer,ObservableList<Double>>();
	private ObservableList<Integer>  idList;
	
	public TurtleTracker(ObservableList<Integer> IDlist) {
		idList=IDlist;		
	}
	
	/**
	 * @param ID
	 * @return Returns a list of data for a turtle with the given ID
	 */
	public ObservableList<Double> getObservableData(int ID){
		return infoMap.get(ID);
	}
	
	/**
	 * @param t
	 * Tracks a turtle t by getting an observable list which changes whenever the turtle's data changes
	 */
	protected void track(SingleTurtle t) {		
		addData(t);
		
		ObservableList<Integer> tracker = t.getDataTracker();
		
        tracker.addListener(new ListChangeListener<Integer>() {
			@Override
			public void onChanged(Change<? extends Integer> c) {
				changeData(t);
				System.out.println(t.getY());
				System.out.println("changed");
			}

        });
		
	}
	
	/**
	 * @param t
	 * Updates the data of a turtle t 
	 */
	private void changeData(SingleTurtle t) {
		 infoMap.get(t.getID()).clear();
		 getData(t,infoMap.get(t.getID()));
	}

	/**
	 * @param t 
	 * maps the data of a turtle t to its ID
	 */
	private void addData(SingleTurtle t) {
		ObservableList<Double> turtleData = FXCollections.observableArrayList();
		getData(t, turtleData);
		infoMap.put(t.getID(), turtleData);
	}

	/**
	 * @param t
	 * @param turtleData
	 * Adds a turtle t's data to the list its id is mapped to
	 */
	private void getData(SingleTurtle t, ObservableList<Double> turtleData) {
		turtleData.add(t.getID()+0.0);
		turtleData.add(t.getX());
		turtleData.add(t.getY());
		turtleData.add(t.getHeading());
		double penDown = 0;
		if(t.isPenDown()) {
			penDown = 1;
		}
		turtleData.add(penDown);
	}
	
	/**
	 * @return Returns an observable list of IDs for front end to keep track of
	 */
	public ObservableList<Integer> getObservableIds(){

		return idList;
	}
	
}
