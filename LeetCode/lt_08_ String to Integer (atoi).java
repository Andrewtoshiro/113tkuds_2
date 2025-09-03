class Solution {
  public int myAtoi(String s) {
    s = s.strip();
    if (s.isEmpty())
      return 0;

    final int sign = s.charAt(0) == '-' ? -1 : 1;
    if (s.charAt(0) == '+' || s.charAt(0) == '-')
      s = s.substring(1);

    long num = 0;

    for (final char c : s.toCharArray()) {
      if (!Character.isDigit(c))
        break;
      num = num * 10 + (c - '0');
      if (sign * num <= Integer.MIN_VALUE)
        return Integer.MIN_VALUE;
      if (sign * num >= Integer.MAX_VALUE)
        return Integer.MAX_VALUE;
    }

    return sign * (int) num;
  }
}

// 1. The code first preprocesses the string by removing leading/trailing whitespace
//    and then determines the sign (+ or -) of the number.

// 2. It iteratively builds the number from digit characters, stopping at the first
//    non-digit character encountered.

// 3. Overflow is handled inside the loop by checking if the number exceeds the 32-bit
//    integer range after each digit is added, clamping the result to Integer.MAX_VALUE
//    or Integer.MIN_VALUE if necessary.
