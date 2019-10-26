package structure;

public class Empty extends Cell{

	public Empty(String cellName, String pieceName, boolean isAlive, int x, int y) {
		super(cellName, pieceName, isAlive, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean move(int currX, int currY, int tarX, int tarY) {
		// TODO Auto-generated method stub
		return false;
	}

}
