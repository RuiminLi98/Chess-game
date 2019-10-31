package structure;

import java.util.ArrayList;

import chess.Chess;

/** 
 * @author Junjie He
 * @author Ruimin Li
 */

/**
 * This is the subclass Rook which extends the abstract class Cell. 
 * The Rook class implements the movement of Rook piece on the board.
 */
public class Rook extends Cell{

	/**
	 * Initialize the Rook object with cellName, pieceName, isAlive, x, y.
	 * @param cellName The cell's name, such as "##" or "  ",which is under the Rook piece. 
	 * @param pieceName The Rook piece's name, such as "wR" or "bR".
	 * @param isAlive The boolean value to show whether the piece is alive on the board.
	 * @param x The x-coordinate of the piece on the board. 
	 * @param y The y-coordinate of the piece on the board.
	 */
	public Rook(String cellName, String pieceName, boolean isAlive, int x, int y) {
		super(cellName, pieceName, isAlive, x, y);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method performs the movement of a Rook piece from current point to targeting point.
	 * @param currX The x-coordinate of starting point in the movement.
	 * @param currY The y-coordinate of starting point in the movement.
	 * @param tarX The x-coordinate of targeting point in the movement.
	 * @param tarY The y-coordinate of targeting point in the movement.
	 * @return True if successfully move. Otherwise, return false.
	 */
	@Override
	public boolean move(int currX, int currY, int tarX, int tarY) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer[]> list = detector(currX, currY);
		Integer[] arr=new Integer[2];
		arr[0]=tarX;
		arr[1]=tarY;
		if(contains(list,arr)) {
			if(currX==0&&currY==0) {

			}
			if(Chess.board[currX][currY].pieceName.charAt(0)=='w') {
				if(currY==0) {
					Chess.CastlingWL=false;
				}
				if(currY==7) {
					Chess.CastlingWS=false;
				}
			}else {
				if(currY==0) {

					Chess.CastlingBL=false;
				}
				if(currY==7) {
					Chess.CastlingBS=false;
				}
			}
			Chess.enpassant_flag=false;
			jump(currX, currY, tarX, tarY);
			return true;
		}
		return false;
	}

	/**
	 * This method is going to decide whether the ArrayList contains my Integer array.
	 * @param list The ArrayList
	 * @param arr My Integer Array contains x and y coordinates
	 * @return True if contains. Otherwise, return false.
	 */
	public boolean contains(ArrayList<Integer[]> list,Integer[] arr) {
		for(Integer[] a:list) {
			if(a[0]==arr[0]&&a[1]==arr[1])return true;
		}
		return false;
	}

	/**
	 * This method is going to search all of the possible location where the rook can reach.
	 * @param currX The x-coordinate of Rook
	 * @param currY The y-coordinate of Rook
	 * @return The ArrayList contains all of the possible locations.
	 */
	public ArrayList<Integer[]> detector(int currX, int currY){
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();

		// Detect Right row
		for(int i=currY+1;i<8;i++) {
			if(!Chess.board[currX][i].isAlive) {
				Integer[] arr=new Integer[2];
				arr[0]=currX;
				arr[1]=i;
				list.add(arr);
			}else {
				if(chess.Chess.board[currX][i].pieceName.charAt(0)!=chess.Chess.board[currX][currY].pieceName.charAt(0)) {
					Integer[] arr=new Integer[2];
					arr[0]=currX;
					arr[1]=i;
					list.add(arr);
				}
				break;
			}
		}

		// Detect left row
		for(int i=currY-1;i>=0;i--) {
			if(!Chess.board[currX][i].isAlive) {
				Integer[] arr=new Integer[2];
				arr[0]=currX;
				arr[1]=i;
				list.add(arr);
			}else {
				if(chess.Chess.board[currX][i].pieceName.charAt(0)!=chess.Chess.board[currX][currY].pieceName.charAt(0)) {
					Integer[] arr=new Integer[2];
					arr[0]=currX;
					arr[1]=i;
					list.add(arr);
				}
				break;
			}
		}

		//Detect up column
		for(int i=currX+1;i<8;i++) {
			if(!Chess.board[i][currY].isAlive) {
				Integer[] arr=new Integer[2];
				arr[0]=i;
				arr[1]=currY;
				list.add(arr);
			}else {
				if(chess.Chess.board[i][currY].pieceName.charAt(0)!=chess.Chess.board[currX][currY].pieceName.charAt(0)) {
					Integer[] arr=new Integer[2];
					arr[0]=i;
					arr[1]=currY;
					list.add(arr);
				}
				break;
			}
		}
		//Detect down column
		for(int i=currX-1;i>=0;i--) {
			if(!Chess.board[i][currY].isAlive) {
				Integer[] arr=new Integer[2];
				arr[0]=i;
				arr[1]=currY;
				list.add(arr);
			}else {
				if(chess.Chess.board[i][currY].pieceName.charAt(0)!=chess.Chess.board[currX][currY].pieceName.charAt(0)) {
					Integer[] arr=new Integer[2];
					arr[0]=i;
					arr[1]=currY;
					list.add(arr);
				}
				break;
			}
		}
		return list;
	}
	/**
	 * This method is going to search all of the possible location where the Rook can reach.
	 * @return The ArrayList contains all of the possible locations. Null if no possible location.
	 */
	@Override
	public ArrayList<Point> Searcher() {
		// TODO Auto-generated method stub
		ArrayList<Integer[]> list1=detector(this.x, this.y);
		ArrayList<Point> result=new ArrayList<Point>();
		for(Integer[] i:list1) {
			Point p=new Point(i[0],i[1]);
			result.add(p);
		}
		return result.isEmpty()?null:result;
	}

}
