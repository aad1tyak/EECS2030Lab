package lab1;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 * Comprehensive test suite for Lab1 recursive methods.
 * Tests are organized by method and executed in alphabetical order.
 * Includes original provided tests plus additional test cases.
 * 
 * @author Sukhwant Sagar
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_Lab1 {

    // ========== TEST CASES FOR sum() METHOD ==========
    
    @Test
    public void test02_sum_zeroToFive() {
        int start = 0;
        int end = 5;
        int sum = 0;
        for (int i = start; i <= end; i++)
            sum += i;
        assertEquals("Failed at sum (" + start + ", " + end + ")", sum, Lab1.sum(start, end));
    }

    @Test
    public void test03_sum_negativeToPositive() {
        int start = -10;
        int end = 10;
        int sum = 0;
        for (int i = start; i <= end; i++)
            sum += i;
        assertEquals("Failed at sum (" + start + ", " + end + ")", sum, Lab1.sum(start, end));
    }
     
    @Test
    public void test05_sum_basicRange() {
        assertEquals("sum(1, 5) should return 15", 15, Lab1.sum(1, 5));
    }
    
    // ========== TEST CASES FOR makeString() METHOD ==========
    
    @Test
    public void test07_makeString_spaceZeroTimes() {
        char init = ' ';
        int n = 0;
        String result = "";
        assertEquals("Failed at makeString(" + init + ", " + n + ")", result, Lab1.makeString(init, n));
    }
      
    @Test
    public void test10_makeString_singleCharacter() {
        assertEquals("makeString('X', 1) should return 'X'", "X", Lab1.makeString('X', 1));
    }
    
    @Test
    public void test11_makeString_digit() {
        assertEquals("makeString('7', 6) should return '777777'", "777777", Lab1.makeString('7', 6));
    }
       

    // ========== TEST CASES FOR interlace() METHOD ==========
    
    @Test
    public void test14_interlace_onePosition() {
        String str = "*";
        assertEquals("Failed: interlace(\"*\",\"-\",1)", str, Lab1.interlace("*", "-", 1));
    }

    @Test
    public void test15_interlace_twoPositions() {
        String str = "*-";
        assertEquals("Failed: interlace(\"*\",\"-\",2)", str, Lab1.interlace("*", "-", 2));
    }
  
    @Test
    public void test18_interlace_longStrings() {
        String result = "Hello World Hello World Hello ";
        assertEquals("Failed: interlace(\"Hello \",\"World \",5)", result, Lab1.interlace("Hello ", "World ", 5));
    }
      

    // ========== TEST CASES FOR getSubstring() METHOD ==========
    
    @Test
    public void test21_getSubstring_mathExpression() {
        String result = " y * z";
        assertEquals("Failed: getSubstring(\"x + y + z - ( y * z) / 3 * n \", \"(\", \")\")", result, Lab1.getSubstring("x + y + z - ( y * z) / 3 * n ", '(', ')'));
    }

    @Test
    public void test22_getSubstring_quotedWord() {
        String result = "good";
        assertEquals("Failed: getSubstring(\"This is a 'good' practice\", '\"', '\"')", result, Lab1.getSubstring("This is a \"good\" practice", '"', '"'));
    }

    @Test
    public void test24_getSubstring_emptySquareBrackets() {
        String result = "";
        assertEquals("Failed: getSubstring(\"[]\", '[', ']')", result, Lab1.getSubstring("[]", '[', ']'));
    }

    // ========== TEST CASES FOR decimalToBinary() METHOD ==========
    
    @Test
    public void test28_decimalToBinary_zero() {
        String binary = "0";
        assertEquals("Failed: decimalToBinary(0)", binary, Lab1.decimalToBinary(0));
    }

   @Test
    public void test31_decimalToBinary_twentyThree() {
        String binary = "10111";
        assertEquals("Failed: decimalToBinary(23)", binary, Lab1.decimalToBinary(23));
    }

       
    @Test
    public void test34_decimalToBinary_eight() {
        assertEquals("decimalToBinary(8) should return '1000'", "1000", Lab1.decimalToBinary(8));
    }
}