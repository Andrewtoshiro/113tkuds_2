import java.lang.StringBuilder;

class Solution {
    public String intToRoman(int num) {
        // Mappings of values and symbols, sorted in descending order
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder result = new StringBuilder();
        
        // Iterate through the values from largest to smallest
        for (int i = 0; i < values.length; i++) {
            // Repeatedly subtract the current value if the number is large enough
            while (num >= values[i]) {
                num -= values[i];
                result.append(symbols[i]);
            }
        }
        
        return result.toString();
    }
}

// 1. This solution employs a greedy algorithm that always picks the largest possible
//    Roman numeral value that can be subtracted from the remaining number.

// 2. The code iterates through a pre-sorted list of values, and a while loop appends
//    the corresponding symbol and reduces the number until it's smaller than the
//    current value.

// 3. Including special subtractive forms (like 900 for 'CM' and 4 for 'IV') in the
//    sorted lists allows the greedy approach to work correctly and simplifies the logic.
