import java.util.HashMap;
import java.util.Map;

class Solution {
    public int romanToInt(String s) {
        // Create a map to store the values of Roman numerals
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        int n = s.length();

        // Iterate through the Roman numeral string
        for (int i = 0; i < n; i++) {
            // Get the value of the current character
            int currentValue = romanMap.get(s.charAt(i));

            // Check if there is a next character and if it forms a subtractive pair
            if (i < n - 1 && currentValue < romanMap.get(s.charAt(i + 1))) {
                // If it's a subtractive pair, subtract the current value
                result -= currentValue;
            } else {
                // Otherwise, add the current value
                result += currentValue;
            }
        }

        return result;
    }
}
