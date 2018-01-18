/**
 * @author Santo Grillo, Ben Welton
 */
package commands;

import java.util.ArrayList;

import errors.ArgumentCountException;

public class CommandList extends ArrayList<BasicCommand> {

	private static final long serialVersionUID = -1350559063474192891L;

	public CommandList() {
		super();
	}
	
	public BasicCommand nextCommand() throws ArgumentCountException {
		try {
			BasicCommand c = get(0);
			remove(0);
			return c;
		} catch (IndexOutOfBoundsException e){
			throw new ArgumentCountException();
		}
	}
}
