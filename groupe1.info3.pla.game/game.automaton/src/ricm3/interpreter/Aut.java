package ricm3.interpreter;

import java.util.Iterator;

import edu.ricm3.game.Entity;

public class Aut {

	State m_initialState;
	State m_currentState;
	Entity m_attachedObject;

	public Aut(State init, State current, Entity attachedObject) {
		m_initialState = init;
		m_currentState = current;
		m_attachedObject = attachedObject;
	}

	public void step() {
		Transition t;
		Iterator<Transition> iter = m_currentState.m_transitions.iterator();
		while (iter.hasNext()) {
			t = iter.next();
			if (t.m_condition.eval())
				t.m_action.exec(m_attachedObject);
			m_currentState = t.m_nextState;
		}

	}
}
