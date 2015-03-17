package tp.pr4.views.console;

import tp.pr4.logic.Counter;
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.Observable;
import tp.pr4.logic.ReadOnlyBoard;
import tp.pr4.control.ConsoleController;

public class ConsoleView implements GameObserver (Observable<GameObserver> g, ConsoleController c) {	
	
	public ConsoleView(Observable<GameObserver> g) {
		g.addObserver(this);
	}

	//Methods
	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player,
			Counter nextPlayer) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void moveExecStart(Counter player) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onMoveError(String msg) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onUndoNotPossible() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		// TODO Auto-generated method stub
	}
}
