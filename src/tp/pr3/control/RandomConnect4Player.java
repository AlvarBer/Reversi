package tp.pr3.control;

import tp.pr3.Util.Misc;
import tp.pr3.logic.Board;
import tp.pr3.logic.Connect4Move;
import tp.pr3.logic.Counter;
import tp.pr3.logic.Move;

public class RandomConnect4Player implements Player {

	@Override
	public Move getMove(Board board, Counter colour) {
		Connect4Move randomMove;

		int i = Misc.randInt(1, board.getWidth());
		while (Misc.topCounter(board, i) == 1) { //While we can't find a valid column
			i = Misc.randInt(1, board.getWidth()); //Keep searching for one
		}
		randomMove = new Connect4Move(i, colour);

		return randomMove;
	}
}