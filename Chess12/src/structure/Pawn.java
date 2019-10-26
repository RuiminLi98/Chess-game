package structure;

import java.util.*;

import chess.Chess;

public class Pawn extends Cell {
 
	public boolean Ownside(String name1,String name2)
	{
		switch (name1) {
		case "wp": if (name2 == "wR" || name2== "wN" || name2== "wB" || name2== "wQ" || name2 == "wK" || name2 == "wp")
						return true;
		            else
		            	return false;
		default : if (name2 == "bR" || name2== "bN" || name2== "bB" || name2== "bQ" || name2 == "bK" || name2 == "bp")
						return true;
					else
						return false;
		}
	}
    public Pawn(String cellName, String pieceName, boolean isAlive, int x, int y) {
		super(cellName, pieceName, isAlive, x, y);
		
	}

	public boolean move(int currX, int currY, int tarX, int tarY) {
		if(currX==1 && pieceName.equals("wp"))
		{
			if(tarX==currX+1 && tarY==currY && Chess.board[currX+1][currY].pieceName.equals("empty"))
			{
				jump(currX,currY,tarX,tarY);
				return true;
			}
			else if(tarX==currX+1 && tarY==currY && !(Chess.board[currX+1][currY].pieceName.equals("empty")))
				return false;
			else if(tarX==currX+2 && tarY==currY && Chess.board[currX+2][currY].pieceName.equals("empty"))
			{
				jump(currX,currY,tarX,tarY);
				return true;
			}
			else if(tarX==currX+2 && tarY==currY && !(Chess.board[currX+2][currY].pieceName.equals("empty")))
				return false;
			else if(tarX==currX+1 && tarY == (currY-1) && !(Ownside(pieceName, Chess.board[currX+1][currY-1].pieceName)) && tarY>=0)
			{
				jump(currX,currY,tarX,tarY);
				return true;
			}
			else if(tarX==currX+1 && tarY == (currY+1) && !(Ownside(pieceName, Chess.board[currX+1][currY+1].pieceName)) && tarY<=7)
			{
				jump(currX,currY,tarX,tarY);
				return true;
			}
			
		}
		else if(currX==6 && pieceName.equals("bp"))
		{
			if(tarX==(currX-1) && tarY==currY && Chess.board[currX-1][currY].pieceName.equals("empty"))
			{
				jump(currX,currY,tarX,tarY);
				return true;
			}
			else if(tarX==(currX-1) && tarY==currY && !(Chess.board[currX-1][currY].pieceName.equals("empty")))
				return false;
			else if(tarX==(currX-2) && tarY==currY && Chess.board[currX-2][currY].pieceName.equals("empty"))
			{
				jump(currX,currY,tarX,tarY);
				return true;
			}
			else if(tarX==currX-2 && tarY==currY && !(Chess.board[currX-2][currY].pieceName.equals("empty")))
				return false;
			else if(tarX==currX-1 && tarY == (currY-1) && !(Ownside(pieceName, Chess.board[currX-1][currY-1].pieceName)) && tarY>=0)
			{
				jump(currX,currY,tarX,tarY);
				return true;
			}
			else if(tarX==currX-1 && tarY == (currY+1) && !(Ownside(pieceName, Chess.board[currX-1][currY+1].pieceName)) && tarY<=7)
			{
				jump(currX,currY,tarX,tarY);
				return true;
			}
		}
		else if(currX!=0 && currX!=7)
		{
			if(pieceName.equals("wp"))
			{
				if(tarX==currX+1 && tarY==currY && Chess.board[currX+1][currY].pieceName.equals("empty"))
				{
					jump(currX,currY,tarX,tarY);
					return true;
				}
				else if(tarX==currX+1 && tarY==currX && !(Chess.board[currX+1][currY].pieceName.equals("empty")))
					return false;
				else if(tarX==currX+1 && tarY == (currY-1) && !(Ownside(pieceName, Chess.board[currX+1][currY-1].pieceName)) && tarY>=0)
				{
					jump(currX,currY,tarX,tarY);
					return true;
				}
				else if(tarX==currX+1 && tarY == (currY+1) && !(Ownside(pieceName, Chess.board[currX+1][currY+1].pieceName)) && tarY<=0)
				{
					jump(currX,currY,tarX,tarY);
					return true;
				}
			}
			if(pieceName.equals("bp"))
			{
				if(tarX==(currX-1) && tarY==currY && Chess.board[currX-1][currY].pieceName.equals("empty"))
				{
					jump(currX,currY,tarX,tarY);
					return true;
				}
				else if(tarX==(currX-1) && tarY==currY && !(Chess.board[currX-1][currY].pieceName.equals("empty")))
					return false;
				else if(tarX==currX-1 && tarY == (currY-1) && !(Ownside(pieceName, Chess.board[currX-1][currY-1].pieceName)) && tarY>=0)
				{
					jump(currX,currY,tarX,tarY);
					return true;
				}
				else if(tarX==currX-1 && tarY == (currY+1) && !(Ownside(pieceName, Chess.board[currX-1][currY+1].pieceName)) && tarY<=7)
				{
					jump(currX,currY,tarX,tarY);
					return true;
				}
			}
		}
			return false;
    }
}

