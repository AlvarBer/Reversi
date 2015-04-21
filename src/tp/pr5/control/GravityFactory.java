package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logic.Board;
import tp.pr5.logic.Counter;
import tp.pr5.logic.GameRules;
import tp.pr5.logic.GravityMove;
import tp.pr5.logic.GravityRules;
import tp.pr5.logic.Move;

/**
 * Implementation of the factory for Gravity. The methods return the objects capable of playing this game. 
 * Given that the size of the Gravity board is not fixed, the factory is parameterised by the board size.
 * 
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 3
 * @see: tp.pr5.control.GameTypeFactory
 */

public class GravityFactory implements GameTypeFactory {

		//Default size for the board of Gravity
		private static final int DEFAULT_DIM_X = 10;
		private static final int DEFAULT_DIM_Y = 10;
		
		//Attributes

		private int dim_x;
		private int dim_y;
		
		public GravityFactory() {
			this(DEFAULT_DIM_X,DEFAULT_DIM_Y);
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