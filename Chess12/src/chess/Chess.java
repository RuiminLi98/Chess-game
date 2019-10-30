package chess;

import java.util.ArrayList;
import java.util.Scanner;

import structure.*;

public class Chess {

	public static Cell[][] board;

	public static boolean CastlingWL;
	public static boolean CastlingBL;
	public static boolean CastlingBS;
	public static boolean CastlingWS;
	
	public static boolean enpassant_flag=false;
	public static int enpassant_flagx=0;
	public static int enpassant_flagy=0;
	
	public static boolean CastlingWLPrev;
	public static boolean CastlingBLPrev;
	public static boolean CastlingBSPrev;
	public static boolean CastlingWSPrev;

	public static boolean jumpBack;
	
	public static Point WKLoc;
	public static Point BKLoc;
	
//	public static boolean WCheck;
//	public static boolean BCheck;
	public static boolean draw_flag;
	public static String []a;

	public static void Initialize(){
		CastlingBL=true;
		CastlingWL=true;
		CastlingBS=true;
		CastlingWS=true;
		CastlingWLPrev=true;
		CastlingBLPrev=true;
		CastlingBSPrev=true;
		CastlingWSPrev=true;
		jumpBack=false;
//		WCheck=false;
//		BCheck=false;
		Chess.WKLoc=new Point(0,4);
		Chess.BKLoc=new Point(7,4);
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
//		board[7][1]=new Empty("##", "Empty", false,7, 1);
//		board[7][2]=new Empty("  ", "Empty", false,7, 2);
//		board[7][3]=new Empty("##", "Empty", false,7, 3);
		board[7][4]=new King("  ", "bK", true,7, 4);
		board[7][5]=new Bishop("##", "bB", true,7, 5);
		board[7][6]=new Knight("  ", "bN", true,7, 6);
//		board[7][5]=new Empty("##", "Empty", false,7, 5);
//		board[7][6]=new Empty("  ", "Empty", false,7, 6);
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
//						board[1][i]=new Empty("  ", "empty", false, 1, i);
//						board[6][i]=new Empty("##", "empty", false, 6, i);
			//
			board[2][i]=new Empty("##", "empty", false, 2, i);
			board[3][i]=new Empty("  ", "empty", false, 3, i);
			board[4][i]=new Empty("##", "empty", false, 4, i);
			board[5][i]=new Empty("  ", "empty", false, 5, i);
		}
		for(int i=1;i<8;i+=2) {
			//
//						board[1][i]=new Empty("##", "empty", false, 1, i);
//						board[6][i]=new Empty("  ", "empty", false, 6, i);
			//
			board[2][i]=new Empty("  ", "empty", false, 2, i);
			board[3][i]=new Empty("##", "empty", false, 3, i);
			board[4][i]=new Empty("  ", "empty", false, 4, i);
			board[5][i]=new Empty("##", "empty", false, 5, i);
		}
//		board[6][4]=new Rook("##", "wR", true,6, 4);
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
		if(Point.check(WKLoc,WKLoc)&&Point.check(BKLoc,BKLoc))return 3;
		if(Point.check(WKLoc,WKLoc))return 1;
		if(Point.check(BKLoc,BKLoc))return 2;
		return 0;
	}

	public static boolean Illegalmove(char turn,int curX,int curY,int tarX,int tarY) {
		switch(isCheckmate()) {
		case 0:{
			Chess.jumpBack=false;
			return false;
		}
		case 1:{
			if(turn=='w') {
				Chess.board[tarX][tarY].move(tarX, tarY,curX, curY);
//				Chess.CastlingWS=Chess.CastlingWSPrev;
//				Chess.CastlingWL=Chess.CastlingWLPrev;
//				Chess.jumpBack=false;
				return true;
			}
			break;
		}
		case 2:{
			if(turn=='b') {
				Chess.board[tarX][tarY].move(tarX, tarY,curX, curY);
//				Chess.CastlingBS=Chess.CastlingWSPrev;
//				Chess.CastlingBL=Chess.CastlingWLPrev;
//				Chess.jumpBack=false;
				return true;
			}
			break;
		}
		case 3:{
			
			break;
		}

		}
//		Chess.jumpBack=false;
		return false;
	}
	
	public static ArrayList<Point> findPath(Point p1,Point p2){
		ArrayList<Point> path=new ArrayList<Point>();
		if(p1.x==p2.x) {
			for(int i=Math.min(p1.y, p2.y)+1;i<Math.max(p1.y, p2.y);i++) {
				path.add(new Point(p1.x,i));
			}
		}else if(p1.y==p2.y) {
			for(int i=Math.min(p1.x, p2.x)+1;i<Math.max(p1.x, p2.x);i++) {
				path.add(new Point(i,p1.y));
			}
		}else {
			if(p1.x<p2.x&&p1.y<p2.y) {
				for(int i=p1.x+1,j=p1.y+1;i<p2.x&&j<p2.y;i++,j++) {
					path.add(new Point(i,j));
				}
			}else if(p1.x>p2.x&&p1.y<p2.y){
				for(int i=p1.x-1,j=p1.y+1;i>p2.x&&j<p2.y;i--,j++) {
					path.add(new Point(i,j));
				}
			}else if(p1.x<p2.x&&p1.y>p2.y){
				for(int i=p1.x+1,j=p1.y-1;i<p2.x&&j>p2.y;i++,j--) {
					path.add(new Point(i,j));
				}
			}else {
				for(int i=p1.x-1,j=p1.y-1;i>p2.x&&j>p2.y;i--,j--) {
					path.add(new Point(i,j));
				}
			}
		}
		return path;
	}
	
	
	/**
	 * <p> aaaaaasfsdvgfdxvds</p>
	 * 
	 * 
	 */
	public static boolean checkToDeath(char turn) {
		Point kLoc;
		if(turn=='w') {
			//black
			 kLoc=Point.getLocation("bK");
		}else {
			//white
			kLoc=Point.getLocation("wK");
		}
		if(Point.check(kLoc,kLoc)) {
			if(Point.inScale(kLoc.x-1, kLoc.y-1)) {
				Cell c=Chess.board[kLoc.x-1][kLoc.y-1];
				if(!c.isAlive&&!Point.check(new Point(c.x,c.y),kLoc)) {
					return false;
				}
			}
			
			if(Point.inScale(kLoc.x-1, kLoc.y)) {
				Cell c=Chess.board[kLoc.x-1][kLoc.y];
				if(!c.isAlive&&!Point.check(new Point(c.x,c.y),kLoc)) {
					return false;
				}
			}
			
			if(Point.inScale(kLoc.x-1, kLoc.y+1)) {
				Cell c=Chess.board[kLoc.x-1][kLoc.y+1];
				if(!c.isAlive&&!Point.check(new Point(c.x,c.y),kLoc)) {
					return false;
				}
			}
			if(Point.inScale(kLoc.x, kLoc.y-1)) {
				Cell c=Chess.board[kLoc.x][kLoc.y-1];
				if(!c.isAlive&&!Point.check(new Point(c.x,c.y),kLoc)) {
					return false;
				}
			}
			if(Point.inScale(kLoc.x, kLoc.y+1)) {
				Cell c=Chess.board[kLoc.x][kLoc.y+1];
				if(!c.isAlive&&!Point.check(new Point(c.x,c.y),kLoc)) {
					return false;
				}
			}
			if(Point.inScale(kLoc.x+1, kLoc.y-1)) {
				Cell c=Chess.board[kLoc.x+1][kLoc.y-1];
				if(!c.isAlive&&!Point.check(new Point(c.x,c.y),kLoc)) {
					return false;
				}
			}
			if(Point.inScale(kLoc.x+1, kLoc.y)) {
				Cell c=Chess.board[kLoc.x+1][kLoc.y];
				if(!c.isAlive&&!Point.check(new Point(c.x,c.y),kLoc)) {
					return false;
				}
			}
			if(Point.inScale(kLoc.x+1, kLoc.y+1)) {
				Cell c=Chess.board[kLoc.x+1][kLoc.y+1];
				if(!c.isAlive&&!Point.check(new Point(c.x,c.y),kLoc)) {
					return false;
				}
			}
			
			Cell threat=Point.findCheck(kLoc);
			if(Point.check(new Point(threat.x,threat.y),new Point(threat.x,threat.y))) {
				
				Cell threat2=Point.findCheck(new Point(threat.x,threat.y));
				if(threat2.pieceName.equals(Chess.board[kLoc.x][kLoc.y].pieceName)) {
					if(Point.check(new Point(threat.x,threat.y), kLoc)) {
						return true;
					}
				}
				
				return false;
			}
			if(threat.pieceName.charAt(1)=='N'||threat.pieceName.charAt(1)=='p')return true;
			
			ArrayList<Point> path= findPath(kLoc,new Point(threat.x,threat.y));
			for(Point p:path) {
				if(Point.checkFromSelf(p,kLoc)) {
					return false;
				}
			}
			
			return true;
			}
		
		
		
		return false;
		
		
		
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
			String line="";
			if(count%2==0)
				System.out.print("White's move: ");

			else
				System.out.print("Black's move: ");
			line=sc.nextLine();
			a=line.split(" ");
			int num=a.length;
			start=a[0];
			if(start.equals("draw") && draw_flag==true)
			{

//				System.out.println("We draw successfully");
				return;
			}
			else
			{
				draw_flag=false;
			}
			if(count%2==0) {
				if(start.equals("resign"))
				{
					System.out.println("Black wins");
					return;
				}
				else
				{
					end=a[1];
					if(num==3) {
						if(a[2].equals("draw?"))
							draw_flag=true;}
					turn='w';
					System.out.println();}
			}else {

				if(start.equals("resign"))
				{
					System.out.println("White wins");
					return;
				}
				else
				{
					end=a[1];
					if(num==3) {
						if(a[2].equals("draw?"))
							draw_flag=true;}
					turn='b';
					System.out.println();
				}
			}
			count++;
			int curX=parseLocation(start.charAt(1));
			int curY=parseLocation(start.charAt(0));
			int tarX=parseLocation(end.charAt(1));
			int tarY=parseLocation(end.charAt(0));
			while((Chess.board[curX][curY].pieceName.charAt(0)!=turn)||!Chess.board[curX][curY].move(curX, curY, tarX, tarY)
					||Illegalmove(turn, curX, curY, tarX, tarY)) {
				System.out.print("Illegal move, try again: ");
				if(count%2==1) {  turn='w';
				line=sc.nextLine();
				a=line.split(" ");
				num=a.length;
				start=a[0];
				if(start.equals("draw") && draw_flag==true)
				{

//					System.out.println("We draw successfully");
					return;
				}
				else
				{
					draw_flag=false;
				}
				if(start.equals("resign"))
				{
					System.out.println("Black wins");
					return;
				}
				else
				{
					end=a[1];
					if(num==3) {
						if(a[2].equals("draw?"))
							draw_flag=true;}
					System.out.println();}}
				else
				{turn='b';
				line=sc.nextLine();
				a=line.split(" ");
				num=a.length;
				start=a[0];
				if(start.equals("draw") && draw_flag==true)
				{

//					System.out.println("We draw successfully");
					return;
				}
				else
				{
					draw_flag=false;
				}
				if(start.equals("resign"))
				{
					System.out.println("Black wins");
					return;
				}
				else
				{
					end=a[1];
					if(num==3) {
						if(a[2].equals("draw?"))
							draw_flag=true;}
					System.out.println();}
				}
				curX=parseLocation(start.charAt(1));
				curY=parseLocation(start.charAt(0));
				tarX=parseLocation(end.charAt(1));
				tarY=parseLocation(end.charAt(0));
			}
			
			
			
			printBoard();
			Point kLoc;
			if(turn=='w') {
				//black
				 kLoc=Point.getLocation("bK");
				 if(Point.check(kLoc,kLoc)) {
					 if(checkToDeath(turn)) {
						 System.out.println("Checkmate\n White wins");
						 return;
					 }else {
						 System.out.println("Check");
					 }
				 }
			}else {
				//white
				kLoc=Point.getLocation("wK");
				if(Point.check(kLoc,kLoc)) {
					 if(checkToDeath(turn)) {
						 System.out.println("Checkmate\n Black wins");
						 return;
					 }else {
						 System.out.println("Check");
					 }
				 }
			}
			

		}
		
	}

}

