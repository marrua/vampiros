package objects;
import logic.Game;

public class SlayerList {
	private Slayer[] slayers;
	private int posicion;
	private Game game;
	

	public SlayerList(Game game) {
		this.game = game;
		this.slayers =  new Slayer[((game.getLevel().getDim_x())*(game.getLevel().getDim_y()))];
		posicion = -1;
		
	}
	
//============================================================================
	//GETTERS
	


//============================================================================
//UTILIDADES POSICION/CORDENADAS

	public boolean ifSlayer(int cx, int cy) {
		boolean ok=false;
		for(int i=0;i<posicion+1;i++) {
			if (slayers[i].getX()==cx && slayers[i].getY()==cy) {
				ok=true;
			}
		}
		return ok;
	}
	public int posSlayer(int cx, int cy) {
		int pos=0;
		for(int i=0;i<posicion+1;i++) {
			if (slayers[i].getX()==cx && slayers[i].getY()==cy) {
				pos=i;
			}
		}
		return pos;
	}
//=============================================================================
	//AÑADIR Y BORRAR OBJETOS
	public void add(int x, int y) {
		Slayer newSlayer= new Slayer(3,x,y,game);
		posicion++;
		slayers[posicion]=newSlayer;
		
	}
	public void remvDeath() {
		for(int i=0;i<(posicion+1);i++) {
			if(slayers[i].getLife()<=0) {
				for(int j=i;j<posicion;j++) {
					slayers[j]=slayers[j+1];
				}
				i--;
				posicion--;
			}
		}
	}
//==============================================================================
	//UTILIDADES DE VIDA
	public int returnLife(int x,int y) {
		int life =  slayers[posSlayer(x,y)].getLife();
		return life;
	}
	
	public void restLife(int x, int y) {
		int pos  = posSlayer(x,y);
		slayers[pos].restLife();
	}
	public void attack() {
		for(int i=0;i<posicion+1;i++) {
			slayers[i].attackSlayer();
		}
	}
//==============================================================================
}