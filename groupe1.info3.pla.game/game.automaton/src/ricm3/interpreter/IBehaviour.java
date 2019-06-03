package ricm3.interpreter;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IBehaviour {
	IState source ;
	List<ITransition> transitions ;
	
	public IBehaviour(IState source, List<ITransition> transitions){
		this.source = source ; 
		this.transitions = transitions ;
	}
	
	IState step() {
		Iterator<ITransition>
		// - selectionne la première transition faisable
		// - lève une exception si aucune transition possible
		return // l'état cible de la transition choisie
	}
}
