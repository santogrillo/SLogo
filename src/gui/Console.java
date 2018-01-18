/**
 * @author 
 */

package gui;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.CommandController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * @author Madhavi
 * @author Raphael
 * This class creates the Console which takes in commands, communicates
 * with the controller to execute the commands and displays outputs
 * of the commands along with the previous command history.
 *
 */
public class Console {

	private static final int OFFSET = 7;
	private CommandController controller;
	private BorderPane border;
	private TextArea output;
	private TextArea input;
	private BorderPane textRegion;
	private VBox buttonRegion;
	private List<String> prevCommands;
	private int prevPlace;
	private KeyCode lastKey;
	private boolean consoleClear;
	
	private static final String LABELS = "resources.labels/labels";
	private static ResourceBundle labelResources = ResourceBundle.getBundle(LABELS);

	public Console(BorderPane bp, CommandController cc) {
		controller = cc;
		border = bp;
		output = new TextArea();
		input = new TextArea();
		textRegion = new BorderPane();
		buttonRegion = new VBox();
		prevCommands = new ArrayList<String>();
	}
	
	/**
	 * Sets up the various textAreas for the console view.
	 */
	public void createConsole() {
		output.setPadding(new Insets(OFFSET));
		output.setText(labelResources.getString("initialConsole"));
		output.setEditable(false);
		
		input.setPrefRowCount(5);
		input.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		
		input.textProperty().addListener((obs, oldText, newText) -> {
		    if (consoleClear && newText.equals("\n")) {
		    	input.clear();
		    	consoleClear = false;
		    }
		});
				
		textRegion.setTop(output);
		textRegion.setBottom(input);
		
		Button compile = Factory.makeButton(labelResources.getString("compile"), e -> compile());
		buttonRegion.getChildren().add(compile);
		buttonRegion.setAlignment(Pos.BOTTOM_RIGHT);
		buttonRegion.setPadding(new Insets(Window.PADDING, 0, 0, 0));
		
		border.setCenter(textRegion);
		border.setBottom(buttonRegion);
	}
	
	/**
	 * Compiles commands read from the input text area.
	 */
	public void compile() {
		input.setText(input.getText().trim());
		output.appendText(input.getText() + "\n");
		String[] commands = input.getText().split("\n");
		for(int i = 0; i< commands.length; i++) {
			prevCommands.add(0, commands[i]);
		}		
		output.appendText(">> " + executeCommand(input.getText()) + "\n\n");
		consoleClear = true;
		input.clear();
	}
	
	/**
	 * @param s
	 * @return Returns the string output from the controller executing a command
	 */
	public String executeCommand(String s) {
		return controller.executeCommand(s);
	}
	
    /**
     * @param code
     * Provides up arrow functionality, and entering twice for compiling
     */
    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.UP && prevCommands.size() > 0) {
        	input.clear();
            input.setText(prevCommands.get(prevPlace));
            prevPlace++;
			if(prevPlace>=prevCommands.size()) {
				prevPlace = 0;
			}
        }
        if (code == KeyCode.ENTER && code == lastKey) {
        	compile();
        	lastKey = null;
        }
        lastKey = code;
    }
    
    /**
     * Clears the input text area
     */
    protected void clearInput() {
    		input.clear();
    }
    
    /**
     * @param s Adds text to the input area
     */
    protected void addInput(String s) {
    		input.appendText(s);
    }
    
    /**
     * @return Returns all the text in the output textArea
     */
    protected String getCommandHistory() {
    		return output.getText();
    }

	/**
	 * @param commandHistory
	 * Sets the text in the output console
	 */
	public void setConsoleOutput(String commandHistory) {
		output.clear();
		output.setText(commandHistory);		
	}
    
}
