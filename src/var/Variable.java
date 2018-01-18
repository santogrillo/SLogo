package var;

import javafx.beans.binding.NumberExpression;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Variable {
	private StringProperty name;
	
	public Variable(String name) {
		this.name = new SimpleStringProperty(name);
	}
	
	public StringProperty getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = new SimpleStringProperty(newName);
	}
	
	public abstract NumberExpression getValue();
	public abstract void setValue(Number newValue);
	
}
