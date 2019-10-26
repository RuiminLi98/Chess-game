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
				if(target.isAlive&&!Point.isEnemy(target, curr)&&target.pieceName.charAt(1)=='R') {
					// Castling
					if(curr.pieceName.charAt(0)=='w') {
						if(tarY==0&&Chess.CastlingWL&&!haveObs(currX, currY, tarY)&&!Chess.WCheck) {
							// white Castling long
							jump(currX, currY, tarX, tarY+1);
							jump(tarX,tarY,tarX,tarY+2);
							
							if(Point.check(new Point(tarX, tarY+1))) {
								jump(tarX, tarY+1,currX, currY);
								jump(tarX,tarY+2,tarX,tarY);
								return false;
							}
							
							Chess.CastlingWL=false;
							Chess.CastlingWL=false;
							return true;

						}
						if(tarY==7&&Chess.CastlingWS&&!haveObs(currX, currY, tarY)&&!Chess.WCheck) {
							// white Castling short
							jump(currX, currY, tarX, tarY-1);
							jump(tarX,tarY,tarX,tarY-2);
							
							if(Point.check(new Point(tarX, tarY-1))) {
								jump(tarX, tarY-1,currX, currY);
								jump(tarX,tarY-2,tarX,tarY);
								return false;
							}
							
							Chess.CastlingWL=false;
							Chess.CastlingWL=false;
							return true;
						}

					}else {
						// black
						if(tarY==7&&Chess.CastlingBS&&!haveObs(currX, currY, tarY)&&!Chess.BCheck) {
							// black Castling short
							jump(currX, currY, tarX, tarY-1);
							jump(tarX,tarY,tarX,tarY-2);
							
							if(Point.check(new Point(tarX, tarY-1))) {
								jump(tarX, tarY-1,currX, currY);
								jump(tarX,tarY-2,tarX,tarY);
								return false;
							}
							
							Chess.CastlingBL=false;
							Chess.CastlingBL=false;
							return true;

						}
						if(tarY==0&&Chess.CastlingBL&&!haveObs(currX, currY, tarY)&&!Chess.BCheck) {
							// black Castling long
							jump(currX, currY, tarX, tarY+1);
							jump(tarX,tarY,tarX,tarY+2);
							
							if(Point.check(new Point(tarX, tarY+1))) {
								jump(tarX, tarY+1,currX, currY);
								jump(tarX,tarY+2,tarX,tarY);
								return false;
							}
							
							Chess.CastlingBL=false;
							Chess.CastlingBL=false;
							return true;
						}

					}
				}
			}
		}
		return false;
	}

}
