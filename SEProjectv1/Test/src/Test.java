import java.util.ArrayList;
public class Test {
	public static void main(String [] args){
		ArrayList<Integer> test = new ArrayList<Integer>();
		test.add(1); test.add(1); test.add(3);
test.remove((Integer)1);
		for(int i = test.size() - 1; i >= 0; i--){
			System.out.println(test.get(i));
		}
	}
}
