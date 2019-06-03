package ricm3.interpreter;
import java.util.Iterator;
import java.util.List;

import edu.ricm3.game.purgatoire.Entity;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IBehaviour {
	IState source ;
	List<ITransition> transitions ;
	
	public IBehaviour(IState source, List<ITransition> transitions){
		this.source = source ; 
		this.transitions = transitions ;
	}
	
	IState step(Entity e, IKeyEnum lastPressedKey) throws NoTransitionException {
		Iterator<ITransition> iter = transitions.iterator();
		while(iter.hasNext()) {
			ITransition transition = iter.next();
			if(transition.feasible(e, lastPressedKey)) {
				return transition.exec(e);
			}
		}
		throw new NoTransitionException();
	}
	
	public IState getSource() {
		return source;
	}
}
