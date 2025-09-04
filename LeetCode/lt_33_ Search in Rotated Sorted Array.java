public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // Target found
            }

            // Determine which half is sorted
            if (nums[left] <= nums[mid]) { // Left half is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // Target is in the sorted left half
                } else {
                    left = mid + 1; // Target is in the unsorted right half
                }
            } else { // Right half is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // Target is in the sorted right half
                } else {
                    right = mid - 1; // Target is in the unsorted left half
                }
            }
        }
        return -1; // Target not found
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();
        System.out.println(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)); // Output: 4
        System.out.println(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3)); // Output: -1
        System.out.println(solution.search(new int[]{1}, 0)); // Output: -1
    }
}
// This code uses a modified binary search to achieve an O(log n) time complexity.
// The key is to determine which subarray (left or right of mid) is sorted.
// Once the sorted portion is identified, we can easily check if the target lies within its range.
