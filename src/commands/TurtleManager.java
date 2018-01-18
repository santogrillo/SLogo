/**
 * @author Madhavi Rajiv, Ben Whelton
 */
package commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Keeps track of the lists of active and turtles and IDs mapping to turtles.
 * This class also contains the TurtleTracker which tracks the turtles for changes in information
 * so that this information can be displayed in Front End.
 *
 */
public class TurtleManager{

	private List<SingleTurtle> activeTurtles = new ArrayList<SingleTurtle>();
	private Map<Integer,SingleTurtle> IDmap = new HashMap<Integer,SingleTurtle>();
	private Stack<List<SingleTurtle>> savedActiveTurtles = new Stack<>();
    private Pane root;
    private ObservableList<Integer> idList = FXCollections.observableArrayList();
    private TurtleTracker tracker= new TurtleTracker(idList);
    
	public TurtleManager(Pane r) {
		root = r;
		addNewTurtle(1);
		
	}

	/**
	 * @param index
	 * Adds a new turtle of ID index, and sets up the tracking for whether it's active, 
	 * and the tracking for its general state.
	 */
	private void addNewTurtle(int index) {
		SingleTurtle turtle = new SingleTurtle(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("turtle4.png"))),index);
		turtle.initialize(root);
		activeTurtles.add(turtle);
		IDmap.put(index, turtle);
		
		tracker.track(turtle);
		idList.add(index);

		ObservableList<Integer> activeTracker = turtle.getTracker();
        activeTracker.addListener(new ListChangeListener<Integer>() {
			@Override
			public void onChanged(Change<? extends Integer> c) {
				if(turtle.isActive())	{
					if(!activeTurtles.contains(turtle)) {
						activeTurtles.add(turtle);
					}
				}
				else {
					if(activeTurtles.contains(turtle)) {
						activeTurtles.remove(turtle);
					}
				}
			}
        });
	}
	
	/**
	 * @param activeIDs 
	 * sets the list of Active Turtles and changes them graphically;
	 */
	public void setActive(List<Integer> activeIDs) {
		fadeNonActiveTurtles();
		createActiveList(activeIDs);
	}


	/**
	 * Fades the non active turtles so that it's visually clear which turtles are active
	 */
	private void fadeNonActiveTurtles() {
		for(SingleTurtle t: activeTurtles) {
			t.makeInactive();
		}
		activeTurtles.clear();
	}
	
	/**
	 * @param activeIDs
	 * sets the list of ActiveTurtles
	 */
	private void createActiveList(List<Integer> activeIDs) {
		for(Integer i:activeIDs) {			
			if(!IDmap.containsKey(i)) {
				addNewTurtle(i);
			}
			else {
				activeTurtles.add(IDmap.get(i));
				IDmap.get(i).makeActive();
			}			
		}
	}


	/**
	 * @param activeIDs
	 * Sets a temporary list of active turtles for the command structure ask
	 */
	public void setTempActive(List<Integer> activeIDs) {
		savedActiveTurtles.push(new ArrayList<>(activeTurtles));
		fadeNonActiveTurtles();
		createActiveList(activeIDs);
	}

	/**
	 * Restores original list of active turtles after an ask structure
	 */
	public void restoreActive() {
		fadeNonActiveTurtles();
		activeTurtles = savedActiveTurtles.pop();
		for(SingleTurtle t:activeTurtles) {
			t.makeActive();
		}
	}
	
	/**
	 * @return Returns the total number of turtles
	 */
	public int getNumTurtles() {
		return IDmap.keySet().size();
	}

	/**
	 * @param id
	 * @return Returns the turtle with the given ID
	 */
	public SingleTurtle getID(Integer id) {
		return (id==null) ? activeTurtles.get(activeTurtles.size()-1) : IDmap.get(id);
	}
	
	/**
	 * @return Returns the set of IDs
	 */
	public Set<Integer> getIDSet(){
		return IDmap.keySet();
	}
	
	/**
	 * @return Returns the list of active turtles
	 */
	public List<SingleTurtle> getActiveTurtles() {
		return activeTurtles;
	}

	/**
	 * @return Returns the turtle tracker
	 */
	public TurtleTracker getTracker() {
		return tracker;
	}

}
