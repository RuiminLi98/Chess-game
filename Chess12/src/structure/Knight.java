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
