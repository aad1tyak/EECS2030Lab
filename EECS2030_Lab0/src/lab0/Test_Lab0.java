package lab0;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

/**
 * Comprehensive test cases for Lab0 methods
 * 
 * @author Sukhwant Sagar
 */
public class Test_Lab0 {

    // Test cases for calculateBMI() method
    @Test
    @Order(1)
    public void test01_CalculateBMI_NormalValues() {
        // Test normal BMI calculations
        assertEquals(22.9, Lab0.calculateBMI(70.0, 1.75), 0.01);
        assertEquals(26.2, Lab0.calculateBMI(85.0, 1.80), 0.01);
        assertEquals(18.4, Lab0.calculateBMI(50.0, 1.65), 0.01);
    }

    @Test
    @Order(2)
    public void test02_CalculateBMI_EdgeCases() {
        // Test edge case: height is zero
        assertEquals(-1.0, Lab0.calculateBMI(70.0, 0.0), 0.01);
        
        // Test edge case: height is negative
        assertEquals(-1.0, Lab0.calculateBMI(70.0, -1.5), 0.01);
        
        // Test edge case: height is extremely small (less than 0.01)
        assertEquals(-1.0, Lab0.calculateBMI(70.0, 0.005), 0.01);
        
        // Test edge case: negative weight
        assertEquals(-1.0, Lab0.calculateBMI(-70.0, 1.75), 0.01);
        
        // Test edge case: both negative
        assertEquals(-1.0, Lab0.calculateBMI(-70.0, -1.75), 0.01);
    }

    @Test
    @Order(3)
    public void test03_CalculateBMI_BoundaryValues() {
        // Test with minimum acceptable height (0.01)
        assertEquals(700000.0, Lab0.calculateBMI(70.0, 0.01), 0.1);
        
        // Test with weight = 0 (should be valid)
        assertEquals(0.0, Lab0.calculateBMI(0.0, 1.75), 0.01);
        
        // Test potential overflow scenarios
        assertEquals(-1.0, Lab0.calculateBMI(Double.MAX_VALUE, 0.001), 0.01);
    }

    @Test
    @Order(4)
    public void test04_CalculateBMI_RoundingCheck() {
        // Test rounding to one decimal place
        assertEquals(25.0, Lab0.calculateBMI(81.0, 1.8), 0.01);  // Should be 25.0
        assertEquals(24.7, Lab0.calculateBMI(80.0, 1.8), 0.01);  // Should be 24.7
    }

    @Test
    @Order(5)
    public void test05_CalculateBMI_InfiniteAndNaN() {
        // Test cases that would produce infinite BMI
        assertEquals(-1.0, Lab0.calculateBMI(Double.MAX_VALUE, 0.0001), 0.01);
        assertEquals(-1.0, Lab0.calculateBMI(1e308, 0.01), 0.01);
        
        // Test with Double.POSITIVE_INFINITY as weight
        assertEquals(-1.0, Lab0.calculateBMI(Double.POSITIVE_INFINITY, 1.75), 0.01);
        
        // Test with Double.NEGATIVE_INFINITY as weight
        assertEquals(-1.0, Lab0.calculateBMI(Double.NEGATIVE_INFINITY, 1.75), 0.01);
        
        // Test with Double.POSITIVE_INFINITY as height
        assertEquals(-1.0, Lab0.calculateBMI(70.0, Double.POSITIVE_INFINITY), 0.01);
        
        // Test cases that would produce NaN
        assertEquals(-1.0, Lab0.calculateBMI(Double.NaN, 1.75), 0.01);
        assertEquals(-1.0, Lab0.calculateBMI(70.0, Double.NaN), 0.01);
        assertEquals(-1.0, Lab0.calculateBMI(Double.NaN, Double.NaN), 0.01);
        
        // Test division by extremely small number that causes overflow
        assertEquals(-1.0, Lab0.calculateBMI(Double.MAX_VALUE, Double.MIN_VALUE), 0.01);
        
        // Test 0/0 scenario (weight=0, height=0)
        assertEquals(-1.0, Lab0.calculateBMI(0.0, 0.0), 0.01);
    }

    // Test cases for findLongestWord() method
    @Test
    @Order(6)
    public void test06_FindLongestWord_NormalCases() {
        ArrayList<String> words1 = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "date"));
        assertEquals("banana", Lab0.findLongestWord(words1));
        
        ArrayList<String> words2 = new ArrayList<>(Arrays.asList("cat", "dog", "elephant", "bird"));
        assertEquals("elephant", Lab0.findLongestWord(words2));
        
        ArrayList<String> words3 = new ArrayList<>(Arrays.asList("programming", "java", "eclipse"));
        assertEquals("programming", Lab0.findLongestWord(words3));
    }
    
   
    @Test
    @Order(7)
    public void test07_FindLongestWord_EqualLength() {
        // Test when multiple words have same length - should return first one
        ArrayList<String> words = new ArrayList<>(Arrays.asList("hello", "world", "tests"));
        assertEquals("hello", Lab0.findLongestWord(words));
    }

    @Test
    @Order(8)
    public void test08_FindLongestWord_EdgeCases() {
        // Test empty list
        ArrayList<String> emptyList = new ArrayList<>();
        assertEquals("", Lab0.findLongestWord(emptyList));
        
        // Test null list
        assertEquals("", Lab0.findLongestWord(null));
        
        // Test single word
        ArrayList<String> singleWord = new ArrayList<>(Arrays.asList("single"));
        assertEquals("single", Lab0.findLongestWord(singleWord));
    }

    @Test
    @Order(9)
    public void test09_FindLongestWord_WithEmptyStrings() {
        ArrayList<String> words = new ArrayList<>(Arrays.asList("", "hello", "", "world"));
        assertEquals("hello", Lab0.findLongestWord(words));
        
        // Test list with only empty strings
        ArrayList<String> emptyStrings = new ArrayList<>(Arrays.asList("", "", ""));
        assertEquals("", Lab0.findLongestWord(emptyStrings));
    }

    @Test
    @Order(10)
    public void test10_FindLongestWord_WithNullElements() {
        ArrayList<String> words = new ArrayList<>();
        words.add("hello");
        words.add(null);
        words.add("world");
        assertEquals("hello", Lab0.findLongestWord(words));
    }

    // Test cases for countVowels() method
    @Test
    @Order(11)
    public void test11_CountVowels_NormalCases() {
        assertEquals(3, Lab0.countVowels("Hello World"));
        assertEquals(3, Lab0.countVowels("PROGRAMMING"));
        assertEquals(5, Lab0.countVowels("bEaUtiful"));
        assertEquals(5, Lab0.countVowels("education"));
    }

    @Test
    @Order(12)
    public void test12_CountVowels_CaseInsensitive() {
        assertEquals(10, Lab0.countVowels("AEIOUaeiou"));
        assertEquals(3, Lab0.countVowels("HeLLo WoRLd"));
        assertEquals(1, Lab0.countVowels("tEsT"));
    }

    @Test
    @Order(13)
    public void test13_CountVowels_NoVowels() {
        assertEquals(0, Lab0.countVowels("xyz"));
        assertEquals(0, Lab0.countVowels("bcdfg"));
        assertEquals(0, Lab0.countVowels("123"));
    }

    @Test
    @Order(14)
    public void test14_CountVowels_EdgeCases() {
        // Test empty string
        assertEquals(0, Lab0.countVowels(""));
        
        // Test null string
        assertEquals(0, Lab0.countVowels(null));
        
        // Test string with only vowels
        assertEquals(5, Lab0.countVowels("aeiou"));
        assertEquals(5, Lab0.countVowels("AEIOU"));
    }

    @Test
    @Order(15)
    public void test15_CountVowels_WithSpecialCharacters() {
        assertEquals(4, Lab0.countVowels("hello@world.com"));
        assertEquals(4, Lab0.countVowels("test123!@#aei"));
        assertEquals(1, Lab0.countVowels("$%^&*()a"));
        
        // Test with accented characters (should not count as vowels)
        assertEquals(2, Lab0.countVowels("café résumé")); // only 'a' and 'e' count
    }

    @Test
    @Order(16)
    public void test16_CountVowels_WithSpaces() {
        assertEquals(4, Lab0.countVowels("a e i o"));
        assertEquals(2, Lab0.countVowels("   hello   "));
        assertEquals(0, Lab0.countVowels("   "));
    }

    @Test
    @Order(17)
    public void test17_CountVowels_LongString() {
        String longString = "This is a veEry loOOOng string with many vowels to test the efficiency";
        assertEquals(21, Lab0.countVowels(longString));
        
        // Test extremely long string for performance
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("aeiou");
        }
        assertEquals(50000, Lab0.countVowels(sb.toString()));
    }
}