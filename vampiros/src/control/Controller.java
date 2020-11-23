package control;

import java.util.Scanner;   

import logic.Game;
import view.GamePrinter;

public class Controller {

	public final String prompt = "Command > ";
	public static final String helpMsg = String.format(
			"Available commands:%n" +
			"[a]dd <x> <y>: add a slayer in position x, y%n" +
			"[h]elp: show this help%n" + 
			"[r]eset: reset game%n" + 
			"[e]xit: exit game%n"+ 
			"[n]one | []: update%n");
	
	public static final String unknownCommandMsg = String.format("Unknown command");
	public static final String invalidCommandMsg = String.format("Invalid command");
	public static final String invalidPositionMsg = String.format("Invalid position");

    private Game game;
    private Scanner scanner;
    private boolean exit;
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
	    this.exit=false;
	    
    }
    
    public void  printGame() {
   	 System.out.println(game);
   }

    public void run() {
    	

    	
    	while(!exit) {
	//============================================================================================================================
    		//PINTA TABLERO
        	 	paint();
    			game.ciclo++;
    			boolean validComand = false;
	 //============================================================================================================================
    			//MENU DEL JUGADOR
    			while(!validComand) {
            	String line= scanner.nextLine();
            	String[] words = line.toLowerCase().trim().split("\\s+");
            	System.out.println("Command >");
            	
            		switch(words[0]){
            		
            		case "add":
            		case "a":
            				if(words.length<4&&words.length>1) {
            					int x = Integer.parseInt(words[1]);
                        		int y = Integer.parseInt(words[2]);
                        		System.out.println("[DEBUG] Executing: "+ words[0]+" "+ x+ " "+ y);
                        		if(game.inTab(x,y)&&game.getBoard().emptycell(x, y)) {
                        			if(game.getPlayer().enoughtMoney()) {
                        				game.addSlayer(x,y);
                        				game.addMoney();
                            			validComand = true;
                        			}else {
                        				System.out.println("[ERROR]: Not enough coins");
                        				validComand = false;
                        			}
                        			
                        		}else {
                        			System.out.println("[ERROR]: Invalid position");
                        			validComand = false;
                        		}
                    			
            				}else {
            					System.out.println(helpMsg);
            					validComand = false;
            				}
            			
            			break;
            		case "help":
            		case "h":
            			System.out.println("[DEBUG] Executing: "+ words[0]);
                    	System.out.println(helpMsg);
                    	validComand = false;
            			break;
            		case "reset":
            		case "r":
            			System.out.println("[DEBUG] Executing: "+ words[0]);
            			game = new Game(game.getSeed(),game.getLevel());
            			validComand = true;
            			break;
            		case "exit":
            		case "e":
            			System.out.println("[DEBUG] Executing: "+ words[0]);
            			System.out.println("[Game over] Nobody wins...");
            			
            			exit= true;
            			validComand = true;
            			break;
            		case "none":
            		case "n":
            		case "":
            			System.out.println("[DEBUG] Executing: "+ words[0]);
            			validComand = true;
            			game.addMoney();
            			break;
            		default:
            			System.out.println("[DEBUG] Executing: "+ words[0]);
            			validComand = false;
            			System.out.println("[ERROR]: Unknown command");
            			break;
            		
            	}
            	}
            	
    //============================================================================================================================
        		//AVANZAN LOS VAMPIROS
        		game.goVampires();

    //============================================================================================================================
            	//ATACAN LOS CAZADORES
            	game.slayersAttack();
            	//ATACAN LOS VAMPIROS
            	game.vampiresAttack();

    //============================================================================================================================
        		//SE AÑADEN LOS VAMPIROS
        		game.addVampire();

    //============================================================================================================================
            	//SE BORRAN OBJETOS
        		game.remvDeaths();
    //============================================================================================================================
            	//COMPROBAR FIN DEL JUEGO
        		endGame();
            	
            	
   
    	}
    	
    }
    public void endGame() {
    	switch(game.endGame()) {
		case 1:
			game.ciclo--;
			paint();
			System.out.println("[Game over] Player wins");
			exit=true;
			break;
		case 2:
			game.ciclo--;
			paint();
			System.out.println("[Game over] Vampires win!");
			exit=true;
		}
    }
    public void paint() {
    	//SE PINTA EL BOARD
		String ciclo=Integer.toString(game.ciclo);
		String money = Integer.toString(game.getPlayer().getMoney());
		GamePrinter printer = new GamePrinter(this.game,this.game.getLevel().getDim_x(),this.game.getLevel().getDim_y());
    	String printboard = printer.toString();
    	String infgeneral = " Number of cycles: " + ciclo +
    						"\n Coins: "+ money + 
    						"\n Remaining vampires: " + (game.getLevel().getNumberOfVampires()-game.getBoard().getVampirelist().numberOfVampires())+
    						"\n Vampires on the board: " + (game.getBoard().getVampirelist().getPosicion()+1)+"\n";
    	System.out.println(infgeneral + printboard);
    }

}