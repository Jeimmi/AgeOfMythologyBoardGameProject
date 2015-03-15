import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Lyndon Kondratczyk
 * @version 3/12/15
 * 
 * The god card Hera, which is a build card that grants the activePlayer a house
 */
public class Hera extends BuildCard{
	public Hera(BufferedImage front, BufferedImage back, 
			String firstDescription, String secondDescription, 
			int value, int cost){
		super(front, back, false, "Hera", firstDescription,	
			secondDescription, value,cost);
	}
	
	/**
	 * Overrides execute in Building card to allow special god ability
	 */
	public void execute(Game game){
		ArrayList<String> tollMessage = new ArrayList<String>();
		UserInterface<String> handleToll = new UserInterface<String>();
		Building houseToAdd = null;
		
		if(game.activePlayer.city.size() == 16){
			System.out.println("The gods think you have grown too large to" +
					" grant your request");	
		}
		else{
			for(int i = 0; i < game.activePlayer.buildingPool.size(); i++){
				if(game.activePlayer.buildingPool.get(i).type ==
						Building.Type.HOUSE){
					houseToAdd = game.activePlayer.buildingPool.get(i);
					i = game.activePlayer.buildingPool.size();
				}
			}
			if(houseToAdd != null){
				tollMessage.add("Pay one favor to prove you are worthy.");
				handleToll.provideMenuOptions("Pay tribute to your god?", game, 
						tollMessage, "Run away in fear...");
				if(handleToll.getPlayerSelection(game, tollMessage, true) != null){
					if(game.activePlayer.wallet[3] < 1){
						System.out.println("The gods deem you unworthy!!!");
					}
					else{
						game.activePlayer.wallet[3] -= 1;
						addBuilding(game, houseToAdd);
						System.out.println("The gods have answered your prayers.");	
					}
				}
			}
			else{
				System.out.println("The gods think there are enough of you running around");
			}
		}	
		
		super.execute(game);
		
	}
}
