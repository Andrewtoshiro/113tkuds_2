class Solution {
  public int lengthOfLongestSubstring(String s) {
    int ans = 0;
    int[] count = new int[128];

    for (int l = 0, r = 0; r < s.length(); ++r) {
      ++count[s.charAt(r)];
      while (count[s.charAt(r)] > 1)
        --count[s.charAt(l++)];
      ans = Math.max(ans, r - l + 1);
    }

    return ans;
  }
}

// 1. This solution uses the 'sliding window' technique with two pointers, 'l' (left)
//    and 'r' (right), to efficiently find the longest substring without repeats.

// 2. An integer array 'count' acts as a frequency map for ASCII characters, allowing
//    for constant-time checks to see if a character already exists in the current window.

// 3. The 'r' pointer always expands the window, while the 'l' pointer only moves to
//    shrink the window from the left when a duplicate character is detected.
