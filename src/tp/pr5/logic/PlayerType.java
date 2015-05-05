package tp.pr5.logic;


/**
 * Represents the type of player, either Human or Automatic.
 * The enumeration is used to store the information of what type of player is playing.
 *
 * @author : Alvaro Bermejo
 * @author : Francisco Lozano
 * @version : 28/04/2015
 * @since : Assignment 5
 */
public enum PlayerType {
	HUMAN("Human"), AUTO("Automatic");
	
	private String name;
	
	PlayerType(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
