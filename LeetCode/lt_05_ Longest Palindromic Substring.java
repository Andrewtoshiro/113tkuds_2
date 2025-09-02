class Solution {
  public String longestPalindrome(String s) {
    if (s.isEmpty())
      return "";

    // (start, end) indices of the longest palindrome in s
    int[] indices = {0, 0};

    for (int i = 0; i < s.length(); ++i) {
      int[] indices1 = extend(s, i, i);
      if (indices1[1] - indices1[0] > indices[1] - indices[0])
        indices = indices1;
      if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
        int[] indices2 = extend(s, i, i + 1);
        if (indices2[1] - indices2[0] > indices[1] - indices[0])
          indices = indices2;
      }
    }

    return s.substring(indices[0], indices[1] + 1);
  }

  // Returns the (start, end) indices of the longest palindrome extended from
  // the substring s[i..j].
  private int[] extend(final String s, int i, int j) {
    for (; i >= 0 && j < s.length(); --i, ++j)
      if (s.charAt(i) != s.charAt(j))
        break;
    return new int[] {i + 1, j - 1};
  }
}

// 1. The core strategy is "expand from center," where each character (and each pair
//    of adjacent, identical characters) is treated as a potential center of a palindrome.

// 2. The main loop iterates through the string, checking for both odd-length palindromes
//    (centered at `i`) and even-length palindromes (centered between `i` and `i+1`).

// 3. The `extend` helper method is responsible for expanding outwards from a given
//    center to find the exact start and end boundaries of the longest possible palindrome.
