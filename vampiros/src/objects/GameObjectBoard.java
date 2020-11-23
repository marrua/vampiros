package objects;
import logic.Level;
import logic.Game;

public class GameObjectBoard {
	private SlayerList slayerlist;
	private VampireList vampirelist;
	
	public GameObjectBoard(Game game) {
		this.slayerlist  = new SlayerList(game);
		this.vampirelist = new VampireList(game);
		
	}
//======================================================================================================
//GETTERS
	public SlayerList getSlayerlist() {
		return slayerlist;
	}

	public VampireList getVampirelist() {
		return vampirelist;
	}
//=======================================================================================================
//UTILIDADES DE TABLERO
	public boolean emptycell(int x,int y) {
		boolean ok=true;
		if(slayerlist.ifSlayer(x, y) || vampirelist.ifVampires(x, y)) {
			ok =false;
		}
		return ok;
	}
	public int element(int x, int y) {
		int element=-1;
		if(slayerlist.ifSlayer(x, y)) {
			element=0;
		}
		else if(vampirelist.ifVampires(x, y)) {
			element=1;
		}
		return element;
	}
//========================================================================================================
	//AÑADIR OBJETOS
	public void addSlayer(int x, int y) {
		slayerlist.add(x, y);
	}
	public boolean maxVampires(int max) {
		boolean ok=true;
		if(vampirelist.numberOfVampires()<max) {
			ok=false;
		}
		return ok;
		
	}
	public void addVampire(int x, int y) {
		if(emptycell(x,y)) {
			vampirelist.add(x, y);
		}
	}
//========================================================================================================
	//OBTENER VIDA DE LOS OBJETOS
	public int numlife(int x, int y) {
		int life;
		if(slayerlist.ifSlayer(x, y)) {
			life = slayerlist.returnLife(x, y);
		}else {
			life = vampirelist.returnLife(x, y);
		}
		return life;
	}
//===========================================================================================================
//ATAQUES
	public void slayersAttack() {
		slayerlist.attack();
	}
	public void vampiresAttack() {
		vampirelist.attack();
	}
	public void reciveAtackVamp(int x, int y) {
		vampirelist.restLife(x, y);
	}
	public void reciveAtackSlayer(int x, int y) {
		slayerlist.restLife(x, y);
	}
	
//===========================================================================================================
	//VAMPIROS AVANZAN
	public void goVampires(Level level) {
		this.vampirelist.avanzar();
	}
}
	