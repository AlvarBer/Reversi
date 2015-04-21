package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logic.Board;
import tp.pr5.logic.Connect4Move;
import tp.pr5.logic.Connect4Rules;
import tp.pr5.logic.Counter;
import tp.pr5.logic.GameRules;
import tp.pr5.logic.Move;

/**
 * Implementation of the factory for Connect-4. The methods return the objects capable of playing this game.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 3
 * @see: tp.pr5.control.GameTypeFactory
 */
public class Connect4Factory implements GameTypeFactory {

	@Override
	//Anonymous class that implements a player of Connect4 played by a human
	public Player createHumanPlayerAtConsole(final Scanner in) { //Declared final in order to be able to compile it
		return new Player() {
			public Move getMove(Board board, Counter colour) {
				System.out.print("Please provide the column number: ");
				int column = in.nextInt();
				in.nextLine();
				int row = 0;
				return createMove(column, row, colour);
			}
		};
	}

	@Override
	public Move createMove(int col, int row, Counter colour) {
		Connect4Move c4Move = new Connect4Move(col, colour);
		return c4Move;
	}

	@Override
	public Player createRandomPlayer() {
		Player player = new RandomConnect4Player();
		return player;
	}

	@Override
	public GameRules createRules() {
		Connect4Rules c4Rules = new Connect4Rules();
		return c4Rules;
	}

}
