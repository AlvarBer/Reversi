package tp.pr3.control;

import tp.pr3.Util.Misc;
import tp.pr3.logic.Board;
import tp.pr3.logic.ComplicaMove;
import tp.pr3.logic.Counter;
import tp.pr3.logic.Move;

public class RandomComplicaPlayer implements Player {

	@Override
	public Move getMove(Board board, Counter colour) {
		ComplicaMove randomMove;
		int i = Misc.randInt(1, board.getWidth());

		randomMove = new ComplicaMove(i, colour);

		return randomMove;
	}
}