package ricm3.interpreter;

import edu.ricm3.game.purgatoire.Entity;
import edu.ricm3.game.purgatoire.Entity.Direction;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public abstract class IAction {

	IAction() {
	}

	protected void exec(Entity e) {
	}

	public static class IMove extends IAction {
		Direction direction;

		public IMove(Direction direction) {
			this.direction = direction;
		}

		public IMove() {
			this(edu.ricm3.game.purgatoire.Entity.Direction.NORD);
		}

		protected void exec(Entity e) {
			e.move(direction);
		}
	}

}
