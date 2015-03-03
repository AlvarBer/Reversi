package tp.pr3.control;

import tp.pr3.logic.Counter;
import tp.pr3.logic.GameRules;
import tp.pr3.logic.Move;
import java.util.Scanner;

public interface GameTypeFactory {

	Player createHumanPlayerAtConsole(Scanner in);
	//Constructs a player responsible for asking the user via the console which command he or she would like to execute.

	Move createMove(int col, int row, Counter colour);
	//Constructs a move for a particular game.

	Player createRandomPlayer();
	//Constructs a player capable of playing the current game by randomly choosing moves.

	GameRules createRules();
	//Constructs the concrete rules of the game. 


}