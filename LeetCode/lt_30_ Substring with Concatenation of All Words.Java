import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public List<Integer> findSubstring(String s, String[] words) {
    // Handle edge cases where the input string or words array is empty.
    if (s.isEmpty() || words.length == 0)
      return new ArrayList<>();

    final int k = words.length; // Number of words.
    final int n = words[0].length(); // Length of each word.
    List<Integer> ans = new ArrayList<>();
    Map<String, Integer> count = new HashMap<>();

    // Create a frequency map of all words in the 'words' array.
    for (final String word : words)
      count.merge(word, 1, Integer::sum);

    // Iterate through the string 's' with a sliding window of size k * n.
    for (int i = 0; i <= s.length() - k * n; ++i) {
      Map<String, Integer> seen = new HashMap<>();
      int j = 0;
      for (; j < k; ++j) {
        final String word = s.substring(i + j * n, i + j * n + n);
        seen.merge(word, 1, Integer::sum);
        // If a word appears more times than needed or isn't in the original list, break.
        if (seen.get(word) > count.getOrDefault(word, 0))
          break;
      }
      // If the inner loop completed, all words were found correctly.
      if (j == k)
        ans.add(i);
    }

    return ans;
  }
}

// 1. This solution uses a sliding window approach combined with HashMaps to efficiently check substrings.
// 2. A frequency map `count` is first built to know how many times each word in `words` should appear.
// 3. The outer loop iterates through all possible starting indices, while the inner loop validates the current window.
