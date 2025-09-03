import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }

        Arrays.sort(nums); // Sort the array to efficiently use two pointers and handle duplicates

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate values for the first element of the triplet
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicate values for the second and third elements
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // Sum is too small, move left pointer to increase sum
                } else {
                    right--; // Sum is too large, move right pointer to decrease sum
                }
            }
        }
        return result;
    }
}

// 1. This solution first sorts the array, then iterates through it, fixing one
//    element and using a two-pointer approach on the rest of the array.

// 2. For each fixed element, 'left' and 'right' pointers converge to find a pair
//    that sums to the target, adjusting inward based on whether the sum is too
//    large or too small.

// 3. Duplicate triplets are avoided by skipping over identical elements for the
//    fixed number and for both pointers after a valid triplet is found.
