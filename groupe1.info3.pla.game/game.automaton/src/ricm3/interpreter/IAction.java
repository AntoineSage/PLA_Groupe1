package ricm3.interpreter;

import edu.ricm3.game.purgatoire.Entity;
import ricm3.parser.Ast.Direction;
import ricm3.parser.Ast.Value;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IAction {

	IAction() {
	}

	protected void exec(Entity e) {
	}

	public class Move extends IAction {
		Direction direction;

		public Move(Direction direction) {
			this.direction = direction;
		}

		protected void exec(Entity e) {
			e.move(edu.ricm3.game.purgatoire.Entity.Direction.NORD);
		}
	}

}
