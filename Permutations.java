/*
 * Name: Arewa (Morountudun) Ojelade
 * Date: 5/21/2024
 * Course: CMSC 412
 * Description:  This class handles the permutations for the calling function.
 * The getAllPermutations method returns a list of all possible permutations.
 * The validatePermutation() method takes in an ArrayList and a  string key,
 * returning the boolean of key search.
 */
import java.util.ArrayList;
public class Permutations  { 
	
		public static void main(String[] args) {
			System.out.println(getAllPermutations("1234"));
			System.out.println(validatePermutation(getAllPermutations("1234"), "4321"));
		}
		
		/* 
     * This function returns the all possible permutations of the string.
     */
    public static ArrayList<String> getAllPermutations(String str)  
    {  
    		
        int n = str.length();   
        ArrayList<String> list = new ArrayList<>();
        Permutations.permute(list,str, 0, n-1);  

        //System.out.println(validatePermutation(list,"4321"));
        return list;
    }  
  
    /* 
     * This function returns the boolean result of the string search from list.
     */
    public static boolean validatePermutation(ArrayList<String> list, String str) {
  			
  		if(Search.binarySearch(list, str) > -1) {
  			return true;
  		}
  		 
  		return false;
  	}
    
    /* 
     * This function recursively loops through the string and 
     * adds all possible permutations of the string to the ArrayList.
     */
    public static void permute(ArrayList<String> list, String str,  
                         int l, int r){  
    	
        if (l == r)  
            list.add(str);  
        else
        {  
            for (int i = l; i <= r; i++)  
            {  
                str = swap(str,l,i);  
                permute(list, str, l+1, r);  
                str = swap(str,l,i);  
            }  
        }
    }  
  
    /* 
     * This function is a helper function for permute.
     * It swaps the characters in the string and returns
     * permutation to the calling function.       
    */
    public static String swap(String a,  
                       int i, int j)  
    {  
        char temp;  
        char[] charArray = a.toCharArray();  
        temp = charArray[i] ;  
        charArray[i] = charArray[j];  
        charArray[j] = temp;  
        return String.valueOf(charArray);  
    }  
}