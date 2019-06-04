package ricm3.interpreter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IState {
	int m_id;
	String m_name ;
	// Le nom de l'état (Waiting, Hungry, Angry, ...) peut vous servir à adapter la représentation de l'entité à son humeur. 
	
	IState(String name){
		m_name = name ;
	}

	public IState(String name, int id) {
		m_name = name;
		m_id = id;
	}
}
