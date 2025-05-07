/*
 * Name: Arewa (Morountudun) Ojelade
 * Date: 5/21/2024
 * Course: CMSC 412
 * Description: The purpose of this class is to find a pattern 
 * that can be used to predict a vampire Number.
 * This class attempts to find all the sums of all the vampire numbers
 * in the interval 100000 to 999999. Once the sums of each number is
 * calculated it is added to an ArrayList that contains all the unique sums 
 * of all vampire numbers within the interval. 
 * 
 * 
 * 
 * */
import java.util.Arrays;
import java.util.ArrayList;
public class LimitIntervalWithSums {
	  public static Integer sumDigits(Integer n) {
			if(n > 0) {
				return (n % 10) + sumDigits(n/10); 
			}
			return n;
		}
		  public static int binarySearch(ArrayList<Integer> list, int key) {
	    int low = 0;
	    int high = list.size() - 1;
	    return binarySearch(list, key, low, high);
	  }

	  private static int binarySearch(ArrayList<Integer> list, int key,
	    int low, int high) {
	    if (low > high)  // The list has been exhausted without a match
	      return -low - 1;

	    int mid = (low + high) / 2;
	    if (key < list.get(mid))
	      return binarySearch(list, key, low, mid - 1);
	    else if (key == list.get(mid))
	      return mid;
	    else
	      return binarySearch(list, key, mid + 1, high);
	  }
	  
		public static void main(String[] args) {
			/*
			 * The vampire numbers for the interval 100000 to 999999 
			 * is calculated and assigned to list.
			 */
	    int[] list = {102510, 104260, 108135, 105210, 105264, 105750,
	                  116725, 117067, 110758, 124483, 115672, 125433,
	                  126027, 118440, 129775, 120600, 133245, 134725,
	                  123354, 135837, 136525, 125248, 125460, 125500,
	                  126846, 129640, 131242, 146137, 132430, 135828,
	                  136948, 
	                  152685, 156289, 140350, 156915, 145314, 146952,
	                  150300, 152608, 153436, 156240, 175329, 180225,
	                  180297, 162976, 163944, 172822, 193257, 173250,
	                  193945, 174370, 197725, 182250, 182650, 205785,
	                  186624, 190260, 192150, 216733, 201852, 211896,
	                  213466, 215860, 217638, 218488, 226498, 226872,
	                  229648, 233896, 241564, 245182, 251896, 253750,
	                  254740, 260338, 262984, 263074, 284598, 284760,
	                  286416, 304717, 312475, 296320, 312975, 319059,
	                  315594, 315900, 319536, 326452, 341653, 329346, 
	                  329656, 336550, 336960, 338296, 346968, 361989,
	                  369189, 362992, 371893, 365638, 368550, 378400,
	                  378418, 378450, 386415, 384912, 392566, 404968, 
	                  414895, 416650, 416988, 428980, 429664, 447916,
	                  456840, 457600, 458640, 475380, 486720, 498550,
	                  489159, 489955, 529672, 516879, 538650, 559188,
	                  536539, 567648, 568750, 629680, 638950, 673920, 
	                  679500, 729688, 738468, 769792, 789250, 794088,
	                  736695, 809964, 815958, 829696, 789525, 792585,
	                  809919, 841995, 939658};
	    
	    // list is sorted and printed to screen with list length to 
	    // validate correct number of vampire numbers are in list.
	    Arrays.sort(list);
	    System.out.println("Vampire Numbers in interval 100000 to 999999:\n " + Arrays.toString(list));
	    System.out.println("\n\tList Length: " + list.length);
	    
	    
	    ArrayList<Integer> sumList = new ArrayList<>();
	    
	    /*
			 * List containing all vampire numbers is parsed.
			 * The sum of the digits in each number is calculated and
			 * added to sumList if unique.
			 */
	    
	    System.out.println("\n\t   Number : sumDigits");
	    for(int i = 0; i < list.length; i++){
	        Integer sum = sumDigits(list[i]);
	        int num = i +1;
	        System.out.println("\t" + num +". " +list[i] + " : " + sum);
	        if(binarySearch(sumList,sum ) < 0){
	            sumList.add(sum);
	        }
	        
	    }
	    
	    /*
			 * sumList containing all unique sums is printed to screen.
			 * This list can be used to discriminate an interval such that
			 * only numbers whose sum add to a number in the list is included.
			 * sumList includes [9, 13, 18, 22, 27, 31, 36, 40]
			 *                   [+4,  +5, +4, +5, +4, +5, +4]
			 * Each number increases with a pattern of 4 and 5 repeatedly.
			 * 
			 * I hypothesize that the next interval 10000000 to 99999999
			 * can be limited with 
			 * the sumList [9, 13, 18, 22, 27, 31, 36, 40, 45, 49, 54,  58,  63,  67,  72,  77]
			 * if the pattern is followed...            [+5, +4, +5,  +4,  +5,  +4,  +5,  +4]
			 * 
			 * 
			 * Using this method finding vampire numbers in 
			 * the Interval of 100,000 to 999,999 is shortened from
			 * parsing 899,999 unique numbers to 197,690 unique numbers.
			 * This increases processing efficiency by 78%.
			 */
	    System.out.println("\nInterval Limit Sum List: " + sumList);
	                  
    }
}
