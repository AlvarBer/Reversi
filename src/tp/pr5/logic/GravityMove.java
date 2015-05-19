package tp.pr5.logic;

import tp.pr5.Util.Misc;

public class GravityMove extends Move {


	//Attributes
	private int moveRow;
	private int moveColumn;
	private Counter moveColour;

	//Constructor
	
	/**
	 * Constructor of the class.
	 *
	 * @param moveColumn Number of the column which will be modified by the movement
	 * @param moveRow Number of the row which will be modified by the movement
	 * @param moveColour Colour of the player who has made the movement
	 */
	public GravityMove(int moveColumn, int moveRow, Counter moveColour) {
		this.moveColumn = moveColumn;
		this.moveColour = moveColour;
		this.moveRow = moveRow;
		this.playerType = moveColour.getMode();
	}
	

	@Override
	public void executeMove(Board board) throws InvalidMove {
		if (Misc.validPosition(board, getColumn(), getRow())) {
			if (board.getPosition(this.getColumn(), this.getRow()) == Counter.EMPTY)
				board.setPosition(gravityForce(board, this).getColumn(), gravityForce(board, this).getRow(), this.getPlayer());
			else
				throw new InvalidMove("position (" + getColumn() + ", " + getRow() + ") is already occupied.");
		} else
			throw new InvalidMove("position (" + getColumn() + ", " + getRow() + ") is not on the board.");

	}

	@Override
	public void undo(Board boa) {
		boa.setPosition(this.getColumn(), this.getRow(), Counter.EMPTY);
	}

	@Override
	public int getRow() {
		return moveRow;
	}
	

	@Override
	public int getColumn() {		
		return this.moveColumn;
	}

	@Override
	public Counter getPlayer() {
		return this.moveColour;
	}

	/**
	 * Mutator method that modifies the number of a row of a certain movement
	 * 
	 * @param moveRow The number of the row to be modified
	 */
	public void setMoveRow(int moveRow) {
		this.moveRow = moveRow;
	}
	
	/**
	 * Mutator method that modifies the number of a column of a certain movement
	 * 
	 * @param moveColumn The number of the column to be modified
	 */
	public void setMoveColumn(int moveColumn) {
		this.moveColumn = moveColumn;
	}
	
	/**
	 * Function that tells exert Gravity to move in a certain direction
	 * 
	 * @param board
	 * @param gravityMove
	 * @return
	 */
	private static GravityMove gravityForce(Board board, GravityMove gravityMove) {
		int distanceToLeftSide = gravityMove.getColumn();
		int distanceToUpSide = gravityMove.getRow();
		int distanceToRightSide = board.getWidth() + 1 - distanceToLeftSide;
		int distanceToDownSide = board.getHeight() + 1 - distanceToUpSide;

		int min = Math.min(Math.min(Math.min(distanceToLeftSide, distanceToUpSide), distanceToRightSide), distanceToDownSide);

		if (distanceToLeftSide == distanceToRightSide && distanceToUpSide == distanceToDownSide) {
			//The counter is in balance
		} else if (distanceToLeftSide == distanceToUpSide && distanceToLeftSide == distanceToDownSide && distanceToLeftSide == min) {
			exertGravity(board, gravityMove, "left");
		} else if (distanceToUpSide == distanceToLeftSide && distanceToUpSide == distanceToRightSide && distanceToUpSide == min) {
			exertGravity(board, gravityMove, "up");
		} else if (distanceToRightSide == distanceToUpSide && distanceToRightSide == distanceToDownSide && distanceToRightSide == min) {
			exertGravity(board, gravityMove, "right");
		} else if (distanceToDownSide == distanceToLeftSide && distanceToDownSide == distanceToRightSide && distanceToDownSide == min) {
			exertGravity(board, gravityMove, "down");
		} else if (min == distanceToLeftSide) {
			if (min == distanceToUpSide) {
				exertGravity(board, gravityMove, "upleft");
			} else if (min == distanceToDownSide) {
				exertGravity(board, gravityMove, "downleft");
			} else {
				exertGravity(board, gravityMove, "left");
			}
		} else if (min == distanceToUpSide) {
			if (min == distanceToLeftSide) {
				exertGravity(board, gravityMove, "upleft");
			} else if (min == distanceToRightSide) {
				exertGravity(board, gravityMove, "upright");
			} else {
				exertGravity(board, gravityMove, "up");
			}
		} else if (min == distanceToRightSide) {
			if (min == distanceToUpSide) {
				exertGravity(board, gravityMove, "upright");
			} else if (min == distanceToDownSide) {
				exertGravity(board, gravityMove, "downright");
			} else {
				exertGravity(board, gravityMove, "right");
			}
		} else if (min == distanceToDownSide) {
			if (min == distanceToRightSide) {
				exertGravity(board, gravityMove, "downight");
			} else if (min == distanceToLeftSide) {
				exertGravity(board, gravityMove, "downleft");
			} else {
				exertGravity(board, gravityMove, "down");
			}
		}

		return gravityMove;
	}

	/**
	 * Given a direction moves the Counter simulating gravity
	 * 
	 * @param board
	 * @param gravityMove
	 * @param direction
	 * @return
	 */
	private static GravityMove exertGravity(Board board, GravityMove gravityMove, String direction) {
		int i = 0;
		int j = 0;
		int totalI = gravityMove.getColumn();
		int totalJ = gravityMove.getRow();

		if (direction.equals("up")) {
			j = -1;
		} else if (direction.equals("down")) {
			j = 1;
		} else if (direction.equals("left")) {
			i = -1;
		} else if (direction.equals("right")) {
			i = 1;
		} else if (direction.equals("downleft")) {
			i = -1;
			j = 1;
		} else if (direction.equals("downright")) {
			i = 1;
			j = 1;
		} else if (direction.equals("upleft")) {
			i = -1;
			j = -1;
		} else if (direction.equals("upright")) {
			i = 1;
			j = -1;
		}
		while (Misc.validPosition(board, totalI + i, totalJ + j) && board.getPosition(totalI + i, totalJ + j) == Counter.EMPTY) {
			totalI = totalI + i;
			totalJ = totalJ + j;
		}
		gravityMove.setMoveColumn(totalI);
		gravityMove.setMoveRow(totalJ);

		return gravityMove;
	}


}