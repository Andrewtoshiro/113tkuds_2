import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<String, Boolean> memo;

    public boolean isMatch(String s, String p) {
        memo = new HashMap<>();
        return dp(0, 0, s, p);
    }

    private boolean dp(int i, int j, String s, String p) {
        // Create a key for memoization
        String key = i + "," + j;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Base case: If pattern is fully consumed
        if (j == p.length()) {
            return i == s.length();
        }

        // Check if the current characters match
        boolean firstMatch = (i < s.length()) &&
                             (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');

        // Case: The next character in pattern is '*'
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            // Option 1: '*' matches zero of the preceding element
            boolean matchZero = dp(i, j + 2, s, p);
            // Option 2: '*' matches one or more of the preceding element
            boolean matchOneOrMore = firstMatch && dp(i + 1, j, s, p);

            memo.put(key, matchZero || matchOneOrMore);
            return matchZero || matchOneOrMore;

        } else { // Case: The next character is NOT '*'
            boolean result = firstMatch && dp(i + 1, j + 1, s, p);
            memo.put(key, result);
            return result;
        }
    }
}

// 1. This solution uses a top-down dynamic programming approach with memoization to
//    solve the regular expression matching problem recursively.

// 2. The logic for the '*' character involves exploring two possibilities: either the '*'
//    matches zero preceding elements (by skipping ahead in the pattern) or it matches
//    one or more (by consuming a character from the string).

// 3. A HashMap is used for memoization, storing the results of subproblems to avoid
//    redundant computations and significantly improve the algorithm's efficiency.
