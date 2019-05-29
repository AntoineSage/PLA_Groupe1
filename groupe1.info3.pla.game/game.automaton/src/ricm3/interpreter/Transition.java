package ricm3.interpreter;

public class Transition {
	Condition m_condition;
	Action m_action;
	State m_nextState; 
	
	Transition(Condition condition, Action action, State nextState) {
		m_condition = condition;
		m_action = action;
		m_nextState = nextState;
	}
	
}
