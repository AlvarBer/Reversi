package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.Util.Misc;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Game;
import tp.pr4.logic.InvalidMove;
import tp.pr4.logic.Move;

/**
 * Class which controls the execution of the game, presenting the user with a set of alternatives and asking which he/she
 * wishes to choose until the game finishes.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 08/01/2015
 * @since: Assignment 1
 */

public class Controller {

	//Attributes
	private Game game;
	private Scanner in;
	private GameTypeFactory currentGame;
	private Player black;
	private Player white;

	//Constructor

	/**
     * Class constructor.
     * @param p The game which is to be played
     * @param in The scanner to be used to read the information the user provides.
     */
	public Controller (GameTypeFactory factory, Game p, Scanner in) {
		this.game = p;
		this.in = in;
		this.currentGame = factory;
		this.black = currentGame.createHumanPlayerAtConsole(in);
		this.white = currentGame.createHumanPlayerAtConsole(in);
	}
	
	//Methods
	/**
     * Execute the game until it finishes. 
     * It is assumed that this method is called just once. If it is called again, the behavior is undefined.
     */
	public void run() {	
		
		boolean exit = false;
		String strCommand;	
		Counter turn;
		while (!this.game.isFinished() && !exit) {
			Command command = Command.NO_COMMAND;			
			System.out.println(this.game);
			turn = game.getTurn();						
			System.out.println (Misc.strTurn(turn) + " to move");
			Move move;
			System.out.print ("Please enter a command: ");
			String wholeCommand = in.nextLine();
			Scanner strScanner = new Scanner(wholeCommand);
			strCommand = strScanner.next();
			try {
				command = Command.updateCommand(command, strCommand);
				switch (command) {
					case MAKE: {
						if (!strScanner.hasNext())
							throw new CommandException();
						strCommand = strScanner.next();							
						command = Command.updateCommand(command, strCommand);
						if (command.equals(Command.MAKE_A)) {			
							if (!strScanner.hasNext())
								throw new CommandException();
							strCommand = strScanner.next();	
							command = Command.updateCommand(command, strCommand);
							if (command.equals(Command.MAKE_A_MOVE)) {
								if (turn == Counter.BLACK)
									move = this.game.getMove(black);
								else
									move = this.game.getMove(white);
								try {
									game.executeMove(move);									
								} catch (InvalidMove e) {
									System.err.println("Invalid move: " + e.getMessage());
								}						
							}
						}
					} break;	
					case UNDO: {
						boolean undo;
						undo = this.game.undo();
						if (!undo) { 
							System.err.println ("Nothing to undo.");
						}	
					} break;	
					case RESTART: {	
						this.game.reset(currentGame.createRules());
						System.out.println ("Game restarted.");
					} break;
					case PLAY: {
						if (!strScanner.hasNext())
							throw new CommandException();
						strCommand = strScanner.next();	
						command = Command.updateCommand(command, strCommand);
						if (command.equals(Command.PLAY_CONNECT4)) {
							currentGame = new Connect4Factory();			
							this.game = new Game(currentGame.createRules());
							this.black = currentGame.createHumanPlayerAtConsole(in);
							this.white = currentGame.createHumanPlayerAtConsole(in);
							
							System.out.println ("Game restarted.");
							
						} 
						else if (command.equals(Command.PLAY_COMPLICA)) {
							currentGame = new ComplicaFactory();			
							this.game = new Game(currentGame.createRules());
							this.black = currentGame.createHumanPlayerAtConsole(in);
							this.white = currentGame.createHumanPlayerAtConsole(in);
							System.out.println ("Game restarted.");
						}
						else if (command.equals(Command.PLAY_GRAVITY)) {
							if (!strScanner.hasNext())
								throw new CommandException();	
							int x = strScanner.nextInt();
							if (x < 1) x = 1;
							if (!strScanner.hasNext())
								throw new CommandException();
							int y = strScanner.nextInt();
							if (y < 1) y = 1;
							GravityFactory factory = new GravityFactory(x,y);	
							this.currentGame = factory;
							
							this.game = new Game(factory.createRules());
							this.black = currentGame.createHumanPlayerAtConsole(in);
							this.white = currentGame.createHumanPlayerAtConsole(in);
							System.out.println ("Game restarted.");	
						}									
					} break;
					case PLAYER: {
						if (!strScanner.hasNext())
							throw new CommandException();
						strCommand = strScanner.next();	
						command = Command.updateCommand(command, strCommand);	
						Counter player = Counter.EMPTY;
						if (command.equals(Command.PLAYER_BLACK)) {
							player = Counter.BLACK;
						}
						else if (command.equals(Command.PLAYER_WHITE)) {
							player = Counter.WHITE;
						}
						if (!strScanner.hasNext())
							throw new CommandException();
						strCommand = strScanner.next();	
						command = Command.updateCommand(command, strCommand);
						if (command.equals(Command.PLAYER_HUMAN)) {
							if (player.equals(Counter.BLACK))
								this.black = currentGame.createHumanPlayerAtConsole(in);
							else if (player.equals(Counter.WHITE))
								this.white = currentGame.createHumanPlayerAtConsole(in);		
						}
						else if (command.equals(Command.PLAYER_RANDOM)) {
							if (player.equals(Counter.BLACK))
								this.black = currentGame.createRandomPlayer();
							else if (player.equals(Counter.WHITE))
								this.white = currentGame.createRandomPlayer();	
						}
					} break;
					case EXIT:{
						exit = true;
						System.out.println ("Exit requested. ");
						System.out.println ("Closing the game... ");
					}
					break;
					case HELP:{
						System.out.println("The available commands are:\n");
						System.out.println("MAKE A MOVE: place a counter on the board.");
						System.out.println("UNDO: undo the last move of the game.");
						System.out.println("RESTART: restart the game.");
						System.out.println("PLAY [c4|co|gr] [dimX dimY]: change the type of game.");
						System.out.println("PLAYER [white|black] [human|random]: change the type of game.");
						System.out.println("EXIT: exit the application.");
						System.out.println("HELP: show this help.\n");
					}
					break;	
				}
			} catch (CommandException ex) {System.err.println (wholeCommand.toLowerCase() + ": command not understood, please try again");}

		}
		if (this.game.isFinished()) {
			System.out.println (this.game.getBoard().toString());
			System.out.print ("Game over. ");
			if (game.getWinner()==Counter.EMPTY)
				System.out.println("Game ended in a draw");
			else
				System.out.println(Misc.strTurn(game.getWinner()) + " wins");
			System.out.println("Closing the game... ");
		}
	}
}