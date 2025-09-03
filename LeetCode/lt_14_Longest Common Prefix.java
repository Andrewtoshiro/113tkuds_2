class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0]; // Initialize with the first string

        // Iterate through the rest of the strings
        for (int i = 1; i < strs.length; i++) {
            // While the current prefix is not a prefix of the current string
            while (!strs[i].startsWith(prefix)) {
                // Shorten the prefix by removing the last character
                prefix = prefix.substring(0, prefix.length() - 1);
                // If prefix becomes empty, no common prefix exists
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix; // Return the longest common prefix found
    }
}

// 1. This algorithm uses a "horizontal scanning" approach, starting with the first
//    string as the initial prefix and comparing it against all other strings.

// 2. The inner `while` loop shortens the candidate prefix by one character at a time
//    until it matches the beginning of the current string being checked.

// 3. If the prefix ever becomes empty, it means no common prefix exists; otherwise,
//    the final prefix remaining after all comparisons is the correct result.
