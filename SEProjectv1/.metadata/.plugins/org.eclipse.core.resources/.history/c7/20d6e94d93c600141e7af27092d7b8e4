/**
 * @author Lyndon Kondratczyk
 * @version 3/9/15
 * 
 * A tool for randomly selecting an element from a generic ArrayList
 */
import java.util.ArrayList;
import java.util.Random;

public class RandomSelection <T>{
	ArrayList<T> options;
	
	/**
	 * The constructor for the RandomSelection
	 * 
	 * @param list The list being selected from
	 */
	public RandomSelection(ArrayList<T> list){
		options = list;
	}
	
	/**
	 * Chooses a random element from options, removes it and returns it
	 * 
	 * @return The removed Object from options
	 */
	public T getRandomFromList () {
		Random rand = new Random();
		int index = rand.nextInt(options.size());
		T temp = options.get(index);
		options.remove(index);
		return temp;
		
	}
	
	/**
	 * Takes a generic object and places it in options
	 * 
	 * @param returnValue The Object to place options
	 */
	public void returnToList(T returnValue){
		options.add(returnValue);
	}
}
