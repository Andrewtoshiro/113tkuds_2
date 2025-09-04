class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Calculate middle index to prevent overflow

            if (nums[mid] == target) {
                return mid; // Target found, return its index
            } else if (nums[mid] < target) {
                left = mid + 1; // Target is in the right half
            } else {
                right = mid - 1; // Target is in the left half
            }
        }
        // If the loop finishes, the target was not found.
        // 'left' now represents the correct insertion point.
        return left;
    }
}

// This solution uses a binary search algorithm for efficient searching.
// The time complexity is O(log n), making it very fast for large arrays.
// The space complexity is O(1) as it uses a constant amount of extra memory.
