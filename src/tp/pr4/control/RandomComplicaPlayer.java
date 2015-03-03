package tp.pr4.control;

import tp.pr4.Util.Misc;
import tp.pr4.logic.Board;
import tp.pr4.logic.ComplicaMove;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Move;

public class RandomComplicaPlayer implements Player {

	@Override
	public Move getMove(Board board, Counter colour) {
		ComplicaMove randomMove;
		int i = Misc.randInt(1, board.getWidth());

		randomMove = new ComplicaMove(i, colour);

		return randomMove;
	}
}