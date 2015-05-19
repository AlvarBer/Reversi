package tp.pr5.logic;

/**
 * Represents the colour information of a counter.
 * The enumeration is used to store the information of each position on the board, for which reason it also contains a symbol to indicate the absence of a counter in that position.
 * It is also used to identify the player, for example for the information of whose turn it is.
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 28/04/2015
 * @since: Assignment 1
 */

public enum Counter {
	EMPTY("Empty"), WHITE("White"), BLACK("Black");
	
	private String name;
	private PlayerType mode = PlayerType.HUMAN;
	
	Counter(String name) {
		this.name = name;
	}
	
	public PlayerType getMode() { 
		return mode;
	}
	public void setMode(PlayerType mode) {
		this.mode = mode;
	}
	
	public String toString() {return name;}
	
}
