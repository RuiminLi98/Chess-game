package structure;

import java.util.ArrayList;

import chess.Chess;

/** 
 * @author Junjie He
 * @author Ruimin Li
 */

/**
 * This is the subclass King which extends the abstract class Cell. 
 * The King class implements the movement of King piece on the board. 
 */
public class King extends Cell{

	/**
	 * Initialize the King object with cellName, pieceName, isAlive, x, y.
	 * @param cellName The cell's name, such as "##" or "  ",which is under the King piece. 
	 * @param pieceName The King piece's name, such as "wK" or "bK".
	 * @param isAlive The boolean value to show whether the piece is alive on the board.
	 * @param x The x-coordinate of the piece on the board. 
	 * @param y The y-coordinate of the piece on the board.
	 */
	public King(String cellName, String pieceName, boolean isAlive, int x, int y) {
		super(cellName, pieceName, isAlive, x, y);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method is used in the Castling to detect the pieces between king and rook.
	 * @param currX The x-coordinate of starting point in the movement.
	 * @param currY The y-coordinate of starting point in the movement.
	 * @param tarY The y-coordinate of targeting point in the movement.
	 * @return True if there is at least one piece in the Castling path. False, if the path is clear for Castling.
	 */
	public boolean haveObs(int currX, int currY,int tarY) {
		for(int i=Math.min(currY, tarY)+1;i<Math.max(tarY, currY);i++) {
			if(Chess.board[currX][i].isAlive)return true;
		}
		return false;
	}

	/**
	 * This method performs the movement of a King piece from current point to targeting point.
	 * Additionally, this method enabled the function of castling.
	 * @param currX The x-coordinate of starting point in the movement.
	 * @param currY The y-coordinate of starting point in the movement.
	 * @param tarX The x-coordinate of targeting point in the movement.
	 * @param tarY The y-coordinate of targeting point in the movement.
	 * @return True if successfully move. Otherwise, return false.
	 */
	@Override
	public boolean move(int currX, int currY, int tarX, int tarY) {
		// TODO Auto-generated method stub
		if(tarX<8&&tarX>=0&&tarY<8&&tarY>=0) {
			if(Math.abs(tarX-currX)<=1&&Math.abs(tarY-currY)<=1) {
				if(!(tarX==currX&&tarY==currY)) {
					if(Chess.board[tarX][tarY].pieceName.charAt(0)!=Chess.board[currX][currY].pieceName.charAt(0)) {
						if(Chess.board[currX][currY].pieceName.charAt(0)=='w') {

							Chess.CastlingWSPrev=Chess.CastlingWS;
							Chess.CastlingWLPrev=Chess.CastlingWL;


							Chess.CastlingWS=false;
							Chess.CastlingWL=false;
						}else if(Chess.board[currX][currY].pieceName.charAt(0)=='b') {

							Chess.CastlingBSPrev=Chess.CastlingBS;
							Chess.CastlingBLPrev=Chess.CastlingBL;

							Chess.CastlingBS=false;
							Chess.CastlingBL=false;
						}
						
						jump(currX, currY, tarX, tarY);
						Chess.enpassant_flag=false;
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
					if(tarX==0&&tarY==2&&Chess.CastlingWL&&!haveObs(currX, currY, 0)&&!Point.check(new Point(0,4),new Point(0,4))
							&&!Point.check(new Point(0,3),new Point(0,4))
							&&!Point.check(new Point(0,2),new Point(0,4))
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
						Chess.CastlingWS=false;
						Chess.enpassant_flag=false;
						return true;

					}
					if(tarX==0&&tarY==6&&Chess.CastlingWS&&!haveObs(currX, currY, 7)&&!Point.check(new Point(0,4),new Point(0,4))
							&&!Point.check(new Point(0,5),new Point(0,4))
							&&!Point.check(new Point(0,6),new Point(0,4))
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
						Chess.CastlingWS=false;
						Chess.enpassant_flag=false;
						return true;
					}

				}else {
					// black
					if(tarX==7&&tarY==6&&Chess.CastlingBS&&!haveObs(currX, currY, 7)&&!Point.check(new Point(7,4),new Point(7,4))
							&&!Point.check(new Point(7,5),new Point(7,4))
							&&!Point.check(new Point(7,6),new Point(7,4))
							&&Chess.board[7][7].isAlive
							&&!Point.isEnemy(Chess.board[7][7], curr)
							&&Chess.board[7][7].pieceName.charAt(1)=='R'
							) {
						// black Castling shortChess.BKLoc=new Point(tarX, currY+2);
						jump(currX, currY, currX, currY+2);
						jump(7,7,7,currY+1);

						//							if(Point.check(new Point(tarX, tarY-1))) {
						//								jump(currX, currY-2,currX, currY);
						//								jump(7,currY-1,7,0);
						//								return false;
						//							}

						Chess.CastlingBS=false;
						Chess.CastlingBL=false;
						Chess.enpassant_flag=false;
						return true;

					}
					if(tarX==7&&tarY==2&&Chess.CastlingBL&&!haveObs(currX, currY, 0)&&!Point.check(new Point(7,4),new Point(7,4))
							&&!Point.check(new Point(7,3),new Point(7,4))
							&&!Point.check(new Point(7,2),new Point(7,4))
							&&Chess.board[7][0].isAlive
							&&!Point.isEnemy(Chess.board[7][0], curr)
							&&Chess.board[7][0].pieceName.charAt(1)=='R'
							) {
						// black Castling long
						
						jump(currX, currY, currX, currY-2);
						jump(7,0,7,currY-1);

						//							if(Point.check(new Point(tarX, tarY+1))) {
						//								jump(currX, currY+2,currX, currY );
						//								jump(7,currY+1,7,7);
						//								return false;
						//							}

						Chess.CastlingBS=false;
						Chess.CastlingBL=false;
						Chess.enpassant_flag=false;
						return true;
					}

				}
				//				}
			}
		}
		return false;
	}

	@Override
	/**
	 * This method is going to search all of the possible location where the King can reach, and the possible location cannot be 
	 * Checked by enemy.
	 * @return The ArrayList<Point> contains all of the possible locations.
	 */
	public ArrayList<Point> Searcher() {
		// TODO Auto-generated method stub
		ArrayList<Point> result=new ArrayList<Point>();
		Point kLoc = new Point(this.x,this.y);
		if(Point.inScale(kLoc.x-1, kLoc.y-1)) {
			Cell c=Chess.board[kLoc.x-1][kLoc.y-1];
			if(!c.isAlive||Point.isEnemy(new Point(c.x,c.y),kLoc )) {
				result.add(new Point(c.x,c.y));
			}
		}

		if(Point.inScale(kLoc.x-1, kLoc.y)) {
			Cell c=Chess.board[kLoc.x-1][kLoc.y];
			if(!c.isAlive||Point.isEnemy(new Point(c.x,c.y),kLoc )) {
				result.add(new Point(c.x,c.y));
			}
		}

		if(Point.inScale(kLoc.x-1, kLoc.y+1)) {
			Cell c=Chess.board[kLoc.x-1][kLoc.y+1];
			if(!c.isAlive||Point.isEnemy(new Point(c.x,c.y),kLoc )) {
				result.add(new Point(c.x,c.y));
			}
		}
		if(Point.inScale(kLoc.x, kLoc.y-1)) {
			Cell c=Chess.board[kLoc.x][kLoc.y-1];
			if(!c.isAlive||Point.isEnemy(new Point(c.x,c.y),kLoc )) {
				result.add(new Point(c.x,c.y));
			}
		}
		if(Point.inScale(kLoc.x, kLoc.y+1)) {
			Cell c=Chess.board[kLoc.x][kLoc.y+1];
			if(!c.isAlive||Point.isEnemy(new Point(c.x,c.y),kLoc )) {
				result.add(new Point(c.x,c.y));
			}
		}
		if(Point.inScale(kLoc.x+1, kLoc.y-1)) {
			Cell c=Chess.board[kLoc.x+1][kLoc.y-1];
			if(!c.isAlive||Point.isEnemy(new Point(c.x,c.y),kLoc )) {
				result.add(new Point(c.x,c.y));
			}
		}
		if(Point.inScale(kLoc.x+1, kLoc.y)) {
			Cell c=Chess.board[kLoc.x+1][kLoc.y];
			if(!c.isAlive||Point.isEnemy(new Point(c.x,c.y),kLoc )) {
				result.add(new Point(c.x,c.y));
			}
		}
		if(Point.inScale(kLoc.x+1, kLoc.y+1)) {
			Cell c=Chess.board[kLoc.x+1][kLoc.y+1];
			if(!c.isAlive||Point.isEnemy(new Point(c.x,c.y),kLoc )) {
				result.add(new Point(c.x,c.y));
			}
		}
		return result.isEmpty()?null:result;
	}

}
