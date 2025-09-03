import java.util.Arrays;

class Solution {
  public int threeSumClosest(int[] nums, int target) {
    int ans = nums[0] + nums[1] + nums[2];

    Arrays.sort(nums);

    for (int i = 0; i + 2 < nums.length; ++i) {
      if (i > 0 && nums[i] == nums[i - 1])
        continue;
      // Choose nums[i] as the first number in the triplet, then search the
      // remaining numbers in [i + 1, n - 1].
      int l = i + 1;
      int r = nums.length - 1;
      while (l < r) {
        final int sum = nums[i] + nums[l] + nums[r];
        if (sum == target)
          return sum;
        if (Math.abs(sum - target) < Math.abs(ans - target))
          ans = sum;
        if (sum < target)
          ++l;
        else
          --r;
      }
    }

    return ans;
  }
}

// 1. This solution first sorts the array, which is essential for the two-pointer
//    approach that follows to efficiently find triplets.

// 2. The core logic uses a nested structure: an outer loop to pick the first number,
//    and an inner two-pointer loop (l and r) to find the other two numbers.

// 3. The pointers 'l' and 'r' move inward based on whether the current sum is less
//    than or greater than the target, while the 'ans' variable is continuously
//    updated to store the sum closest to the target found so far.
