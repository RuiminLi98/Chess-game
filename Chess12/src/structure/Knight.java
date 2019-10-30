package structure;

import chess.Chess;

public class Knight extends Cell{

	

	public Knight(String cellName, String pieceName, boolean isAlive, int x, int y) {
		super(cellName, pieceName, isAlive, x, y);
		// TODO Auto-generated constructor stub
	}
	
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
	public boolean JudgePos(int currX, int currY, int tarX, int tarY)
	{
		if((tarX==currX+1 && tarY==currY-2) || (tarX==currX+2 && tarY==currY-1) || (tarX==currX+2 && tarY==currY+1) || (tarX==currX+1 && tarY==currY+2) || (tarX==currX-1 && tarY==currY+2) || (tarX==currX-2 && tarY==currY+1) || (tarX==currX-2 && tarY==currY-1) || (tarX==currX-1 && tarY==currY-2))
			return true;
		else
			return false;
	}
	@Override
	public boolean move(int currX, int currY, int tarX, int tarY) {
			Chess.enpassant_flag=false;
		if(tarX>=0 && tarX<=7 && tarY>=0 && tarY<=7 && JudgePos(currX,currY,tarX,tarY) && !(Ownside(Chess.board[currX][currY].pieceName,Chess.board[tarX][tarY].pieceName)))
		{
			jump(currX,currY,tarX,tarY);
			return true;
		}	
		return false;
	}

}
