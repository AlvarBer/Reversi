package tp.pr4.logic;

import tp.pr4.control.Player;

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
	}

	//Methods

	/**
	 * Restarts the current game. This operation cannot be undone. The parameter enables the type of game to be changed on reset.
	 *
	 * @param rules The game rules that are to be used from now on.
	 */
	public void reset(GameRules rules) {
		this.board = rules.newBoard();
		this.finished = false;
		this.turn = rules.initialPlayer();
		this.undoStack = new DequeStack();

	}

	/**
	 * Executes the move indicated.
	 *
	 * @param move Move to be executed. It is assumed that the move is compatible with the rules of the game being played (if Connect-4 is being played, the move is a Connect-4 move,etc). If this is not the case, the behaviour is undefined.
	 * @return True if the move can be carried out. It is an error to try and place a counter out of turn, or when the game has finished.
	 * @since: Assignment 2
	 */
	public void executeMove(Move move) throws InvalidMove {

		if (move.getPlayer() == this.turn) {
			if (!this.finished) {
				move.executeMove(board);
				this.turn = rules.nextTurn(this.turn, this.board);
				undoStack.push(move);
				if (rules.isDraw(this.turn, this.board)) {
					this.finished = true;
					this.winner = Counter.EMPTY;
				} else {
					this.winner = rules.winningMove(move, board);
					if (winner != Counter.EMPTY)
						this.finished = true;
				}
			} else {
				throw new InvalidMove("Game already finished");
			}
		} else {
			throw new InvalidMove("The turn of the movement doesn't match with the current turn");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver(GameObserver o) {
		// TODO Auto-generated method stub
		
	}
	
}