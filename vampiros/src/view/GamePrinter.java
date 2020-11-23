package view;

import logic.Game;
import utils.MyStringUtils;

public class GamePrinter {
	
	Game game;
	int numRows; 
	int numCols;
	String[][] board;
	final String space = " ";
	
	public GamePrinter (Game game, int cols, int rows) {
		this.game = game;
		this.numRows = rows;
		this.numCols = cols;
	}
	
	private void encodeGame(Game game) {
		// TODO fill your code
		int x = game.getLevel().getDim_x();
		int y = game.getLevel().getDim_y();
		String[][] board = new String[y][x];
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				switch(game.element(i, j)) {
					case 0:
						String lifeSlayer=Integer.toString(game.getBoard().numlife(i, j));
						board[j][i]="S ["+ lifeSlayer +"]";
						break;
					case 1:
						String lifeVamp=Integer.toString(game.getBoard().numlife(i, j));
						board[j][i]="V ["+ lifeVamp +"]";
						break;
					default:
						board[j][i]="";
						break;
			}
			}
		}
		this.board = board;
		
	}
	
	 public String toString() {
		encodeGame(game);
		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		String intersect = space;
		String vIntersect = space;
		String hIntersect = "-";
		String corner = space;

		String cellDelimiter = MyStringUtils.repeat(hDelimiter, cellSize);

		String rowDelimiter = vIntersect + MyStringUtils.repeat(cellDelimiter + intersect, numCols-1) + cellDelimiter + vIntersect;
		String hEdge =  corner + MyStringUtils.repeat(cellDelimiter + hIntersect, numCols-1) + cellDelimiter + corner;

		String margin = MyStringUtils.repeat(space, marginSize);
		String lineEdge = String.format("%n%s%s%n", margin, hEdge);
		String lineDelimiter = String.format("%n%s%s%n", margin, rowDelimiter);

		StringBuilder str = new StringBuilder();

		str.append(lineEdge);
		for(int i=0; i<numRows; i++) {
		        str.append(margin).append(vDelimiter);
		        for (int j=0; j<numCols; j++)
		            str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
		        if (i != numRows - 1) str.append(lineDelimiter);
		        else str.append(lineEdge);   
		}

		return str.toString();
	    }
}