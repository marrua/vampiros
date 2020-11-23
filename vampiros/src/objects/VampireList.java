package objects;

import logic.Game;

public class VampireList {
	private Vampire[] vampires;
	private int posicion;
	private int numVampires;
	private Game game;
	public VampireList(Game game) {
		this.vampires =  new Vampire[14];
		posicion = -1;
		this.numVampires=0;
		this.game = game;
	}
//==================================================================================
	//GETTERS
	
	public int getPosicion() {
		return posicion;
	}
	public int numberOfVampires() {
		return numVampires;
	}
//===================================================================================
	//UTILIDADES POSICION/CORDENADAS
	public boolean ifVampires(int cx, int cy) {
		boolean ok=false;
		for(int i=0;i<posicion+1;i++) {
			if (vampires[i].getX()==cx && vampires[i].getY()==cy) {
				ok=true;
			}
		}
		return ok;
	}

	private int posVampire(int cx, int cy) {
		int pos=0;
		for(int i=0;i<posicion+1;i++) {
			if (vampires[i].getX()==cx && vampires[i].getY()==cy) {
				pos=i;
			}
		}
		return pos;
	}
//===================================================================================
	//UTILIDADES MOVIMIENTO
	public void avanzar() {
		for(int i=0;i<posicion+1;i++) {
			vampires[i].goVampire();
		}
	}
	public void attack() {
		for(int i=0;i<posicion+1;i++) {
			vampires[i].attackVampire();
		}
	}
//====================================================================================
	//AÑADIR Y BORRAR VAMPIROS
	public void add(int x, int y) {
		Vampire newVampire;
		newVampire= new Vampire(5,x,y,game);
		posicion++;
		numVampires++;
		vampires[posicion]=newVampire;
	}
	public void remvDeath() {
		for(int i=0;i<(posicion+1);i++) {
			if(vampires[i].getLife()<=0) {
				for(int j=i;j<posicion;j++) {
					vampires[j]=vampires[j+1];
				}
				i--;
				posicion--;
			}
		}
	}
//===================================================================================
	//UTILIDADES DE VIDA
	public int returnLife(int x,int y) {
		int life =  vampires[posVampire(x,y)].getLife();
		return life;
	}
	public void restLife(int x, int y) {
		int pos  = posVampire(x,y);
		vampires[pos].restLife();
	}
	public boolean finish() {
		if(posicion==-1) {
			return true;
		}else {
			return false;
		}
	}
	
}