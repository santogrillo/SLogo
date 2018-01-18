package gui;

import java.util.Enumeration;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Raphael Kim ck174
 * 
 * Creates a new tab that holds one GUI panel and stacks
 *
 */
public class ViewerTab extends WindowViewer {

	private BorderPane mainRegion;
	private WindowViewer parentTab;
	
	private Window window;
	
	public ViewerTab(BorderPane p, WindowViewer pt, Window w) {
		super(p, pt.display, pt.control);
		parentTab = pt;
		layout.setPadding(new Insets(0));
		window = w;
		window.start();
		mainRegion = window.parent;
		setup();
	}
	
	private void setup() {
		MenuButton menu = new MenuButton("New Tab", null);
		Enumeration<String> iter = wTypes.getKeys();
		while (iter.hasMoreElements()) {
			String type = iter.nextElement();
			menu.getItems().add(Factory.createMenuItem(type, e -> createTab(wTypes.getString(type))));
		}
		Button remove = Factory.makeButton("Remove Tab", e -> removeTab());
		menuBox.getChildren().addAll(remove, menu);
		leftRegion.setCenter(menuBox);
		
		layout.setLeft(leftRegion);
		layout.setRight(mainRegion);
	}
	
	private void removeTab() {
		parentTab.removeChild();
	}
	
	public VBox getMenuBox() {
		return menuBox;
	}
	
	public void update() {
		window.update();
		if (childTab != null) {
			childTab.update();
		}
	}
}
