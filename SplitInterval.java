/*
 * Name: Arewa (Morountudun) Ojelade
 * Date: 5/21/2024
 * Course: CMSC 412
 * Description: This class uses multi-threading to split
 * the interval into two array lists of even and odd numbers. 
 * This contributes to the project by allowing the 
 * VampireNumbers class access to two arrays so that it only needs
 * two threads to process each array per project requirement.
 */
import java.util.ArrayList;

public class SplitInterval extends Thread{
	int start;
	int end;
	int threadNumber;
	int number;
	private static ArrayList<Integer> evenList = new ArrayList<>();
	private static ArrayList<Integer> oddList = new ArrayList<>();
	
	public SplitInterval(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	public ArrayList<Integer> getEvenList(){
		return evenList;
	}
	public ArrayList<Integer> getOddList(){
		return oddList;
	}
	
	/*
	 * This function loops through the interval and adds
	 * each number to the correct list simultaneously using threading.
	 */
	public  void split() {
		for(int i = start; i <= end; i++) {
			if(i % 2 == 0) {
				SplitInterval even = new SplitInterval(start, end );
				even.number = i;
				even.threadNumber = 0;
				even.run();
			}
			else {
				SplitInterval odd = new SplitInterval(start, end );
				odd.number = i;
				odd.threadNumber = 1;
				odd.run();
			
			}
		}
	}
	
	/*
	 * This run() function adds the number to the even list if the thread is 0.
	 * else the number is added to the odd list.
	 */
	public void run() {
			if(this.threadNumber == 0) {
				evenList.add(number);
			}
			else {
				oddList.add(number);
			}
		
	}
}

