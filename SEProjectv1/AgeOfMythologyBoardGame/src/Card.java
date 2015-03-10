/**
 * @author Lyndon Kondratczyk
 * @version 3/9/15
 * 
 * The template for an AoM Card attributes
 */
import java.awt.image.BufferedImage;

public abstract class Card {
	protected BufferedImage front;
	protected BufferedImage back;
	protected boolean permanent;
	protected String name;
	protected String firstDescription;
	protected String secondDescription;
	protected int value;
	protected int cost;

	/**
	 * The constructor for an AoM Card
	 * 
	 * @param front The front image of the card
	 * @param back The back image of the card
	 * @param permanent True if the card is permanent
	 * @param name The name of the Card
	 * @param firstDescription the secondary Card description
	 * @param secondDescription The secondary Card description
	 * @param value The value of the Card
	 * @param cost The cost to play the Card
	 */
	public Card(BufferedImage front, BufferedImage back,
			boolean permanent, String name, String firstDescription,
			String secondDescription, int value, int cost) {
		this.front = front;
		this.back = back;
		this.permanent = permanent;
		this.name = name;
		this.firstDescription = firstDescription;
		this.secondDescription = secondDescription;
		this.value = value;
		this.cost = cost;
	}
	/**
	 * Function for adding 2 resource arrays. The destination is overwritten
	 * 
	 * @param toAdd Source being added to destination
	 * @param addedTo Destination being added to Source and updated
	 */
	public void addResources(int[] toAdd, int[] addedTo){
		for(int i = 0; ((i < toAdd.length) && (i < addedTo.length)); i++){
			addedTo[i] = addedTo[i] + toAdd[i];
		}
	}
	
	/**
	 * Function for subtracting 2 resource arrays. The destination is overwritten
	 * 
	 * @param toSub Source to subtract from destination
	 * @param subbedFrom Destination to be subtracted from adn updated
	 */
	public void subtractResources(int[] toSub, int[] subbedFrom){
		for(int i = 0; ((i < toSub.length) && (i < subbedFrom.length)); i++){
			subbedFrom[i] = subbedFrom[i] - toSub[i];
		}
	}
	
	/**
	 * Takes a building selected by the activePlayer and adds it to his/her
	 * city, removes it from the building pool, and tracks the refund
	 * 
	 * @param game The game being updated 
	 * @param selection The Building the Player selected
	 * @param refund The total refund available to the Player
	 * 
	 * @return The amount of the discount used in the transaction
	 */
	public int addBuilding(Game game, Building selection, int[] refund){
		int discountUsed = 0;
		for(int i = 0; i < 4; i ++){
			if(game.activePlayer.wallet[i] < selection.cost[i]){
				int temp = selection.cost[i] - game.activePlayer.wallet[i];
				discountUsed = discountUsed + temp;
				game.activePlayer.wallet[i] = game.activePlayer.wallet[i] - selection.cost[i] + temp;
				game.bank[i] = game.bank[i] + selection.cost[i] - discountUsed;
				refund[i] = refund[i] + refund[i] - discountUsed;	
			}
			else{
				game.activePlayer.wallet[i] = game.activePlayer.wallet[i] - selection.cost[i];
				game.bank[i] = game.bank[i] + selection.cost[i];
				refund[i] = refund[i] + refund[i];
			}
		}
		game.activePlayer.buildingPool.remove(selection);
		game.activePlayer.city.add(selection);
		return discountUsed;
	}
	
	@Override
	public String toString(){
		if(this.secondDescription.equals("")){
			return ("|" + name + " : " + firstDescription + "|");
		}
		else{
			return ("|" + name + " : " + firstDescription + " : " + secondDescription +
					" : Cost - " + cost + "Favor|" );
		}
	}

	public abstract void execute(Game game);
}
