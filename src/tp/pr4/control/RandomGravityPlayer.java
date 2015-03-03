package tp.pr4.control;

import tp.pr4.Util.Misc;
import tp.pr4.logic.Board;
import tp.pr4.logic.Counter;
import tp.pr4.logic.GravityMove;
import tp.pr4.logic.Move;

public class RandomGravityPlayer implements Player {

	@Override
	public Move getMove(Board board, Counter colour) {
		int i = Misc.randInt(1, board.getWidth());
		int j = Misc.randInt(1, board.getHeight());
		GravityMove randomMove;

		while (board.getPosition(i, j) != Counter.EMPTY) { //While we can't find a valid postion
			i = Misc.randInt(1, board.getWidth()); //Keep searching for one
			j = Misc.randInt(1, board.getHeight());
		}
		randomMove = new GravityMove(i, j, colour);

		return randomMove;
	}
}
