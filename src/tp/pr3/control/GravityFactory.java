package tp.pr3.control;

import tp.pr3.logic.GravityMove;
import tp.pr3.logic.GameRules;
import tp.pr3.logic.Move;
import tp.pr3.logic.Counter;
import tp.pr3.logic.GravityRules;
import tp.pr3.logic.Board;
import java.util.Scanner;

public class GravityFactory implements GameTypeFactory {

	//Default size for the board of Gravity
	private static int dim_x = 10;
	private static int dim_y = 10;


	public GravityFactory() {
		this.dim_x = 10;
		this.dim_y = 10;
	}

	public GravityFactory(int x, int y) {
		this.dim_x = x;
		this.dim_y = y;
	}

	@Override
	public Player createHumanPlayerAtConsole(final Scanner in) { //Declared final in order to be able to compile it
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
		GravityMove gMove = new GravityMove(col, row, colour);
		return gMove;
	}

	@Override
	public Player createRandomPlayer() {
		Player player = new RandomGravityPlayer();
		return player;
	}

	//Class constants

	@Override
	public GameRules createRules() {
		return createRules(dim_x, dim_y);
	}

	public GameRules createRules(int x, int y) {
		GravityRules gRules = new GravityRules(x, y);
		return gRules;
	}
}