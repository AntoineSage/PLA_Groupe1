package ricm3.interpreter;

import edu.ricm3.game.purgatoire.Entity;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ICondition {

	public ICondition() {
	}

	boolean eval(Entity e) {
		return true;
	} // à redéfinir dans chaque sous-classe

	public class True extends ICondition {
		True() {
		}

		boolean eval(Entity e) {
			return true;
		}
	}

	public class Cell extends ICondition {
		Direction direction;
		Kind kind;
		Distance distance;

		Cell(Direction direction, Kind kind, Distance distance) {
			this.direction = direction;
			this.kind = kind;
			this.distance = distance;
		}

		Cell(Direction direction, Kind kind) {
			this.direction = direction;
			this.kind = kind;
			this.distance = 1;
		}

		boolean eval(Entity e) {
			return is_Kind(this.kind, this.direction, this.distance, e.position, e.map);
		}
	}

	public class GotPower extends ICondition {
		GotPower() {
		}

		boolean eval(Entity e) {
			return (e.power > 0);
		}
	}

	public class IUnaryCondition extends ICondition {
		ICondition condition;

		IUnaryCondition() {
		}

		public void setCondition(ICondition condition) {
			this.condition = condition;
		}
	}

	public class Not extends IUnaryCondition {

		public Not() {
		}

		boolean eval(Entity e) {
			return !condition.eval(e);
		}
	}

	public class IBinaryCondition extends ICondition {
		ICondition left;
		ICondition right;

		IBinaryCondition() {
		}

		public void setConditions(ICondition left, ICondition right) {
			this.left = left;
			this.right = right;
		}
	}

	public class And extends IBinaryCondition {

		public And() {
		}

		boolean eval(Entity e) {
			return left.eval(e) && right.eval(e);
		}
	}

	public class Or extends IBinaryCondition {

		public Or() {
		}

		boolean eval(Entity e) {
			return left.eval(e) || right.eval(e);
		}
	}
}