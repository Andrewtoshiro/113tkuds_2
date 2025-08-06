public class RecursiveMathCalculator {

    public static int combinations(int n, int k) {
        if (k < 0 || k > n) {
            return 0;
        }
        if (k == 0 || k == n) {
            return 1;
        }
        return combinations(n - 1, k - 1) + combinations(n - 1, k);
    }

    public static long catalan(int n) {
        if (n <= 1) {
            return 1;
        }
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    public static long hanoiMoves(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return 2 * hanoiMoves(n - 1) + 1;
    }

    public static boolean isPalindromeNumber(int num) {
        String s = Integer.toString(Math.abs(num));
        return isPalindromeHelper(s, 0, s.length() - 1);
    }

    private static boolean isPalindromeHelper(String s, int start, int end) {
        if (start >= end) {
            return true;
        }
        if (s.charAt(start) != s.charAt(end)) {
            return false;
        }
        return isPalindromeHelper(s, start + 1, end - 1);
    }

    public static void main(String[] args) {
        System.out.println("====== Recursive Math Calculator Demo ======");

        int n_comb = 6, k_comb = 3;
        System.out.printf("\n1. Combinations C(%d, %d): %d\n", n_comb, k_comb, combinations(n_comb, k_comb));

        System.out.println("\n2. Catalan Numbers:");
        for (int i = 0; i < 6; i++) {
            System.out.printf("   Catalan(%d): %d\n", i, catalan(i));
        }

        int disks = 4;
        System.out.printf("\n3. Tower of Hanoi moves for %d disks: %d\n", disks, hanoiMoves(disks));

        System.out.println("\n4. Palindrome Number Check:");
        int num1 = 12321;
        int num2 = 12345;
        int num3 = 7;
        System.out.printf("   Is %d a palindrome? %s\n", num1, isPalindromeNumber(num1));
        System.out.printf("   Is %d a palindrome? %s\n", num2, isPalindromeNumber(num2));
        System.out.printf("   Is %d a palindrome? %s\n", num3, isPalindromeNumber(num3));

        System.out.println("\n==========================================");
    }
}
