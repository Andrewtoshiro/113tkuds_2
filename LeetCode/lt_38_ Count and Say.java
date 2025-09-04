class Solution {
    public String countAndSay(int n) {
        StringBuilder sb = new StringBuilder("1"); // Start with the base case
        
        // Iterate n-1 times to generate the nth term
        while (--n > 0) {
            StringBuilder next = new StringBuilder(); // To build the next sequence term
            
            for (int i = 0; i < sb.length(); ++i) {
                int count = 1; // Initialize count for current character
                
                // Count consecutive identical characters
                while (i + 1 < sb.length() && sb.charAt(i) == sb.charAt(i + 1)) {
                    count++;
                    i++;
                }
                
                // Append count and character to the next sequence
                next.append(count).append(sb.charAt(i));
            }
            sb = next; // Update current sequence for the next iteration
        }
        return sb.toString();
    }
}
/*
1. This solution generates the "Count and Say" sequence iteratively, starting with "1" and building each subsequent term by reading the previous one aloud.
2. The core logic involves scanning the current term, counting consecutive identical digits using a nested loop, and then appending that count followed by the digit to a new sequence builder.
3. Using `StringBuilder` is a key optimization for performance, as it allows for efficient modification and appending of characters without creating a new immutable `String` object in each step of the loop.
*/


