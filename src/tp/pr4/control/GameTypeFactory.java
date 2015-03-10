package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.Counter;
import tp.pr4.logic.GameRules;
import tp.pr4.logic.Move;

/**
 * Interface that collects together the construction methods of the different objects involved in a concrete game. 
 * There will be one implementation of this interface for each type of game supported.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 3
 */

public interface GameTypeFactory {

	/**
	 * Constructs a player responsible for asking the user via the console which command he or she would like to execute.
	 *
	 * @param in Input scanner that the player will use to ask the user.
	 * @return Player object that will be used to ask the user for the next command to be executed
	 */
	Player createHumanPlayerAtConsole(Scanner in);
	
	/**
	 * Constructs a move for a particular game. A given implementation may not use all the parameters.
	 *
	 * @param col Column where the counter is to be placed.
	 * @param row Row where the counter is to be placed (not used in C4 or Complica).
	 * @param colour Colour of the counter to be placed.
	 * @return Move object capable of executing the move for a given type of game.
	 */
	Move createMove(int col, int row, Counter colour);
	
	/**
	 * Constructs a player capable of playing the current game by randomly choosing moves.
	 *.
	 * @return Player object that will play randomly.
	 */
	Player createRandomPlayer();
	
	/**
	 * Constructs the concrete rules of the game.
	 *.
	 * @return The object that implements the rules of the required game.
	 */
	GameRules createRules();
	


}