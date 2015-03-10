package tp.pr4.logic;

import tp.pr4.Util.Misc;

public class GravityMove extends Move {

	//Constructor

	//Attributes
	private int moveRow;

	/**
	 * Class constructor. Invokes the constructor of the superclass
	 *
	 * @param moveColumn Column in which to place the counter.
	 * @param moveColour Color of the counter to be placed (also that of the player that places it).
	 */
	public GravityMove(int moveColumn, int moveRow, Counter moveColour) {
		super(moveColumn, moveColour);
		this.moveRow = moveRow;
	}

	@Override
	public void executeMove(Board board) throws InvalidMove {
		if (Misc.validPosition(board, getColumn(), getRow())) {
			if (board.getPosition(this.getColumn(), this.getRow()) == Counter.EMPTY)
				board.setPosition(gravityForce(board, this).getColumn(), gravityForce(board, this).getRow(), this.getMoveColour());
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

	public void setMoveRow(int moveRow) {
		this.moveRow = moveRow;
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

	@Override
	public int getColumn() {
		// TODO Auto-generated method stub
		return 0;
	}
}