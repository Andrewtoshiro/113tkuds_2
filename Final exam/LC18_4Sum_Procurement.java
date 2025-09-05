import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Solves the 4Sum problem.
 * The context is a government procurement system trying to find all unique combinations of four items
 * whose prices sum exactly to a target budget, ensuring no waste or shortfall.
 */
public class LC18_4Sum_Procurement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of items and the target budget
        int n = scanner.nextInt();
        long target = scanner.nextLong(); // Use long for target to avoid overflow in sum calculations

        // Read the prices of the items
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        List<List<Integer>> result = fourSum(nums, target);

        // Print the results
        for (List<Integer> quadruplet : result) {
            System.out.println(quadruplet.get(0) + " " + quadruplet.get(1) + " " + quadruplet.get(2) + " " + quadruplet.get(3));
        }

        scanner.close();
    }

    /**
     * Finds all unique quadruplets in the array that sum up to the target.
     * @param nums The input array of integers (prices).
     * @param target The target sum (budget).
     * @return A list of unique quadruplets.
     */
    public static List<List<Integer>> fourSum(int[] nums, long target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        // Sorting is essential for the two-pointer approach and for handling duplicates.
        Arrays.sort(nums);

        // Fix the first element (i)
        for (int i = 0; i < nums.length - 3; i++) {
            // Skip duplicates for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Fix the second element (j)
            for (int j = i + 1; j < nums.length - 2; j++) {
                // Skip duplicates for the second element
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // Now, solve the 2Sum problem for the rest of the array
                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    // Use long for the sum to prevent potential integer overflow
                    long currentSum = (long)nums[i] + nums[j] + nums[left] + nums[right];

                    if (currentSum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicates for the third element
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // Skip duplicates for the fourth element
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        // Move pointers to find the next unique pair
                        left++;
                        right--;
                    } else if (currentSum < target) {
                        // Sum is too small, need larger numbers
                        left++;
                    } else { // currentSum > target
                        // Sum is too large, need smaller numbers
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
// This solution effectively reduces the K-Sum problem to a (K-1)Sum problem.
// By fixing two elements, it becomes a standard 2-Sum problem solvable with two pointers.
// Sorting the array initially is the key that enables both the two-pointer scan and duplicate skipping.
