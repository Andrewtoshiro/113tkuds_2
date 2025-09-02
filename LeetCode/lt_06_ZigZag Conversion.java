import java.util.ArrayList;
import java.util.List;

class Solution {
  public String convert(String s, int numRows) {
    if (numRows == 1)
      return s;
      
    StringBuilder sb = new StringBuilder();
    List<Character>[] rows = new List[numRows];
    int k = 0;
    int direction = -1;

    for (int i = 0; i < numRows; ++i)
      rows[i] = new ArrayList<>();

    for (final char c : s.toCharArray()) {
      rows[k].add(c);
      if (k == 0 || k == numRows - 1)
        direction *= -1;
      k += direction;
    }

    for (List<Character> row : rows)
      for (final char c : row)
        sb.append(c);

    return sb.toString();
  }
}

// 1. This algorithm simulates the zigzag pattern by creating a list of characters for
//    each row and distributing the input string's characters among them.

// 2. A 'direction' variable controls the vertical movement, flipping between down (+1)
//    and up (-1) whenever the top or bottom row is reached.

// 3. After the entire string is processed into the row lists, the final result is
//    constructed by concatenating the characters from each row in order.
