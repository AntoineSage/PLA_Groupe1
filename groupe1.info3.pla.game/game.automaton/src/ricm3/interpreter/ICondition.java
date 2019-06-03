package ricm3.interpreter;

import edu.ricm3.game.purgatoire.Entity;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public abstract class ICondition {

	public ICondition() {
	}

	boolean eval(Entity e) {
		return true;
	}

	public static class ITrue extends ICondition {
		public ITrue() {
		}

		boolean eval(Entity e) {
			return true;
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

		boolean eval(Entity e) {
			return left.eval(e) && right.eval(e);
		}
	}

	public static class IOr extends IBinaryCondition {

		public IOr() {
		}

		boolean eval(Entity e) {
			return left.eval(e) || right.eval(e);
		}
	}
}