public class ProductionTile {
	protected enum Terrain {
		DESERT, SWAMP, FOREST, FERTILE, HILLS, MOUNTAINS
	};

	Terrain type;
	protected int[] resource;
	protected int quantity;

	public ProductionTile(ProductionTile.Terrain terrain, int food, int wood,
			int gold, int favor, int quantity) {
		resource = new int[4];
		type = terrain;
		resource[0] = food;
		resource[1] = wood;
		resource[2] = gold;
		resource[3] = favor;
		this.quantity = quantity;
	}
}