import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[] { numMap.get(complement), i };
            }
            numMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

// 1. This solution uses a HashMap to store previously seen numbers and their indices,
//    allowing for a single pass through the array and achieving O(n) time complexity.

// 2. The core logic involves calculating the 'complement' (target - current number) and
//    checking the HashMap to see if this complement has already been processed.

// 3. If the complement is found, the indices are returned immediately; otherwise, the
//    current number and its index are added to the map for future reference.
