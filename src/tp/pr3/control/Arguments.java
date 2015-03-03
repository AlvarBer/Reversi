package tp.pr3.control;

import tp.pr3.Main;
import tp.pr3.logic.Game;

public class Arguments {
	//Atributes
	private GameTypeFactory factory;
	private Game game;

	//Constructor
	public Arguments(String[] args) throws ArgumentException {
		this.factory = null;
		this.game = null;
		argumentsHandling(args);
	}

	//Methods
	private void argumentsHandling(String[] args) throws ArgumentException {
		if (args.length == 0) {
			this.factory = new Connect4Factory();
			this.game = new Game(factory.createRules());
		} else {
			switch (args[0]) {
				case "-g":
				case "--game": {
					switch (args[1]) {
						case "c4": {
							try {
								if (args[2] != null) {
									String msg = "";
									for (int i = 2; i < args.length; i++)
										msg += args[i] + " ";
									//We now strip the space at the end
									throw new ArgumentException("Illegal arguments: " + msg.replaceAll("\\s+$", ""));
								}
							} catch (ArrayIndexOutOfBoundsException ex) {
								this.factory = new Connect4Factory();
								this.game = new Game(factory.createRules());
							}
						}
						break;
						case "co": {
							try {
								if (args[2] != null) {
									String msg = "";
									for (int i = 2; i < args.length; i++)
										msg += args[i] + " ";
									throw new ArgumentException("Illegal arguments: " + msg);
								}
							} catch (ArrayIndexOutOfBoundsException ex) {
								this.factory = new ComplicaFactory();
								this.game = new Game(factory.createRules());
							}
							break;
						}
						case "gr": {
							try {
								if (args[2] != null) {
									int x, y;
									if (args[2].equals("-x") || args[2].equals("--dimX")) {
										x = Integer.parseInt(args[3]);
									} else {
										throw new ArgumentException("Unrecognized option: " + args[2]);
									}
									if (args[4].equals("-y") || args[4].equals("--dimY")) {
										y = Integer.parseInt(args[5]);
									} else {
										throw new ArgumentException("Unrecognized option: " + args[4]);
									}
									this.factory = new GravityFactory();
									this.game = new Game(new GravityFactory().createRules(x, y));
								}
							} catch (ArrayIndexOutOfBoundsException ex) {
								this.factory = new GravityFactory();
								this.game = new Game(factory.createRules());
							}
						}
						break;
						default: {
							throw new ArgumentException("Game '" + args[1] + "' incorrect.");
						}
					}
				}
				break;
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
				default: {
					throw new ArgumentException("Unrecognized option: " + args[0]);
				}
			}
		}
	}

	/**
	 * @return the factory
	 */
	public GameTypeFactory getFactory() {
		return factory;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}


}
