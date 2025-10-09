package lab1;


/**
 * A utility class containing several recursive methods
 *
 * <pre>
 *
 * For all methods in this API, you are forbidden to use any loops,
 * String or List based methods such as "contains", or methods that use regular expressions
 * You must not define any instance variables to solve the problem.
 * You must use direct recursion to solve the problem. (no indirect recursion is allowed).
 * </pre>
 *
 * @author Sukhwant Sagar
 * @author Aaditya Karamchandani
 *
 */
public final class Lab1 {

    /**
     * This is empty by design, Lab1 cannot be instantiated
     * Final Class: The keyword final means that this class cannot be subclassed. No other class can 
	 * extend Lab1.
     * Private Constructor: The private constructor ensures that instances of Lab1 cannot be created 
     * from outside the class. Even within the class itself, it's not possible to instantiate Lab1 since 
     * the constructor does not provide any way to call it.
     */
    private Lab1() {
        // empty by design
    }
    
    //Method 1
    /**
     * Calculates the sum of all integers in the range from start to end (inclusive).
     * Uses recursion by adding the end value to the sum of the range [start, end-1].
     * 
     * <p>This method computes the arithmetic sum: start + (start+1) + (start+2) + ... + end</p>
     * 
     * <p><strong>Examples:</strong></p>
     * <ul>
     *   <li>sum(1, 5) returns 15 (1+2+3+4+5)</li>
     *   <li>sum(3, 7) returns 25 (3+4+5+6+7)</li>
     *   <li>sum(5, 5) returns 5 (single number)</li>
     *   <li>sum(10, 8) returns 10 (end <= start, returns start)</li>
     * </ul>
     * 
     * @param start the starting value of the range (inclusive)
     * @param end the ending value of the range (inclusive)
     * @return the sum of all integers from start to end inclusive, or start if end <= start
     */
    
    public static int sum(int start, int end) {
    	// Insert your code here. You may want to change the return value. 
    	if(start == end) return end;
    	else return start + sum(start+1, end);
    }
    
    //Method 2
    /**
     * Creates a string by repeating a given character n times using recursion.
     * 
     * <p>This method builds a string containing the specified character repeated 
     * the specified number of times. It uses recursion by concatenating the character 
     * with the result of repeating it (n-1) times.</p>
     * 
     * <p><strong>Examples:</strong></p>
     * <ul>
     *   <li>makeString('A', 5) returns "AAAAA"</li>
     *   <li>makeString('*', 3) returns "***"</li>
     *   <li>makeString('X', 1) returns "X"</li>
     *   <li>makeString('Z', 0) returns "" (empty string)</li>
     *   <li>makeString(' ', 4) returns "    " (4 spaces)</li>
     * </ul>
     * 
     * @param first the character to repeat
     * @param n the number of times to repeat the character (must be >= 0)
     * @return a string containing the character repeated n times, or empty string if n is 0
     */
    public static String makeString(char first, int n) {
    	// Insert your code here. You may want to change the return value. 
    			if(n==0) return "";
    			else return first + makeString(first, n-1);

    }
    
    //Method 3
    /**
     * Creates an interlaced string by alternating between two strings for n positions.
     * The pattern alternates starting with 'first', then 'second', then 'first', etc.
     * 
     * <p>This method builds a string by placing the two input strings in alternating
     * positions. If n is odd, it starts and ends with 'first'. If n is even, it 
     * starts with 'first' and ends with 'second'.</p>
     *  
     * <p><strong>Examples:</strong></p>
     * <ul>
     *   <li>interlace("A", "B", 5) returns "ABABA" (A-B-A-B-A)</li>
     *   <li>interlace("X", "Y", 4) returns "XYXY" (X-Y-X-Y)</li>
     *   <li>interlace("Hello", "World", 3) returns "HelloWorldHello"</li>
     *   <li>interlace("*", "-", 6) returns "*-*-*-"</li>
     *   <li>interlace("A", "B", 1) returns "A" (only first string)</li>
     *   <li>interlace("A", "B", 0) returns "" (empty string)</li>
     * </ul>
     * @param first the string to place at odd positions (1st, 3rd, 5th, etc.)
     * @param second the string to place at even positions (2nd, 4th, 6th, etc.)
     * @param n the total number of positions to fill (must be >= 0)
     * @return an interlaced string with alternating first and second strings,
     *         or empty string if n is 0
     */
    public static String interlace(String first, String second, int n) {
    	// Insert your code here. You may want to change the return value. 
    			if (n == 0) return "";
    			else return first + interlace(second, first, n-1);

    }
    
    //Method 4
    /**
     * Extracts the substring between the first occurrence of 'open' character and 
     * the last occurrence of 'close' character by recursively trimming characters 
     * from both ends until the desired pattern is found. 
     * <p><strong>Examples:</strong></p>
     * <ul>
     *   <li>getSubstring("(hello world)", '(', ')') returns "hello world"</li>
     *   <li>getSubstring("<<data>>", '<', '>') returns "data"</li>
     *   <li>getSubstring("abc[test]xyz", '[', ']') returns "test"</li>
     *   <li>getSubstring("{content}", '{', '}') returns "content"</li>
     *   <li>getSubstring("\"quoted text\"", '"', '"') returns "quoted text"</li>
     *   <li>getSubstring("nomatch", '(', ')') → undefined behavior (no delimiters)</li>
     * </ul>
     * 
     * @param str the input string to search within (must not be null or empty)
     * @param open the opening delimiter character to find
     * @param close the closing delimiter character to find  
     * @return the substring between the first 'open' and last 'close' characters,
     *         excluding the delimiter characters themselves
     * @pre The given str contains only one open and one close character.
     */
    public static String getSubstring(String str, char open, char close) {
    	// Insert your code here. You may want to change the return value.
    	if(str.length() <= 1) {
    		return str;
    	}
    	if(str.charAt(0) == open && str.charAt(str.length()-1) == close) {
    		return str.substring(1, str.length()-1);
    	}
    	else if(str.charAt(str.length()-1) ==close) {
    		return getSubstring(str.substring(1), open, close);
    	}
    	else if(str.charAt(0) == open) {
    		return getSubstring(str.substring(0, str.length()-1), open, close);
    	}
    	else return getSubstring(str.substring(1, str.length()-1), open, close);
    }
    
    //Method 5
    /**
     * Converts a decimal (base-10) integer to its binary (base-2) string representation
     * using recursive division by 2 algorithm.
     * 
     * <p>This method implements the classic "divide by 2" algorithm for binary conversion:</p>
     * <ul>
     *   <li>Recursively divide the number by 2</li>
     *   <li>The remainder (0 or 1) becomes the rightmost binary digit</li>
     *   <li>The quotient is processed recursively for the remaining digits</li>
     *   <li>Base case: when value ≤ 1, return the value as string</li>
     * </ul>
     * @param value the non-negative decimal integer to convert (should be ≥ 0)
     * @return the binary string representation of the input value
     */
    public static String decimalToBinary(int value) {
    	// Insert your code here. You may want to change the return value. 
  if(value == 0) return "0";
    			if (value == 1) return "1";
    			else {
    				String r = "";
    				if (value%2 == 0) {
    					r = "0";
    				}
    				else {
    					r = "1";
    				}
    				return decimalToBinary(value/2) + r;
    			}
    }
}


