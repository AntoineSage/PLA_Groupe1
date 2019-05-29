package ricm3.interpreter;

import edu.ricm3.game.purgatoire.Entity;

public class Move implements IAction {

	public Move(){
		
	}
	
	@Override
	public boolean exec(Entity attachedObject) {

		attachedObject.move(attachedObject.m_direction); 
		return true;
	}
	
}
