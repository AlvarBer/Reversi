package tp.pr5.views.console;

import tp.pr5.logic.Counter;
import tp.pr5.logic.GameObserver;
import tp.pr5.logic.Observable;
import tp.pr5.logic.ReadOnlyBoard;
import tp.pr5.Util.Misc;
import tp.pr5.control.ConsoleController;
import tp.pr5.logic.Board;

/**
 * A class that implement a console view. Note that it implement GameObserver.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 19/03/2015
 * @since: Assignment 4
 */
public class ConsoleView implements GameObserver  {	
	
	/**
	 * The constructor of the class.
	 * 
	 * @param g A Game observable (an Game object).
	 * @param c A console controller.
	 */
	public ConsoleView(Observable<GameObserver> g, ConsoleController c) {
		g.addObserver(this);
	}

	//Methods
	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player, Counter nextPlayer) {
		update(board,nextPlayer);	
	}
	@Override
	public void moveExecStart(Counter player) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		System.out.println (board);
		System.out.print ("Game over. ");
		if (winner==Counter.EMPTY)
			System.out.println("Game ended in a draw");
		else
			System.out.println(winner + " wins");
		System.out.println("Closing the game... ");		
	}
	@Override
	public void onMoveError(String msg) {
		System.err.println("Invalid move: " + msg);
		
	}
	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible) {
		if (!undoPossible)
			onUndoNotPossible();
		update(board,nextPlayer);
		
	}
	@Override
	public void onUndoNotPossible() {
		System.err.println ("Nothing to undo.");
	}
	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		System.out.println ("Game restarted.");
		update(board,player);
	}
	
	private void update (ReadOnlyBoard board,Counter turn) {
		System.out.println(board);	
		System.out.println (turn + " to move");
	}

    @Override
    public void onAddObserver(Board board, Counter nextPlayer) {
        update(board,nextPlayer);
    }
}
