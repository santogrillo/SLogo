package commands;

import errors.ArgumentCountException;
import errors.InvalidArgumentException;
import errors.InvalidCommandException;
import errors.MissingBracketException;
import javafx.collections.ObservableList;
import var.Variable;

public class Stamp extends BasicCommand {

	public Stamp(String name, ObservableList<Variable> vars, ObservableList<UserCommand> methods) {
		super(name, vars, methods);
	}

	@Override
	public String executeCommand(TurtleDisplay td, CommandList commands, Integer id) throws NumberFormatException,
			ArgumentCountException, MissingBracketException, InvalidArgumentException, InvalidCommandException {
		Integer ID = 0;
		
		for(SingleTurtle t : td.getTurtleManager().getActiveTurtles()) {
			td.addStamp(t.getStamp());
			ID = t.getID();
		}
		
		return ID.toString();
	}

}
