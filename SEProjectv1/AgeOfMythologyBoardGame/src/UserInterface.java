/**
 * @author Lyndon Kondratczyk
 * @version 3/9/15
 * 
 * A tool that creates a menu for displaying and selecting entries from a 
 * generic ArrayList
 */
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface <T>{
	
	/**
	 * Prints a list of menu options fed as an ArrayList
	 * 
	 * @param prompt The text label for the menu option
	 * @param player The Player making the selection
	 * @param options The ArrayList containing the menu options
	 * @param passOption The text label for the "pass" options if available
	 */
	public void provideMenuOptions(String prompt, Game game, ArrayList<T> options, String passOption){
		if(game.activePlayer.human){
			displayGamestate("CURRENT GAMESTATE...", game);
			System.out.println("\n\n" + game.activePlayer.name + " : " + prompt);
			for(int i = 0; i < options.size(); i++){
				System.out.println("(" + i + ")" + options.get(i).toString());
				if((i == (options.size() - 1) && passOption != null)){
					System.out.println("(" + (i + 1) + ") " + passOption);
				}
			}
			if((options.size() == 0) && (!(passOption.equals(null)))){
				System.out.println("(0) " + passOption);
			}
		}
		else{
			displayGamestate("Current gamestate...", game);
		}
	}
	
	/**
	 * Gets and returns the content of an ArrayList selected by a Player
	 * 
	 * @param player The Player making the selection
	 * @param options The list of menu options
	 * @param passOption True if the option to "pass" is available
	 * @return The player's selected object
	 */
	public T getPlayerSelection(Game game, ArrayList<T> options, boolean passOption){
		int passAvailable = 0;
		if(passOption){
			passAvailable ++;
		}
		if(game.activePlayer.human){
			Scanner input = new Scanner(System.in);
			int selection = -99;
	
			while((selection < 0) || (selection >= options.size() + passAvailable)){
				if(selection != -99){
					System.out.println("Invalid selection, please try again.");
				}
				selection = input.nextInt();
			}
			if(selection == options.size()){
				//input.close();
				return null;
			}
			//input.close();
			return options.get(selection);
		}
		else if(options.size() > 0){
			displayGamestate("Current gamestate...", game);
			RandomSelection<T> optionsList = new RandomSelection<T>();
			T selection = optionsList.getRandomFromList(options, false, passAvailable);
			return selection;
		}
		else{
			return null;
		}
	}
	
	/**
	 * Displays a reader-friendly list using toString given a generic ArrayList
	 * 
	 * @param title The label for the list
	 * @param genericList The contents of the list
	 */
	public void displayList(String title, ArrayList<T> genericList){
		System.out.print(title);
		for(int i = 0; i < genericList.size(); i++){
			System.out.print(genericList.get(i));
			if(i < genericList.size() - 1)
				System.out.print(", ");
		}
		System.out.println("");
	}
	

	/**
	 * Prints the current state of a Game
	 * 
	 * @param title The title of the printout
	 * @param game The game being printed
	 */
	public void displayGamestate(String title, Game game){
		
		Player playerPointer;
		playerPointer = game.activePlayer;
		UserInterface<ProductionTile.Terrain> terrainAvailable = 
				new UserInterface<ProductionTile.Terrain>();
		UserInterface<Card> hand = new UserInterface<Card>();
		UserInterface<ProductionTile> productionArea = new UserInterface<ProductionTile>();
		UserInterface<Building> cityArea = new UserInterface<Building>();
		UserInterface<VictoryPool> victory = new UserInterface<VictoryPool>();
	 
		System.out.println();
		System.out.println(title);
		game.displayFunds("Bank: ", game.bank);
		victory.displayList("Victory Pools: ", game.victory);
		for(int i = 0; i < 3; i++){
			switch(i){
			case 0:
				System.out.println("Current Player - " + playerPointer.name);
				break;
			case 1:
				System.out.println("Next Player - " + playerPointer.name);
				break;
			default:
				System.out.println("Player - " + playerPointer.name);
				break;
			}
			System.out.println((playerPointer.human)?"(Human)":"(AI)");
			System.out.println(playerPointer.playerRace);
			System.out.println(playerPointer.playerAge);
			game.displayFunds("Wallet: ", playerPointer.wallet);
			terrainAvailable.displayList("Available Terrain: ", playerPointer.terrainAvailable);
			hand.displayList("Hand: ", playerPointer.hand);
			System.out.println("Number of cards in rand deck :::: " + playerPointer.randomDeck.size());
			System.out.println("Number of cards in used rand deck :::: " + playerPointer.usedRandomDeck.size());
			productionArea.displayList("Production Area: ", playerPointer.production);
			cityArea.displayList("City Area: ", playerPointer.city);
			cityArea.displayList("Available to build: ", playerPointer.buildingPool);
			
			playerPointer = playerPointer.next;
			
			System.out.println();
		}		
	}
}
