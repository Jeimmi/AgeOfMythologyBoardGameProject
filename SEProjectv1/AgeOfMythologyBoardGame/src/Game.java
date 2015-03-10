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
		player2 = new Player("Player2", true);
		player3 = new Player("Player3", true);
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
	 * Manages the Draw Cards phase in the turn sequence, as well 
	 * as managing the player interaction during permanent deck selection 
	 */
	public void drawCards(){
		int handSize;
		int numberOfPlayers = 3;
		UserInterface<Card> drawDeck = new UserInterface<Card>(); 
		
		for(int i = 0; i < numberOfPlayers; i ++){
			Card choice = activePlayer.permanentDeck.get(0);
			switch(activePlayer.playerAge){
			case ARCHAIC:
				handSize = 4; 
				break;
			case CLASSICAL:
				handSize = 5;
				break;
			case HERIOC:
				handSize = 6;
				break;
			case MYTHIC:
				handSize = 7;
				break;
			default:
				handSize = 4;
				break;
			}
			do{
				drawDeck.provideMenuOptions("Select a permanent card or pass", activePlayer, 
						activePlayer.permanentDeck, "Draw " + (handSize - activePlayer.hand.size()) +
								" cards from the random deck");
				choice = drawDeck.getPlayerSelection(activePlayer, activePlayer.permanentDeck, true);
				if(choice != null){
					activePlayer.hand.add(choice);	
					activePlayer.permanentDeck.remove(choice);	
				}
			}
			while((activePlayer.hand.size() <= handSize) && (choice != null) && (activePlayer.randomDeck.size() > 1));
			while(activePlayer.hand.size() < handSize){
				if(activePlayer.randomDeck.size() == 0){
					activePlayer.randomDeck = activePlayer.usedRandomDeck;
					activePlayer.usedRandomDeck = new ArrayList<Card>();
				}
				RandomSelection<Card> selector = new RandomSelection<Card>(activePlayer.randomDeck);
				activePlayer.hand.add(selector.getRandomFromList(true));
			}
			activePlayer = activePlayer.next;
		}
	}
	
	public void setVictoryPoint(){
		
	}
	
	public void handleSpoilage(){
		
	}
	
	/**
	 * Manages the Action Card phase in the game's turn sequence
	 * @param ui The user interface managing board display so a player ca
	 */
	public void actionCardPhase(){
		int numberOfPlayers = 3;
		for(int i = 0; i < numberOfPlayers*3; i++){
			playActionCard();
			activePlayer= activePlayer.next;
		}
	}
	
	/**
	 * Handles the Player's interaction of Card selection
	 */
	public void playActionCard(){
		UserInterface<Card> ui = new UserInterface<Card>();
		ui.provideMenuOptions("Select an Action Card to play: ", activePlayer, activePlayer.hand, null);
		Card selection = ui.getPlayerSelection(activePlayer, activePlayer.hand, false);
		activePlayer.hand.remove(selection);
		selection.execute(this);
	}
	
	/**
	 * Executes the turn sequences managed by the game
	 */
	public void run(){
		InitializeGame.initialize(this);
		UserInterface<Game> ui = new UserInterface<Game>();
		drawCards();
		ui.displayGamestate("Current Gamestate:", this);
		actionCardPhase();
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
