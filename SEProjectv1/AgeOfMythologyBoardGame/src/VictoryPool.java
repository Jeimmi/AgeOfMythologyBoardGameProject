
public class VictoryPool {
	
	protected enum Strategy{WONDER, MOST_BUILDINGS, GREATEST_ARMY, LAST_BATTLE};
	protected Strategy strategy;
	protected int value;
	protected String description;
	
	/**
	 * Constructor for the VictoryBin
	 * 
	 * @param strategy
	 */
	public VictoryPool(VictoryPool.Strategy strategy){
		this.strategy = strategy;
		switch(strategy){
		case WONDER:
			description = "The first player to build The Wonder.";
			break;
		case MOST_BUILDINGS:
			description = "The player with the most buildings.";
			break;
		case GREATEST_ARMY:
			description = "The player with the most units.";
		case LAST_BATTLE:
			description = "The player who won the last battle.";
		}
		value = 0;
	}
	
	/**
	 * Override Object toString() to be user friendly
	 */
	public String toString(){
		return "" + strategy + ": " + description + " (" + value + ")";
	}
}
