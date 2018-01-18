package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import commands.TurtleDisplay;
import controller.CommandController;
import controller.Workspace;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * @author Madhavi
 * This class creates a vertical pane with all the buttons that give users power
 * to edit their experience.
 */
public class EditBar {
	
	private VBox bar;
	private TurtleDisplay td;
	private List<MenuButton> menuList = new ArrayList<MenuButton>();
	
	private Stage stage;
	private static final String LOGO_EXTENSION = "*.logo";
	private FileChooser chooseLogoFile;
	private ConsoleWindow consoleWindow;
	private Map<Integer,Workspace> wsMap = new HashMap<Integer,Workspace>();
	private int wsCount = 0;
	private MenuButton wsMenu;
	private CommandController controller;
	
	private Integer count;
	
	private static final String LANGUAGE = "editBarResources/languageLabel";
	private static ResourceBundle langResources = ResourceBundle.getBundle(LANGUAGE);
	
	private static final String COLOR = "editBarResources/Color";
	private static ResourceBundle colorResources = ResourceBundle.getBundle(COLOR);
	
	private static final String BACKGROUND = "editBarResources/backgroundColor";
	private static ResourceBundle backgroundResources = ResourceBundle.getBundle(BACKGROUND);
	
	private static final String TURTLE = "editBarResources/turtles";
	private static ResourceBundle turtleResources = ResourceBundle.getBundle(TURTLE);
	
	private static final String LABELS = "resources.labels/labels";
	private static ResourceBundle labelResources = ResourceBundle.getBundle(LABELS);

	public static final int PADDING = 5;
	
	public EditBar(VBox box, TurtleDisplay d, Stage s, ConsoleWindow c) {
		bar = box;
		bar.setPadding(new Insets(PADDING));
		td = d;
		stage = s;
		consoleWindow = c;
		controller = c.getController();
	}
	
	/**
	 * Add all the menus and buttons
	 */
	public void start() {
        addColorMenu();	
        addImageMenu();
        addLanguageMenu();
        addWorkspaceMenu();
		for(MenuButton menu: menuList) {
			bar.getChildren().add(menu);
		}
		createFileChooser();
		Factory.makeButtonInPane(labelResources.getString("upload"), e->openFile(), bar);
		Factory.makeButtonInPane(labelResources.getString("save"),e->makeNewWorkspace(),bar);
		
	}
	
	/**
	 * Create a new workspace, map it to a number, and have it sent to the console 
	 * to get the appropriate information saved into it.
	 */
	private void makeNewWorkspace() {
		wsCount++;
		Workspace newWS = new Workspace();
		newWS.setTurtleManager(td.getTurtleManager());
		consoleWindow.editAndSendWorkspace(newWS);
		wsMap.put(wsCount, newWS);
		addToWorkspaceMenu();
		alertNewWorkspaceSaved();
	}

	private void alertNewWorkspaceSaved() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(labelResources.getString("saved"));
		alert.setContentText(labelResources.getString("savedws") + " " +wsCount );
		alert.showAndWait();
	}
	
	/**
	 * Add a workspace to the workspace menu
	 */
	private void addToWorkspaceMenu() {
		int i = wsCount;
		wsMenu.getItems().add(createMenuItem(labelResources.getString("ws") + " "+ wsCount,e->loadWorkspace(wsMap.get(i))));
	}
	
	/**
	 * Add the menu to choose workspaces from
	 */
	private void addWorkspaceMenu() {
		wsMenu = new MenuButton(labelResources.getString("load"),null);
		if(wsCount!=0) {
			for(Integer i:wsMap.keySet()) {
				wsMenu.getItems().add(createMenuItem(labelResources.getString("ws") + " "+ i.toString(),e->loadWorkspace(wsMap.get(i))));
			}
		}
		menuList.add(wsMenu);

	}
	
	/**
	 * @param ws
	 * Load the information from a workspace
	 */
	private void loadWorkspace(Workspace ws) {
		consoleWindow.setWorkspace(ws);
	}

	/**
	 * Create a file chooser to choose code to upload
	 */
	private void createFileChooser() {
		chooseLogoFile = new FileChooser();
		chooseLogoFile.setTitle(labelResources.getString("open"));
		chooseLogoFile.setInitialDirectory(new File(System.getProperty("user.dir")));
		chooseLogoFile.getExtensionFilters().setAll(new ExtensionFilter("Text Files", LOGO_EXTENSION));	
	}

	private void openFile(){
		File data = chooseLogoFile.showOpenDialog(stage);
		if(data!=null) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(data));
	            String line;
	            try {
	            		consoleWindow.clearInput();
					while ((line = reader.readLine()) != null) {
					    System.out.println(line);
					    consoleWindow.addInput(line + "\n");
					}
				} catch (IOException e) {
					Errors.createErrorPopup(labelResources.getString("IO Exception"));
				}
			} catch (FileNotFoundException e) {
				Errors.createErrorPopup(labelResources.getString("lostfile"));
			}
		}
		else {
			Errors.createErrorPopup(labelResources.getString("choose"));
		}
	}
	
	private void addLanguageMenu() {
		
		MenuButton mb = new MenuButton(labelResources.getString("language"),null);
        Enumeration<String> iter = langResources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            mb.getItems().add(createMenuItem(langResources.getString(key),e->setLanguage(langResources.getString(key))));         
        }       
        menuList.add(mb);
	}
	
	private void setLanguage(String lang) {
		controller.setLanguage(lang);
	}

	private void addColorMenu() {
		MenuButton mb = new MenuButton(labelResources.getString("backgroundcolor"),null);
        Enumeration<String> iter = colorResources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            mb.getItems().add(createMenuItem(colorResources.getString(key),e->td.setColor(backgroundResources.getString(key))));         
        }       
        menuList.add(mb);
	}
	
	private void addImageMenu() {
		
		MenuButton mb = new MenuButton(labelResources.getString("turtle"),null);
        Enumeration<String> iter = turtleResources.getKeys();
        Factory f = new Factory();
        count = 1;
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String s = "setshape " + count;
            MenuItem mi = createMenuItem("",e -> consoleWindow.executeCommand(s));
            mi.setGraphic(f.getImageView(turtleResources.getString(key)));
            mb.getItems().add(mi);
            
            count++;
        }       
        menuList.add(mb);
        
	}
	
	private MenuItem createMenuItem(String title, EventHandler<ActionEvent> handler) {
		MenuItem item = new MenuItem(title);
		item.setOnAction(handler);
		return item;
	}

}
