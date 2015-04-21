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
		if (Check.checkFullGravity(board) && !Check.checkConnect4Win(board)) {
			draw = true;
		}
		return draw;
	}

	@Override
	public Counter nextTurn(Counter lastPlayer, Board board) {
		return Misc.changeTurn(lastPlayer);
	}

	@Override
	public Counter winningMove(Move lastMove, Board board) {
		Counter winningMove = Counter.EMPTY;
		if (Check.checkConnect4Win(board)) {
			winningMove = lastMove.getPlayer();
		}
		return winningMove;
	}

}