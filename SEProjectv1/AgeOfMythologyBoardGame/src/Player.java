/**
 * @author Lyndon Kondratczyk
 * @version 3/9/15
 * 
 * The template for an AoM Player
 */
import java.util.ArrayList;

public class Player {
	protected String name;
	protected enum Age{ARCHAIC, CLASSICAL, HEROIC, MYTHIC}
	protected Age playerAge;
	protected enum Race{EGYPTIAN, GREEK, NORSE}
	protected Race playerRace;
	protected ArrayList<ProductionTile.Terrain> terrainAvailable;
	protected ArrayList<Card> hand;
	protected ArrayList<ProductionTile> production;
	protected ArrayList<Building> city;
	protected ArrayList<Card> randomDeck;
	protected ArrayList<Card> usedRandomDeck;
	protected ArrayList<Card> permanentDeck;
	protected int[] wallet;
	protected ArrayList<Building> buildingPool;
	protected Player next;
	protected boolean human;
	
	/**
	 * Constructor for a Player
	 * 
	 * @param name The name of a Player
	 * @param human True if the Player is human, false if AI
	 */
	public Player(String name, boolean human) {
		this.name = name;
		playerAge = Age.ARCHAIC;
		terrainAvailable = new ArrayList<ProductionTile.Terrain>();
		hand = new ArrayList<Card>();
		production = new ArrayList<ProductionTile>();
		city = new ArrayList<Building>();
		randomDeck = new ArrayList<Card>();
		usedRandomDeck = new ArrayList<Card>();
		permanentDeck = new ArrayList<Card>();
		wallet = new int [5];
		buildingPool = new ArrayList<Building>();
		this.human = human;
	}
	

}
