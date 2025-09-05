import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Generates all possible letter combinations from a phone number string (digits 2-9).
 * This simulates expanding numeric task tags from an old phone keypad system
 * into all possible alphabetic combinations for a customer service center.
 */
public class LC17_PhoneCombos_CSShift {

    // Mapping of digits to letters, matching a standard phone keypad.
    private static final String[] KEYPAD = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String digits = scanner.nextLine();

        List<String> combinations = letterCombinations(digits);
        for (String combo : combinations) {
            System.out.println(combo);
        }

        scanner.close();
    }

    /**
     * Public method to initiate the combination generation.
     * @param digits The input string of digits.
     * @return A list of all possible letter combinations.
     */
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        // If the input is empty, return an empty list as there are no combinations.
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        // Start the backtracking process.
        backtrack(result, digits, new StringBuilder(), 0);
        return result;
    }

    /**
     * The recursive backtracking function to generate combinations.
     * @param result             The list to store final combinations.
     * @param digits             The original input digit string.
     * @param currentCombination The StringBuilder holding the combination being built.
     * @param index              The current digit index in the 'digits' string to process.
     */
    private static void backtrack(List<String> result, String digits, StringBuilder currentCombination, int index) {
        // Base case: If we have processed all digits, we have a complete combination.
        if (index == digits.length()) {
            result.add(currentCombination.toString());
            return;
        }

        // Get the letters corresponding to the current digit.
        String letters = KEYPAD[digits.charAt(index) - '0'];

        // Explore each letter as the next character in the combination.
        for (char letter : letters.toCharArray()) {
            // 1. Choose: Append the letter.
            currentCombination.append(letter);
            // 2. Explore: Recurse with the next digit index.
            backtrack(result, digits, currentCombination, index + 1);
            // 3. Unchoose (Backtrack): Remove the last letter to explore other possibilities.
            currentCombination.deleteCharAt(currentCombination.length() - 1);
        }
    }
}
// This solution uses backtracking, a powerful recursive technique for exploration.
// The StringBuilder is used for efficient string manipulation during recursion.
// The problem is modeled as a tree traversal, finding all paths from root to leaf.
