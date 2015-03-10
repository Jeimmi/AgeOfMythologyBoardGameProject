/**
 * @author Lyndon Kondratczyk
 * @version 3/9/15
 * 
 * The template for an AoM Build Card attributes
 */
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BuildCard extends Card {
	
	/**
     * The constructor for creating a Build Card with image
     * 
     * @param front The Card's front image
     * @param back The Card's back image
     * @param permanent True if Card is permanent
     * @param name The Card name
     * @param firstDescription The main Card description
     * @param secondDescription The secondary Card description
     * @param value The max number of builds
     * @param cost The cost to play the Card
     */
	public BuildCard(BufferedImage front, BufferedImage back,
			boolean permanent, String firstDescription,
			String secondDescription, int value, int cost) {
		super(front, back, permanent, "Build", firstDescription,
				secondDescription, value, cost);
	}
	
	/**
	 * This function creates a list of buildings a player can build
	 * given his available building, wallet and discounts
	 * 
	 * @param player The player playing the BuildCard
	 * @param discount The player's applicable discount
	 * 
	 * @return A list of build options for the player
	 */
	public ArrayList<Building> populateAffordable(Player player, int discount) {
		ArrayList<Building> affordable = new ArrayList<Building>();
		ArrayList<Building> toCheck = new ArrayList<Building>();
		
		for(int i = 0; i < player.buildingPool.size(); i++){
			if(!(toCheck.contains(player.buildingPool.get(i)))){
				toCheck.add(player.buildingPool.get(i));
			} 
		}
		
		ArrayList<Building> tempList = new ArrayList<Building>();
		Building tempBuilding;
		for(int i = 0; i < discount; i++){
			for(int j = toCheck.size() - 1; j >= 0; j++){
				tempBuilding = new Building(toCheck.get(j));
				for(int k = 0; k < 4; k++){
					tempList.add((tempBuilding.reduce(tempBuilding, k)));
				}
			}
			discount --;
		}
		toCheck = tempList;
		
		for(int i = 0; i < toCheck.size(); i++){
			if(canAfford(player, toCheck.get(i)) && !(affordable.contains(toCheck.get(i)))){
				affordable.add(toCheck.get(i));
			}
		}
		
		return affordable;
	}
	
	/**
	 * Checks to see if a player can afford a building
	 * 
	 * @param player The player playing the Build Card
	 * @param building The Building to check
	 * 
	 * @return True if the Player can afford the Building
	 */
	public boolean canAfford(Player player, Building building){
		for(int i = 0; i < 4; i++){
			if(player.wallet[i] < building.cost[i]){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method executes a sequence of events in the Game that represent
	 * playing a BuildCard
	 * 
	 * @param game The Game being worked on by the Card
	 */
	@Override
	public void execute(Game game) {
		UserInterface<Building> ui = new UserInterface<Building>();
		int discount = 0;
		int discountUsed = 0;
		int builds = value;
		int[] refund = {0, 0, 0, 0};
		
		if(game.activePlayer.city.contains(Building.Type.QUARRY)){
			discount = 1;
		}
		
		for(int i = 0; i < builds; i++){
			ArrayList<Building> options = populateAffordable(game.activePlayer, discount);
			ui.provideMenuOptions("Please select a building to build from the following:", 
					game.activePlayer, options, "Pass remainder of turn");
			Building selection = ui.getPlayerSelection(game.activePlayer, options, true);
			if(selection == null){
				i = builds;
			}
			else{
				discountUsed = discountUsed + addBuilding(game, selection, refund);
			}
		}		
		// TODO Auto-generated method stub
	}
}