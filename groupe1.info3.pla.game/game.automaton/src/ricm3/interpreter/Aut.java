package ricm3.interpreter;

import java.util.Iterator;

public class Aut {

	State m_initialState;
	State m_currentState;

	public Aut(State init, State current) {
		m_initialState = init;
		m_currentState = current;
	}

	public void step() {
		Transition t;
		Iterator<Transition> iter = m_currentState.m_transitions.iterator();
		while (iter.hasNext()) {
			t = iter.next();
			if (t.m_condition.eval())
				t.m_action.exec();
			m_currentState = t.m_nextState;
		}

	}
}
