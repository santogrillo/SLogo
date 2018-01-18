package gui;

import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.ResourceBundle;

import commands.TurtleDisplay;
import controller.CommandController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Raphael Kim ck174
 * 
 * A window that can start creating tabs to hold variable number of windows
 *
 */
public class WindowViewer extends Window {

	protected static final String WINDOW_TYPE = "resources/windows";
	
	protected ViewerTab childTab;
	protected VBox menuBox;
	protected BorderPane leftRegion;
	
	protected TurtleDisplay display;
	protected CommandController control;

	protected static ResourceBundle wTypes = ResourceBundle.getBundle(WINDOW_TYPE);
	
	public WindowViewer(BorderPane r, TurtleDisplay td, CommandController c) {
		super(r);
		leftRegion = new BorderPane();
		menuBox = new VBox();
		display = td;
		control = c;
	}
	
	public void createViewer() {
		Label label = new Label("No tab is currently open");
		MenuButton menu = new MenuButton("New Tab", null);
		Enumeration<String> iter = wTypes.getKeys();
		while (iter.hasMoreElements()) {
			String type = iter.nextElement();
			menu.getItems().add(Factory.createMenuItem(type, e -> createTab(wTypes.getString(type))));
		}
		menuBox.getChildren().addAll(label, menu);
		leftRegion.setCenter(menuBox);
		
		layout.setLeft(leftRegion);
	}
	
	protected void createTab(String viewerType) {
		try {
			BorderPane main = new BorderPane();
			Constructor<?> constructor = Class.forName(viewerType).getConstructors()[0];
			int nParam = constructor.getParameterCount();
			Object[] param = new Object[nParam];
			param[0] = main;
			for (int i = 0; i < nParam; i++) {
				Class<?> c = constructor.getParameterTypes()[i];
				if (c.equals(main.getClass())) {
					param[i] = main;
				}
				else if (c.equals(display.getClass())) {
					param[i] = display;
				}
				else if (c.equals(control.getClass())) {
					param[i] = control;
				}
			}
			Window w = (Window) constructor.newInstance(param);
			childTab = new ViewerTab(leftRegion, this, w);
			childTab.start();
		} 
		catch (Exception e) {
			// Do something
		}
	}
	
	protected void removeChild() {
		childTab = null;
		leftRegion.setCenter(menuBox);
	}
	
	public void update() {
		if (childTab != null) {
			childTab.update();
		}
	}
	
}
