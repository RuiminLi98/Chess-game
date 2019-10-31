package structure;

import java.util.ArrayList;

import chess.Chess;

/** 
 * @author Junjie He jh1285
 * @author Ruimin Li rl751
 */

/**
 * This is the subclass Knight which extends the abstract class Cell. 
 * The Knight class implements the movement of Knight piece on the board.
 */
public class Knight extends Cell{

	
/**
 *  five arg for Knight constructor
 * @param cellName its cells value in the table
 * @param pieceName its piece name 
 * @param isAlive whether this unit is alive 
 * @param x the x coordinate of Knight
 * @param y the y coordinate of Knight
 */
	public Knight(String cellName, String pieceName, boolean isAlive, int x, int y) {
		super(cellName, pieceName, isAlive, x, y);
		// TODO Auto-generated constructor stub
	}
	/**
	 * This method is to judge if the pieces named "name1" and "name2" are on the same side
	 * @param name1 Current pieces name 
	 * @param name2 Target pieces name
	 * @return if current pieces and target pieces are on one side, then true. Otherwise, false
	 */
	public boolean Ownside(String name1,String name2)
	{
		switch (name1) {
		case "wN": if (name2 == "wR" || name2== "wN" || name2== "wB" || name2== "wQ" || name2 == "wK" || name2 == "wp")
						return true;
		            else
		            	return false;
		default : if (name2 == "bR" || name2== "bN" || name2== "bB" || name2== "bQ" || name2 == "bK" || name2 == "bp")
						return true;
					else
						return false;
		}
	}
	/**
	 * This method is to judge if the Knight at (currX,currY) can jump to (tarX,tarY)
	 * @param currX The current piece X coordinate 
	 * @param currY The current piece Y coordinate 
	 * @param tarX The target position X coordinate 
	 * @param tarY The target position Y coordinate
	 * @return if the target position is available for current piece(Knight), then true. Otherwise, false
	 */
	public boolean JudgePos(int currX, int currY, int tarX, int tarY)
	{
		if((tarX==currX+1 && tarY==currY-2) || (tarX==currX+2 && tarY==currY-1) || (tarX==currX+2 && tarY==currY+1) || (tarX==currX+1 && tarY==currY+2) || (tarX==currX-1 && tarY==currY+2) || (tarX==currX-2 && tarY==currY+1) || (tarX==currX-2 && tarY==currY-1) || (tarX==currX-1 && tarY==currY-2))
			return true;
		else
			return false;
	}
	@Override
	/**
	 * This method performs the movement of a Knight piece from current point to targeting point.
	 * @param currX The x-coordinate of starting point in the movement.
	 * @param currY The y-coordinate of starting point in the movement.
	 * @param tarX The x-coordinate of targeting point in the movement.
	 * @param tarY The y-coordinate of targeting point in the movement.
	 * @return True if successfully move. Otherwise, return false.
	 */
	public boolean move(int currX, int currY, int tarX, int tarY) {
		Chess.enpassant_flag=false;
		if(tarX>=0 && tarX<=7 && tarY>=0 && tarY<=7 && JudgePos(currX,currY,tarX,tarY) && !(Ownside(Chess.board[currX][currY].pieceName,Chess.board[tarX][tarY].pieceName)))
		{
			jump(currX,currY,tarX,tarY);
			return true;
		}	
		return false;
	}

	/**
	 * This method is going to search all of the possible location where the Knight can reach.
	 * @return The ArrayList contains all of the possible locations. Null if no possible location.
	 */
	public ArrayList<Point> Searcher(){
		ArrayList<Point> arr=new ArrayList<Point>();
		if((this.x)+1 <=7 && (this.y)-2>=0 && (!(Ownside(Chess.board[this.x][this.y].pieceName, Chess.board[(this.x)+1][(this.y)-2].pieceName) || Chess.board[(this.x)+1][(this.y)-2].pieceName.equals("empty"))))
			arr.add(new Point((this.x)+1,(this.y)-2));
		if((this.x)+2 <=7 && (this.y)-1>=0 && (!(Ownside(Chess.board[this.x][this.y].pieceName, Chess.board[(this.x)+2][(this.y)-1].pieceName) || Chess.board[(this.x)+2][(this.y)-1].pieceName.equals("empty"))))
			arr.add(new Point((this.x)+2,(this.y)-1));
		if((this.x)+2 <=7 && (this.y)+1<=7 && (!(Ownside(Chess.board[this.x][this.y].pieceName, Chess.board[(this.x)+2][(this.y)+1].pieceName) || Chess.board[(this.x)+2][(this.y)+1].pieceName.equals("empty"))))
			arr.add(new Point((this.x)+2,(this.y)+1));	
		if((this.x)+1 <=7 && (this.y)+2<=7 && (!(Ownside(Chess.board[this.x][this.y].pieceName, Chess.board[(this.x)+1][(this.y)+2].pieceName) || Chess.board[(this.x)+1][(this.y)+2].pieceName.equals("empty"))))
			arr.add(new Point((this.x)+1,(this.y)+2));	
		if((this.x)-1 >=0 && (this.y)+2<=7 && (!(Ownside(Chess.board[this.x][this.y].pieceName, Chess.board[(this.x)-1][(this.y)+2].pieceName) || Chess.board[(this.x)-1][(this.y)+2].pieceName.equals("empty"))))
			arr.add(new Point((this.x)-1,(this.y)+2));	
		if((this.x)-2 >=0 && (this.y)+1<=7 && (!(Ownside(Chess.board[this.x][this.y].pieceName, Chess.board[(this.x)-2][(this.y)+1].pieceName) || Chess.board[(this.x)-2][(this.y)+1].pieceName.equals("empty"))))
			arr.add(new Point((this.x)-2,(this.y)+1));	
		if((this.x)-2 >=0 && (this.y)-1>=0 && (!(Ownside(Chess.board[this.x][this.y].pieceName, Chess.board[(this.x)-2][(this.y)-1].pieceName) || Chess.board[(this.x)-2][(this.y)-1].pieceName.equals("empty"))))
			arr.add(new Point((this.x)-2,(this.y)-1));
		if((this.x)-1 >=0 && (this.y)-2>=0 && (!(Ownside(Chess.board[this.x][this.y].pieceName, Chess.board[(this.x)-1][(this.y)-2].pieceName) || Chess.board[(this.x)-1][(this.y)-2].pieceName.equals("empty"))))
			arr.add(new Point((this.x)-1,(this.y)-2));
		return arr;
	}
}
