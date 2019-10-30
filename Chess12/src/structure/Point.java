package structure;

import java.util.ArrayList;

import chess.Chess;

public class Point {
	public int x,y;
	public Point(int x,int y) {
		this.x=x;this.y=y;
	}

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
	public static boolean isEnemy(Cell p1,Cell p2) {
		if(p1.pieceName.charAt(0)==p2.pieceName.charAt(0))return false;
		return true;
	}
	
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
	
	private static Cell PointThreatFromPN(Point kLoc)
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
	private static Cell PointTreatFromKN(Point kLoc)
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

	private static boolean threatFromPorNSelf(Point kLoc, Point kLoc2) {
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
		if(threatFromPorN(kLoc)) {
			return true;
		}
		return false;
	}
	public static boolean inScale(int x, int y) {
		if(x<8&&x>=0&&y>=0&&y<8)return true;
		return false;
	}
	private static boolean threatFromPorN(Point kLoc) {		
		if((kLoc.x==0 || kLoc.x==1) && Chess.board[kLoc.x][kLoc.y].pieceName.charAt(0)=='b')
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
		if((kLoc.x==2 || kLoc.x==3 || kLoc.x==4 || kLoc.x==5 || kLoc.x==6 || kLoc.x==7) && Chess.board[kLoc.x][kLoc.y].pieceName.charAt(0)=='b')
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


		
		if((kLoc.x==6 || kLoc.x==7) && Chess.board[kLoc.x][kLoc.y].pieceName.charAt(0)=='w')
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
		if((kLoc.x==0 || kLoc.x==1 || kLoc.x==2 || kLoc.x==3 || kLoc.x==4 || kLoc.x==5) && Chess.board[kLoc.x][kLoc.y].pieceName.charAt(0)=='w')
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
