import java.util.Scanner;

/**
 * Solves the "Implement strStr()" problem, also known as finding a substring.
 * This simulates a basic full-text search feature for a city government's notice board,
 * finding the first occurrence of a keyword ('needle') within the full text ('haystack').
 */
public class LC28_StrStr_NoticeSearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the main text and the keyword to search for
        String haystack = scanner.nextLine();
        String needle = scanner.nextLine();

        // Find and print the index
        System.out.println(strStr(haystack, needle));

        scanner.close();
    }

    /**
     * Finds the index of the first occurrence of needle in haystack.
     * @param haystack The string to search in.
     * @param needle The string to search for.
     * @return The index of the first occurrence, or -1 if not found.
     */
    public static int strStr(String haystack, String needle) {
        // Per the problem definition, if the needle is empty, return 0.
        if (needle.isEmpty()) {
            return 0;
        }

        int n = haystack.length();
        int m = needle.length();

        // If the needle is longer than the haystack, it can't be found.
        if (m > n) {
            return -1;
        }

        // Iterate through the haystack for all possible starting positions of the needle.
        // The loop only needs to go up to the point where the remaining haystack
        // is at least as long as the needle.
        for (int i = 0; i <= n - m; i++) {
            // Assume we have a match until proven otherwise.
            boolean isMatch = true;
            // Check if the substring of haystack starting at 'i' matches the needle.
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    // Mismatch found, break the inner loop.
                    isMatch = false;
                    break;
                }
            }
            // If the inner loop completed without a mismatch, we found the solution.
            if (isMatch) {
                return i;
            }
        }

        // If the outer loop finishes, the needle was not found in the haystack.
        return -1;
    }
}
// This is a classic brute-force implementation of substring search.
// The time complexity is O(n*m) in the worst case, where n and m are string lengths.
// While more advanced algorithms like KMP exist, this approach is simple and intuitive.
