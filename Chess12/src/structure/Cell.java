package structure;

public abstract class Cell {
	public String cellName;
	public String pieceName;
	public boolean isAlive;
	public Cell(String cellName,String pieceName,boolean isAlive) {
		this.cellName=cellName;
		this.pieceName=pieceName;
		this.isAlive=isAlive;
	}
	public abstract boolean move(int currX,int currY,int tarX,int tarY);
			
}
