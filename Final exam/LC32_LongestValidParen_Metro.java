import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Finds the length of the longest valid (well-formed) parentheses substring.
 * This simulates analyzing metro station logs ('(' for entry, ')' for exit)
 * to find the longest period of perfectly matched entries and exits.
 */
public class LC32_LongestValidParen_Metro {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(longestValidParentheses(s));
        scanner.close();
    }

    /**
     * Calculates the length of the longest valid parentheses substring using a stack.
     * @param s The input string consisting of '(' and ')'.
     * @return The length of the longest valid substring.
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int maxLength = 0;
        // The stack will store indices of the characters.
        Deque<Integer> stack = new ArrayDeque<>();
        
        // Push -1 to serve as a base for calculating the length of the first valid substring.
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if (currentChar == '(') {
                // If it's an opening bracket, push its index onto the stack.
                stack.push(i);
            } else { // currentChar == ')'
                // If it's a closing bracket, pop the top element.
                stack.pop();

                if (stack.isEmpty()) {
                    // If the stack is empty, it means the current ')' is not matched.
                    // Push the current index to act as a new base for the next substring.
                    stack.push(i);
                } else {
                    // If the stack is not empty, a valid pair is formed.
                    // The length is the current index minus the index of the element
                    // now at the top of the stack.
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }

        return maxLength;
    }
}
// This solution cleverly uses a stack to keep track of indices.
// The initial -1 on the stack simplifies length calculation for valid pairs.
// The time complexity is O(n) as it involves a single pass through the string.
