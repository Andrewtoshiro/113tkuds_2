/**
 * A calculator for various mathematical problems using recursive methods.
 * This implementation is based on the requirements of Exercise 2.1 from the provided document.
 */
public class RecursiveMathCalculator {

    public static void main(String[] args) {
        System.out.println("====== Recursive Math Calculator Demo ======");

        // 1. Combination (nCk)
        System.out.println("\n--- 1. Combinations C(n, k) ---");
        int n = 5, k = 2;
        System.out.printf("C(%d, %d) = %d\n", n, k, combinations(n, k));

        // 2. Catalan Numbers
        System.out.println("\n--- 2. Catalan Numbers C(n) ---");
        int catalanN = 4;
        System.out.printf("Catalan number C(%d) = %d\n", catalanN, catalan(catalanN));

        // 3. Tower of Hanoi Steps
        System.out.println("\n--- 3. Tower of Hanoi Steps hanoi(n) ---");
        int hanoiDisks = 3;
        System.out.printf("Steps to solve Tower of Hanoi with %d disks: %d\n", hanoiDisks, hanoiSteps(hanoiDisks));

        // 4. Palindrome Number
        System.out.println("\n--- 4. Palindrome Number Check ---");
        int num1 = 12321;
        int num2 = 12345;
        System.out.printf("Is %d a palindrome? %s\n", num1, isPalindrome(num1));
        System.out.printf("Is %d a palindrome? %s\n", num2, isPalindrome(num2));
    }

    /**
     * Calculates the combination "n choose k" using the recursive formula
     * [span_0](start_span)C(n,k) = C(n-1, k-1) + C(n-1, k).[span_0](end_span)
     */
    public static long combinations(int n, int k) {
        // Base cases
        if (k < 0 || k > n) {
            return 0;
        }
        if (k == 0 || k == n) {
            return 1;
        }
        // Recursive step
        return combinations(n - 1, k - 1) + combinations(n - 1, k);
    }

    /**
     * Calculates the nth Catalan number using the recursive formula
     * [span_1](start_span)C(n) = Σ(C(i) * C(n-1-i)) for i from 0 to n-1.[span_1](end_span)
     */
    public static long catalan(int n) {
        // Base case
        if (n <= 1) {
            return 1;
        }
        // Recursive step
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    /**
     * Calculates the number of steps to solve the Tower of Hanoi puzzle using
     * [span_2](start_span)the recursive formula hanoi(n) = 2 * hanoi(n-1) + 1.[span_2](end_span)
     */
    public static long hanoiSteps(int n) {
        // Base case
        if (n <= 1) {
            return n;
        }
        // Recursive step
        return 2 * hanoiSteps(n - 1) + 1;
    }

    /**
     * [span_3](start_span)Checks if a number is a palindrome.[span_3](end_span)
     * This method converts the number to a string and uses a recursive helper.
     */
    public static boolean isPalindrome(int number) {
        String s = Integer.toString(number);
        return isPalindromeHelper(s, 0, s.length() - 1);
    }

    /**
     * Recursive helper to check if a string is a palindrome.
     */
    private static boolean isPalindromeHelper(String s, int start, int end) {
        // Base case: If we have one or zero characters left to check.
        if (start >= end) {
            return true;
        }
        // Recursive step: Check if the outer characters match and recurse inward.
        if (s.charAt(start) != s.charAt(end)) {
            return false;
        }
        return isPalindromeHelper(s, start + 1, end - 1);
    }
}
