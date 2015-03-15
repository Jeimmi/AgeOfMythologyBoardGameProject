/**
 * @author Lyndon Kondratczyk
 * @version 3/12/15
 * 
 * The god card Nepthys, which is a Build Card with the added feature of allowing
 * an activePlayer to reduce the cost of buildings purchased with the card by
 * two of its resource costs
 */

import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Nephthys extends BuildCard{
	
	/**
	 * The constructor for the god card Nephthys
	 * 
     * @param front The Card's front image
     * @param back The Card's back image
     * @param firstDescription The main Card description
     * @param secondDescription The secondary Card description
     * @param value The max number of builds
     * @param cost The cost to play the Card
	 */
	public Nephthys(BufferedImage front, BufferedImage back, 
			String firstDescription, String secondDescription, 
			int value, int cost){
		super(front, back, false, "Nephthys", firstDescription,	
			secondDescription, value,cost);
	}
	
	/**
	 * Overrides execute in Building card to allow special god ability
	 */
	public void execute(Game game){
		ArrayList<String> tollMessage = new ArrayList<String>();
		UserInterface<String> handleToll = new UserInterface<String>();
		
		tollMessage.add("Pay" + cost + "favor to prove you are worthy.");
		handleToll.provideMenuOptions("Pay tribute to your god?", game, 
				tollMessage, "Run away in fear...");
		if(handleToll.getPlayerSelection(game, tollMessage, true) != null){
			if(game.activePlayer.wallet[3] < 2){
				System.out.println("The gods deem you unworthy!!!");
			}
			else{
				game.activePlayer.wallet[3] -= 2;
				game.bank[3] += 2;
				System.out.println("The gods have answered your prayers.");	
			}
		}
		
		if(game.activePlayer.city.size() < 16){
			UserInterface<Building> buildingSelect = new UserInterface<Building>();
			UserInterface<String> refundSelect = new UserInterface<String>(); 
			ArrayList<String> resources = new ArrayList<String>();
			resources.add("food");
			resources.add("wood");
			resources.add("gold");
			resources.add("favor");
			int discount = 2;
			for(int i = 0; i < game.activePlayer.city.size(); i++){
				if((game.activePlayer.city.get(i)).type == Building.Type.QUARRY){
					discount = 3;
				}
			}
			int builds = value;
			
			for(int i = 0; i < builds; i++){
				int[] refundAvailable = {0, 0, 0, 0};
				int discountUsed = 0;
				ArrayList<Building> options = populateAffordable(game.activePlayer, discount);
				buildingSelect.provideMenuOptions("Please select a building to build from the following:", 
						game, options, "Pass remainder of turn");
				Building selection = buildingSelect.getPlayerSelection(game, options, true);
				if(selection == null){
					i = builds;
				}
				else{
					discountUsed = discountUsed + buildBuilding(game, selection, refundAvailable);
				}
				for(int j = 0; j < discount - discountUsed; j++){
					ArrayList<String> refundOptions = new ArrayList<String>();
					for(int k = 0; k < 4; k++){
						if(refundAvailable[k] > 0){
							refundOptions.add(resources.get(k));
						}
					}
					if(refundOptions.size() > 0){
						refundSelect.provideMenuOptions("Select a refund: ", game,
								refundOptions, null);
						String refund = refundSelect.getPlayerSelection(
								game, refundOptions, false);
						switch(refund){
						case("food"):
							System.out.println();
							game.activePlayer.wallet[0] += 1;
							game.bank[0] -= 1;
							refundAvailable[0] -= 1;
							break;
						case("wood"):
							game.activePlayer.wallet[1] += 1;
							game.bank[1] -= 1;
							refundAvailable[1] -= 1;
							break;
						case("gold"):
							game.activePlayer.wallet[2] += 1;
							game.bank[2] -= 1;
							refundAvailable[2] -= 1;
							break;
						case("favor"):
							game.activePlayer.wallet[3] += 1;
							game.bank[3] -= 1;
							refundAvailable[3] -= 1;
							break;
						default:
								break;
						}
					}
				}
			}
		}
		// TODO Auto-generated method stub
	}
}