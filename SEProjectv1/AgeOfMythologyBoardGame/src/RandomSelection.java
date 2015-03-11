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
	 * @param remove True if item selected is to be removed
	 * @return The removed Object from options
	 */
	public T getRandomFromList (boolean remove) {
		Random rand = new Random();
		int index = rand.nextInt(options.size());
		T temp = options.get(index);
		if(remove)
			options.remove(index);
		System.out.println("*****AI selected : " + temp.toString() + "*****");
		return temp;
	}
}
