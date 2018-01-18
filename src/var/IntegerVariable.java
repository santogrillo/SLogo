package var;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class IntegerVariable extends Variable {

	private IntegerProperty value;
	
	public IntegerVariable(String name, int value) {
		super(name);
		this.value = new SimpleIntegerProperty(value);
	}
	
	public IntegerProperty getValue() {
		return value;
	}

	public void setValue(Number newValue) {
		value = new SimpleIntegerProperty(newValue.intValue());
	}
}
