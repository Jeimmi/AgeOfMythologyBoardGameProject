import java.util.ArrayList;
import java.util.Random;
import java.util.Queue;
public class Game <T> {
	
	protected Player p1;
	protected Player p2;
	protected Player p3;	
	private ProductionPool productionTiles;
	private ArrayList<ProductionTile> playerList = new ArrayList<ProductionTile>();
	
	Game() {
		p1 = new Player();
		p2 = new Player();
		p3 = new Player();
		
	}
	public void initialize() {
	}
	
	public T getRandomFromList (ArrayList<T> genericList) {
		Random rand = new Random();
		int index = rand.nextInt(genericList.size());
		T temp = genericList.get(index);
		genericList.remove(index);
		return temp;
		
	}
}
