package tp.pr4.logic;

/**
 * An interface for a game observer.
 * 
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 4
 */
public interface GameObserver {
	
	/**
	 * When the execution of a move finishes, the observer receives a notification through this method.
	 * 
	 * @param board
	 * @param player
	 * @param nextPlayer
	 */
	public void moveExecFinished(ReadOnlyBoard board, Counter player, Counter nextPlayer);
	
	/**
	 * When a move starts executing, the observer receives a notification through this method.
	 * 
	 * @param player
	 */
	public void moveExecStart(Counter player);
	
	/**
	 * When the game finishes, the observer receives a notification through this method.
	 * 
	 * @param board
	 * @param winner
	 */
	public void onGameOver(ReadOnlyBoard board, Counter winner);
	
	/**
	 * Move errors are reported to observers through this method.
	 *
	 * @param msg
	 */
	public void onMoveError(java.lang.String msg);
	
	/**
	 * When the execution of undo finishes, the observer receives a notification through this method.
	 * 
	 * @param board
	 * @param nextPlayer
	 * @param undoPossible
	 */
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible);

	/**
	 * When the undo fails, because it is not possible, observers are notified through this method.
	 * 
	 */
	public void onUndoNotPossible();
	
	/**
	 * When the game is reset, either with a new game or simply restarting the current game, the observer receives a notification through this method.
	 * 
	 * @param board
	 * @param player
	 * @param undoPossible
	 */
	public void reset(ReadOnlyBoard board, Counter player, java.lang.Boolean undoPossible);
}
