package tp.pr5.logic;

import tp.pr5.Util.Check;
import tp.pr5.Util.Misc;

public class GravityRules implements GameRules {

	//Variables
	private int Width = 10;
	private int Height = 10;

	public GravityRules(int numCols, int numRows) {
		this.Width = numCols;
		this.Height = numRows;
	}

	@Override
	public Board newBoard() {
		Board board = new Board(Width, Height);

		return board;
	}

	@Override
	public Counter initialPlayer() {
		return Counter.WHITE;
	}

	@Override
	public boolean isDraw(Counter lastPlayer, Board board) {
		boolean draw = false;
		if (Check.checkFullGravity(board) && Check.checkComplicaWin(board) == Counter.EMPTY) {
			draw = true;
		}
		return draw;
	}

	@Override
	public Counter nextTurn(Counter lastPlayer, Board board) {
		return Misc.changeTurn(lastPlayer);
	}

	@Override
	public Counter winningMove(Move lastMove, ReadOnlyBoard board) {
		return Check.checkComplicaWin(board);
	}

}