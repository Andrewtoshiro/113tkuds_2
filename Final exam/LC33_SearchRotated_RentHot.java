import java.util.Scanner;

/**
 * Solves the "Search in Rotated Sorted Array" problem.
 * This scenario simulates searching for a specific equipment ID in a list that was
 * originally sorted but was "rotated" (split and re-joined) during deployment.
 */
public class LC33_SearchRotated_RentHot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of elements and the target ID
        int n = scanner.nextInt();
        int target = scanner.nextInt();

        // Read the rotated array of IDs
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // Perform the search and print the result
        System.out.println(search(nums, target));

        scanner.close();
    }

    /**
     * Searches for a target value in a rotated sorted array.
     * @param nums The rotated sorted array.
     * @param target The value to search for.
     * @return The index of the target if found, otherwise -1.
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // The key is to determine which half of the array is sorted.
            // Case 1: The left half (from left to mid) is sorted.
            if (nums[left] <= nums[mid]) {
                // Check if the target is within the sorted left half.
                if (target >= nums[left] && target < nums[mid]) {
                    // If so, search in the left half.
                    right = mid - 1;
                } else {
                    // Otherwise, the target must be in the unsorted right half.
                    left = mid + 1;
                }
            } 
            // Case 2: The right half (from mid to right) is sorted.
            else {
                // Check if the target is within the sorted right half.
                if (target > nums[mid] && target <= nums[right]) {
                    // If so, search in the right half.
                    left = mid + 1;
                } else {
                    // Otherwise, the target must be in the unsorted left half.
                    right = mid - 1;
                }
            }
        }

        // If the loop finishes, the target was not found.
        return -1;
    }
}
// This solution is a clever modification of the standard binary search.
// In each step, it identifies the sorted subarray and narrows the search space accordingly.
// This approach guarantees a time complexity of O(log n), meeting the problem's requirements.
