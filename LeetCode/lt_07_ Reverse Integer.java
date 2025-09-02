class Solution {
  public int reverse(int x) {
    long ans = 0;

    while (x != 0) {
      ans = ans * 10 + x % 10;
      x /= 10;
    }

    return (ans < Integer.MIN_VALUE || ans > Integer.MAX_VALUE) ? 0 : (int) ans;
  }
}

// 1. The core logic uses a while loop to mathematically reverse the integer by
//    repeatedly extracting the last digit with the modulo operator (%) and
//    appending it to the result.

// 2. A 'long' type is used for the result variable 'ans' to temporarily hold the
//    reversed number, which prevents premature overflow during calculation.

// 3. The final return statement checks if the completed 'long' result falls
//    outside the 32-bit integer range; if it does, 0 is returned to indicate
//    an overflow, otherwise the result is cast to an 'int'.
