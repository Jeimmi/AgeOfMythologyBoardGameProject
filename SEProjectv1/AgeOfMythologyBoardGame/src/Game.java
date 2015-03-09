/**
 * @author Lyndon Kondratczyk
 * @version 3/9/15
 * 
 * The top level module for running the board game engine
 * and implementing the user interface
 */
import java.util.ArrayList;
public class Game implements InitializeGame{
	
	protected Player player1;
	protected Player player2;
	protected Player player3;	
	protected Player activePlayer;
	protected ArrayList<ProductionTile> productionPool;
	protected int[] bank;
	
	/**
	 * The default constructor for a game 
	 */
	Game() {
		player1 = new Player("Player1", true);
		player2 = new Player("Player2", false);
		player3 = new Player("Player3", false);
		productionPool  = new ArrayList<ProductionTile>(); 
		bank = new int[5];
	}
	
	/**
	 * Displays the relevant funds in a readable format
	 * 
	 * @param title Label for the funds to be displayed on an interface
	 * @param financial An integer array representing funds
	 */
	public void displayFunds(String title, int[] financial){
		System.out.print(title + " : ");
		for(int i = 0; i < financial.length; i++){
			switch(i){
				case 0: 
					System.out.print(financial[i] + " Food");
					break;
				case 1: 
					System.out.print(financial[i] + " Wood");
					break;
				case 2: 
					System.out.print(financial[i] + " Gold");
					break;
				case 3: 
					System.out.print(financial[i] + " Favor");
					break;
				case 4: 
					System.out.print(financial[i] + " Victory");
					break;
				default: break;
			}
			if(i < financial.length - 1)
				System.out.print(" , ");
		}
		System.out.println("");
	}
	
	/**
	 * Executes the turn sequences managed by the game
	 */
	public void run(){
		InitializeGame.initialize(this);
		UserInterface<Game> ui = new UserInterface<Game>();
		ui.displayGamestate("Current Gamestate:", this);
	}
	
	/**
	 * Main method for running the game
	 * 
	 * @param args
	 */
	public static void main(String [] args){
		Game game = new Game();
		game.run();		
	}
}
