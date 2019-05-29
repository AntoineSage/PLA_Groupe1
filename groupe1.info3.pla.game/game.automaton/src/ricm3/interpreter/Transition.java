package ricm3.interpreter;

public class Transition {
	public Condition m_condition;
	public Action m_action;
	public State m_nextState; 
	
	Transition(Condition condition, Action action, State nextState) {
		m_condition = condition;
		m_action = action;
		m_nextState = nextState;
	}
	
}
