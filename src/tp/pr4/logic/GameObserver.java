package tp.pr4.logic;

public interface GameObserver {
	
	public void moveExecFinished(ReadOnlyBoard board, Counter player, Counter nextPlayer);
	
	public void moveExecStart(Counter player);
	
	public void onGameOver(ReadOnlyBoard board, Counter winner);
	
	public void onMoveError(java.lang.String msg);
	
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible);

	public void onUndoNotPossible();
	
	public void reset(ReadOnlyBoard board, Counter player, java.lang.Boolean undoPossible);
}
