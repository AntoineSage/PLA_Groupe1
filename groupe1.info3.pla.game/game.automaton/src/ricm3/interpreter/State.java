package ricm3.interpreter;

import java.util.LinkedList;
import java.util.List;

public class State {
	public List<Transition> m_transitions;
	
	public State(List<Transition> transitions) {
		m_transitions = transitions;
	}
	
	public State() {
		m_transitions = new LinkedList<Transition>();
	}
	
	public void addTransition(Transition transition) {
		m_transitions.add(transition);
	}
}
