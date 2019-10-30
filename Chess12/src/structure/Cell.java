package structure;

import java.util.ArrayList;

import chess.Chess;

/** 
* @author Junjie He
* @author Ruimin Li
*/

/**
 * This is the abstract class which is used as the unit on board[][].
 * Each piece, such as king, has their own movement rules. They have to extend the cell class 
 * and write the different move methods. 
 */
public abstract class Cell {

	
	/**
	 * The cell's name, such as "##" or "  ",which is under the piece.
	 */
	public String cellName;
	
	/**
	 * The piece's name, such as "wR" or "bR".
	 */
	public String pieceName;
	
	/**
	 * The boolean value to show whether the piece is alive on the board.
	 */
	public boolean isAlive;
	
	/**
	 * The x-coordinate of the piece on the board. 
	 */
	public int x;
	
	/**
	 * The y-coordinate of the piece on the board.
	 */
	public int y;
	
	/**
	 * Initialize the Cell object with cellName, pieceName, isAlive, x, y.
	 * @param cellName The cell's name, such as "##" or "  ",which is under the piece. 
	 * @param pieceName The piece's real name, such as "wR" or "bR".
	 * @param isAlive The boolean value to show whether the piece is alive on the board.
	 * @param x The x-coordinate of the piece on the board. 
	 * @param y The y-coordinate of the piece on the board.
	 */
	public Cell(String cellName,String pieceName,boolean isAlive,int x,int y) {
		this.cellName=cellName;
		this.pieceName=pieceName;
		this.isAlive=isAlive;
		this.x=x;
		this.y=y;
	}
	
	/**
	 * The abstract move method which should be override by subclass.
	 * @param currX The x-coordinate of starting point in the movement.
	 * @param currY The y-coordinate of starting point in the movement.
	 * @param tarX The x-coordinate of targeting point in the movement.
	 * @param tarY The y-coordinate of targeting point in the movement.
	 * @return True if successfully move. Otherwise, return false.
	 */
	public abstract boolean move(int currX,int currY,int tarX,int tarY);
	
	/**
	 * The abstract Searcher method which should be override by subclass.
	 * @return An ArrayList contains all of the possible location of a piece can reach
	 */
	public abstract ArrayList<Point> Searcher();
	
	/**
	 * This method perform the exact movement from one position to another.
	 * It will put the override the targeting unit on the board and put an Empty object on the Starting position.
	 * @param currX The x-coordinate of starting point in the movement.
	 * @param currY The y-coordinate of starting point in the movement.
	 * @param tarX The x-coordinate of targeting point in the movement.
	 * @param tarY The y-coordinate of targeting point in the movement.
	 */
	public static void jump (int currX,int currY,int tarX,int tarY) {
		Cell temp=Chess.board[tarX][tarY];
		String temp2=Chess.board[currX][currY].cellName;
		Chess.board[tarX][tarY]=Chess.board[currX][currY];
		Chess.board[tarX][tarY].cellName=temp.cellName;
		Chess.board[tarX][tarY].x=temp.x;
		Chess.board[tarX][tarY].y=temp.y;
		Chess.board[currX][currY]=new Empty(temp2, "empty", false, currX, currY);
	}
	
}
