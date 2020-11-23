package objects;

public class Player {
	private int money;

	public Player(int money) {
		super();
		this.money = money;
	}
//=======================================
	//GETTERS Y SETTERS
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
//=======================================
	//UTILIDADES DINERO
	public boolean enoughtMoney() {
		boolean ok=false;
		if(money>=50) {
			ok=true;
		}
		return ok;
	}
	public void addMoney(float randMoney) {
		if(randMoney>0.5) {
			money=money+10;
		}
	}
}