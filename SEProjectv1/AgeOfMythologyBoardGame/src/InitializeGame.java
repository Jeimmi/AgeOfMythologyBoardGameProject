/**
 * @author Lyndon Kondratczyk
 * @version 3/9/15
 * 
 * Creates/executes the starting conditions/tasks of the AoM board game
 */

import java.awt.image.BufferedImage;
import java.util.*;
public interface InitializeGame<T>{
	
	/**
	 * 
	 * @param game The Game to be initialized
	 */
	public static void initialize(Game game){
		initializePlayerPriority(game);
		initializePlayerRaces(game);
		initializeResources(game);
		initializeVictoryStratgies(game);
		initializeBuildingPool(game);
		initializeProductionPool(game);
		initializePlayerDecks(game);
		initializePlayerTerrain(game);
		choosePlayerResources(game);
	}
	
	/**
	 * Randomly assigns Player priority. Order always stays the same,
	 * only starting player is changed.
	 * 
	 * @param game The Game with the Players to be assigned
	 */
	public static void initializePlayerPriority(Game game){
				
		game.player1.next = game.player2;
		game.player2.next = game.player3;
		game.player3.next = game.player1;
		game.activePlayer = game.player1;
		
		int turnOrder = (int)(Math.random()*3);
		for(int i = turnOrder; i > 0; i--){
			game.activePlayer = game.activePlayer.next.next;
		}
	}
	
	/**
	 * Randomly assigns 3 Players one of each Race
	 * 
	 * @param game The Game with the Players to assign Races
	 */
	public static void initializePlayerRaces(Game game){
		ArrayList<Player.Race> playerRace = new ArrayList<Player.Race>();
		playerRace.add(Player.Race.NORSE);
		playerRace.add(Player.Race.EGYPTIAN);
		playerRace.add(Player.Race.GREEK);
		int numberOfPlayers = 3;
		for(int i = 0; i < numberOfPlayers; i++){
			RandomSelection<Player.Race> selector = new RandomSelection<Player.Race>();
			game.activePlayer.playerRace = selector.getRandomFromList(playerRace, true, 0);
			game.activePlayer = game.activePlayer.next;
		}
	}
	
	/**
	 * Gives the Players and Bank the appropriate starting resources
	 * 
	 * @param game With the Players and bank to be worked on
	 */
	public static void initializeResources(Game game){
		int numberOfPlayers = 3;
		for(int i = 0; i < 4; i++ ){
			game.bank[i] = 25;
			for(int j = 0; j < numberOfPlayers; j++){
				game.activePlayer.wallet[i] = 4;
				game.activePlayer = game.activePlayer.next;
			}
		}
		game.bank[4] = 30;
		int turnOrder = (int)(Math.random()*3);
		for(int i = turnOrder; i > 0; i--){
			game.activePlayer = game.activePlayer.next.next;
		}
	}
	
	public static void initializeVictoryStratgies(Game game){
		game.victory.add(new VictoryPool(VictoryPool.Strategy.MOST_BUILDINGS));
		game.victory.add(new VictoryPool(VictoryPool.Strategy.WONDER));
	}

	/**
	 * Puts in each Players buildingPool all the Buildings they can build
	 * 
	 * @param game The Game with the Players to be worked on
	 */
	public static void initializeBuildingPool(Game game){
		for(int i = 0; i < 3; i++){
			game.activePlayer.buildingPool.add(new Building(Building.Type.MARKET, 0,0,3,2));
			game.activePlayer.buildingPool.add(new Building(Building.Type.STOREHOUSE, 2,2,2,2));
			game.activePlayer.buildingPool.add(new Building(Building.Type.QUARRY, 4,0,1,0));
			game.activePlayer.buildingPool.add(new Building(Building.Type.MONUMENT, 3,0,2,0));
			game.activePlayer.buildingPool.add(new Building(Building.Type.GRANARY, 0,2,3,0));
			game.activePlayer.buildingPool.add(new Building(Building.Type.WOOD_WORKSHOP, 2,0,3,0));
			game.activePlayer.buildingPool.add(new Building(Building.Type.GOLD_MINT, 3,2,0,0));
			
			for(int j = 0; j < 10; j++){
				game.activePlayer.buildingPool.add(new Building(Building.Type.HOUSE, 2,2,0,0));
			}
			game.activePlayer = game.activePlayer.next;
		}
	}
	
	/**
	 * Puts in the Game's productionPool all the ProductionTiles available
	 * in the Game
	 * 
	 * @param game The Game to have ProductionTiles added to
	 */
	public static void initializeProductionPool(Game game){

		ArrayList<ProductionTile> tempList = new ArrayList<ProductionTile>();

		tempList.add(new ProductionTile(ProductionTile.Terrain.HILLS, 1, 0, 0, 0, 4));
		tempList.add(new ProductionTile(ProductionTile.Terrain.HILLS, 0, 0, 0, 1, 4));
		tempList.add(new ProductionTile(ProductionTile.Terrain.HILLS, 0, 1, 0, 0, 4));
		tempList.add(new ProductionTile(ProductionTile.Terrain.HILLS, 0, 0, 2, 0, 4));

		tempList.add(new ProductionTile(ProductionTile.Terrain.MOUNTAINS, 0, 1,	0, 0, 3));
		tempList.add(new ProductionTile(ProductionTile.Terrain.MOUNTAINS, 0, 0,	0, 1, 3));
		tempList.add(new ProductionTile(ProductionTile.Terrain.MOUNTAINS, 0, 0,	2, 0, 6));

		tempList.add(new ProductionTile(ProductionTile.Terrain.FOREST, 1, 0, 0,	0, 2));
		tempList.add(new ProductionTile(ProductionTile.Terrain.FOREST, 0, 2, 0,	0, 9));
		tempList.add(new ProductionTile(ProductionTile.Terrain.FOREST, 0, 0, 1,	0, 2));
		tempList.add(new ProductionTile(ProductionTile.Terrain.FOREST, 0, 0, 0,	1, 2));

		tempList.add(new ProductionTile(ProductionTile.Terrain.DESERT, 0, 0, 1,	0, 7));
		tempList.add(new ProductionTile(ProductionTile.Terrain.DESERT, 0, 0, 0,	2, 7));
		
		tempList.add(new ProductionTile(ProductionTile.Terrain.FERTILE, 0, 0, 0, 1, 3));
		tempList.add(new ProductionTile(ProductionTile.Terrain.FERTILE, 0, 0, 1, 0, 3));
		tempList.add(new ProductionTile(ProductionTile.Terrain.FERTILE, 2, 0, 0, 0, 12));
		tempList.add(new ProductionTile(ProductionTile.Terrain.FERTILE, 0, 1, 0, 0, 3));

		tempList.add(new ProductionTile(ProductionTile.Terrain.SWAMP, 1, 0, 0, 0, 4));
		tempList.add(new ProductionTile(ProductionTile.Terrain.SWAMP, 0, 0, 0, 1, 3));
		tempList.add(new ProductionTile(ProductionTile.Terrain.SWAMP, 0, 2, 0, 0, 4));

		for (int i = 0; i < tempList.size(); i++) {
			ProductionTile temp = tempList.get(i);
			for (int j = 0; j < temp.quantity; j++) {
				game.productionPool.add(temp);
			}
		}
	}
	
	/**
	 * Puts in each Players permanentDeck and randomDeck all the Cards that
	 * are available to draw
	 * 
	 * @param game The game with the Players to be worked on
	 */
	public static void initializePlayerDecks(Game game){
		int numberOfPlayers = 3;
	
		for(int i = 0; i < numberOfPlayers; i ++){
			
			/*----BASE PACK----*/
		switch(game.activePlayer.playerRace){
			case NORSE:
				game.activePlayer.permanentDeck.add(new BuildCard
						(null, null, true, "Build", "Build up to " + 1 + " building",
								"", 1, 0));
				game.activePlayer.permanentDeck.add(new BuildCard
						(null, null, true, "Build", "Build up to " + 1 + " building",
								"", 1, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 4 + " buildings",
								"", 4, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 2 + " buildings",
								"", 2, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 2 + " buildings",
								"", 2, 0));
				game.activePlayer.permanentDeck.add(new GatherCard(null, null,
						true, "Gather", "Resource type or terrain tpye. ", "", 
						0, GatherCard.GatherType.RESOURCE, 
						GatherCard.GatherType.TERRAIN));
				game.activePlayer.permanentDeck.add(new GatherCard(null, null,
						true, "Gather", "Resource type or terrain tpye. ", "", 
						0, GatherCard.GatherType.RESOURCE, 
						GatherCard.GatherType.TERRAIN));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
		
				game.activePlayer.permanentDeck.add(new NextAge(null, null,
						true, "Next Age ", "Advance to the next age - " + 4 + 
						" of each resource for Classical, " + 5 + 
						" for Heroic, " + 6 + " for Mythical ",
						"", 0, false));	
				game.activePlayer.permanentDeck.add(new NextAge(null, null,
						true, "Next Age", "Advance to the next age- " + 4 + 
						" of each resource for Classical, " + 5 + 
						" for Heroic, " + 6 + " for Mythical ",
						"", 0, false));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
							
				game.activePlayer = game.activePlayer.next;
				break;
				
			case GREEK:
			
				game.activePlayer.permanentDeck.add(new GatherCard(null, null,
						true, "Gather", "Resource type or terrain tpye. ", "", 
						0, GatherCard.GatherType.RESOURCE, 
						GatherCard.GatherType.TERRAIN));
				game.activePlayer.permanentDeck.add(new GatherCard(null, null,
						true, "Gather", "Resource type or terrain tpye. ", "", 
						0, GatherCard.GatherType.RESOURCE, 
						GatherCard.GatherType.TERRAIN));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.permanentDeck.add(new BuildCard
						(null, null, true, "Build", "Build up to " + 1 + " building",
								"", 1, 0));
				game.activePlayer.permanentDeck.add(new BuildCard
						(null, null, true, "Build", "Build up to " + 1 + " building",
								"", 1, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 4 + " buildings",
								"", 4, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 2 + " buildings",
								"", 2, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 2 + " buildings",
								"", 2, 0));
				game.activePlayer.randomDeck.add(new Hera(null, null, 
						"Build up to " + 3 + " buildings", "Pay 1 favor to "
								+ "gain 1 house.", 3, 1));

				game.activePlayer.permanentDeck.add(new NextAge(null, null,
						true, "Next Age", "Advance to the next age- " + 4 + 
						" of each resource for Classical, " + 5 + 
						" for Heroic, " + 6 + " for Mythical ",
						"", 0, false));	
				game.activePlayer.permanentDeck.add(new NextAge(null, null,
						true, "Next Age", "Advance to the next age- " + 4 + 
						" of each resource for Classical, " + 5 + 
						" for Heroic, " + 6 + " for Mythical ",
						"", 0, false));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));

				game.activePlayer = game.activePlayer.next;
				break;
				
			case EGYPTIAN:
				game.activePlayer.permanentDeck.add(new GatherCard(null, null,
						true, "Gather", "Resource type or terrain tpye. ", "", 
						0, GatherCard.GatherType.RESOURCE, 
						GatherCard.GatherType.TERRAIN));
				game.activePlayer.permanentDeck.add(new GatherCard(null, null,
						true, "Gather", "Resource type or terrain tpye. ", "", 
						0, GatherCard.GatherType.RESOURCE, 
						GatherCard.GatherType.TERRAIN));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.permanentDeck.add(new BuildCard
						(null, null, true, "Build", "Build up to " + 1 + " building",
								"", 1, 0));
				game.activePlayer.permanentDeck.add(new BuildCard
						(null, null, true, "Build", "Build up to " + 1 + " building",
								"", 1, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 4 + " buildings",
								"", 4, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 2 + " buildings",
								"", 2, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 2 + " buildings",
								"", 2, 0));
				game.activePlayer.randomDeck.add(new Horus(null, null, 
						"Build up to " + 3 + " buildings", "Pay 1 favor to "
						+ "destroy 1 building", 3, 1));
				game.activePlayer.randomDeck.add(new Nephthys(null, null, 
						"Build up to " + 3 + " buildings", "Pay 2 favor to "
								+ "reduce building costs by 2 resources.", 3, 2));

				game.activePlayer.permanentDeck.add(new NextAge(null, null,
						true, "Next Age", "Advance to the next age- " + 4 + 
						" of each resource for Classical, " + 5 + 
						" for Heroic, " + 6 + " for Mythical ",
						"", 0, false));	
				game.activePlayer.permanentDeck.add(new NextAge(null, null,
						true, "Next Age", "Advance to the next age- " + 4 + 
						" of each resource for Classical, " + 5 + 
						" for Heroic, " + 6 + " for Mythical ",
						"", 0, false));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
								
				game.activePlayer = game.activePlayer.next;
				break;
			default:
				break;
			}
		}
	}
			/*----BUILDERS PACK----*/
			/*
			switch(game.activePlayer.playerRace){
			case NORSE:
				game.activePlayer.permanentDeck.add(new BuildCard
						(null, null, true, "Build", "Build up to " + 1 + " building",
								"", 1, 0));
				game.activePlayer.permanentDeck.add(new BuildCard
						(null, null, true, "Build", "Build up to " + 1 + " building",
								"", 1, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 4 + " buildings",
								"", 4, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 2 + " buildings",
								"", 2, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 2 + " buildings",
								"", 2, 0));
				game.activePlayer = game.activePlayer.next;
				break;
			case GREEK:
				game.activePlayer.permanentDeck.add(new BuildCard
						(null, null, true, "Build", "Build up to " + 1 + " building",
								"", 1, 0));
				game.activePlayer.permanentDeck.add(new BuildCard
						(null, null, true, "Build", "Build up to " + 1 + " building",
								"", 1, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 4 + " buildings",
								"", 4, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 2 + " buildings",
								"", 2, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 2 + " buildings",
								"", 2, 0));
				game.activePlayer = game.activePlayer.next;
				break;
			case EGYPTIAN:
				game.activePlayer.permanentDeck.add(new BuildCard
						(null, null, true, "Build", "Build up to " + 1 + " building",
								"", 1, 0));
				game.activePlayer.permanentDeck.add(new BuildCard
						(null, null, true, "Build", "Build up to " + 1 + " building",
								"", 1, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 4 + " buildings",
								"", 4, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 3 + " buildings",
								"", 3, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 2 + " buildings",
								"", 2, 0));
				game.activePlayer.randomDeck.add(new BuildCard
						(null, null, false, "Build", "Build up to " + 2 + " buildings",
								"", 2, 0));
				game.activePlayer = game.activePlayer.next;
				break;
			default:
				break;
			}
			*/
			/*----TESTER PACK HORUS----*////////////////////////////////////////////////////
			/*
			for(int j = 0; j < 6; j++){
				game.activePlayer.randomDeck.add(new Horus(null, null, 
						"Build up to " + 3 + " buildings", "Pay 1 favor to "
						+ "destroy 1 building", 3, 1));
			}
			for(int j = 0; j < 4; j++){
				game.activePlayer.randomDeck.add(new Horus(null, null, 
						"Build up to " + 1 + " buildings", "Pay 1 favor to "
						+ "destroy 1 building", 1, 1));
			}
			*/
			
			/*----TESTER PACK NEPHTHYS----*/
		/*
			for(int j = 0; j < 6; j++){
				game.activePlayer.randomDeck.add(new Nephthys(null, null, 
						"Build up to " + 3 + " buildings", "Pay 2 favor to "
								+ "reduce building costs by 2 resources.", 3, 2));
			}
			for(int j = 0; j < 4; j++){
				game.activePlayer.randomDeck.add(new Nephthys(null, null, 
						"Build up to " + 1 + " buildings", "Pay 2 favor to "
								+ "reduce building costs by 2 resources.", 1, 2));
			}
			/*
			/*----TESTER PACK HERA----*/
			/*
			for(int j = 0; j < 6; j++){
				game.activePlayer.randomDeck.add(new Hera(null, null, 
						"Build up to " + 3 + " buildings", "Pay 1 favor to "
								+ "gain 1 house.", 3, 1));
			}
			for(int j = 0; j < 4; j++){
				game.activePlayer.randomDeck.add(new Hera(null, null, 
						"Build up to " + 1 + " buildings", "Pay 1 favor to "
								+ "gain 1 house.", 1, 1));
			}
			/*
			/*----GATHER PACK----*/
			/*
			switch(game.activePlayer.playerRace){
			case NORSE:
				game.activePlayer.permanentDeck.add(new GatherCard(null, null,
						true, "Gather", "Resource type or terrain tpye. ", "", 
						0, GatherCard.GatherType.RESOURCE, 
						GatherCard.GatherType.TERRAIN));
				game.activePlayer.permanentDeck.add(new GatherCard(null, null,
						true, "Gather", "Resource type or terrain tpye. ", "", 
						0, GatherCard.GatherType.RESOURCE, 
						GatherCard.GatherType.TERRAIN));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				
				
				game.activePlayer = game.activePlayer.next;
				break;
			case GREEK:
				game.activePlayer.permanentDeck.add(new GatherCard(null, null,
						true, "Gather", "Resource type or terrain tpye. ", "", 
						0, GatherCard.GatherType.RESOURCE, 
						GatherCard.GatherType.TERRAIN));
				game.activePlayer.permanentDeck.add(new GatherCard(null, null,
						true, "Gather", "Resource type or terrain tpye. ", "", 
						0, GatherCard.GatherType.RESOURCE, 
						GatherCard.GatherType.TERRAIN));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				
				game.activePlayer = game.activePlayer.next;
				break;
			case EGYPTIAN:
				game.activePlayer.permanentDeck.add(new GatherCard(null, null,
						true, "Gather", "Resource type or terrain tpye. ", "", 
						0, GatherCard.GatherType.RESOURCE, 
						GatherCard.GatherType.TERRAIN));
				game.activePlayer.permanentDeck.add(new GatherCard(null, null,
						true, "Gather", "Resource type or terrain tpye. ", "", 
						0, GatherCard.GatherType.RESOURCE, 
						GatherCard.GatherType.TERRAIN));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer.randomDeck.add(new GatherCard(null, null,
						false, "Gather", "All resources. ", "", 
						0, GatherCard.GatherType.ALL_RESOURCES, null));
				game.activePlayer = game.activePlayer.next;
				break;
			default:
				break;
			}
			
		}}
		*/
		/*----BUILDERS PACK----*/
		/*
		switch(game.activePlayer.playerRace){
		case NORSE:
			game.activePlayer.permanentDeck.add(new BuildCard
					(null, null, true, "Build", "Build up to " + 1 + " building",
							"", 1, 0));
			game.activePlayer.permanentDeck.add(new BuildCard
					(null, null, true, "Build", "Build up to " + 1 + " building",
							"", 1, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 4 + " buildings",
							"", 4, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 3 + " buildings",
							"", 3, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 3 + " buildings",
							"", 3, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 3 + " buildings",
							"", 3, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 2 + " buildings",
							"", 2, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 2 + " buildings",
							"", 2, 0));
			game.activePlayer = game.activePlayer.next;
			break;
		case GREEK:
			game.activePlayer.permanentDeck.add(new BuildCard
					(null, null, true, "Build", "Build up to " + 1 + " building",
							"", 1, 0));
			game.activePlayer.permanentDeck.add(new BuildCard
					(null, null, true, "Build", "Build up to " + 1 + " building",
							"", 1, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 4 + " buildings",
							"", 4, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 3 + " buildings",
							"", 3, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 3 + " buildings",
							"", 3, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 3 + " buildings",
							"", 3, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 2 + " buildings",
							"", 2, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 2 + " buildings",
							"", 2, 0));
			game.activePlayer.randomDeck.add(new Hera(null, null, 
					"Build up to " + 3 + " buildings", "Pay 1 favor to "
							+ "gain 1 house.", 3, 1));
			game.activePlayer = game.activePlayer.next;
			break;
		case EGYPTIAN:
			game.activePlayer.permanentDeck.add(new BuildCard
					(null, null, true, "Build", "Build up to " + 1 + " building",
							"", 1, 0));
			game.activePlayer.permanentDeck.add(new BuildCard
					(null, null, true, "Build", "Build up to " + 1 + " building",
							"", 1, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 4 + " buildings",
							"", 4, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 3 + " buildings",
							"", 3, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 3 + " buildings",
							"", 3, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 2 + " buildings",
							"", 2, 0));
			game.activePlayer.randomDeck.add(new BuildCard
					(null, null, false, "Build", "Build up to " + 2 + " buildings",
							"", 2, 0));
			game.activePlayer = game.activePlayer.next;
			game.activePlayer.randomDeck.add(new Horus(null, null, 
					"Build up to " + 3 + " buildings", "Pay 1 favor to "
					+ "destroy 1 building", 3, 1));
			game.activePlayer.randomDeck.add(new Nephthys(null, null, 
					"Build up to " + 3 + " buildings", "Pay 2 favor to "
							+ "reduce building costs by 2 resources.", 3, 2));
			break;
		default:
			break;
		}
					/*----NEXT AGE PACK----*/
			/*
			switch(game.activePlayer.playerRace){
			case NORSE:
				game.activePlayer.permanentDeck.add(new NextAge(null, null,
						true, "Next Age ", "Advance to the next age - " + 4 + 
						" of each resource for Classical, " + 5 + 
						" for Heroic, " + 6 + " for Mythical ",	"", 0, false));	
				game.activePlayer.permanentDeck.add(new NextAge(null, null,
						true, "Next Age", "Advance to the next age- " + 4 + 
						" of each resource for Classical, " + 5 + 
						" for Heroic, " + 6 + " for Mythical ",	"", 0, false));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				
				game.activePlayer = game.activePlayer.next;
				break;
			case GREEK:
				game.activePlayer.permanentDeck.add(new NextAge(null, null,
						true, "Next Age", "Advance to the next age- " + 4 + 
						" of each resource for Classical, " + 5 + 
						" for Heroic, " + 6 + " for Mythical ",
						"", 0, false));	
				game.activePlayer.permanentDeck.add(new NextAge(null, null,
						true, "Next Age", "Advance to the next age- " + 4 + 
						" of each resource for Classical, " + 5 + 
						" for Heroic, " + 6 + " for Mythical ",
						"", 0, false));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				
				game.activePlayer = game.activePlayer.next;
				break;
			case EGYPTIAN:
				game.activePlayer.permanentDeck.add(new NextAge(null, null,
						true, "Next Age", "Advance to the next age- " + 4 + 
						" of each resource for Classical, " + 5 + 
						" for Heroic, " + 6 + " for Mythical ",
						"", 0, false));	
				game.activePlayer.permanentDeck.add(new NextAge(null, null,
						true, "Next Age", "Advance to the next age- " + 4 + 
						" of each resource for Classical, " + 5 + 
						" for Heroic, " + 6 + " for Mythical ",
						"", 0, false));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));
				game.activePlayer.randomDeck.add(new NextAge(null, null,
						false, "Next Age", "Advance to the next age- " + 3 + 
						" of each resource for Classical, " + 4 + 
						" for Heroic, " + 5 + " for Mythical ",
						"", 0, true));

				game.activePlayer = game.activePlayer.next;
				break;
			default:
				break;
			}
			
		}
	}
	
	/**
	 * Puts in Player's terrainAvailable all of the Terrain that is available
	 * to the for the duration of the game
	 * 
	 * @param game The Game with the Players to be worked on
	 */
	public static void initializePlayerTerrain(Game game){
		int numberOfPlayers = 3;
		for(int i = 0; i < numberOfPlayers; i ++){
			switch(game.activePlayer.playerRace){
			case NORSE:
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FERTILE);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FERTILE);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FERTILE);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FERTILE);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FERTILE);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FOREST);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.HILLS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.HILLS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.DESERT);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.DESERT);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.DESERT);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.DESERT);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.DESERT);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.DESERT);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.SWAMP);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.SWAMP);
				game.activePlayer = game.activePlayer.next;
				
				break;
			case GREEK:
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.MOUNTAINS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FERTILE);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FERTILE);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FERTILE);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FOREST);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FOREST);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.HILLS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.HILLS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.HILLS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.HILLS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.HILLS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.HILLS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.HILLS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.HILLS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.DESERT);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.SWAMP);
				game.activePlayer = game.activePlayer.next;
				break;
			case EGYPTIAN:
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.MOUNTAINS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.MOUNTAINS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.MOUNTAINS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.MOUNTAINS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FERTILE);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FERTILE);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FERTILE);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FOREST);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FOREST);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FOREST);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.FOREST);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.HILLS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.HILLS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.HILLS);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.DESERT);
				game.activePlayer.terrainAvailable.add(
						ProductionTile.Terrain.SWAMP);
				game.activePlayer = game.activePlayer.next;
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * Facilitates the Players' initial ProductionTile selection
	 * 
	 * @param game The game with the Players selecting ProductionTile
	 */
	public static void choosePlayerResources(Game game){
		int numberOfPlayers = 3;
		RandomSelection<ProductionTile> selector = 
				new RandomSelection<ProductionTile>();
		ArrayList<ProductionTile> options = new ArrayList<ProductionTile>();
		boolean reverseFlag = false;
		
		for(int i = 0; i < numberOfPlayers*6; i++){
			options.add(selector.getRandomFromList(game.productionPool, true, 0));
		}
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < numberOfPlayers; j++){
				ArrayList<ProductionTile> playerOptions = new ArrayList<ProductionTile>();
				for(int k = 0; k < options.size(); k++){
					if(game.activePlayer.terrainAvailable.contains((options.get(k)).type)){
						playerOptions.add(options.get(k));
					}
				}
				if(options.size() > 0){
					UserInterface<ProductionTile> ui = new UserInterface<ProductionTile>();
					ui.provideMenuOptions("Select a resource tile :", game, playerOptions, "Pass");
					ProductionTile selected = ui.getPlayerSelection(game, playerOptions, true);
					if(selected != null){
						game.activePlayer.production.add(selected);
						options.remove(selected);
						game.activePlayer.terrainAvailable.remove(selected.type);
					}
				}
				if(j < numberOfPlayers - 1){
					if(reverseFlag){
						for(int k = 0; k < numberOfPlayers - 1; k++)
							game.activePlayer = game.activePlayer.next;
					}
					else{
						game.activePlayer = game.activePlayer.next;
					}
				}
			}
			reverseFlag = (reverseFlag)?false:true;
		}
		for(int i = 0; i < options.size(); i++){
			game.productionPool.add(options.get(i));
		}
	}
}
