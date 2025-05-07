/*
 * Name: Arewa (Morountudun) Ojelade
 * Date: 5/21/2024
 * Course: CMSC 412
 * Description: This Search class implements binary search in two ways.
 * The first takes in an ArrayList<String> and a String as a parameter
 * using a while loop to find the key.
 * The second is a recursive call taking in an int[] array 
 * and an int key as parameters and searches through the length
 * of the array for the key. 
 * 
 * If the key is found the index is returned.
 * if the list is exhausted then the negative of where the index should have 
 * been is returned. 
 */
import java.util.ArrayList;
import java.util.Collections;
public class Search{
	
	  public static int binarySearch(ArrayList<String> list, String key){
	    // Returns key index if key is found.
			Collections.sort(list);
	    Integer low = 0, high = list.size() - 1;
	    while (low <= high) {
	        Integer mid = low + (high - low) / 2;
	
	        // return mid if same as key
	        if (Integer.parseInt(list.get(mid)) == Integer.parseInt(key))
	            return mid;
	
	        // Set low to mid + 1 if mid < key
	        if (Integer.parseInt(list.get(mid)) < Integer.parseInt(key))
	            low = mid + 1;
	
	        // else Set high to mid - 1
	        else
	            high = mid - 1;
	    }
	
	    // return -index  if key is not found
	    return -low -1 ;
	  }
	  
	  public static int binarySearch(int[] list, int key) {
	    int low = 0;
	    int high = list.length - 1;
	    return binarySearch(list, key, low, high);
	  }

	  private static int binarySearch(int[] list, int key,
	    int low, int high) {

    	// The list has been exhausted without a match
	    if (low > high)
	      return -low - 1;

	    int mid = (low + high) / 2;
	    if (key < list[mid])
	      return binarySearch(list, key, low, mid - 1);
	    else if (key == list[mid])
	      return mid;
	    else
	      return binarySearch(list, key, mid + 1, high);
	  }
	  public static void main(String[] args){
	    ArrayList<String> list1 = new ArrayList<>();
	    list1.add("3");
	    list1.add("1");
	    list1.add("2");
	    list1.add("4");
	    list1.add("9");
	    list1.add("6");
	    String keys = "3";
	    System.out.println(binarySearch(list1,keys));
	  }
}
