package tp.pr5.control;

import tp.pr5.logic.*;

import java.util.Scanner;

/**
 * Implementation of the factory for Reversi. 
 * The methods return the objects capable of playing this game.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 21/04/2015
 * @since: Assignment 5
 * @see: tp.pr5.control.GameTypeFactory
 */
public class ReversiFactory implements GameTypeFactory {

	@Override
	public Player createHumanPlayerAtConsole(final Scanner in) {
		return new Player() {
			public Move getMove(Board board, Counter colour) {
				System.out.print("Please provide the column number: ");
				int column = in.nextInt();
				in.nextLine();
				System.out.print("Please provide the row number: ");
				int row = in.nextInt();
				in.nextLine();
				return createMove(column, row, colour);
			}
		};
	}

	@Override
	public Move createMove(int col, int row, Counter colour) {
		ReversiMove rMove = new ReversiMove(col, row, colour);
		return rMove;
	}

	@Override
	public Player createRandomPlayer() {
		Player player = new RandomReversiPlayer();
		return player;
	}

	@Override
	public GameRules createRules() {
		return new ReversiRules();
	}

}
