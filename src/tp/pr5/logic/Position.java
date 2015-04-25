package tp.pr5.logic;

/**
 * Class that facilitates the work with a position in the board
 *
 * @author: Alvaro Bermejo
 * @author: Francisco Lozano
 * @version: 25/04/2015
 * @since: Assignment 5
 */
public class Position {
	//Attributes
	private int posX;
	private int posY;

	//Functions

	//Getters
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	//Setters
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setPos(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

}
