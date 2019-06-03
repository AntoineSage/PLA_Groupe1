package ricm3.interpreter;

import edu.ricm3.game.purgatoire.Entity;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public abstract class ICondition {

	public ICondition() {
	}

	boolean eval(Entity e, IKeyEnum lastPressedKey) {
		return eval(e);
	}

	boolean eval(Entity e) {
		return false;
	}

	public static class ITrue extends ICondition {
		public ITrue() {
		}

		@Override
		boolean eval(Entity e) {
			return true;
		}
	}

	public static class IGotPower extends ICondition {
		public IGotPower() {
		}

		@Override
		boolean eval(Entity e) {
			return e.gotPower();
		}
	}

	public static class IGotStuff extends ICondition {
		public IGotStuff() {
		}

		@Override
		boolean eval(Entity e) {
			return e.gotStuff();
		}
	}

	public static class IKey extends ICondition {
		IKeyEnum key;
		
		public IKey(IKeyEnum k) {
			key = k;
		}

		@Override
		boolean eval(Entity e, IKeyEnum lastPressedKey) {
			return lastPressedKey.equals(key);
		}
	}

	public static class IMyDir extends ICondition {
		IDirection direction;
		
		public IMyDir(IDirection d) {
			direction = d;
		}

		@Override
		boolean eval(Entity e) {
			return e.directionIs(direction);
		}
	}

	public static class ICell extends ICondition {
		IDirection direction;
		IEntity entity;
		
		public ICell (IDirection d, IEntity e) {
			direction = d;
			entity = e;
		}

		@Override
		boolean eval(Entity e) {
			return e.cellAtIs(direction, entity);
		}
	}

	public static class IClosest extends ICondition {
		IDirection direction;
		IEntity entity;
		
		public IClosest (IEntity e, IDirection d) {
			direction = d;
			entity = e;
		}

		@Override
		boolean eval(Entity e) {
			return e.closestEntityAt(entity, direction);
		}
	}

	public static abstract class IUnaryCondition extends ICondition {
		ICondition condition;

		IUnaryCondition() {
		}

		public void setCondition(ICondition condition) {
			this.condition = condition;
		}
	}

	public static class INot extends IUnaryCondition {

		public INot() {
		}

		@Override
		boolean eval(Entity e) {
			return !condition.eval(e);
		}
	}

	public static abstract class IBinaryCondition extends ICondition {
		ICondition left;
		ICondition right;

		IBinaryCondition() {
		}

		public void setConditions(ICondition left, ICondition right) {
			this.left = left;
			this.right = right;
		}
	}

	public static class IAnd extends IBinaryCondition {

		public IAnd() {
		}

		@Override
		boolean eval(Entity e) {
			return left.eval(e) && right.eval(e);
		}
	}

	public static class IOr extends IBinaryCondition {

		public IOr() {
		}

		@Override
		boolean eval(Entity e) {
			return left.eval(e) || right.eval(e);
		}
	}
}