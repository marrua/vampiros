package objects;
import logic.Game;

public class Slayer {
	private int life;
	private int x;
	private int y;
	private Game game;
	public Slayer(int life, int x, int y, Game game) {
		super();
		this.life = life;
		this.x = x;
		this.y = y;
		this.game = game;
	}
//========================================
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
//=========================================
	//UTILIDADES
	public void restLife() {
		life--;
	}
	public void attackSlayer() {
		for(int i=0;i<game.getLevel().getDim_x();i++) {
			if(game.element(i,y)==1&&life>0) {
				game.reciveAtackVamp(x-1, y);
			}
		}
		
	}
}