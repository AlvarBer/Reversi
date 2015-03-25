package tp.pr4.control;


import tp.pr4.logic.Counter;
import tp.pr4.logic.Game;
import tp.pr4.logic.InvalidMove;
import tp.pr4.logic.Move;
import tp.pr4.views.console.ConsoleView;
import java.util.Scanner;

/**
 * Class which controls the execution of the game in a console mode, presenting the user with a set of alternatives and asking which he/she wishes to choose until the game finishes. 
 * This, in principle, is the class Controller of the previous assignments, with some refactoring to use MVC.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 4
 */

public class ConsoleController extends Controller {
	
	//Attributes
	private Game game;
	private Scanner in;
	private GameTypeFactory currentGame;	
	private Player black;
	private Player white;
	
	
	
    /**
     * Class constructor.
     * 
     * @param factory The gameType factory to be used.
     * @param g The game which is to be played.
     */
	public ConsoleController(GameTypeFactory factory, Game g) {
		this.game = g;
		this.in = new Scanner(System.in);
		this.currentGame = factory;
		
		//TODO:Refactor this - Make an arrayList of Players
		this.black = currentGame.createHumanPlayerAtConsole(in);
		this.white = currentGame.createHumanPlayerAtConsole(in);	
	}
	
	
	/**
	 * It create the console view, and executes the game until it finishes or the user request to exit.
	 * It is assumed that this method is called just once. If it is called again, the behaviour is undefined.
	 */
	public void run() {
                new ConsoleView(game,this);
		boolean exit = false;
		String strCommand;	
		Counter turn;
		Scanner strScanner = null;
		while (!this.game.isFinished() && !exit) {
			Command command = Command.NO_COMMAND;	
			turn = game.getTurn();						
			Move move;
			System.out.print ("Please enter a command: ");
			String wholeCommand = in.nextLine();
			strScanner = new Scanner(wholeCommand);
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
								} catch (InvalidMove e) {}						
							}
						}
					} break;	
					case UNDO: {
						this.game.undo();				
					} break;	
					case RESTART: {	
						this.game.reset(currentGame.createRules());						
					} break;
					case PLAY: {
						if (!strScanner.hasNext())
							throw new CommandException();
						strCommand = strScanner.next();	
						command = Command.updateCommand(command, strCommand);
						if (command.equals(Command.PLAY_CONNECT4)) {
							currentGame = new Connect4Factory();			
							this.game.reset(currentGame.createRules());
							this.black = currentGame.createHumanPlayerAtConsole(in);
							this.white = currentGame.createHumanPlayerAtConsole(in);
							
							System.out.println ("Game restarted.");
							
						} 
						else if (command.equals(Command.PLAY_COMPLICA)) {
							currentGame = new ComplicaFactory();			
							this.game.reset(currentGame.createRules());
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
							
							this.game.reset(factory.createRules());
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
			} catch (CommandException ex) {System.err.println(command + ": command not understood, please try again");;}				
		}
		strScanner.close();		
		this.in.close();
	}	

}
