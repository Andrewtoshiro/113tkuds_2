import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<String> generateParenthesis(int n) {
    List<String> ans = new ArrayList<>();
    dfs(n, n, new StringBuilder(), ans);
    return ans;
  }

  private void dfs(int l, int r, StringBuilder sb, List<String> ans) {
    if (l == 0 && r == 0) {
      ans.add(sb.toString());
      return;
    }

    if (l > 0) {
      sb.append("(");
      dfs(l - 1, r, sb, ans);
      sb.deleteCharAt(sb.length() - 1);
    }
    if (l < r) {
      sb.append(")");
      dfs(l, r - 1, sb, ans);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}

// This solution employs a backtracking algorithm using a recursive DFS (Depth-First Search) helper function.
// The parameters 'l' and 'r' represent the number of remaining left and right parentheses we can still add.
// The key condition 'l < r' ensures we only add a closing parenthesis if there's a corresponding open one, guaranteeing a well-formed result.
