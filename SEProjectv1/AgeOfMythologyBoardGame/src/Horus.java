/**
 * @author Lyndon Kondratczyk
 * @version 3/12/15
 * 
 * The god card Horus, which is a Build Card with the added feature of allowing
 * an activePlayer to destroy one of any Player's building
 */
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Horus extends  BuildCard{
	/**
	 * The constructor for the god card Horus
	 * 
     * @param front The Card's front image
     * @param back The Card's back image
     * @param firstDescription The main Card description
     * @param secondDescription The secondary Card description
     * @param value The max number of builds
     * @param cost The cost to play the Card
	 */
		public Horus(BufferedImage front, BufferedImage back, 
				String firstDescription, String secondDescription, 
				int value, int cost){
		super(front, back, false, "Horus", firstDescription,	
				secondDescription, value, cost);
	}
	
	/**
	 * Overrides execute in Building card to allow special god ability
	 */
	public void execute(Game game){
		int numberOfPlayers = 3;
		Player playerDestroy;
		Building cityDestroy;
		ArrayList<String> tollMessage = new ArrayList<String>();
		ArrayList<Player> playerTargets = new ArrayList<Player>();
		ArrayList<String> targetNames = new ArrayList<String>();
		UserInterface<String> handleToll = new UserInterface<String>();
		UserInterface<Player> selectPlayerTargets = new UserInterface<Player>();
		UserInterface<String> displayTargetNames = new UserInterface<String>();
		UserInterface<Building> handleTargetCity = new UserInterface<Building>();
		
		tollMessage.add("Pay one favor to prove you are worthy.");
		handleToll.provideMenuOptions("Pay tribute to your god?", game, 
				tollMessage, "Run away in fear...");
		
		if(handleToll.getPlayerSelection(game, tollMessage, true) != null){
			if(game.activePlayer.wallet[3] < 1){
				System.out.println("The gods deem you unworthy!!!");
			}
			else{
				for(int i = 0; i < numberOfPlayers; i++){
					playerTargets.add(game.activePlayer);
					targetNames.add(game.activePlayer.name);
					game.activePlayer = game.activePlayer.next;	
				}
				game.activePlayer.wallet[3] -= 1;
				game.bank[3] += 1;
				displayTargetNames.provideMenuOptions(
						"Select the victim of a cataclysm.", game, targetNames, 
						"Demand mercy for your tribute");
				playerDestroy = selectPlayerTargets.getPlayerSelection(
						game, playerTargets, true);
				if(playerDestroy == null){
					System.out.println("The gods have answered your prayers.");			
				}
				else{
					handleTargetCity.provideMenuOptions("Which of " + 
							playerDestroy.name + "'s cites"	+ 
							" shall suffer my wrath?", game, playerDestroy.city,
							"Demand mercy for your tribute");
					cityDestroy = handleTargetCity.getPlayerSelection(game, playerDestroy.city, true);
					playerDestroy.buildingPool.add(cityDestroy);
					playerDestroy.city.remove(cityDestroy);
					if(cityDestroy == null){
						System.out.println("The gods have answered your prayers.");	
					}
				}
			}
		}
		
		
		
		displayTargetNames.provideMenuOptions("Select a player to destroy their building: ", 
				game, targetNames, "Destroy nothing...");
		
		
		
		
		super.execute(game);
	}
}
