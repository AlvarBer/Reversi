package tp.pr3.control;

import tp.pr3.logic.Board;
import tp.pr3.logic.Counter;
import tp.pr3.logic.Move;
import tp.pr3.logic.ComplicaMove;
import tp.pr3.logic.GameRules;
import tp.pr3.logic.ComplicaRules;

public class ComplicaFactory implements GameTypeFactory {
	@Override
	public Player createHumanPlayerAtConsole(final java.util.Scanner in) { //Declared final in order to be able to compile it
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
		ComplicaMove cMove = new ComplicaMove(col, colour);
		return cMove;
	}

	@Override
	public Player createRandomPlayer() {
		Player player = new RandomComplicaPlayer();
		return player;
	}

	@Override
	public GameRules createRules() {
		ComplicaRules cRules = new ComplicaRules();
		return cRules;
	}

}