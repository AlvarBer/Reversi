package tp.pr3.Util;

import tp.pr3.logic.Board;
import tp.pr3.logic.Counter;

public class Check {
	/**
	 * Method which checks if there is a 4 counter combination in a given board according to the Connect 4 rules.
	 *
	 * @param board The board of the game
	 * @return True if there is a 4 counter combination either vertical, horizontal or diagonal
	 */
	public static boolean checkConnect4Win(Board board) {
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
	 */
	public static Counter CheckComplicaWin(Board board) {
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
	 * Method which checks if a given board is full of counters
	 *
	 * @param board The board of the game
	 * @return True if the board is full
	 */
	public static boolean checkFull(Board board) {
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

	public static boolean checkFullGravity(Board board) {
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
