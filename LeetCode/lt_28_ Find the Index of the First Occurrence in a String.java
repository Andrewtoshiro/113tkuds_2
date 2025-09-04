class Solution {
    /**
     * Find the first occurrence of needle in haystack using a sliding window approach.
     * Returns the index of the first occurrence, or -1 if needle is not found.
     * * @param haystack the string to search in
     * @param needle the string to search for
     * @return the starting index of the first occurrence of needle in haystack, or -1 if not found
     */
    public int strStr(String haystack, String needle) {
        // Handle empty needle case - empty string is always found at index 0
        if (needle.isEmpty()) {
            return 0;
        }

        int haystackLength = haystack.length();
        int needleLength = needle.length();
      
        // Pointers for traversing haystack and needle respectively
        int haystackPointer = 0;  // Current position in haystack
        int needlePointer = 0;     // Current position in needle being matched
      
        // Iterate through the haystack
        while (haystackPointer < haystackLength) {
            // Check if current characters match
            if (haystack.charAt(haystackPointer) == needle.charAt(needlePointer)) {
                // Special case: needle has only one character and it matches
                if (needleLength == 1) {
                    return haystackPointer;
                }
              
                // Move both pointers forward to continue matching
                haystackPointer++;
                needlePointer++;
            } else {
                // Characters don't match - backtrack in haystack
                // Move haystack pointer back to the next starting position
                // (original start position + 1)
                haystackPointer = haystackPointer - needlePointer + 1;
              
                // Reset needle pointer to start matching from beginning
                needlePointer = 0;
            }

            // Check if we've matched the entire needle
            if (needlePointer == needleLength) {
                // Return the starting index of the match
                return haystackPointer - needlePointer;
            }
        }
      
        // Needle not found in haystack
        return -1;
    }
}

// This implementation uses a naive sliding window (or "brute force") approach. It moves a window
// of size `needle.length()` across the `haystack`, but when a mismatch occurs, it has to
// backtrack the main pointer, which can be inefficient.

// The time complexity is O(N * M) in the worst case, where N is the length of the haystack and M
// is the length of the needle. This occurs when many partial matches happen, for example,
// searching for "AAAAX" in "AAAAAAAAAAAAAAAAAB".

// The space complexity is O(1) because it only uses a few integer variables for pointers,
// regardless of the input string sizes. More optimal algorithms like KMP can achieve O(N + M) time.
