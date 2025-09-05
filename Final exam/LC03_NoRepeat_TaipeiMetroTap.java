import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Finds the length of the longest substring without repeating characters.
 * This is analogous to finding the maximum number of unique passengers
 * tapping their cards in a continuous sequence at a Taipei Metro gate.
 */
public class LC03_NoRepeat_TaipeiMetroTap {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the string representing the card tap sequence
        String s = scanner.nextLine();

        if (s == null || s.isEmpty()) {
            System.out.println(0);
            scanner.close();
            return;
        }

        // Map to store the last seen index of each character (passenger ID)
        Map<Character, Integer> lastSeenIndex = new HashMap<>();
        
        int maxLength = 0;
        int windowStart = 0; // The left pointer of our sliding window

        // Iterate through the string with the right pointer of the window
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char currentChar = s.charAt(windowEnd);

            // If the character is already in our current window,
            // move the start of the window to the right of its last occurrence.
            if (lastSeenIndex.containsKey(currentChar) && lastSeenIndex.get(currentChar) >= windowStart) {
                windowStart = lastSeenIndex.get(currentChar) + 1;
            }

            // Update the last seen index for the current character
            lastSeenIndex.put(currentChar, windowEnd);

            // Calculate the length of the current valid window and update the max length
            int currentLength = windowEnd - windowStart + 1;
            maxLength = Math.max(maxLength, currentLength);
        }

        System.out.println(maxLength);
        scanner.close();
    }
}
// This solution uses the sliding window technique for optimal performance.
// The HashMap tracks character positions, allowing for efficient window adjustments.
// The time complexity is O(n) because each character is visited at most twice.
