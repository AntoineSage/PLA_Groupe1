package ricm3.interpreter;

import java.awt.event.KeyEvent;
import java.util.List;

import edu.ricm3.game.purgatoire.Controller;
import edu.ricm3.game.purgatoire.Entity;
import edu.ricm3.game.purgatoire.Singleton;
import ricm3.parser.Ast.Parameter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

abstract public class ICondition {

	ICondition() {
	}


	public ICondition(List<Parameter> parameters) {
	}

	abstract boolean eval(Entity e);

	abstract public static class IUnaryOp extends ICondition {
		protected ICondition m_condition;

		public IUnaryOp() {
		}

		public void setCondition(ICondition condition) {
			m_condition = condition;
		}
	}

	public static class INot extends IUnaryOp {

		@Override
		boolean eval(Entity e) {
			return !m_condition.eval(e);
		}

	}

	abstract public static class IBinaryOp extends ICondition {
		protected ICondition m_condition1;
		protected ICondition m_condition2;

		public IBinaryOp() {
		}

		public void setConditions(ICondition condition1, ICondition condition2) {
			m_condition1 = condition1;
			m_condition2 = condition2;
		}
	}

	public static class IAnd extends IBinaryOp {

		@Override
		boolean eval(Entity e) {
			return m_condition1.eval(e) && m_condition2.eval(e);
		}

	}

	public static class IOr extends IBinaryOp {

		@Override
		boolean eval(Entity e) {
			return m_condition1.eval(e) || m_condition2.eval(e);
		}

	}

	public static class ITrue extends ICondition {
		public ITrue() {
		}

		@Override
		boolean eval(Entity e) {
			return true;
		}
	}

	public static class IKey extends ICondition {
		private int m_key;

		public IKey(int key) {
			m_key = key;
		}

		public IKey(List<Parameter> parameters) {
			if(parameters.size() != 1) throw new IllegalStateException("IKey should always been created with a KeyEvent parameter");
			m_key = (int)parameters.get(0).make();
		}

		boolean eval(Entity e) {
			return Singleton.getController().isKeyPressed(m_key);
		}
	}

//	public static class IMyDir extends ICondition {
//		private KeyEvent m_key;
//		private Controller m_controller;
//
//		public IMyDir(KeyEvent key, Controller controller) {
//			m_key = key;
//		}
//
//		boolean eval(Entity e) {
//			return controller.isKeyPressed(key);
//		}
//	}
//
//	public class Cell extends ICondition {
//		IDirection direction;
//		Kind kind;
//		Distance distance;
//
//		Cell(Direction direction, Kind kind, Distance distance) {
//			this.direction = direction;
//			this.kind = kind;
//			this.distance = distance;
//		}
//
//		Cell(Direction direction, Kind kind) {
//			this.direction = direction;
//			this.kind = kind;
//			this.distance = 1;
//		}
//
//		boolean eval(Entity e) {
//			return is_Kind(this.kind, this.direction, this.distance, e.position, e.map);
//		}
//	}
//
//	public class GotPower extends Condition {
//		GotPower() {
//		}
//
//		boolean eval(Entity e) {
//			return (e.power > 0);
//		}
//	}

}