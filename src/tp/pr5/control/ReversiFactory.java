package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logic.Counter;
import tp.pr5.logic.GameRules;
import tp.pr5.logic.Move;

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
	public Player createHumanPlayerAtConsole(Scanner in) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Move createMove(int col, int row, Counter colour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player createRandomPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameRules createRules() {
		// TODO Auto-generated method stub
		return null;
	}

}
