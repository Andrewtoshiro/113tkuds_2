class Solution {
  public boolean isPalindrome(int x) {
    if (x < 0)
      return false;

    long reversed = 0;
    int y = x;

    while (y > 0) {
      reversed = reversed * 10 + y % 10;
      y /= 10;
    }

    return reversed == x;
  }
}

// 1. The function first handles the edge case by immediately returning false for
//    any negative number, as they cannot be palindromes.

// 2. A 'while' loop is used to mathematically reverse the integer by extracting the
//    last digit and prepending it to the 'reversed' variable.

// 3. The final step compares the fully reversed number with the original integer;
//    if they are identical, the number is a palindrome.
