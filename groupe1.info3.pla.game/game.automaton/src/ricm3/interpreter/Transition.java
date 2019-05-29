package ricm3.interpreter;

public class Transition {
	public Condition m_condition;
	public IAction m_action;
	public State m_nextState; 
	
	public Transition(Condition condition, IAction action, State nextState) {
		m_condition = condition;
		m_action = action;
		m_nextState = nextState;
	}
	
}
