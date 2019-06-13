package edu.ricm3.game.purgatoire;

public enum QuarterType {
	NOTHING(0), SPECIAL(1), NEST(2);
	
	private final int m_type;
	
	QuarterType(int type){
		m_type = type;
	}

	public int getTypeasInt() {
		return m_type;
	}
	
	public String getQuarterAsString() {
		return String.valueOf(m_type);
	}
	
	public QuarterType convertIntToType(int type) {
		for(QuarterType m_type : values()) {
			if(m_type.getTypeasInt() == type)
				return m_type;
		}
		return null;
	}
}
