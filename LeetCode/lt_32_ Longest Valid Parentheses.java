import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        int maxLength = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // Initialize stack with -1 to handle edge cases and serve as a base for calculations

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if (currentChar == '(') {
                stack.push(i); // Push the index of an opening parenthesis
            } else { // currentChar == ')'
                stack.pop(); // Pop the top element (either an opening parenthesis index or -1)

                if (stack.empty()) {
                    stack.push(i); // If stack becomes empty, push current index as the new base for future calculations
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek()); // Calculate length and update max
                }
            }
        }
        return maxLength;
    }
}

// 1. The algorithm uses a stack to store the indices of characters, with an initial -1 as a starting base for length calculations.
// 2. When a ')' is encountered, popping from the stack effectively pairs it with a previous '('. The length of the valid substring is then the current index minus the index of the element now at the top of the stack.
// 3. If popping for a ')' empties the stack, it means the ')' is unmatched. Its index is then pushed to serve as the new base for any valid parentheses string that might follow it.
