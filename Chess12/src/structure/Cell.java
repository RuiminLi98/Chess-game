package structure;

import chess.Chess;

public abstract class Cell {
	public String cellName;
	public String pieceName;
	public boolean isAlive;
	public int x;
	public int y;
	public Cell(String cellName,String pieceName,boolean isAlive,int x,int y) {
		this.cellName=cellName;
		this.pieceName=pieceName;
		this.isAlive=isAlive;
		this.x=x;
		this.y=y;
	}
	public abstract boolean move(int currX,int currY,int tarX,int tarY);
	public void jump (int currX,int currY,int tarX,int tarY) {
		Cell temp=Chess.board[tarX][tarY];
		String temp2=Chess.board[currX][currY].cellName;
		Chess.board[tarX][tarY]=Chess.board[currX][currY];
		Chess.board[tarX][tarY].cellName=temp.cellName;
		Chess.board[tarX][tarY].x=temp.x;
		Chess.board[tarX][tarY].y=temp.y;
		Chess.board[currX][currY]=new Empty(temp2, "empty", false, currX, currY);
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj==null||!(obj instanceof Cell))return false;
		Cell other=(Cell)obj;
		return this.pieceName.equals(other.pieceName);
	}
}
