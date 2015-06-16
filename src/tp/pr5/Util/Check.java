package tp.pr5.Util;

import tp.pr5.logic.Board;
import tp.pr5.logic.Counter;
import tp.pr5.logic.ReadOnlyBoard;

/**
 * A fully static class that provides useful methods for checking things like the winner or if a Board is full
 *
 * @author	Alvaro Bermejo
 * @author	Francisco Lozano
 * @version	08/03/2015
 * @since	Assignment 2
 */
public class Check {

    //Methods

	/**
	 * Method which checks if there is a 4 counter combination in a given board according to the Connect 4 rules.
	 *
	 * @param board The board of the game
	 * @return True if there is a 4 counter combination either vertical, horizontal or diagonal
     * @see Board
	 */
	public static boolean checkConnect4Win(ReadOnlyBoard board) {
		int i = 0, j = 0;
		boolean finished = false;
		Counter firstTile;
		//Horizontal check
		while (i < board.getHeight() && !finished) {
			while (j < board.getWidth() - 3 && !finished) {
				firstTile = board.getPosition(j + 1, i + 1);
				if (firstTile != Counter.EMPTY) {
					if (firstTile == board.getPosition(j + 2, i + 1)) {
						if (firstTile == board.getPosition(j + 3, i + 1)) {
							if (firstTile == board.getPosition(j + 4, i + 1)) {
								finished = true;
							}
						}
					}
				}
				j++;
			}
			i++;
			j = 0;
		}

		//Vertical check
		i = 0;
		j = 0;
		while (i < board.getWidth() && !finished) {
			while (j < board.getHeight() - 3 && !finished) {
				firstTile = board.getPosition(i + 1, j + 1);
				if (firstTile != Counter.EMPTY) {
					if (firstTile == board.getPosition(i + 1, j + 2)) {
						if (firstTile == board.getPosition(i + 1, j + 3)) {
							if (firstTile == board.getPosition(i + 1, j + 4)) {
								finished = true;
							}
						}
					}
				}
				j++;
			}
			i++;
			j = 0;
		}


		//Diagonal check
		i = 0;
		j = 0;
		while (i < board.getWidth() - 3 && !finished) {
			while (j < board.getHeight() - 3 && !finished) {
				firstTile = board.getPosition(i + 1, j + 1);
				if (firstTile != Counter.EMPTY) {
					if (firstTile == board.getPosition(i + 2, j + 2)) {
						if (firstTile == board.getPosition(i + 3, j + 3)) {
							if (firstTile == board.getPosition(i + 4, j + 4)) {
								finished = true;
							}
						}
					}
				}
				j++;
			}
			i++;
			j = 0;
		}

		//Inverted Diagonal check
		i = 3;
		j = 0;
		while (i < board.getWidth() && !finished) {
			while (j < board.getHeight() - 3 && !finished) {
				firstTile = board.getPosition(i + 1, j + 1);
				if (firstTile != Counter.EMPTY) {
					if (firstTile == board.getPosition(i, j + 2)) {
						if (firstTile == board.getPosition(i - 1, j + 3)) {
							if (firstTile == board.getPosition(i - 2, j + 4)) {
								finished = true;
							}
						}
					}
				}
				j++;
			}
			i++;
			j = 0;
		}

		return finished;
	}

	/**
	 * Method which checks if there is a 4 counter combination in a given board according to the Complica rules.
	 *
	 * @param board The board of the game
	 * @return True if there is a 4 counter combination either vertical, horizontal or diagonal
     * @see Board
	 */
	public static Counter checkComplicaWin(ReadOnlyBoard board) {
		int i = 0, j = 0;
		boolean finished = false;
		Counter firstTile;

		Counter player4 = Counter.EMPTY;
		//Horizontal check
		while (i < board.getHeight() && !finished) {
			while (j < board.getWidth() - 3 && !finished) {
				firstTile = board.getPosition(j + 1, i + 1);
				if (firstTile != Counter.EMPTY) {
					if (firstTile == board.getPosition(j + 2, i + 1)) {
						if (firstTile == board.getPosition(j + 3, i + 1)) {
							if (firstTile == board.getPosition(j + 4, i + 1)) {
								if (board.getPosition(j + 4, i + 1) != player4 && player4 != Counter.EMPTY) {
									finished = true;
									player4 = Counter.EMPTY;
								} else {
									player4 = board.getPosition(j + 4, i + 1);
								}
							}
						}
					}
				}
				j++;
			}
			i++;
			j = 0;
		}

		//Vertical check
		i = 0;
		j = 0;
		while (i < board.getWidth() && !finished) {
			while (j < board.getHeight() - 3 && !finished) {
				firstTile = board.getPosition(i + 1, j + 1);
				if (firstTile != Counter.EMPTY) {
					if (firstTile == board.getPosition(i + 1, j + 2)) {
						if (firstTile == board.getPosition(i + 1, j + 3)) {
							if (firstTile == board.getPosition(i + 1, j + 4)) {
								if (board.getPosition(i + 1, j + 4) != player4 && player4 != Counter.EMPTY) {
									finished = true;
									player4 = Counter.EMPTY;
								} else {
									player4 = board.getPosition(i + 1, j + 4);
								}
							}
						}
					}
				}
				j++;
			}
			i++;
			j = 0;
		}

		//Diagonal check
		i = 0;
		j = 0;
		while (i < board.getWidth() - 3 && !finished) {
			while (j < board.getHeight() - 3 && !finished) {
				firstTile = board.getPosition(i + 1, j + 1);
				if (firstTile != Counter.EMPTY) {
					if (firstTile == board.getPosition(i + 2, j + 2)) {
						if (firstTile == board.getPosition(i + 3, j + 3)) {
							if (firstTile == board.getPosition(i + 4, j + 4)) {
								if (board.getPosition(i + 4, j + 4) != player4 && player4 != Counter.EMPTY) {
									finished = true;
									player4 = Counter.EMPTY;
								} else {
									player4 = board.getPosition(i + 4, j + 4);
								}
							}
						}
					}
				}
				j++;
			}
			i++;
			j = 0;
		}

		//Inverted Diagonal check
		i = 3;
		j = 0;
		while (i < board.getWidth() && !finished) {
			while (j < board.getHeight() - 3 && !finished) {
				firstTile = board.getPosition(i + 1, j + 1);
				if (firstTile != Counter.EMPTY) {
					if (firstTile == board.getPosition(i, j + 2)) {
						if (firstTile == board.getPosition(i - 1, j + 3)) {
							if (firstTile == board.getPosition(i - 2, j + 4)) {
								if (board.getPosition(i - 2, j + 4) != player4 && player4 != Counter.EMPTY) {
									finished = true;
									player4 = Counter.EMPTY;
								} else {
									player4 = board.getPosition(i - 2, j + 4);
								}
							}
						}
					}
				}
				j++;
			}
			i++;
			j = 0;
		}

		return player4;
	}

    /**
     * Checks if there is a Reversi Winner
     *
     * @param board The board of this game
     * @return True if the board is full
     * @see Board
     */
    public static Counter checkReversiWinner(ReadOnlyBoard board) {
        int blackCounters = 0, whiteCounters = 0;
        Counter winner;
        for (int i = 1; i <= board.getWidth(); i++) {
            for (int j = 1; j <= board.getHeight(); j++) {
                if (board.getPosition(i, j) == Counter.BLACK)
                    blackCounters++;
                else if (board.getPosition(i, j) == Counter.WHITE)
                    whiteCounters++;
            }
        }

        if (whiteCounters > blackCounters)
            winner = Counter.WHITE;
        else if (whiteCounters < blackCounters)
            winner = Counter.BLACK;
        else
            winner = Counter.EMPTY;

        return winner;
    }

	/**
	 * Method which checks if a given board is full of counters by checking the top counters on each row
	 *
	 * @param board The board of the game
	 * @return True if the board is full
     * @see Board
	 */
	public static boolean checkBoardIsFullSimple(Board board) {
		boolean is_full = false;
		int i = 1;
		while (Misc.topCounter(board, i) == 1 && i <= board.getWidth()) {
			++i;
		}
		if (i > board.getWidth()) {
			is_full = true;
		}
		return is_full;
	}

	/**
     * Method which checks if a given board is full of counters by checking every single counter
	 *
	 * @param board The board of the game
	 * @return True if the board is full
     * @see Board
	 */
	public static boolean checkBoardIsFullComplex(ReadOnlyBoard board) {
		boolean is_full = true;


		for (int i = 1; i <= board.getWidth(); i++) {
			for (int j = 1; j <= board.getHeight(); j++) {
				if (board.getPosition(i, j) == Counter.EMPTY) {
					is_full = false;
				}
			}
		}

		return is_full;
	}
}
