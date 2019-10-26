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
	public static boolean check(Point kLoc) {
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
			if(c.pieceName.charAt(1)=='R'||c.pieceName.charAt(1)=='Q')return true;
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
		for(Cell c:EnemyListR) {
			if(c.pieceName.charAt(1)=='B'||c.pieceName.charAt(1)=='Q')return true;
		}
		
		for(int i=kLoc.x+1,j=kLoc.y-1,k=0;i<8&&j>=0&&j<8&&i>=0&&k<3;j++,k++) {
			Cell c=Chess.board[i][j];
			if(c.isAlive&&c.pieceName.charAt(1)=='K') {
				return true;
			}
		}
		for(int i=kLoc.x-1,j=kLoc.y-1,k=0;i<8&&j>=0&&j<8&&i>=0&&k<3;j++,k++) {
			Cell c=Chess.board[i][j];
			if(c.isAlive&&c.pieceName.charAt(1)=='K') {
				return true;
			}
		}
		for(int i=kLoc.x,j=kLoc.y-1,k=0;i<8&&j>=0&&j<8&&i>=0&&k<1;j++,k++) {
			Cell c=Chess.board[i][j];
			if(c.isAlive&&c.pieceName.charAt(1)=='K') {
				return true;
			}
		}
		for(int i=kLoc.x,j=kLoc.y+1,k=0;i<8&&j>=0&&j<8&&i>=0&&k<1;j++,k++) {
			Cell c=Chess.board[i][j];
			if(c.isAlive&&c.pieceName.charAt(1)=='K') {
				return true;
			}
		}
		if(threatFromPorN(kLoc)) {
			return true;
		}
		return false;
	}

	private static boolean threatFromPorN(Point kLoc) {
		// TODO Auto-generated method stub
		return false;
	}

}