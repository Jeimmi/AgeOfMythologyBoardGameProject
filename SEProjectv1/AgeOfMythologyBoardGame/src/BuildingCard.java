import java.awt.image.BufferedImage;

public class BuildingCard extends Card {
	public BuildingCard(BufferedImage front, BufferedImage back,
			boolean permanent, String name, String firstDescription,
			String secondDescription, int value, int cost) {
		super(front, back, permanent, name, firstDescription,
				secondDescription, value, cost);
	}

	public void displayAvailable(Player p) {
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}
}