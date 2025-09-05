import java.util.Scanner;

/**
 * Solves the "Find First and Last Position of Element in Sorted Array" problem.
 * The scenario is to find the first and last occurrence (the "span") of a specific
 * delay level in a chronologically sorted list of events.
 */
public class LC34_SearchRange_DelaySpan {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read array size and target delay level
        int n = scanner.nextInt();
        int target = scanner.nextInt();

        // Read the sorted list of delay levels
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int[] result = searchRange(nums, target);

        // Print the start and end indices
        System.out.println(result[0] + " " + result[1]);

        scanner.close();
    }

    /**
     * Finds the starting and ending positions of a given target value.
     * @param nums The sorted array to search in.
     * @param target The value to find.
     * @return An array of two integers {start, end}, or {-1, -1} if not found.
     */
    public static int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        int last = findLast(nums, target);
        return new int[]{first, last};
    }

    /**
     * A modified binary search to find the leftmost (first) occurrence of the target.
     */
    private static int findFirst(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // We found an occurrence. Store it and try to find an earlier one.
                index = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else { // nums[mid] > target
                right = mid - 1;
            }
        }
        return index;
    }

    /**
     * A modified binary search to find the rightmost (last) occurrence of the target.
     */
    private static int findLast(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // We found an occurrence. Store it and try to find a later one.
                index = mid;
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else { // nums[mid] > target
                right = mid - 1;
            }
        }
        return index;
    }
}
// This solution correctly uses two binary searches to meet the O(log n) time complexity.
// One search is biased to find the leftmost boundary, the other to find the rightmost.
// Separating the logic into two helper functions makes the code clean and understandable.
