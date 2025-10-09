package lab0;

import java.util.ArrayList;

/**
 * Lab0 class containing three methods for various computational tasks.
 * 
 * @author Sukhwant Sagar
 * @author Aaditya Karamchandani
 */

public class Lab0 {

	//First Method
	/**
	 * This method Calculates BMI using the weight and height provided, using the formula: weight / (height * height) 
	 * @param weight
	 * @param height
	 * @return The BMI score based on input weight and height, in round to one decimal place
	 * @pre The Precondition should be that the input value should be a positive floating point number. 
	 */
    public static double calculateBMI(double weight, double height) {
    	// Handle exceptional cases for height
        if(height <= 0 || height < 0.01 || Double.isInfinite(height)) {
        	return -1.0;
        }
        
        
        // Handle exceptional cases for weight
        if(weight < 0 || Double.isInfinite(weight)) {
        	return -1.0;
        }
        
        // Calculate BMI using the formula: weight / (height²)
        double bmi = weight / (height * height);
        
        // Handle potential overflow/underflow
        if(Double.isInfinite(bmi) || Double.isNaN(bmi)) {
        	return -1.0;
        }
        
        
        // Round to one decimal place and return bmi
        bmi *= 10;
    	long roundValueBMI = Math.round(bmi);
    	return (double) roundValueBMI/10;
    	
        
    }

  //Second Method
    public static String findLongestWord(ArrayList<String> words) {
        // Handle empty list
        if(words == null || words.size() == 0) {
        	return "";
        }
        
            
        // Iterate through all words to find the longest, Handle null strings by treating them as empty and
    	// Update longest word if current word is longer
        String longestWord = words.getFirst();
        for(int i = 0; i < words.size(); i++) {
        	if(words.get(i) == null) {
        		continue;
        	}
        	if(longestWord.length() < words.get(i).length()) {
        		longestWord = words.get(i);
        	}
        }
        
        
       //Return the longest word 
        return longestWord;
    }

  //Third Method
    public static int countVowels(String text) {
        // Handle null input
       if(text == null || text == "") {
    	   return 0;
       }
        
        // Convert to lowercase for case-insensitive comparison
        String lowerCaseText = text.toLowerCase();
        
        
        // Count vowels by checking each character
        int counter = 0;
        for(int i =0; i < lowerCaseText.length(); i++) {
        	if(lowerCaseText.charAt(i) == 'a' || lowerCaseText.charAt(i) == 'e' || lowerCaseText.charAt(i) == 'i' ||lowerCaseText.charAt(i) == 'o' || lowerCaseText.charAt(i) == 'u') {
        		counter++;
        	}
        	
        }
       
        //Return Vowel count
        return counter;
        
    }
}