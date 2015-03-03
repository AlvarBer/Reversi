package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.Board;
import tp.pr4.logic.Connect4Move;
import tp.pr4.logic.Connect4Rules;
import tp.pr4.logic.Counter;
import tp.pr4.logic.GameRules;
import tp.pr4.logic.Move;

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
