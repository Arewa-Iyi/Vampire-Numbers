/*
 * Name: Arewa (Morountudun) Ojelade
 * Date: 5/21/2024
 * Course: CMSC 412
 * Description: This class creates two threads to process
 * the even and odd arrays of a specified interval.
 * The interval is limited to the numbers that meet the limitSums 
 * list criteria. The explanation of the limitSums list is in
 * the LimitIntervalWithSums.java file.
 * The numbers that meet requirements are then analyzed for 
 * vampire qualities. If the number possesses the qualities, 
 * it is added to the corresponding array list and its
 * relative count is incremented. The threads are joined
 * once array processing is completed and the results are
 * displayed to the screen. 
 * */
import java.util.ArrayList;
import java.util.Collections;
public class VampireNumbers extends SplitInterval implements Runnable{
	private static ArrayList<Integer> even;
	private static ArrayList<Integer> odd;
	private static Integer vampireEvenCount = 0;
	private static Integer vampireOddCount = 0;

	private static ArrayList<Integer> vampireNumbers = new ArrayList<>();
	private int thread;
	
	public VampireNumbers(Integer start, Integer end) {
		super(start, end);
	}
	
	public static Integer getVampireEvenCount() {
		return vampireEvenCount;
	}
	public static Integer getVampireOddCount() {
		return vampireOddCount;
	}
	/*This method calls the super's split function to split
	 * the specified interval. Then the super's even and odd
	 * lists are then assigned to VampireNumbers corresponding
	 * lists.
	 */
	public void setLists() {
		 super.split();
		 VampireNumbers.even = super.getEvenList();
		 VampireNumbers.odd = super.getOddList();
	}
	
	public void setThread(int num) {
		this.thread = num;
	}
	
	public void run() {
		if(this.thread == 0) {
			findVampireNumber(even);
			
		}
		else {
			
			findVampireNumber(odd);
		}
	}
	public boolean validateTrailingZeros(String fang1, String fang2) {
			if(fang1.endsWith("0") && fang2.endsWith("0"))
				return false;
		
		return true;
	}

	public boolean validatePermutation(String number, String fangs) {

		ArrayList<String> permutations = Permutations.getAllPermutations(number);
		if(Permutations.validatePermutation(permutations, fangs)) {
			return true;
		}
		return false;
	}	
	/*
	 * This function returns the sums of all the digits in the parameter.
	 */
	public  int sumDigits(Integer n) {
		if(n > 0) {
			return (n % 10) + sumDigits(n/10); 
		}
		return n;
	}
	
	/*
	 * This function limits the interval to be checked.
	 * Only numbers whose digits add to the sum in the limitSums list
	 * continues to the Runnable FindFangs method.
	 * 
	 * The explanation of the limitSums list is included in the 
	 * LimitIntervalWithSums.java file
	 */
	public boolean limitIntervalWithSums(Integer number) {
		int[] limitSums = {9, 13, 18, 22, 27, 31, 36, 40};
		int sum = sumDigits(number);
		if(Search.binarySearch(limitSums, sum) >= 0) {
			return true;
		}
		return false;
	}
	
	public void findVampireNumber(ArrayList<Integer> list ) {
		/* parse through list and send 
		 * each validated limitIntervalWithSums number to 
		 * FindFangs(number) method. 
		 */
		for(Integer index = 0; index < list.size(); index++) {
			int number = list.get(index);
			if(limitIntervalWithSums(number)) {
				Thread ValidateVampNum = new Thread(FindFangs(number));
				ValidateVampNum.start();
			}
		}
	}	
	/*
	 * FindFangs(number) is an embedded multi-threaded method.
	 * This provides findVampireNumber with multiple threads
	 * to simultaneously find fangs.
	 */
	private Runnable FindFangs(int number) {
		//parse through each number starting from 2 up till the square root of current parameter.
		for(Integer j = 2; j <= Math.sqrt(number) + 1; j++) {
			// fang found if current parameter is a multiple. continue to find second fang.
			if(number % j == 0) { 
				Integer i = number/j;
				String fang2 = i.toString();
				String fang1 = j.toString();
				String fang = fang1 + fang2;
				String numberString = number + "";
				boolean halfAsMany = fang1.length() >= numberString.length()/2 &&
														 fang2.length() >= numberString.length()/2;
				
				/*
				 * Add number to VampireNumber list and increment relative count if:
				 * 1. fang1 and fang2 string length is even.
				 * 2. fang1 and fang2 string length is greater or equal to half of current parameter string length.
				 * 3. fang1 and fang2 do not have trailing 0's.
				 */
				if(fang.length() % 2 == 0 && halfAsMany &&
						validatePermutation(numberString,fang) &&
						validateTrailingZeros(fang1, fang2)){
					/*
					 * Add current parameter to corresponding array list and increment count
					 * if all validation requirements have been met.
					 */
					if(thread == 0) {
						vampireNumbers.add(number);
						vampireEvenCount++;							
						System.out.println("First worker found: " + number + "   [" + fang1 + " : " + fang2 + "]");
						//System.out.println(getVampireCount());
						break;
						
					}
					else {

						vampireNumbers.add(number);
						vampireOddCount++; 
						System.out.println("Second worker found: " + number + "  [" + fang1 + " : " + fang2 + "]");
						//System.out.println(getVampireCount());
						break;								
						
					}
				}		
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		long startMilliseconds = System.currentTimeMillis();
		
		// Assign interval
		Integer start = 100000;
		Integer end = 999999;
		
		// create first thread
		VampireNumbers thread = new VampireNumbers(start, end);
		thread.setLists();
		thread.setThread(0);
		Thread findEven = new Thread(thread);
		
		// create second thread
		VampireNumbers thread2 = new VampireNumbers(start, end);
		thread2.setThread(1);
		Thread findOdd = new Thread(thread2);
		
		// start both threads
		findEven.start();
		findOdd.start();
		
		
		// join threads after completion
		try {
			findEven.join();
			findOdd.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		
		// display results
		Integer total = VampireNumbers.getVampireEvenCount() + VampireNumbers.getVampireOddCount();
		System.out.println("\nFirst worker found: " + VampireNumbers.getVampireEvenCount() + " Vampire Numbers");
		System.out.println("Second worker found: " + VampireNumbers.getVampireOddCount() + " Vampire Numbers");
		System.out.println("\nThe Total number of Vampire numbers found is: " + total);
		Collections.sort(vampireNumbers);
		System.out.println(vampireNumbers);
		long endMilliseconds = System.currentTimeMillis();
		long totalSeconds = (endMilliseconds - startMilliseconds) / 1000 ;
		System.out.println("Total time of execution is: " + totalSeconds + " seconds.");
	}
}
