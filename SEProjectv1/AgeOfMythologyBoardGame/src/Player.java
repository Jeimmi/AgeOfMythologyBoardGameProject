import java.util.ArrayList;


public class Player {
	private BuildingPool buildingTiles;
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<ProductionTile> production = new ArrayList<ProductionTile>();
	private ArrayList<Building> city;
	private ArrayList<Card> randomDeck;
	private ArrayList<Card> usedRandomDeck;
	private ArrayList<Card> permanentDeck;
	
	public Player() {
		hand = new ArrayList<Card>();
		production = new ArrayList<ProductionTile>();
		city = new ArrayList<Building>();
		randomDeck = new ArrayList<Card>();
		permanentDeck = new ArrayList<Card>();
	}
	public void shuffle(ArrayList<Card> deck){
		ArrayList<Card> tempDeck = new ArrayList<Card>();
		int temp;
	}
	
	public void drawRandomCard() {
		if (randomDeck.size() == 0) {
			for (int i = 0; i < usedRandomDeck.size(); i++) {
				randomDeck.add(randomDeck.get(i));
			}
			usedRandomDeck.
		}
	}
}
