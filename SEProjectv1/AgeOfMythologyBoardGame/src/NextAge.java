import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class NextAge extends Card{
	boolean discount;
	
	/**
	 * The constructor for a Next Age Card
	 * 
     * @param front The Card's front image
     * @param back The Card's back image
     * @param permanent True if Card is permanent
     * @param name The Card name
     * @param firstDescription The main Card description
     * @param secondDescription The secondary Card description
     * @param cost The cost to play the Card
	 * @param discount The discount for the Card
	 */
	public NextAge(BufferedImage front, BufferedImage back,
			boolean permanent, String name, String firstDescription,
			String secondDescription, int cost, boolean discount) {
		super(front, back, permanent, name, firstDescription,
				secondDescription, 0, cost);
		this.discount = discount;
	}
	
	
	/**
	 * Uses the Player's current age to determine the cost to pass to canAfford
	 *  
	 * @param game The game being altered
	 * @return 0 if the Player can't afford it/ is at the max age. Returns the 
	 * cost of each resource otherwise
	 */
	public int canAffordNextAge(Game game){
		int cost;
		switch(game.activePlayer.playerAge){
		case ARCHAIC:
			cost = 4;
			break;
		case CLASSICAL: 
			cost = 5;
			break;
		case HEROIC:
			cost = 6;
			break;
		default:
			cost = 0;
			break;
		}
		
		cost -= (discount)?1:0;
		
		if(cost > 0){
			for(int i = 0; i < 4; i++){
				if(game.activePlayer.wallet[i] < cost){
					System.out.println("The gods think you are too poor to grant "
							+ "you a next age.");     
					return 0;				
				}
			}
			return cost;
		}
		else{
			System.out.println("The gods think you are too advanced to grant "
					+ "you a next age.");
			return 0;
		}
	}
	
	/**
	 * Executes the Card's effect on the Game
	 * 
	 * @param game The game being affected by the card
	 */
	public void execute(Game game){
		int cost = canAffordNextAge(game);
		if(cost > 0){
			ArrayList<String> useCard = new ArrayList<String>();
			UserInterface<String> manageCardUse = 
					new UserInterface<String>();
			String choice;
			useCard.add("Pay " + cost + 
					" of each resource to advace to the next age.");
			manageCardUse.provideMenuOptions("Advance to the next age? ", 
					game, useCard, "Burn this card instead.");
			choice = manageCardUse.getPlayerSelection(game, useCard, true);
			if(choice != null){
				for(int i = 0; i < 4; i++){
					game.activePlayer.wallet[i] -= cost;
					game.bank[i] += cost;
				}
				switch(game.activePlayer.playerAge){
				case ARCHAIC:
					game.activePlayer.playerAge = Player.Age.CLASSICAL;
					break;
				case CLASSICAL:
					game.activePlayer.playerAge = Player.Age.HEROIC;
					break;
				case HEROIC:
					game.activePlayer.playerAge = Player.Age.MYTHIC;
					game.activePlayer.buildingPool.add(new Building(Building.Type.THE_WONDER, 10,10,10,10));
					System.out.println("You can now build The Wonder.");
					break;
				default:
					break;
				}
			}
		}
	}
}