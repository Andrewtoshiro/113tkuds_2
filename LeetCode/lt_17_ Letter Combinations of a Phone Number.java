import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<String> letterCombinations(String digits) {
    if (digits.isEmpty())
      return new ArrayList<>();

    List<String> ans = new ArrayList<>();

    dfs(digits, 0, new StringBuilder(), ans);
    return ans;
  }

  private static final String[] digitToLetters = {"",    "",    "abc",  "def", "ghi",
                                                  "jkl", "mno", "pqrs", "tuv", "wxyz"};

  private void dfs(String digits, int i, StringBuilder sb, List<String> ans) {
    if (i == digits.length()) {
      ans.add(sb.toString());
      return;
    }

    for (final char c : digitToLetters[digits.charAt(i) - '0'].toCharArray()) {
      sb.append(c);
      dfs(digits, i + 1, sb, ans);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}

// 1. This solution utilizes a recursive depth-first search (DFS) algorithm, a form
//    of backtracking, to generate all possible letter combinations.

// 2. The 'dfs' helper function explores combinations by appending a letter for the
//    current digit and then recursively calling itself for the next digit.

// 3. The key backtracking step, 'sb.deleteCharAt()', undoes the last choice,
//    allowing the function to explore alternative letters for the current digit
//    and thereby traverse all possible combination paths.
