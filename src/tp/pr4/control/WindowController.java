package tp.pr4.control;

import static java.lang.System.in;
import java.util.Scanner;
import tp.pr4.logic.Game;
import tp.pr4.logic.Counter;
import tp.pr4.logic.GameType;
import tp.pr4.logic.InvalidMove;
import tp.pr4.logic.Move;
import tp.pr4.views.window.MainWindow;


/**
 * Class which controls the execution of the game in a windows mode.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 4
 */

public class WindowController extends Controller {
	
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
	public WindowController(GameTypeFactory factory, Game g) {
            this.game = g;
	    this.in = new Scanner(System.in);
	    this.currentGame = factory;
		
            //TODO:Refactor this - Make an arrayList of Players
            this.black = currentGame.createHumanPlayerAtConsole(in);
            this.white = currentGame.createHumanPlayerAtConsole(in);

	}
	
	/**
	 * 	Creates the window view. It is assumed that this method is called just once. If it is called again, the behaviour is undefined.
	 */
	@Override
	public void run(){
            new MainWindow(game);		
	}
	
	public void makeMove(int col, int row, Counter turn) {
            Move move = currentGame.createMove(col, row, turn);
            try {
                    game.executeMove(move);									
            } catch (InvalidMove e) {}		
	}
	
	/**
	 * Undo the last move of the currently played game.
	 */
	public void undo() {
            game.undo();		
	}
	
	/**
	 * Reset the current game.
	 */
	public void reset() {
            game.reset(currentGame.createRules());		
	}
	
	/**
	 * Change to a new game.
	 * 
	 * @param gameType The game to be played.
	 * @param dimX The width of the board (necessary only for Gravity).
	 * @param dimY The height of the board (necessary only for Gravity)
	 */
	public void changeGame(GameType gameType, int dimX, int dimY) {
            switch (gameType){
                case CONNECT4: {
                    currentGame = new Connect4Factory();
                }break;
                case COMPLICA: {
                    currentGame = new ComplicaFactory();
                }break;
                case GRAVITY: {
                    currentGame = new GravityFactory(dimX,dimY);
                }                 
            }    									
            this.game.reset(currentGame.createRules());
            this.black = currentGame.createHumanPlayerAtConsole(in);
            this.white = currentGame.createHumanPlayerAtConsole(in);		
	}
	
	/**
	 * Make a random move.
	 * 
	 * @param player The player who should make the random move.
	 */
	public void randomMove(Counter player) {
            //TODO: Not implemented yet
		
	}
	
	/**
	 * Quit the application.
	 */
	public void requestQuit() {
            System.exit(0);
	}

}
