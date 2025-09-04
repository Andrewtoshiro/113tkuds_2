class Solution {
  public int divide(long dividend, long divisor) {
    // -2^{31} / -1 = 2^31 will overflow, so return 2^31 - 1.
    if (dividend == Integer.MIN_VALUE && divisor == -1)
      return Integer.MAX_VALUE;

    final int sign = dividend > 0 ^ divisor > 0 ? -1 : 1;
    long ans = 0;
    long dvd = Math.abs(dividend);
    long dvs = Math.abs(divisor);

    while (dvd >= dvs) {
      long k = 1;
      while (k * 2 * dvs <= dvd)
        k *= 2;
      dvd -= k * dvs;
      ans += k;
    }

    return sign * (int) ans;
  }
}
// This method calculates the quotient of two integers without using division, multiplication, or modulo operators.
// The core logic uses repeated subtraction, but it's optimized by doubling the divisor at each step (binary exponentiation) to speed up the process significantly.
// The sign of the final result is determined upfront using an XOR operation, which efficiently checks if the signs of the dividend and divisor are different.
