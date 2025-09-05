import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Validates the format of an emergency alert string by checking for correctly nested and matched parentheses.
 * This ensures that message templates like '()', '[]', and '{}' are syntactically correct before use.
 */
public class LC20_ValidParentheses_AlertFormat {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(isValid(s));
        scanner.close();
    }

    /**
     * Checks if a string containing just '(', ')', '{', '}', '[' and ']' is valid.
     * @param s The input string.
     * @return true if the string is valid, false otherwise.
     */
    public static boolean isValid(String s) {
        // A stack (using Deque) to keep track of opening brackets.
        Deque<Character> stack = new ArrayDeque<>();

        // Iterate through each character of the string.
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                // If it's an opening bracket, push it onto the stack.
                stack.push(c);
            } else {
                // If it's a closing bracket, the stack cannot be empty.
                if (stack.isEmpty()) {
                    return false;
                }

                // Pop the top and check if it matches the current closing bracket.
                char top = stack.pop();
                if (c == ')' && top != '(') {
                    return false;
                }
                if (c == ']' && top != '[') {
                    return false;
                }
                if (c == '}' && top != '{') {
                    return false;
                }
            }
        }

        // After the loop, a valid string will result in an empty stack.
        return stack.isEmpty();
    }
}
// This solution uses a stack, the classic data structure for this problem.
// The logic is straightforward: push openers, and pop to match closers.
// The final check ensures no opening brackets were left unmatched.
