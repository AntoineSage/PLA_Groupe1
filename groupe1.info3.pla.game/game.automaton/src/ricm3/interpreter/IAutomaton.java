package ricm3.interpreter;
import java.util.Iterator;
import java.util.List;

import edu.ricm3.game.purgatoire.entities.Entity;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IAutomaton {
	String name;
	IState current ;
	List<IBehaviour> behaviours ;
	
	public IAutomaton(IState initial, List<IBehaviour> behaviours, String name){
		this.current = initial ;
		this.behaviours = behaviours ;
		this.name = name;
	}
	
	public boolean step(Entity e) {
		Iterator<IBehaviour> iter = behaviours.iterator();
		while(iter.hasNext()) {
			IBehaviour behaviour = iter.next();
			if (behaviour.source.m_name.equals(current.m_name)) {
				try {
					current = behaviour.step(e);
				} catch (NoFeasibleTransition exception) {
					return false; 
				}
				return true;
			}
		}
		throw new IllegalStateException();		
	}

	public IAutomaton copy() {
		return new IAutomaton(current.copy(), behaviours, name);
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
