package ricm3.interpreter;

import java.util.List;

import edu.ricm3.game.purgatoire.Entity;
import ricm3.parser.Ast.Parameter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public abstract class IAction {

	IAction() {
	}

	void exec(Entity e) {
	}

	public static class IMove extends IAction {
		private IDirection m_direction;

		IMove(IDirection direction) {
			m_direction = direction;
		}

		public IMove(List<Parameter> parameters) {
			int size = parameters.size();
			if (size == 1) {
				m_direction = (IDirection) parameters.get(0).make();
			} else if (size == 0) {
				m_direction = IDirection.FRONT;
			} else {
				throw new IllegalStateException();
			}
		}

		void exec(Entity e) {
			e.tryMove(m_direction);
		}
	}

	public static class IWait extends IAction {

		public IWait() {
		}

		void exec(Entity e) {
		}
	}

	public static class IWizz extends IAction {
		IDirection m_direction;

		public IWizz(List<Parameter> parameters) {
			int size = parameters.size();
			if (size == 1) {
				m_direction = (IDirection) parameters.get(0).make();
			} else if (size == 0) {
				m_direction = IDirection.FRONT;
			} else {
				throw new IllegalStateException();
			}
		}

		void exec(Entity e) {
			e.wizz(m_direction);
		}
	}

	public static class IPop extends IAction {
		IDirection m_direction;

		public IPop(List<Parameter> parameters) {
			int size = parameters.size();
			if (size == 1) {
				m_direction = (IDirection) parameters.get(0).make();
			} else if (size == 0) {
				m_direction = IDirection.FRONT;
			} else {
				throw new IllegalStateException();
			}
		}

		void exec(Entity e) {
			e.pop(m_direction);
		}
	}

	public static class IHit extends IAction {
		IDirection m_direction;

		public IHit(List<Parameter> parameters) {
			int size = parameters.size();
			if (size == 1) {
				m_direction = (IDirection) parameters.get(0).make();
			} else if (size == 0) {
				m_direction = IDirection.FRONT;
			} else {
				throw new IllegalStateException();
			}
		}

		void exec(Entity e) {
			e.hit(m_direction);
		}
	}

}
