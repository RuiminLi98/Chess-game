package structure;

import java.util.ArrayList;

/** 
 * @author Junjie He
 * @author Ruimin Li
 */

/**
 * This is the subclass Empty which extends the abstract class Cell.
 * Empty means the unit in the board without any piece standing on.  
 */

public class Empty extends Cell{

	/**
	 * Initialize the Empty object with cellName, pieceName, isAlive, x, y.
	 * @param cellName The cell's name, such as "##" or "  ". 
	 * @param pieceName The piece's name must be "Empty" to represent the there is no piece in this unit.
	 * @param isAlive Must be false to show no piece.
	 * @param x The x-coordinate of the piece on the board. 
	 * @param y The y-coordinate of the piece on the board.
	 */
	public Empty(String cellName, String pieceName, boolean isAlive, int x, int y) {
		super(cellName, pieceName, isAlive, x, y);
		// TODO Auto-generated constructor stub
	}

	/**
	 * There is no movement for an Empty piece.
	 * @param currX The x-coordinate of starting point in the movement.
	 * @param currY The y-coordinate of starting point in the movement.
	 * @param tarX The x-coordinate of targeting point in the movement.
	 * @param tarY The y-coordinate of targeting point in the movement.
	 * @return false.
	 */
	@Override
	public boolean move(int currX, int currY, int tarX, int tarY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * This method is going to search all of the possible location where the Empty can reach.
	 * @return null, because empty cannot reach anywhere
	 */
	@Override
	public ArrayList<Point> Searcher() {
		// TODO Auto-generated method stub
		return null;
	}

}
