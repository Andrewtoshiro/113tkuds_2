class Solution {
  public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();

    for (final char c : s.toCharArray())
      if (c == '(')
        stack.push(')');
      else if (c == '{')
        stack.push('}');
      else if (c == '[')
        stack.push(']');
      else if (stack.isEmpty() || stack.pop() != c)
        return false;

    return stack.isEmpty();
  }
}
// This solution utilizes a stack to validate the order of parentheses.
// When an opening bracket is encountered, its corresponding closing bracket is pushed onto the stack.
// A closing bracket is valid only if it matches the item at the top of the stack, which is then popped.
