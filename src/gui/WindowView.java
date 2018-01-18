package gui;

/**
 * The controller for SLOGO. It initializes and controls different components of the program as the common parent in hierarchy.
 */
public interface WindowView {
	
	/**
	 * Puts the display, console, toolbox, and workspace information onto the screen
	 */
	public void render();
	
	
	/**
	 * Save the current display, console content, and workspace
	 */
	public void saveState();
		
	/**
	 * Update the history on console, reset the editable text field, and send the written command to be compiled
	 */
	public void updateConsole();
	
}
