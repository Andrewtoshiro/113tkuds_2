class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};

        // Find the first occurrence of the target
        int firstOccurrence = findBound(nums, target, true);

        // If the target isn't found, return [-1, -1] immediately
        if (firstOccurrence == -1) {
            return result;
        }

        // Find the last occurrence of the target
        int lastOccurrence = findBound(nums, target, false);

        result[0] = firstOccurrence;
        result[1] = lastOccurrence;

        return result;
    }

    /**
     * Helper function to find either the first or the last occurrence of a target.
     * @param nums The sorted array to search in.
     * @param target The value to search for.
     * @param findFirst If true, finds the leftmost boundary; otherwise, finds the rightmost.
     * @return The index of the found boundary, or -1 if not found.
     */
    private int findBound(int[] nums, int target, boolean findFirst) {
        int low = 0;
        int high = nums.length - 1;
        int ans = -1;

        while (low <= high) {
            // Standard binary search mid-point calculation to prevent overflow
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                ans = mid; // Found a potential answer
                if (findFirst) {
                    high = mid - 1; // Try to find an even earlier occurrence on the left
                } else {
                    low = mid + 1; // Try to find a later occurrence on the right
                }
            } else if (nums[mid] < target) {
                low = mid + 1; // Target is in the right half
            } else {
                high = mid - 1; // Target is in the left half
            }
        }
        return ans;
    }
}

// This solution cleverly uses a modified binary search twice to find the start and end indices.
// The `findFirst` boolean parameter in the helper function is the key to controlling the search direction after a target is found.
// The time complexity is O(log n) because the algorithm is based on binary search, which is highly efficient for sorted arrays.
