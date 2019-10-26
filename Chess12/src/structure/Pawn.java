package structure;

import java.util.*;

import chess.Chess;

public class Pawn extends Cell implements PawnPromotion{
 
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

    public void CheckTransPawnForW(int i, int x, int y)
    {
    	if(i==7)
    	{
    		System.out.println("This pawn can be promote, which you want (Knight/Rook/Queen/Bishop):");
    		Scanner sc=new Scanner(System.in);
    		String str=sc.next();
    		String celln1=Chess.board[x][y].cellName;
    		TransPawnForW(str,celln1,x,y);
    	}
    }
    public void TransPawnForW(String str,String celln, int x, int y)
    {
    	if(str.equals("Knight") || str.equals("wN"))
    	{
    		Chess.board[x][y]=new Knight(celln,"wN",true,x,y);
    	}
    	if(str.equals("Rook") || str.equals("wR"))
    	{
    		Chess.board[x][y]=new Rook(celln,"wR",true,x,y);
    	}
    	if(str.equals("Queen") || str.equals("wQ"))
    	{
    		Chess.board[x][y]=new Queen(celln,"wQ",true,x,y);
    	}
    	if(str.equals("Bishop") || str.equals("wB"))
    	{
    		Chess.board[x][y]=new Bishop(celln,"wB",true,x,y);
    	}
    }
    
    public void CheckTransPawnForB(int i, int x, int y)
    {
    	if(i==0)
    	{
    		System.out.println("This pawn can be promote, which you want (Knight/Rook/Queen/Bishop):");
    		Scanner sc=new Scanner(System.in);
    		String str=sc.next();
    		String celln1=Chess.board[x][y].cellName;
    		TransPawnForW(str,celln1,x,y);
    	}
    }
    public void TransPawnForB(String str,String celln, int x, int y)
    {
    	if(str.equals("Knight") || str.equals("bN"))
    	{
    		Chess.board[x][y]=new Knight(celln,"bN",true,x,y);
    	}
    	if(str.equals("Rook") || str.equals("bR"))
    	{
    		Chess.board[x][y]=new Rook(celln,"bR",true,x,y);
    	}
    	if(str.equals("Queen") || str.equals("bQ"))
    	{
    		Chess.board[x][y]=new Queen(celln,"bQ",true,x,y);
    	}
    	if(str.equals("Bishop") || str.equals("bB"))
    	{
    		Chess.board[x][y]=new Bishop(celln,"bB",true,x,y);
    	}
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
			else if(tarX==currX+1 && tarY == (currY-1) && !(Ownside(pieceName, Chess.board[currX+1][currY-1].pieceName)) && !(Chess.board[currX+1][currY-1].pieceName.contentEquals("empty")) && tarY>=0)
			{
				jump(currX,currY,tarX,tarY);
				return true;
			}
			else if(tarX==currX+1 && tarY == (currY+1) && !(Ownside(pieceName, Chess.board[currX+1][currY+1].pieceName)) && !(Chess.board[currX+1][currY+1].pieceName.contentEquals("empty"))&& tarY<=7)
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
			else if(tarX==currX-1 && tarY == (currY-1) && !(Ownside(pieceName, Chess.board[currX-1][currY-1].pieceName)) && !(Chess.board[currX-1][currY-1].pieceName.contentEquals("empty")) && tarY>=0)
			{
				jump(currX,currY,tarX,tarY);
				return true;
			}
			else if(tarX==currX-1 && tarY == (currY+1) && !(Ownside(pieceName, Chess.board[currX-1][currY+1].pieceName)) && !(Chess.board[currX-1][currY+1].pieceName.contentEquals("empty")) && tarY<=7)
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
					CheckTransPawnForW(tarX,tarX,tarY);
					return true;
				}
				else if(tarX==currX+1 && tarY==currX && !(Chess.board[currX+1][currY].pieceName.equals("empty")))
					return false;
				else if(tarX==currX+1 && tarY == (currY-1) && !(Ownside(pieceName, Chess.board[currX+1][currY-1].pieceName)) && !(Chess.board[currX+1][currY-1].pieceName.contentEquals("empty"))&& tarY>=0)
				{
					jump(currX,currY,tarX,tarY);
					CheckTransPawnForW(tarX,tarX,tarY);
					return true;
				}
				else if(tarX==currX+1 && tarY == (currY+1) && !(Ownside(pieceName, Chess.board[currX+1][currY+1].pieceName)) && !(Chess.board[currX+1][currY+1].pieceName.contentEquals("empty"))&& tarY<=7)
				{
					jump(currX,currY,tarX,tarY);
					CheckTransPawnForW(tarX,tarX,tarY);
					return true;
				}
			}
			if(pieceName.equals("bp"))
			{
				if(tarX==(currX-1) && tarY==currY && Chess.board[currX-1][currY].pieceName.equals("empty"))
				{
					CheckTransPawnForB(tarX, tarX, tarY);
					jump(currX,currY,tarX,tarY);
					return true;
				}
				else if(tarX==(currX-1) && tarY==currY && !(Chess.board[currX-1][currY].pieceName.equals("empty")))
					return false;
				else if(tarX==currX-1 && tarY == (currY-1) && !(Ownside(pieceName, Chess.board[currX-1][currY-1].pieceName)) && !(Chess.board[currX-1][currY-1].pieceName.contentEquals("empty"))&& tarY>=0)
				{
					CheckTransPawnForB(tarX, tarX, tarY);
					jump(currX,currY,tarX,tarY);
					return true;
				}
				else if(tarX==currX-1 && tarY == (currY+1) && !(Ownside(pieceName, Chess.board[currX-1][currY+1].pieceName)) && !(Chess.board[currX-1][currY+1].pieceName.contentEquals("empty")) && tarY<=7)
				{
					CheckTransPawnForB(tarX, tarX, tarY);
					jump(currX,currY,tarX,tarY);
					return true;
				}
			}
		}
		
			return false;
    }
}
