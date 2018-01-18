package gui;

import java.util.ResourceBundle;

import commands.TurtleDisplay;
import controller.CommandController;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Madhavi
 * This class creates a view through which users can enter values to change the turtle's state
 *
 */
public class InteractDisplayWindow extends Window{

private CommandController control;

private static final String LABELS = "resources.labels/labels";
private static ResourceBundle labelResources = ResourceBundle.getBundle(LABELS);

	public InteractDisplayWindow(BorderPane r, CommandController cc) {
		super(r);
		createInputFields();
		control = cc;
	}
	
	/**
	 * Create the different input fields
	 */
	private void createInputFields() {
		inputForMovingTurtle();
	}

	/**
	 * Creates different ways to get information from text fields to control turtles
	 */
	private void inputForMovingTurtle() {
		VBox editDisplay = new VBox();
		editDisplay.getChildren().add(getInput(labelResources.getString("pixelsBack"), labelResources.getString("goback"),d->control.executeCommand(labelResources.getString("back")+" "+d.toString())));
		editDisplay.getChildren().add(getInput(labelResources.getString("pixelsForward"), labelResources.getString("goforward"),d->control.executeCommand(labelResources.getString("forward")+" "+d.toString())));
		editDisplay.getChildren().add(getInput(labelResources.getString("degreesRight"), labelResources.getString("turnright"),d->control.executeCommand(labelResources.getString("right")+" "+d.toString())));
		editDisplay.getChildren().add(getInput(labelResources.getString("degreesLeft"), labelResources.getString("turnleft"),d->control.executeCommand(labelResources.getString("left")+" "+d.toString())));
		Factory.makeButtonInPane(labelResources.getString("penUp"),e->control.executeCommand(labelResources.getString("penup")),editDisplay);
		Factory.makeButtonInPane(labelResources.getString("penDown"),e->control.executeCommand(labelResources.getString("pendown")),editDisplay);
		layout.setCenter(editDisplay);
	}
	
	private HBox getInput(String prompt, String buttonLabel, EditDisplay d) {
		HBox hbox = new HBox();
		TextField field = Factory.makeTextField(prompt,hbox);
		Factory.makeButtonInPane(buttonLabel, e->useTextFieldInformation(field, d),hbox);
		return hbox;
	}

	private void useTextFieldInformation(TextField t, EditDisplay d) {
		if (t.getText().length()==0) {
			Errors.createErrorPopup(labelResources.getString("enterValue"));
			t.clear();
		}
		else {
			//https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
			try  
			  {  
			    double n = Double.parseDouble(t.getText());  
			    d.editDisplay(n);
			    t.clear();
			  }  
			  catch(NumberFormatException nfe)  
			  {  
				  Errors.createErrorPopup(labelResources.getString("numValue"));  
				  t.clear();
			  }  
		}
	}
	
	public interface EditDisplay{
		public void editDisplay(Double num);
	}

	public void update() {
		//do nothing
	}

}
