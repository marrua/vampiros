package logic;

import java.util.Random;
import objects.Player;
import objects.GameObjectBoard;

public class Game {
	//ATRIBUTOS
	public int ciclo;
	private Level level;
	private Long seed;
	GameObjectBoard board;
	Player player = new Player(50);
	Random r1;
	private Game game;
	//CONSTRUCTOR
	public Game(Long seed,Level level) {
		
		this.level = level;
		this.seed = seed;
		this.ciclo=0;
		r1=new Random(seed);
		this.board = new GameObjectBoard(this);
	}
//====================================================================================================
	//GETTERS
	public GameObjectBoard getBoard() {
		return board;
	}
	public Player getPlayer() {
		return player;
	}
	public Level getLevel() {
		return level;
	}
	public Long getSeed() {
		return seed;
	}
//======================================================================================================
	//AÑADIR OBJETOS
	public void addSlayer(int x, int y) {
		board.addSlayer(x, y);
		player.setMoney(player.getMoney()-50);
	}

	public void addVampire() {
		if(!board.maxVampires(level.getNumberOfVampires())) {
			double randNumber=r1.nextDouble();
			if(randNumber<level.getVampireFrequency()) {
				int randFile=r1.nextInt((level.getDim_y()));		
				board.addVampire((level.getDim_x()-1), randFile);
			}
		}
	}
//=======================================================================================================
	//UTILIDADES TABLERO
	public boolean inTab(int x, int y) {
		boolean inTab = false;
		if(x<level.getDim_x()&&x>-1  && y<level.getDim_y()&&y>-1&&x<(level.getDim_x()-1)) {
			inTab= true;
		}
		return inTab;
			
	}
	public int element(int x, int y) {
		int element= board.element(x, y);
		return element;
	}
//========================================================================================================
	//UTILIDADES PLAYER
	public void addMoney() {
		float randMoney = r1.nextFloat();
		player.addMoney(randMoney);
	}
//========================================================================================================
	//ATAQUES
	public void vampiresAttack() {
		board.vampiresAttack();
	}
	public void slayersAttack() {
		board.slayersAttack();
	}
	public void reciveAtackVamp(int x, int y) {
		board.reciveAtackVamp(x,y);
	}
	public void reciveAtackSlayer(int x, int y) {
		board.reciveAtackSlayer(x,y);
	}
//========================================================================================================
	//MOVIMIENTO VAMPIROS
	public void goVampires() {
		board.goVampires(level);
	}
//========================================================================================================
	//BORRADO DE OBJETOS
	public void remvDeaths() {
		board.getSlayerlist().remvDeath();
		board.getVampirelist().remvDeath();
	}
//========================================================================================================
	//FINAL DEL JUEGO
	public int endGame() {
		int endGame=0;
		if((level.getNumberOfVampires()==board.getVampirelist().numberOfVampires())&&(board.getVampirelist().finish())) {
			endGame = 1;
		}
		for(int i = 0;i<level.getDim_y();i++) {
			if(board.getVampirelist().ifVampires(-1, i)&&endGame!=1) {
				endGame = 2;
				
			}
		}
		return endGame;
	}
}
//==============================================================================================================










