package structure;

import java.util.ArrayList;

import chess.Chess;

/** 
 * @author Junjie He
 * @author Ruimin Li
 */

/**
 * This is the subclass Bishop which extends the abstract class Cell. 
 * The Bishop class implements the movement of Bishop piece on the board. 
 */
public class Bishop extends Cell{
	/**
	 * Initialize the Bishop object with cellName, pieceName, isAlive, x, y.
	 * @param cellName The cell's name, such as "##" or "  ",which is under the Bishop piece. 
	 * @param pieceName The Bishop piece's name, such as "wB" or "bB".
	 * @param isAlive The boolean value to show whether the piece is alive on the board.
	 * @param x The x-coordinate of the piece on the board. 
	 * @param y The y-coordinate of the piece on the board.
	 */
	public Bishop(String cellName, String pieceName, boolean isAlive, int x, int y) {
		super(cellName, pieceName, isAlive, x, y);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method performs the movement of a Bishop piece from current point to targeting point.
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
			jump(currX, currY, tarX, tarY);
			Chess.enpassant_flag=false;
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
	private boolean contains(ArrayList<Integer[]> list,Integer[] arr) {
		for(Integer[] a:list) {
			if(a[0]==arr[0]&&a[1]==arr[1])return true;
		}
		return false;
	}

	/**
	 * This method is going to search all of the possible location where the Bishop can reach.
	 * @param currX The x-coordinate of Bishop
	 * @param currY The y-coordinate of Bishop
	 * @return The ArrayList<Integer[]> contains all of the possible locations.
	 */
	private ArrayList<Integer[]> detector(int currX, int currY){
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		// Detect right up dig
		for(int i=currX+1,j=currY+1;i<8&&j<8;i++,j++) {
			Cell temp=Chess.board[i][j];
			Cell curr=Chess.board[currX][currY];
			if(!temp.isAlive) {
				Integer[] arr=new Integer[2];
				arr[0]=i;
				arr[1]=j;
				list.add(arr);
			}else {
				if(temp.pieceName.charAt(0)!=curr.pieceName.charAt(0)) {
					Integer[] arr=new Integer[2];
					arr[0]=i;
					arr[1]=j;
					list.add(arr);
				}
				break;
			}
		}
		// Detect left down dig
		for(int i=currX-1,j=currY-1;i>=0&&j>=0;i--,j--) {
			Cell temp=Chess.board[i][j];
			Cell curr=Chess.board[currX][currY];
			if(!temp.isAlive) {
				Integer[] arr=new Integer[2];
				arr[0]=i;
				arr[1]=j;
				list.add(arr);
			}else {
				if(temp.pieceName.charAt(0)!=curr.pieceName.charAt(0)) {
					Integer[] arr=new Integer[2];
					arr[0]=i;
					arr[1]=j;
					list.add(arr);
				}
				break;
			}
		}
		//Detect right down dig
		for(int i=currX-1,j=currY+1;i>=0&&j<8;i--,j++) {
			Cell temp=Chess.board[i][j];
			Cell curr=Chess.board[currX][currY];
			if(!temp.isAlive) {
				Integer[] arr=new Integer[2];
				arr[0]=i;
				arr[1]=j;
				list.add(arr);
			}else {
				if(temp.pieceName.charAt(0)!=curr.pieceName.charAt(0)) {
					Integer[] arr=new Integer[2];
					arr[0]=i;
					arr[1]=j;
					list.add(arr);
				}
				break;
			}
		}
		//Detect left up dig
		for(int i=currX+1,j=currY-1;i<8&&j>=0;i++,j--) {
			Cell temp=Chess.board[i][j];
			Cell curr=Chess.board[currX][currY];
			if(!temp.isAlive) {
				Integer[] arr=new Integer[2];
				arr[0]=i;
				arr[1]=j;
				list.add(arr);
			}else {
				if(temp.pieceName.charAt(0)!=curr.pieceName.charAt(0)) {
					Integer[] arr=new Integer[2];
					arr[0]=i;
					arr[1]=j;
					list.add(arr);
				}
				break;
			}
		}
		return list;
	}
	
	/**
	 * This method is going to search all of the possible location where the Bishop can reach.
	 * @return The ArrayList<Point> contains all of the possible locations. Null if no possible location.
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
