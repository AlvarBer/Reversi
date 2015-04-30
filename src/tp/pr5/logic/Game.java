package tp.pr5.logic;

import tp.pr5.control.Player;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class which contains the Game information. It stores the state of the board, whose turn it is, whether or not one of the players has won, etc. as well as the list of moves that have been made in order to undo them if required.
 * The game saves at least the last 10 moves.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 08/01/2015
 * @since: Assignment 1
 */

public class Game implements Observable<GameObserver> {

	//Constants 
	private static final byte MAX_UNDO = 10;

	//Attributes
	private Board board;
	private Counter turn;
	private boolean finished;
	private Counter winner;
	private DequeStack undoStack;
	private GameRules rules;
	private Collection<GameObserver> obs;

	//Constructor

	/**
	 * Constructs a new game.
	 *
	 * @param rules The game rules that are to be used during this game (at least until a reset is performed).
	 * @since: Assignment 2
	 */
	public Game(GameRules rules) {
		this.board = rules.newBoard();
		this.finished = false;
		this.turn = rules.initialPlayer();
		this.undoStack = new DequeStack();
		this.rules = rules;
		this.obs = new ArrayList<> ();
		reset(this.rules);
	}

	//Methods

	/**
	 * Restarts the current game. This operation cannot be undone. The parameter enables the type of game to be changed on reset.
	 *
	 * @param rules The game rules that are to be used from now on.
	 */
	public void reset(GameRules rules) {
		this.rules = rules;
		this.board = rules.newBoard();
		this.finished = false;
		this.turn = rules.initialPlayer();
		this.undoStack = new DequeStack();
		for (GameObserver o : obs ) {o.reset(board, turn, false);}
	}

	/**
	 * Executes the move indicated.
	 *
	 * @param move Move to be executed. It is assumed that the move is compatible with the rules of the game being played (if Connect-4 is being played, the move is a Connect-4 move,etc). If this is not the case, the behaviour is undefined.
	 * @since : Assignment 2
	 */
	public void executeMove(Move move) throws InvalidMove {
		
		for (GameObserver o : obs ) {o.moveExecStart(turn);}
		try {
			if (move.getPlayer() == this.turn) {
				if (!this.finished) {
					move.executeMove(board);
					Counter nextTurn = rules.nextTurn(this.turn, this.board);
					for (GameObserver o : obs ) {o.moveExecFinished(board,turn,nextTurn);}
					this.turn = nextTurn;
					undoStack.push(move);
					if (rules.isDraw(this.turn, this.board)) {
						this.finished = true;
						this.winner = Counter.EMPTY;
					} else {
						this.winner = rules.winningMove(move, board);
						if (winner != Counter.EMPTY)
							this.finished = true;
					}
					if (this.finished)
						for (GameObserver o : obs ) {o.onGameOver(board,winner);}				
				} else {
					throw new InvalidMove("Game already finished");
				}
			} else {
				throw new InvalidMove("The turn of the movement doesn't match with the current turn");				
			}
		}
		catch (InvalidMove ex) {
			for (GameObserver o : obs ) {o.onMoveError(ex.getMessage());}
			throw ex;
		}

	}

	/**
	 * Undo the last move executed.Executes the move indicated.
	 *
	 * @return True if the undo was successfully performed.
	 */
	public boolean undo() {
		boolean successful = false;
		if (!undoStack.isEmpty()) {
			undoStack.pop().undo(this.board);
			this.turn = rules.nextTurn(this.turn, board);
			successful = true;
		}
		for (GameObserver o : obs ) {o.onUndo(board,turn,!undoStack.isEmpty());}
			
		return successful;
	}

	/**
	 * Returns the colour of the player whose turn it is to move.
	 *
	 * @return Colour of the player, or Counter.EMPTY if the game has finished.
	 */
	public Counter getTurn() {
		return turn;
	}

	/**
	 * Returns the colour of the winner. It is only valid if the game has finished.
	 *
	 * @return Colour of the winner. If the game finished in a draw, Counter.EMPTY. If the game has not yet finished the result is undefined.
	 */
	public Counter getWinner() {
		return winner;
	}

	/**
	 * Returns true if the game has finished and false otherwise.
	 *
	 * @return True if the game has finished.
	 */
	public boolean isFinished() {
		return finished;
	}

	//Substitution of getBoard by overriding toString method in Game. Now it's not necessary to use the method getBoard to display the board
	//Not documented
	public String toString() {
		return board.toString();
	}

	public Move getMove(Player player) {
		return player.getMove(board, turn);
	}

	/**
	 * Observer method for the board. This method is added for the convenience of the unit tester but should not be used.
	 *
	 * @return Current state of the board.
	 */
	public Board getBoard() {
		return board;
	}

	@Override
	public void addObserver(GameObserver o) {
		obs.add(o);
                o.onAddObserver(board,turn);	
	}

	@Override
	public void removeObserver(GameObserver o) {
		obs.remove(o);	
	}
	
}