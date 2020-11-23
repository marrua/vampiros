package objects;
import logic.Game;

public class Vampire {
	private int life;
	private int x;
	private int y;
	private boolean move;
	private Game game;
	
	public Vampire(int life, int x, int y, Game game) {
		super();
		this.life = life;
		this.x = x;
		this.y = y;
		this.move=false;
		this.game = game;
	}
	

//=================================================
	//GETTERS Y SETTERS
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}
//==================================================
	//UTILIDADES
	public void restLife() {
		life--;
	}
	public void goVampire() {
		if(move&&game.getBoard().emptycell(x-1, y)&&x>-1) {
			x--;
			move =  false;
		}else {
			move=true;
		}
	}
	public void attackVampire() {
		if(game.element(x-1,y)==0&&life>0) {
			game.getBoard().getSlayerlist().restLife(x-1, y);
		}
	}
//==================================================
}