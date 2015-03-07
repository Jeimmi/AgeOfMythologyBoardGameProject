import java.awt.image.BufferedImage;

public abstract class Card {
	BufferedImage front;
	BufferedImage back;
	boolean permanent;
	String name;
	String firstDescription;
	String secondDescription;
	int value;
	int cost;

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

	public abstract void execute();
}