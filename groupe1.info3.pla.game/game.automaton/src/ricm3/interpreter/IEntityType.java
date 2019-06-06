package ricm3.interpreter;

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

	public boolean isCollidingWith(IEntityType type) {
		switch (m_type) {
		case PLAYER:
			switch (type.m_type) {
			case PLAYER:
			case OBSTACLE:
			case DANGER:
				return true;
			default:
				return false;
			}
		case TEAM:
			switch (type.m_type) {
			case OBSTACLE:
			case DANGER:
				return true;
			default:
				return false;
			}
		case ADVERSARY:
			switch (type.m_type) {
			case ADVERSARY:
				return true;
			default:
				return false;
			}
		case OBSTACLE:
			switch (type.m_type) {
			case PLAYER:
			case OBSTACLE:
			case DANGER:
				return true;
			default:
				return false;
			}
		case DANGER:
			switch (type.m_type) {
			case PLAYER:
			case OBSTACLE:
			case DANGER:
				return true;
			default:
				return false;
			}
		default:
			return false;
		}
	}

}
