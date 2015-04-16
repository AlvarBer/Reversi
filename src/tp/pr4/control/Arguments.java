package tp.pr4.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import tp.pr4.Main;
import tp.pr4.logic.Game;


/**
 * Class that manages the execution of arguments to run the game with preset settings
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 3
 */
public class Arguments {
	
	//Attributes
	private GameTypeFactory factory;
	private Game game;
	private Controller cntr;

	//Constructor
	/**
     * Class constructor.
     * @param args The arguments that must be handled
     */
	public Arguments(String[] args) throws ArgumentException {
		this.factory = new Connect4Factory();
		this.game = new Game(factory.createRules());
		this.cntr = new ConsoleController(factory, game);
		argumentsHandling(args);
	}

	//Methods
	/**
     * Set GameTypeFactory, a Controller and a Game to the attributes depending on the arguments
     * @param args The arguments that must be handled
     */
	private void argumentsHandling(String[] args) throws ArgumentException {
		if (args.length != 0) {			
			//Initializes the list and the iterator
			ArrayList<String> arguments = new ArrayList<String>();
			for (int i=0;i < args.length;i++) {
				arguments.add(args[i]);
			}
			Iterator it = arguments.iterator();
			String argument;
			
			//Iterates the arguments until the end
			while(it.hasNext()) {
				argument = (String) it.next();				
				switch (argument) {
				case "-g":
				case "--game": {
					try {argument = (String) it.next();} 
					catch (NoSuchElementException ex) {
						throw new ArgumentException("Unrecognized option: Specify the game that you want to play");						
					}							
					//Select the type of game
					switch (argument) {
						case "c4": {
							this.factory = new Connect4Factory();
							this.game = new Game(factory.createRules());
						} break;					
						case "co": {
								this.factory = new ComplicaFactory();
								this.game = new Game(factory.createRules());							
							
						} break;
						case "gr": {
							try {
								argument = (String) it.next();													
								int x, y;
								if (argument.equalsIgnoreCase("-x") || argument.equalsIgnoreCase("--dimX")) {
									try {argument = (String) it.next();} 
									catch (NoSuchElementException ex) {
										throw new ArgumentException("Unrecognized option: Enter a valid parameter for the width");		
									}								
									x = Integer.parseInt(argument);
								} else {
									throw new ArgumentException("Unrecognized option: " + argument);
								}
								
								try {argument = (String) it.next();} 
								catch (NoSuchElementException ex) {
									throw new ArgumentException("Unrecognized option: You must specify the height of the board using -y <Integer>");		
								}
								if (argument.equalsIgnoreCase("-y") || argument.equalsIgnoreCase("--dimY")) {
									try {argument = (String) it.next();} 
									catch (NoSuchElementException ex) {
										throw new ArgumentException("Unrecognized option: Enter a valid parameter for the height");		
									}
									y = Integer.parseInt(argument);
								} else {
									throw new ArgumentException("Unrecognized option: " + argument);
								}
								this.factory = new GravityFactory();
								this.game = new Game(new GravityFactory().createRules(x, y));							
							} 
							catch (NoSuchElementException ex) {
								this.factory = new GravityFactory();
								this.game = new Game(factory.createRules());													
							}
						} break;
						default: {
							throw new ArgumentException("Game '" + argument + "' incorrect.");
						}
					} 
				} break;
				case "-h":
				case "--help": {
					System.out.println("usage: " + Main.class.getName() + " [-g <game>] [-h] [-x <columnNumber>] [-y <rowNumber>]");
					System.out.println(" -g,--game <game>           Type of game (c4, co, gr). By default, c4.");
					System.out.println(" -h,--help                  Displays this help.");
					System.out.println(" -x,--dimX <columnNumber>   Number of columns on the board (Gravity only).\n" +
							"                            By default, 10.");
					System.out.println(" -y,--dimY <rowNumber>      Number of rows on the board (Gravity only). By\n" +
							"                            default, 10.");
				}
				break;
				case "-u":
				case "--ui": {
					try {argument = (String) it.next();} 
					catch (NoSuchElementException ex) {
						throw new ArgumentException("Unrecognized option: Specify the view to use <console> || <window>");						
					}
					if (argument.equalsIgnoreCase("console"))
						cntr = new ConsoleController(this.factory,this.game);
					else if (argument.equalsIgnoreCase("window"))
						cntr = new WindowController(this.factory,this.game);
					else
						throw new ArgumentException("Unrecognized option: " + argument);		
				} break;					
				default: {
					throw new ArgumentException("Unrecognized option: " + argument);
					}
				}
			}
		}	
	}
	
	/**
	 * 
	 * @return The Controller
	 */
	public Controller getController() {
		return cntr;
	}
}
