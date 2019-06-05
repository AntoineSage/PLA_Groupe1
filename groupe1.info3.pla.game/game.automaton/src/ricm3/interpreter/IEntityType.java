package ricm3.interpreter;

import java.util.Iterator;
import java.util.List;

public class IEntityType {
	public enum IType {
		ADVERSARY, DANGER, GATE, JUMP, MISSILE, OBSTACLE, PICK, TEAM, VOID, PLAYER, ANYTHING
	}

	public static final IEntityType ADVERSARY = new IEntityType(IType.ADVERSARY);
	public static final IEntityType DANGER = new IEntityType(IType.DANGER);
	public static final IEntityType GATE = new IEntityType(IType.GATE);
	public static final IEntityType JUMP = new IEntityType(IType.JUMP);
	public static final IEntityType MISSILE = new IEntityType(IType.MISSILE);
	public static final IEntityType OBSTACLE = new IEntityType(IType.OBSTACLE);
	public static final IEntityType PICK = new IEntityType(IType.PICK);
	public static final IEntityType TEAM = new IEntityType(IType.TEAM);
	public static final IEntityType VOID = new IEntityType(IType.VOID);
	public static final IEntityType PLAYER = new IEntityType(IType.PLAYER);
	public static final IEntityType ANYTHING = new IEntityType(IType.ANYTHING);
	
	IType m_type;
	
	public IEntityType(IType type) {
		m_type = type;
	}

	public boolean isCollidingWith(List<IEntityType> m_collidingTypes) {
		Iterator<IEntityType> iter = m_collidingTypes.iterator();
		while(iter.hasNext()) {
			IEntityType type = iter.next();
			if(isCollidingWith(type)) return true;
		}
		
		return false;
	}

	private boolean isCollidingWith(IEntityType type) {
		return false;
	}
	
}
