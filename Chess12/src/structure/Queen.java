package structure;

import java.util.ArrayList;

import chess.Chess;

public class Queen extends Cell{
	public Queen(String cellName, String pieceName, boolean isAlive, int x, int y) {
		super(cellName, pieceName, isAlive, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean move(int currX, int currY, int tarX, int tarY) {
		// TODO Auto-generated method stub
		ArrayList<Integer[]> list = detector(currX, currY);
		Integer[] arr=new Integer[2];
		arr[0]=tarX;
		arr[1]=tarY;
		if(contains(list,arr)) {
			jump(currX, currY, tarX, tarY);
			return true;
		}
		return false;
	}
	private boolean contains(ArrayList<Integer[]> list,Integer[] arr) {
		for(Integer[] a:list) {
			if(a[0]==arr[0]&&a[1]==arr[1])return true;
		}
		return false;
	}
	
	private ArrayList<Integer[]> detector(int currX, int currY){
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

}