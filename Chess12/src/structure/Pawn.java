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
	public boolean Enpassant(String currName, int currX, int currY, int tarX, int tarY)
	{
		if(currName=="wp" && Chess.board[tarX-1][tarY].pieceName.equals("bp") && currX==tarX-1 && (currY==tarY+1 || currY==tarY-1) && currX==4 && Chess.board[tarX][tarY].pieceName.equals("empty"))
		{
			return true;
		}
		if(currName=="bp" && Chess.board[tarX+1][tarY].pieceName.equals("wp") && currX==tarX+1 && (currY==tarY+1 || currY==tarY-1) && currX==3 && Chess.board[tarX][tarY].pieceName.equals("empty"))
		{

			return true;
		}
		return false;
	}
	public void CheckTransPawnForW(int i, int x, int y)
	{
		if(i==7)
		{
			//System.out.println("This pawn can be promote, which you want (Knight/Rook/Queen/Bishop):");
			//Scanner sc=new Scanner(System.in);
			//String str=sc.next();
			String temp="";
			if(Chess.a.length==2)
				temp="";
			else if(Chess.a.length==3 && (Chess.a[2]=="draw?"))
				temp="";
			else
			{
				temp=Chess.a[2];
			}
			String celln1=temp;
			TransPawnForW(temp,celln1,x,y);
		}
	}
	public void TransPawnForW(String str,String celln, int x, int y)
	{
		if(str.equals("N"))
		{
			Chess.board[x][y]=new Knight(celln,"wN",true,x,y);
		}
		if(str.equals("R"))
		{
			Chess.board[x][y]=new Rook(celln,"wR",true,x,y);
		}
		if(str.equals("Q"))
		{
			Chess.board[x][y]=new Queen(celln,"wQ",true,x,y);
		}
		if(str.equals("B"))
		{
			Chess.board[x][y]=new Bishop(celln,"wB",true,x,y);
		}
		if(str.equals(""))
		{
			Chess.board[x][y]=new Queen(celln,"wQ",true,x,y);
		}
	}

	public void CheckTransPawnForB(int i, int x, int y)
	{
		if(i==0)
		{
			//System.out.println("This pawn can be promote, which you want (Knight/Rook/Queen/Bishop):");
			//Scanner sc=new Scanner(System.in);
			//String str=sc.next();
			String temp="";
			if(Chess.a.length==2)
				temp="";
			else if(Chess.a.length==3 && (Chess.a[2]=="draw?"))
				temp="";
			else
			{
				temp=Chess.a[2];
			}
			String celln1=temp;
			TransPawnForW(temp,celln1,x,y);
		}
	}
	public void TransPawnForB(String str,String celln, int x, int y)
	{
		if(str.equals("N"))
		{
			Chess.board[x][y]=new Knight(celln,"bN",true,x,y);
		}
		if(str.equals("R"))
		{
			Chess.board[x][y]=new Rook(celln,"bR",true,x,y);
		}
		if(str.equals("Q"))
		{
			Chess.board[x][y]=new Queen(celln,"bQ",true,x,y);
		}
		if(str.equals("B"))
		{
			Chess.board[x][y]=new Bishop(celln,"bB",true,x,y);
		}
		if(str.equals(""))
		{
			Chess.board[x][y]=new Queen(celln,"bQ",true,x,y);
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
		else if((currX==2 || currX==3 ||currX==4 || currX==5 || currX==6) && pieceName.contentEquals("wp"))
		{
			if(Enpassant("wp", currX, currY, tarX, tarY))
			{
				String temp3=Chess.board[tarX-1][tarY].cellName;
				Chess.board[tarX-1][tarY]=new Empty(temp3,"empty",false,tarX-1,tarY);
				jump(currX,currY,tarX,tarY);
				CheckTransPawnForW(tarX,tarX,tarY);
				return true;
			}
			if(tarX==currX+1 && tarY==currY && Chess.board[currX+1][currY].pieceName.equals("empty"))
			{
				jump(currX,currY,tarX,tarY);
				CheckTransPawnForW(tarX,tarX,tarY);
				return true;
			}
			else if(tarX==currX+1 && tarY==currY && !(Chess.board[currX+1][currY].pieceName.equals("empty")))
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
		else if((currX==1 || currX==2 || currX==3 ||currX==4 || currX==5) && pieceName.contentEquals("bp"))
		{
			if(Enpassant("bp",currX, currY, tarX, tarY))
			{
				String temp3=Chess.board[tarX+1][tarY].cellName;
				Chess.board[tarX+1][tarY]=new Empty(temp3,"empty",false,tarX+1,tarY);
				jump(currX,currY,tarX,tarY);
				CheckTransPawnForB(tarX, tarX, tarY);
				return true;
			}
			if(tarX==(currX-1) && tarY==currY && Chess.board[currX-1][currY].pieceName.equals("empty"))
			{
				jump(currX,currY,tarX,tarY);
				CheckTransPawnForB(tarX, tarX, tarY);
				return true;
			}
			else if(tarX==(currX-1) && tarY==currY && !(Chess.board[currX-1][currY].pieceName.equals("empty")))
				return false;
			else if(tarX==currX-1 && tarY == (currY-1) && !(Ownside(pieceName, Chess.board[currX-1][currY-1].pieceName)) && !(Chess.board[currX-1][currY-1].pieceName.contentEquals("empty"))&& tarY>=0)
			{
				jump(currX,currY,tarX,tarY);
				CheckTransPawnForB(tarX, tarX, tarY);
				return true;
			}
			else if(tarX==currX-1 && tarY == (currY+1) && !(Ownside(pieceName, Chess.board[currX-1][currY+1].pieceName)) && !(Chess.board[currX-1][currY+1].pieceName.contentEquals("empty")) && tarY<=7)
			{
				jump(currX,currY,tarX,tarY);
				CheckTransPawnForB(tarX, tarX, tarY);
				return true;
			}
		}

		return false;
	}
}
