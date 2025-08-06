import java.util.Stack;

public class RecursionVsIteration {

    // --- 1. Binomial Coefficient C(n, k) ---

    // Inefficient for larger n, k due to re-calculating subproblems.
    public static long binomialCoefficient_recursive(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;
        return binomialCoefficient_recursive(n - 1, k - 1) + binomialCoefficient_recursive(n - 1, k);
    }

    // Efficient, uses dynamic programming to avoid re-calculation.
    public static long binomialCoefficient_iterative(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;
        // C(n, k) is the same as C(n, n-k)
        if (k > n / 2) k = n - k;
        
        long[] dp = new long[k + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, k); j > 0; j--) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[k];
    }

    // --- 2. Product of Array Elements ---

    public static long arrayProduct_recursive(int[] array, int n) {
        if (n <= 0) return 1; // Base case
        return array[n - 1] * arrayProduct_recursive(array, n - 1);
    }

    public static long arrayProduct_iterative(int[] array) {
        long product = 1;
        for (int value : array) {
            product *= value;
        }
        return product;
    }

    // --- 3. Count Vowels in a String ---

    public static int countVowels_recursive(String str) {
        if (str == null || str.isEmpty()) return 0;
        
        char first = Character.toLowerCase(str.charAt(0));
        int count = "aeiou".indexOf(first) != -1 ? 1 : 0;
        
        return count + countVowels_recursive(str.substring(1));
    }

    public static int countVowels_iterative(String str) {
        if (str == null) return 0;
        int count = 0;
        String vowels = "aeiou";
        for (char c : str.toLowerCase().toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    // --- 4. Check for Paired Parentheses ---
    
    // Inefficient due to repeated string creation and replacement.
    public static boolean areParenthesesBalanced_recursive(String str) {
        if (str.isEmpty()) return true;
        
        int originalLength = str.length();
        str = str.replace("()", "").replace("[]", "").replace("{}", "");
        
        // If no pairs were removed, and the string is not empty, it's unbalanced.
        if (str.length() == originalLength) return false;
        
        // Recurse on the modified string.
        return areParenthesesBalanced_recursive(str);
    }

    // Efficient stack-based approach. O(n) time complexity.
    public static boolean areParenthesesBalanced_iterative(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') return false;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') return false;
            } else if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{') return false;
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        System.out.println("====== Recursion vs. Iteration Performance Comparison ======");

        // 1. Binomial Coefficient
        System.out.println("\n--- 1. Binomial Coefficient C(25, 12) ---");
        long startTime = System.nanoTime();
        long result_rec_bin = binomialCoefficient_recursive(25, 12);
        long endTime = System.nanoTime();
        System.out.printf("Recursive Result: %d (took %.3f ms)\n", result_rec_bin, (endTime - startTime) / 1e6);

        startTime = System.nanoTime();
        long result_it_bin = binomialCoefficient_iterative(25, 12);
        endTime = System.nanoTime();
        System.out.printf("Iterative Result: %d (took %.3f ms)\n", result_it_bin, (endTime - startTime) / 1e6);

        // 2. Array Product
        System.out.println("\n--- 2. Product of Array Elements ---");
        int[] productArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        startTime = System.nanoTime();
        long result_rec_prod = arrayProduct_recursive(productArray, productArray.length);
        endTime = System.nanoTime();
        System.out.printf("Recursive Result: %d (took %.3f ms)\n", result_rec_prod, (endTime - startTime) / 1e6);

        startTime = System.nanoTime();
        long result_it_prod = arrayProduct_iterative(productArray);
        endTime = System.nanoTime();
        System.out.printf("Iterative Result: %d (took %.3f ms)\n", result_it_prod, (endTime - startTime) / 1e6);

        // 3. Count Vowels
        System.out.println("\n--- 3. Count Vowels in a String ---");
        String vowelString = "This is a test string to count vowels using recursion and iteration.";
        startTime = System.nanoTime();
        int result_rec_vowel = countVowels_recursive(vowelString);
        endTime = System.nanoTime();
        System.out.printf("Recursive Result: %d (took %.3f ms)\n", result_rec_vowel, (endTime - startTime) / 1e6);

        startTime = System.nanoTime();
        int result_it_vowel = countVowels_iterative(vowelString);
        endTime = System.nanoTime();
        System.out.printf("Iterative Result: %d (took %.3f ms)\n", result_it_vowel, (endTime - startTime) / 1e6);

        // 4. Paired Parentheses
        System.out.println("\n--- 4. Check for Paired Parentheses ---");
        String parensString = "{[()()[]{}][({})]}";
        startTime = System.nanoTime();
        boolean result_rec_paren = areParenthesesBalanced_recursive(parensString);
        endTime = System.nanoTime();
        System.out.printf("Recursive Result: %s (took %.3f ms)\n", result_rec_paren, (endTime - startTime) / 1e6);

        startTime = System.nanoTime();
        boolean result_it_paren = areParenthesesBalanced_iterative(parensString);
        endTime = System.nanoTime();
        System.out.printf("Iterative Result: %s (took %.3f ms)\n", result_it_paren, (endTime - startTime) / 1e6);
        
        System.out.println("\n============================================================");
    }
}
