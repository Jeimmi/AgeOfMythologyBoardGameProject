/**
 * @author Lyndon Kondratczyk
 * @version 3/9/15
 * 
 * The template for an AoM Production Tiles
 */
public class ProductionTile {
	protected enum Terrain {
		DESERT, SWAMP, FOREST, FERTILE, HILLS, MOUNTAINS
	};

	Terrain type;
	protected int[] resource;
	protected int quantity;
	boolean hasVillager;

	/**
	 * The constructor for a ProductionTile
	 * 
	 * @param terrain The Terrain type
	 * @param food The food benefit
	 * @param wood The wood benefit
	 * @param gold The gold benefit
	 * @param favor The favor benefit
	 * @param quantity The number of like template in the starting productionPool
	 */
	public ProductionTile(ProductionTile.Terrain terrain, int food, int wood,
			int gold, int favor, int quantity) {
		resource = new int[4];
		type = terrain;
		resource[0] = food;
		resource[1] = wood;
		resource[2] = gold;
		resource[3] = favor;
		this.quantity = quantity;
		hasVillager = false;
	}
	
	/**
	 * Converts the ProductionTile into a String for printing on a menu
	 */
	@Override
	public String toString(){
		String resources = "";
		String villager = "";
		for(int i = 0; i < resource.length; i ++){
			if(resource[i] > 0){
				resources = resources + resource[i];
				switch(i){
					case 0: 
						resources = resources + " Food";
						break;
					case 1: 
						resources = resources + " Wood";
						break;
					case 2: 
						resources = resources + " Gold";
						break;
					case 3: 
						resources = resources + " Favor";
						break;
					default: break;
				}
			}
		}
		if(this.hasVillager == true)
			villager = "(villager)";
		return ("" + type + " " + villager + " : " + resources);
	}
}
