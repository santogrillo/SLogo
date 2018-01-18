package var;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class DoubleVariable extends Variable {

	private DoubleProperty value;
	
	public DoubleVariable(String name, double value) {
		super(name);
		this.value = new SimpleDoubleProperty(value);
	}
	
	public DoubleProperty getValue() {
		return value;
	}
	
	public void setValue(Number newValue) {
		value = new SimpleDoubleProperty(newValue.doubleValue());
	}
}
