package tp.pr5.logic;

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
	 * When the execution of undo starts, the observer receives a notification through this method.
	 * 
	 * @param board The corresponding board (read only).
	 * @param nextPlayer The player who plays next.
	 * @param undoPossible true if there are moves that can be undone, otherwise false
	 */
	public void onUndoStart(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible);
	
	/**
	 * When the execution of undo finishes, the observer receives a notification through this method.
	 * 
	 * @param board The corresponding board (read only).
	 * @param nextPlayer The player who plays next.
	 * @param undoPossible true if there are moves that can be undone, otherwise false
	 */
	public void onUndoFinish(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible);

	/**
	 * When the undo fails, because it is not possible, observers are notified through this method.
	 * 
	 */
	public void onUndoNotPossible();
	
	/**
	 * When the game is reset, either with a new game or simply restarting the current game, the observer receives a notification through this method.
	 * 
	 * @param board The corresponding board (read only).
	 * @param player
	 * @param undoPossible
	 */
	public void reset(ReadOnlyBoard board, Counter player, java.lang.Boolean undoPossible);
        
        /**
	 * When an observer is added to the Game, the observer receives a notification through this method
	 * 
	 * @param board The corresponding board (read only).
	 * @param nextPlayer The player who plays next.
	 */
       public void onAddObserver(Board board, Counter nextPlayer);
}
