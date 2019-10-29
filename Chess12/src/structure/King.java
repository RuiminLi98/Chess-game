package structure;

import chess.Chess;

public class King extends Cell{

	public King(String cellName, String pieceName, boolean isAlive, int x, int y) {
		super(cellName, pieceName, isAlive, x, y);
		// TODO Auto-generated constructor stub
	}

	private boolean haveObs(int currX, int currY,int tarY) {
		for(int i=Math.min(currY, tarY)+1;i<Math.max(tarY, currY);i++) {
			if(Chess.board[currX][i].isAlive)return true;
		}
		return false;
	}
	@Override
	public boolean move(int currX, int currY, int tarX, int tarY) {
		// TODO Auto-generated method stub
		if(tarX<8&&tarX>=0&&tarY<8&&tarY>=0) {
			if(Math.abs(tarX-currX)<=1&&Math.abs(tarY-currY)<=1) {
				if(!(tarX==currX&&tarY==currY)) {
					if(Chess.board[tarX][tarY].pieceName.charAt(0)!=Chess.board[currX][currY].pieceName.charAt(0)) {
						if(Chess.board[tarX][tarY].pieceName.charAt(0)=='w') {
							Chess.CastlingWS=false;
							Chess.CastlingWL=false;
						}else if(Chess.board[tarX][tarY].pieceName.charAt(0)=='b') {
							Chess.CastlingBS=false;
							Chess.CastlingBL=false;
						}
						jump(currX, currY, tarX, tarY);
						return true;
					}
				}
			}
			else {
				Cell target=Chess.board[tarX][tarY];
				Cell curr=Chess.board[currX][currY];
//				if(target.isAlive&&!Point.isEnemy(target, curr)&&target.pieceName.charAt(1)=='R') {
					// Castling
					if(curr.pieceName.charAt(0)=='w') {
						if(tarX==0&&tarY==2&&Chess.CastlingWL&&!haveObs(currX, currY, 0)&&!Point.check(new Point(0,4))
								&&!Point.check(new Point(0,3))
								&&!Point.check(new Point(0,2))
								&&Chess.board[0][0].isAlive
								&&!Point.isEnemy(Chess.board[0][0], curr)
								&&Chess.board[0][0].pieceName.charAt(1)=='R'
								) {
							// white Castling long
							jump(currX, currY, tarX, currY-2);
							jump(0,0,0,currY-1);
							
//							if(Point.check(new Point(tarX, tarY+1))) {
//								jump(tarX, currY-2,currX, currY );
//								jump(0,currY-1,0,0);
//								return false;
//							}
							
							Chess.CastlingWL=false;
							Chess.CastlingWL=false;
							return true;

						}
						if(tarX==0&&tarY==6&&Chess.CastlingWS&&!haveObs(currX, currY, 7)&&!Point.check(new Point(0,4))
								&&!Point.check(new Point(0,5))
								&&!Point.check(new Point(0,6))
								&&Chess.board[0][7].isAlive
								&&!Point.isEnemy(Chess.board[0][7], curr)
								&&Chess.board[0][7].pieceName.charAt(1)=='R'
								) {
							// white Castling short
							jump(currX, currY, tarX, currY+2);
							jump(0,7,0,currY+1);
							
//							if(Point.check(new Point(tarX, tarY-1))) {
//								jump(tarX, currY+2,currX, currY );
//								jump(0,currY+1,0,7);
//								return false;
//							}
							
							Chess.CastlingWL=false;
							Chess.CastlingWL=false;
							return true;
						}

					}else {
						// black
						if(tarX==7&&tarY==6&&Chess.CastlingBS&&!haveObs(currX, currY, 7)&&!Point.check(new Point(7,4))
								&&!Point.check(new Point(7,5))
								&&!Point.check(new Point(7,6))
								&&Chess.board[7][7].isAlive
								&&!Point.isEnemy(Chess.board[7][7], curr)
								&&Chess.board[7][7].pieceName.charAt(1)=='R'
								) {
							// black Castling short
							jump(currX, currY, currX, currY-2);
							jump(7,0,7,currY-1);
							
//							if(Point.check(new Point(tarX, tarY-1))) {
//								jump(currX, currY-2,currX, currY);
//								jump(7,currY-1,7,0);
//								return false;
//							}
							
							Chess.CastlingBL=false;
							Chess.CastlingBL=false;
							return true;

						}
						if(tarX==7&&tarY==2&&Chess.CastlingBL&&!haveObs(currX, currY, 0)&&!Point.check(new Point(7,4))
								&&!Point.check(new Point(7,3))
								&&!Point.check(new Point(7,2))
								&&Chess.board[7][0].isAlive
								&&!Point.isEnemy(Chess.board[7][0], curr)
								&&Chess.board[7][0].pieceName.charAt(1)=='R'
								) {
							// black Castling long
							jump(currX, currY, currX, currY+2);
							jump(7,7,7,currY+1);
							
//							if(Point.check(new Point(tarX, tarY+1))) {
//								jump(currX, currY+2,currX, currY );
//								jump(7,currY+1,7,7);
//								return false;
//							}
							
							Chess.CastlingBL=false;
							Chess.CastlingBL=false;
							return true;
						}

					}
//				}
			}
		}
		return false;
	}

}
