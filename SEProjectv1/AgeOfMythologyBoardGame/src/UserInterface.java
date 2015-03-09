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
	public void provideMenuOptions(String prompt, Player player, ArrayList<T> options, String passOption){
		if(player.human){
			System.out.println(player.name + " : " + prompt);
			for(int i = 0; i < options.size(); i++){
				System.out.println("(" + i + ")" + options.get(i).toString());
				if(i == (options.size() - 1)){
					System.out.println("(" + (i + 1) + ") " + passOption);
				}
			}
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
	public T getPlayerSelection(Player player, ArrayList<T> options, boolean passOption){
		int passAvailable = 0;
		if(player.human = true){
			Scanner input = new Scanner(System.in);
			int selection = -1;
			if(passOption){
				passAvailable ++;
			}
	
			while((selection < 0) || (selection >= options.size() + passAvailable)){
				if(selection != -1){
					System.out.println("Invalid selection, please try again.");
				}
				selection = input.nextInt();
			}
			if(selection == options.size()){
				return null;
			}
			return options.get(selection);
		}
		else{
			RandomSelection<T> optionsList = new RandomSelection<T>(options);
			T selection = optionsList.getRandomFromList();
			optionsList.returnToList(selection);
			return selection;
		}
	}
	
	/**
	 * Displays a reader-friendly list using toString given a generic ArrayList
	 * 
	 * @param title The label for the list
	 * @param genericList The contents of the list
	 */
	public void displayList(String title, ArrayList<T> genericList){
		System.out.print(title + " : ");
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
		
		System.out.println(title);
		game.displayFunds("Bank: ", game.bank);
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
			game.displayFunds("Wallet: ", playerPointer.wallet);
			terrainAvailable.displayList("Available Terrain: ", playerPointer.terrainAvailable);
			hand.displayList("Hand: ", playerPointer.hand);
			productionArea.displayList("Prodcution Area: ", playerPointer.production);
			cityArea.displayList("City Area: ", playerPointer.city);
			
			playerPointer = playerPointer.next;
		}		
	}
}
