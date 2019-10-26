package chess;

import java.util.Scanner;

import structure.*;

public class Chess {
	
	public static Cell[][] board;
	
	public static boolean CastlingWL;
	public static boolean CastlingBL;
	public static boolean CastlingBS;
	public static boolean CastlingWS;
	
	public static boolean WCheck;
	public static boolean BCheck;
	
	
	public static void Initialize(){
		CastlingBL=true;
		CastlingWL=true;
		CastlingBS=true;
		CastlingWS=true;
		WCheck=false;
		BCheck=false;
		board=new Cell[8][8];
		board[0][0]=new Rook("##", "wR", true, 0, 0);
		board[0][1]=new Knight("  ", "wN", true, 0, 1);
		board[0][2]=new Bishop("##", "wB", true, 0, 2);
		board[0][3]=new Queen("  ", "wQ", true, 0, 3);
		board[0][4]=new King("##", "wK", true, 0, 4);
		board[0][5]=new Bishop("  ", "wB", true, 0, 5);
		board[0][6]=new Knight("##", "wN", true, 0, 6);
		//
//		board[0][6]=new Empty("##", "empty", false, 0, 6);
		//
		board[0][7]=new Rook("  ", "wR", true, 0, 7);
		
		
		board[7][0]=new Rook("  ", "bR", true, 7, 0);
		board[7][1]=new Knight("##", "bN", true,7, 1);
		board[7][2]=new Bishop("  ", "bB", true,7, 2);
		board[7][3]=new Queen("##", "bQ", true,7, 3);
		board[7][4]=new King("  ", "bK", true,7, 4);
		board[7][5]=new Bishop("##", "bB", true,7, 5);
		board[7][6]=new Knight("  ", "bN", true,7, 6);
		board[7][7]=new Rook("##", "bR", true,7, 7);
		
		for(int i=0;i<8;i+=2) {
			board[1][i]=new Pawn("  ", "wp", true, 1, i);
			board[6][i]=new Pawn("##", "bp", true, 6, i);
		}
		for(int i=1;i<8;i+=2) {
			board[1][i]=new Pawn("##", "wp", true, 1, i);
			board[6][i]=new Pawn("  ", "bp", true, 6, i);
		}
		
		for(int i=0;i<8;i+=2) {
			//
//			board[1][i]=new Empty("  ", "empty", false, 1, i);
//			board[6][i]=new Empty("##", "empty", false, 6, i);
			//
			board[2][i]=new Empty("##", "empty", false, 2, i);
			board[3][i]=new Empty("  ", "empty", false, 3, i);
			board[4][i]=new Empty("##", "empty", false, 4, i);
			board[5][i]=new Empty("  ", "empty", false, 5, i);
		}
		for(int i=1;i<8;i+=2) {
			//
//			board[1][i]=new Empty("##", "empty", false, 1, i);
//			board[6][i]=new Empty("  ", "empty", false, 6, i);
			//
			board[2][i]=new Empty("  ", "empty", false, 2, i);
			board[3][i]=new Empty("##", "empty", false, 3, i);
			board[4][i]=new Empty("  ", "empty", false, 4, i);
			board[5][i]=new Empty("##", "empty", false, 5, i);
		}
	}
	
	public static void printBoard() {
		for(int i=7;i>=0;i--) {
			for(int j=0;j<8;j++) {
				if(board[i][j].isAlive) {
					System.out.print(board[i][j].pieceName);
				}else {
					System.out.print(board[i][j].cellName);
				}
				System.out.print(' ');
			}
			System.out.println(i+1);
		}
		System.out.print(" a ");
		System.out.print(" b ");
		System.out.print(" c ");
		System.out.print(" d ");
		System.out.print(" e ");
		System.out.print(" f ");
		System.out.print(" g ");
		System.out.print(" h");
		System.out.println();
		System.out.println();
	}
	public static int parseLocation (char c) {
		if(Character.isDigit(c))return Integer.parseInt(c+"")-1;
		return (int)c-97;
	}
	
	public static int isCheckmate() {
		//return 0 for not checkmate
		//return 1 for white king is checked
		//return 2 for black king is checked
		//return 3 for both
		
		Point WKLoc=Point.getLocation("wK");
		Point BKLoc=Point.getLocation("bK");
		if(Point.check(WKLoc)&&Point.check(BKLoc))return 3;
		if(Point.check(WKLoc))return 1;
		if(Point.check(BKLoc))return 2;
		return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Initialize();
		printBoard();
		Scanner sc=new Scanner(System.in);
		int count=0;
		while(true) {
			String start="";
			String end="";
			char turn=' ';
			if(count%2==0) {
				System.out.print("White's move: ");
				 start=sc.next();
				 end=sc.next();
				 turn='w';
				System.out.println();
			}else {
				System.out.print("Black's move: ");
				 start=sc.next();
				 end=sc.next();
				 turn='b';
				System.out.println();
			}
			count++;
			int curX=parseLocation(start.charAt(1));
			int curY=parseLocation(start.charAt(0));
			int tarX=parseLocation(end.charAt(1));
			int tarY=parseLocation(end.charAt(0));
			while((Chess.board[curX][curY].pieceName.charAt(0)!=turn)||!Chess.board[curX][curY].move(curX, curY, tarX, tarY)) {
				System.out.print("Illegal move, try again: ");
				start=sc.next();
				end=sc.next();
				System.out.println();
				curX=parseLocation(start.charAt(1));
				curY=parseLocation(start.charAt(0));
				tarX=parseLocation(end.charAt(1));
				tarY=parseLocation(end.charAt(0));
			}
				printBoard();

		}

	}

}
