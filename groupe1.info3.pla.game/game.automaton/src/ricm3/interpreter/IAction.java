package ricm3.interpreter;

import edu.ricm3.game.purgatoire.Entity;

public interface IAction {
		
	public boolean exec(Entity attachedObject);
}
