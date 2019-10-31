package structure;

import java.util.ArrayList;

import chess.Chess;

/** 
 * @author Junjie He
 * @author Ruimin Li
 */

/**
 * The is a class represent a piece's position in the chessboard
 * the two elements x and y respectively represent the horizontal and vertical position
 * Additionally, there are some helper methods to do some calculation on Point.
 */
public class Point {
	public int x,y;

	/**
	 * Initialize the Point object by two parameters 
	 * @param x represent the horizontal coordinate of the piece 
	 * @param y represent the vertical coordinate of the piece
	 */
	public Point(int x,int y) {
		this.x=x;this.y=y;
	}

	/**
	 * This method is going to return the location based on the piece's name.
	 * Usually, it is used to search the location of a king, because there is only one king for one side.
	 * @param pieceName The Piece's name is going to be searched.
	 * @return The Point location. Null, if not found.
	 */
	public static Point getLocation(String pieceName) {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(Chess.board[i][j].pieceName.equals(pieceName)&&Chess.board[i][j].isAlive) {
					return new Point(i,j);
				}
			}
		}
		return null;
	}
	
	/**
	 * Decide whether two Cells on the board are enemies. 
	 * @param p1 The first cell
	 * @param p2 The second Cell
	 * @return True if they are enemies. Otherwise, return false.
	 */
	public static boolean isEnemy(Cell p1,Cell p2) {
		if(p1.pieceName.charAt(0)==p2.pieceName.charAt(0))return false;
		return true;
	}
	
	/**
	 * Decide whether two Points on the board are enemies. 
	 * @param p1 The first Point
	 * @param p2 The second Point
	 * @return True if they are enemies. Otherwise, return false.
	 */
	public static boolean isEnemy(Point p1,Point p2) {
		if(Chess.board[p1.x][p1.y].pieceName.charAt(0)==Chess.board[p1.x][p1.y].pieceName.charAt(0))return false;
		return true;
	}
	
	
	/**
	 * Decide whether two Points are equal.
	 * @param obj Another object
	 * @return True if they are same.
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj==null||!(obj instanceof Point))return false;
		Point other=(Point)obj;
		if(other.x==this.x&&other.y==this.y)return true;
		return false;
	}
	
	/**
	 * This method is going to return which enemy piece is checking on my King.
	 * @param kLoc My king's location
	 * @return The cell which is checking my king.
	 */
	public static Cell findCheck(Point kLoc) {
		ArrayList<Cell>EnemyListR=new ArrayList<Cell>();
		//ckeck up row
		for(int i=kLoc.x+1;i<8;i++) {
			Cell c=Chess.board[i][kLoc.y];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc.x][kLoc.y]))EnemyListR.add(c);
				break;
			}
		}
		//ckeck down row
		for(int i=kLoc.x-1;i>=0;i--) {
			Cell c=Chess.board[i][kLoc.y];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc.x][kLoc.y]))EnemyListR.add(c);
				break;
			}
		}
		//ckeck right
		for(int i=kLoc.y+1;i<8;i++) {
			Cell c=Chess.board[kLoc.x][i];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc.x][kLoc.y]))EnemyListR.add(c);
				break;
			}
		}
		//ckeck left
		for(int i=kLoc.y-1;i>=0;i--) {
			Cell c=Chess.board[kLoc.x][i];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc.x][kLoc.y]))EnemyListR.add(c);
				break;
			}
		}
		for(Cell c:EnemyListR) {
			if(c.pieceName.charAt(1)=='R'||c.pieceName.charAt(1)=='Q')return Chess.board[c.x][c.y];
		}
		ArrayList<Cell>EnemyListB=new ArrayList<Cell>();

		//check right up dig
		for(int i=kLoc.x+1,j=kLoc.y+1;i<8&&j<8;i++,j++) {
			Cell c=Chess.board[i][j];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc.x][kLoc.y]))EnemyListB.add(c);
				break;
			}
		}

		//check left down dig
		for(int i=kLoc.x-1,j=kLoc.y-1;i>=0&&j>=0;i--,j--) {
			Cell c=Chess.board[i][j];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc.x][kLoc.y]))EnemyListB.add(c);
				break;
			}
		}

		//check left up dig
		for(int i=kLoc.x+1,j=kLoc.y-1;i<8&&j>=0;i++,j--) {
			Cell c=Chess.board[i][j];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc.x][kLoc.y]))EnemyListB.add(c);
				break;
			}
		}
		//check right down dig
		for(int i=kLoc.x-1,j=kLoc.y+1;i>=0&&j<8;i--,j++) {
			Cell c=Chess.board[i][j];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc.x][kLoc.y]))EnemyListB.add(c);
				break;
			}
		}
		for(Cell c:EnemyListB) {
			if(c.pieceName.charAt(1)=='B'||c.pieceName.charAt(1)=='Q')return Chess.board[c.x][c.y];
		}

		if(inScale(kLoc.x-1, kLoc.y-1)) {
			Cell c=Chess.board[kLoc.x-1][kLoc.y-1];
			if(c.isAlive&&c.pieceName.charAt(1)=='K') {
				return Chess.board[c.x][c.y];
			}
		}

		if(inScale(kLoc.x-1, kLoc.y)) {
			Cell c=Chess.board[kLoc.x-1][kLoc.y];
			if(c.isAlive&&c.pieceName.charAt(1)=='K') {
				return Chess.board[c.x][c.y];
			}
		}

		if(inScale(kLoc.x-1, kLoc.y+1)) {
			Cell c=Chess.board[kLoc.x-1][kLoc.y+1];
			if(c.isAlive&&c.pieceName.charAt(1)=='K') {
				return Chess.board[c.x][c.y];
			}
		}
		if(inScale(kLoc.x, kLoc.y-1)) {
			Cell c=Chess.board[kLoc.x][kLoc.y-1];
			if(c.isAlive&&c.pieceName.charAt(1)=='K') {
				return Chess.board[c.x][c.y];
			}
		}
		if(inScale(kLoc.x, kLoc.y+1)) {
			Cell c=Chess.board[kLoc.x][kLoc.y+1];
			if(c.isAlive&&c.pieceName.charAt(1)=='K') {
				return Chess.board[c.x][c.y];
			}
		}
		if(inScale(kLoc.x+1, kLoc.y-1)) {
			Cell c=Chess.board[kLoc.x+1][kLoc.y-1];
			if(c.isAlive&&c.pieceName.charAt(1)=='K') {
				return Chess.board[c.x][c.y];
			}
		}
		if(inScale(kLoc.x+1, kLoc.y)) {
			Cell c=Chess.board[kLoc.x+1][kLoc.y];
			if(c.isAlive&&c.pieceName.charAt(1)=='K') {
				return Chess.board[c.x][c.y];
			}
		}
		if(inScale(kLoc.x+1, kLoc.y+1)) {
			Cell c=Chess.board[kLoc.x+1][kLoc.y+1];
			if(c.isAlive&&c.pieceName.charAt(1)=='K') {
				return Chess.board[c.x][c.y];
			}
		}
		Cell p=PointThreatFromPN(kLoc);
		if(p!=null)return p;

		p=PointTreatFromKN(kLoc);
		if(p!=null)return p;

		return null;
	}

	/**
	 * This function help us to know if the Point kLoc be threatened by enemy pawn
	 * @param kLoc means the current piece, kLoc have its coordinates 
	 * @return If kLoc do threatened by enemy pawn,we return the coordinates of the enemy coordinate
	 */
	public static Cell PointThreatFromPN(Point kLoc)
	{
		if((kLoc.x==0 || kLoc.x==1) && Chess.board[kLoc.x][kLoc.y].pieceName.charAt(0)=='b')
		{
			return null;
		}
		if((kLoc.x==2 || kLoc.x==3 || kLoc.x==4 || kLoc.x==5 || kLoc.x==6 || kLoc.x==7) && Chess.board[kLoc.x][kLoc.y].pieceName.charAt(0)=='b')
		{
			if(kLoc.x-1>=0 && kLoc.y-1>=0)
			{
				if(Chess.board[(kLoc.x)-1][kLoc.y-1].pieceName.equals("wp") )
					return Chess.board[(kLoc.x)-1][kLoc.y-1];
			}
			if(kLoc.x-1>=0 && kLoc.y+1<=7)
			{
				if(Chess.board[(kLoc.x)-1][kLoc.y+1].pieceName.equals("wp"))
					return Chess.board[(kLoc.x)-1][kLoc.y+1];
			}
		}
		if((kLoc.x==6 || kLoc.x==7) && Chess.board[kLoc.x][kLoc.y].pieceName.charAt(0)=='w')
		{
			return null;
		}
		if((kLoc.x==0 || kLoc.x==1 || kLoc.x==2 || kLoc.x==3 || kLoc.x==4 || kLoc.x==5) && Chess.board[kLoc.x][kLoc.y].pieceName.charAt(0)=='w')
		{
			if(kLoc.x+1<=7 && kLoc.y-1>=0)
			{
				if(Chess.board[(kLoc.x)+1][kLoc.y-1].pieceName.equals("bp") )
					return Chess.board[(kLoc.x)+1][kLoc.y-1];
			}
			if(kLoc.x+1<=7 && kLoc.y+1<=7)
			{
				if(Chess.board[(kLoc.x)+1][kLoc.y+1].pieceName.equals("bp"))
					return Chess.board[(kLoc.x)+1][kLoc.y+1];
			}
		}
		return null;
	}

	/**
	 * This function help us to know if the Point kLoc be threatened by enemy Knight
	 * @param kLoc means the current piece, kLoc have its coordinates 
	 * @return If kLoc do threatened by enemy Knight,we return the coordinates of the enemy coordinate
	 */
	public static Cell PointTreatFromKN(Point kLoc)
	{
		if(Chess.board[kLoc.x][kLoc.y].pieceName.charAt(0)=='b')
		{
			if((kLoc.x)+1 <=7 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)-2].pieceName.equals("wN")) 
					return Chess.board[(kLoc.x)+1][(kLoc.y)-2];
			if((kLoc.x)+2 <=7 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)-1].pieceName.equals("wN"))
					return Chess.board[(kLoc.x)+2][(kLoc.y)-1];
			if((kLoc.x)+2 <=7 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)+1].pieceName.equals("wN"))
					return Chess.board[(kLoc.x)+2][(kLoc.y)+1];
			if((kLoc.x)+1 <=7 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)+2].pieceName.equals("wN"))
					return Chess.board[(kLoc.x)+1][(kLoc.y)+2];
			if((kLoc.x)-1 >=0 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)+2].pieceName.equals("wN"))
					return Chess.board[(kLoc.x)-1][(kLoc.y)+2];
			if((kLoc.x)-2 >=0 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)+1].pieceName.equals("wN"))
					return Chess.board[(kLoc.x)-2][(kLoc.y)+1];
			if((kLoc.x)-2 >=0 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)-1].pieceName.equals("wN"))
					return Chess.board[(kLoc.x)-2][(kLoc.y)-1];
			if((kLoc.x)-1 >=0 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)-2].pieceName.equals("wN"))
					return Chess.board[(kLoc.x)-1][(kLoc.y)-2];
		}
		if(Chess.board[kLoc.x][kLoc.y].pieceName.charAt(0)=='w')
		{
			if((kLoc.x)+1 <=7 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)-2].pieceName.equals("bN")) 
					return Chess.board[(kLoc.x)+1][(kLoc.y)-2];
			if((kLoc.x)+2 <=7 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)-1].pieceName.equals("bN"))
					return Chess.board[(kLoc.x)+2][(kLoc.y)-1];
			if((kLoc.x)+2 <=7 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)+1].pieceName.equals("bN"))
					return Chess.board[(kLoc.x)+2][(kLoc.y)+1];
			if((kLoc.x)+1 <=7 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)+2].pieceName.equals("bN"))
					return Chess.board[(kLoc.x)+1][(kLoc.y)+2];
			if((kLoc.x)-1 >=0 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)+2].pieceName.equals("bN"))
					return Chess.board[(kLoc.x)-1][(kLoc.y)+2];
			if((kLoc.x)-2 >=0 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)+1].pieceName.equals("bN"))
					return Chess.board[(kLoc.x)-2][(kLoc.y)+1];
			if((kLoc.x)-2 >=0 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)-1].pieceName.equals("bN"))
					return Chess.board[(kLoc.x)-2][(kLoc.y)-1];
			if((kLoc.x)-1 >=0 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)-2].pieceName.equals("bN"))
					return Chess.board[(kLoc.x)-1][(kLoc.y)-2];
		}
		return null;
	}

	/**
	 * This method is going to figure out whether a piece from my side which can reach a specific location,
	 * exists.
	 * @param kLoc The targeting location
	 * @param kLoc2 The location of my king
	 * @return True if there is a piece in my side can reach kLoc. Otherwise, return false.
	 */
	public static boolean checkFromSelf(Point kLoc, Point kLoc2) {
		ArrayList<Cell>EnemyListR=new ArrayList<Cell>();
		//ckeck up row
		for(int i=kLoc.x+1;i<8;i++) {
			Cell c=Chess.board[i][kLoc.y];
			if(c.isAlive) {
				if(!isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListR.add(c);
				break;
			}
		}
		//ckeck down row
		for(int i=kLoc.x-1;i>=0;i--) {
			Cell c=Chess.board[i][kLoc.y];
			if(c.isAlive) {
				if(!isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListR.add(c);
				break;
			}
		}
		//ckeck right
		for(int i=kLoc.y+1;i<8;i++) {
			Cell c=Chess.board[kLoc.x][i];
			if(c.isAlive) {
				if(!isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListR.add(c);
				break;
			}
		}
		//ckeck left
		for(int i=kLoc.y-1;i>=0;i--) {
			Cell c=Chess.board[kLoc.x][i];
			if(c.isAlive) {
				if(!isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListR.add(c);
				break;
			}
		}
		for(Cell c:EnemyListR) {
			if(c.pieceName.charAt(1)=='R'||c.pieceName.charAt(1)=='Q')return true;
		}
		ArrayList<Cell>EnemyListB=new ArrayList<Cell>();

		//check right up dig
		for(int i=kLoc.x+1,j=kLoc.y+1;i<8&&j<8;i++,j++) {
			Cell c=Chess.board[i][j];
			if(c.isAlive) {
				if(!isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListB.add(c);
				break;
			}
		}

		//check left down dig
		for(int i=kLoc.x-1,j=kLoc.y-1;i>=0&&j>=0;i--,j--) {
			Cell c=Chess.board[i][j];
			if(c.isAlive) {
				if(!isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListB.add(c);
				break;
			}
		}

		//check left up dig
		for(int i=kLoc.x+1,j=kLoc.y-1;i<8&&j>=0;i++,j--) {
			Cell c=Chess.board[i][j];
			if(c.isAlive) {
				if(!isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListB.add(c);
				break;
			}
		}
		//check right down dig
		for(int i=kLoc.x-1,j=kLoc.y+1;i>=0&&j<8;i--,j++) {
			Cell c=Chess.board[i][j];
			if(c.isAlive) {
				if(!isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListB.add(c);
				break;
			}
		}
		for(Cell c:EnemyListB) {
			if(c.pieceName.charAt(1)=='B'||c.pieceName.charAt(1)=='Q')return true;
		}


		if(threatFromPorNSelf(kLoc,kLoc2)) {
			return true;
		}
		return false;
	}

	/**
	 * This function can help us know if the piece at kLoc can be threatened by own Pawn/Knight
	 * @param kLoc the current pieces coordinates
	 * @param kLoc2 our King's real location
	 * @return If kLoc do threaten by own pawn or Knight, then true; otherwise, false
	 */
	public static boolean threatFromPorNSelf(Point kLoc, Point kLoc2) {
		if((kLoc.x==0 || kLoc.x==1) && Chess.board[kLoc2.x][kLoc2.y].pieceName.charAt(0)=='w')
		{
			if((kLoc.x)+1 <=7 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)-2].pieceName.equals("wN")) 
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)-1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)+1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)+1 <=7 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)+2].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)+2].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)+1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)-1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)-2].pieceName.equals("wN"))
					return true;
		}
		if((kLoc.x==2 || kLoc.x==3 || kLoc.x==4 || kLoc.x==5 || kLoc.x==6 || kLoc.x==7) && Chess.board[kLoc2.x][kLoc2.y].pieceName.charAt(0)=='w')
		{
			if(kLoc.x-1>=0 && kLoc.y-1>=0)
			{
				if(Chess.board[(kLoc.x)-1][kLoc.y-1].pieceName.equals("wp") )
					return true;
			}
			if(kLoc.x-1>=0 && kLoc.y+1<=7)
			{
				if(Chess.board[(kLoc.x)-1][kLoc.y+1].pieceName.equals("wp"))
					return true;
			}
			if((kLoc.x)+1 <=7 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)-2].pieceName.equals("wN")) 
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)-1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)+1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)+1 <=7 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)+2].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)+2].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)+1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)-1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)-2].pieceName.equals("wN"))
					return true;
		}



		if((kLoc.x==6 || kLoc.x==7) && Chess.board[kLoc2.x][kLoc2.y].pieceName.charAt(0)=='b')
		{
			if((kLoc.x)+1 <=7 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)-2].pieceName.equals("bN")) 
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)-1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)+1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)+1 <=7 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)+2].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)+2].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)+1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)-1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)-2].pieceName.equals("bN"))
					return true;
		}
		if((kLoc.x==0 || kLoc.x==1 || kLoc.x==2 || kLoc.x==3 || kLoc.x==4 || kLoc.x==5) && Chess.board[kLoc2.x][kLoc2.y].pieceName.charAt(0)=='b')
		{
			if(kLoc.x+1<=7 && kLoc.y-1>=0)
			{
				if(Chess.board[(kLoc.x)+1][kLoc.y-1].pieceName.equals("bp") )
					return true;
			}
			if(kLoc.x+1<=7 && kLoc.y+1<=7)
			{
				if(Chess.board[(kLoc.x)+1][kLoc.y+1].pieceName.equals("bp"))
					return true;
			}
			if((kLoc.x)+1 <=7 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)-2].pieceName.equals("bN")) 
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)-1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)+1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)+1 <=7 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)+2].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)+2].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)+1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)-1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)-2].pieceName.equals("bN"))
					return true;
		}
		return false;
	}

	
	/**
	 * This method is used to decide whether a piece is under attack.
	 * @param kLoc The location of the piece
	 * @param kLoc2 The location of the king standing on the same side as kLoc
	 * @return return true if the kLoc is under attack. False, if kLoc is safe.
	 */
	public static boolean check(Point kLoc,Point kLoc2) {
		ArrayList<Cell>EnemyListR=new ArrayList<Cell>();
		//ckeck up row
		for(int i=kLoc.x+1;i<8;i++) {
			Cell c=Chess.board[i][kLoc.y];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListR.add(c);
				break;
			}
		}
		//ckeck down row
		for(int i=kLoc.x-1;i>=0;i--) {
			Cell c=Chess.board[i][kLoc.y];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListR.add(c);
				break;
			}
		}
		//ckeck right
		for(int i=kLoc.y+1;i<8;i++) {
			Cell c=Chess.board[kLoc.x][i];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListR.add(c);
				break;
			}
		}
		//ckeck left
		for(int i=kLoc.y-1;i>=0;i--) {
			Cell c=Chess.board[kLoc.x][i];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListR.add(c);
				break;
			}
		}
		for(Cell c:EnemyListR) {
			if(c.pieceName.charAt(1)=='R'||c.pieceName.charAt(1)=='Q')return true;
		}
		ArrayList<Cell>EnemyListB=new ArrayList<Cell>();

		//check right up dig
		for(int i=kLoc.x+1,j=kLoc.y+1;i<8&&j<8;i++,j++) {
			Cell c=Chess.board[i][j];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListB.add(c);
				break;
			}
		}

		//check left down dig
		for(int i=kLoc.x-1,j=kLoc.y-1;i>=0&&j>=0;i--,j--) {
			Cell c=Chess.board[i][j];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListB.add(c);
				break;
			}
		}

		//check left up dig
		for(int i=kLoc.x+1,j=kLoc.y-1;i<8&&j>=0;i++,j--) {
			Cell c=Chess.board[i][j];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListB.add(c);
				break;
			}
		}
		//check right down dig
		for(int i=kLoc.x-1,j=kLoc.y+1;i>=0&&j<8;i--,j++) {
			Cell c=Chess.board[i][j];
			if(c.isAlive) {
				if(isEnemy(c, Chess.board[kLoc2.x][kLoc2.y]))EnemyListB.add(c);
				break;
			}
		}
		for(Cell c:EnemyListB) {
			if(c.pieceName.charAt(1)=='B'||c.pieceName.charAt(1)=='Q')return true;
		}

		if(inScale(kLoc.x-1, kLoc.y-1)) {
			Cell c=Chess.board[kLoc.x-1][kLoc.y-1];
			if(c.isAlive&&c.pieceName.charAt(1)=='K'&&isEnemy(c, Chess.board[kLoc2.x][kLoc2.y])) {
				return true;
			}
		}

		if(inScale(kLoc.x-1, kLoc.y)) {
			Cell c=Chess.board[kLoc.x-1][kLoc.y];
			if(c.isAlive&&c.pieceName.charAt(1)=='K'&&isEnemy(c, Chess.board[kLoc2.x][kLoc2.y])) {
				return true;
			}
		}

		if(inScale(kLoc.x-1, kLoc.y+1)) {
			Cell c=Chess.board[kLoc.x-1][kLoc.y+1];
			if(c.isAlive&&c.pieceName.charAt(1)=='K'&&isEnemy(c, Chess.board[kLoc2.x][kLoc2.y])) {
				return true;
			}
		}
		if(inScale(kLoc.x, kLoc.y-1)) {
			Cell c=Chess.board[kLoc.x][kLoc.y-1];
			if(c.isAlive&&c.pieceName.charAt(1)=='K'&&isEnemy(c, Chess.board[kLoc2.x][kLoc2.y])) {
				return true;
			}
		}
		if(inScale(kLoc.x, kLoc.y+1)) {
			Cell c=Chess.board[kLoc.x][kLoc.y+1];
			if(c.isAlive&&c.pieceName.charAt(1)=='K'&&isEnemy(c, Chess.board[kLoc2.x][kLoc2.y])) {
				return true;
			}
		}
		if(inScale(kLoc.x+1, kLoc.y-1)) {
			Cell c=Chess.board[kLoc.x+1][kLoc.y-1];
			if(c.isAlive&&c.pieceName.charAt(1)=='K'&&isEnemy(c, Chess.board[kLoc2.x][kLoc2.y])) {
				return true;
			}
		}
		if(inScale(kLoc.x+1, kLoc.y)) {
			Cell c=Chess.board[kLoc.x+1][kLoc.y];
			if(c.isAlive&&c.pieceName.charAt(1)=='K'&&isEnemy(c, Chess.board[kLoc2.x][kLoc2.y])) {
				return true;
			}
		}
		if(inScale(kLoc.x+1, kLoc.y+1)) {
			Cell c=Chess.board[kLoc.x+1][kLoc.y+1];
			if(c.isAlive&&c.pieceName.charAt(1)=='K'&&isEnemy(c, Chess.board[kLoc2.x][kLoc2.y])) {
				return true;
			}
		}
		//		if(Chess.getCell(kLoc2).pieceName.charAt(0)=='w') {
		//			kLoc2=getLocation("bK");
		//		}else {
		//			kLoc2=getLocation("wK");
		//		}
		if(threatFromPorN(kLoc,kLoc2)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check whether a location is in the scale of board.
	 * @param x The x-coordinate of a point
	 * @param y The y-coordinate of a point
	 * @return Ture is the point is in the scale
	 */
	public static boolean inScale(int x, int y) {
		if(x<8&&x>=0&&y>=0&&y<8)return true;
		return false;
	}
	
	/**
	 * Check whether kLoc is under attack from a Knight or a Pawn
	 * @param kLoc The targeting point 
	 * @param kLoc2 The king's location in the kLoc's side
	 * @return True if the kLoc is under attack from a Knight or a Pawn. Otherwise, return false.
	 */
	public static boolean threatFromPorN(Point kLoc, Point kLoc2) {		
		if((kLoc.x==0 || kLoc.x==1) && Chess.board[kLoc2.x][kLoc2.y].pieceName.charAt(0)=='b')
		{
			if((kLoc.x)+1 <=7 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)-2].pieceName.equals("wN")) 
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)-1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)+1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)+1 <=7 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)+2].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)+2].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)+1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)-1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)-2].pieceName.equals("wN"))
					return true;
		}
		if((kLoc.x==2 || kLoc.x==3 || kLoc.x==4 || kLoc.x==5 || kLoc.x==6 || kLoc.x==7) && Chess.board[kLoc2.x][kLoc2.y].pieceName.charAt(0)=='b')
		{
			if(kLoc.x-1>=0 && kLoc.y-1>=0)
			{
				if(Chess.board[(kLoc.x)-1][kLoc.y-1].pieceName.equals("wp") )
					return true;
			}
			if(kLoc.x-1>=0 && kLoc.y+1<=7)
			{
				if(Chess.board[(kLoc.x)-1][kLoc.y+1].pieceName.equals("wp"))
					return true;
			}
			if((kLoc.x)+1 <=7 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)-2].pieceName.equals("wN")) 
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)-1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)+1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)+1 <=7 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)+2].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)+2].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)+1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)-1].pieceName.equals("wN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)-2].pieceName.equals("wN"))
					return true;
		}



		if((kLoc.x==6 || kLoc.x==7) && Chess.board[kLoc2.x][kLoc2.y].pieceName.charAt(0)=='w')
		{
			if((kLoc.x)+1 <=7 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)-2].pieceName.equals("bN")) 
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)-1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)+1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)+1 <=7 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)+2].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)+2].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)+1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)-1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)-2].pieceName.equals("bN"))
					return true;
		}
		if((kLoc.x==0 || kLoc.x==1 || kLoc.x==2 || kLoc.x==3 || kLoc.x==4 || kLoc.x==5) && Chess.board[kLoc2.x][kLoc2.y].pieceName.charAt(0)=='w')
		{
			if(kLoc.x+1<=7 && kLoc.y-1>=0)
			{
				if(Chess.board[(kLoc.x)+1][kLoc.y-1].pieceName.equals("bp") )
					return true;
			}
			if(kLoc.x+1<=7 && kLoc.y+1<=7)
			{
				if(Chess.board[(kLoc.x)+1][kLoc.y+1].pieceName.equals("bp"))
					return true;
			}
			if((kLoc.x)+1 <=7 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)-2].pieceName.equals("bN")) 
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)-1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)+2 <=7 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)+2][(kLoc.y)+1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)+1 <=7 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)+1][(kLoc.y)+2].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)+2<=7)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)+2].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)+1<=7)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)+1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-2 >=0 && (kLoc.y)-1>=0)
				if(Chess.board[(kLoc.x)-2][(kLoc.y)-1].pieceName.equals("bN"))
					return true;
			if((kLoc.x)-1 >=0 && (kLoc.y)-2>=0)
				if(Chess.board[(kLoc.x)-1][(kLoc.y)-2].pieceName.equals("bN"))
					return true;
		}
		return false;
	}

}
