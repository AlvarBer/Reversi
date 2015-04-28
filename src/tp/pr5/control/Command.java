package tp.pr5.control;

/**
 * Enumeration that contains and manage all the possible commands used by the Controller
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 10/03/2015
 * @since: Assignment 3
 */

public enum Command {
	NO_COMMAND,
	MAKE,MAKE_A,MAKE_A_MOVE,
	UNDO,
	RESTART,
	PLAY,PLAY_CONNECT4,PLAY_COMPLICA,PLAY_GRAVITY, PLAY_REVERSI,
	PLAYER,PLAYER_WHITE,PLAYER_BLACK,PLAYER_HUMAN, PLAYER_RANDOM,
	EXIT,
	HELP;
	
	/**
     * Updates a command depending on a String entered by the user
     * @param command The previous command to be managed 
     * @param strCommand The following String enter by the user, used to update the command
     * @return: An updated command
     */
	public static Command updateCommand(Command command, String strCommand) throws CommandException {
		strCommand = strCommand.toUpperCase();
		Command newCommand ;
		switch (command) {
		case NO_COMMAND: {
			switch (strCommand) {
				case "MAKE": {newCommand = MAKE;} break;
				case "UNDO": {newCommand = UNDO;} break;
				case "RESTART": {newCommand = RESTART;} break;
				case "PLAY": {newCommand = PLAY;} break;
				case "PLAYER": {newCommand = PLAYER;} break;
				case "EXIT": {newCommand = EXIT;} break;
				case "HELP": {newCommand = HELP;} break;
				default: throw new CommandException();
			} 
		} break;
		case MAKE: {
			if (strCommand.equals("A"))
				newCommand = MAKE_A; 
			else 
				throw new CommandException(); 
			} break;
		case MAKE_A: { 	
			if (strCommand.equals("MOVE"))
				newCommand = MAKE_A_MOVE; 
			else 
				throw new CommandException();
			} break;
		case PLAY: { 
			if (strCommand.equals("C4"))
				newCommand = PLAY_CONNECT4;
			else if (strCommand.equals( "CO"))
				newCommand = PLAY_COMPLICA;
			else if (strCommand.equals( "GR"))
				newCommand = PLAY_GRAVITY;
			else if (strCommand.equals( "RE"))
				newCommand = PLAY_REVERSI;
			else
				throw new CommandException();
			} break;
		case PLAYER:{
			if (strCommand.equals( "WHITE"))
				newCommand = PLAYER_WHITE;
			else if (strCommand.equals( "BLACK"))
				newCommand = PLAYER_BLACK;
			else
				throw new CommandException();
			} break;	
		case PLAYER_WHITE:
		case PLAYER_BLACK: {
			if (strCommand.equals( "HUMAN"))
				newCommand = PLAYER_HUMAN;
			else if (strCommand.equals( "RANDOM"))
				newCommand = PLAYER_RANDOM;
			else
				throw new CommandException();
			} break;
		default: throw new CommandException();
		}
		
		return newCommand;
	}
}


