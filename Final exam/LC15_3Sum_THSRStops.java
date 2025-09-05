import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Solves the 3Sum problem.
 * This is analogous to finding three high-speed rail station adjustments (positive or negative)
 * that sum to zero, indicating a self-balancing change within the system.
 */
public class LC15_3Sum_THSRStops {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of stations/adjustments
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // The core logic to find the triplets
        List<List<Integer>> result = threeSum(nums);

        // Print the results
        for (List<Integer> triplet : result) {
            System.out.println(triplet.get(0) + " " + triplet.get(1) + " " + triplet.get(2));
        }

        scanner.close();
    }

    /**
     * Finds all unique triplets in the array which give the sum of zero.
     * @param nums The input array of integers.
     * @return A list of unique triplets.
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        // Sort the array first. This is key to using the two-pointer approach
        // and for efficiently skipping duplicates.
        Arrays.sort(nums);
        
        List<List<Integer>> result = new ArrayList<>();

        // Iterate through the array, fixing the first element of the potential triplet.
        for (int i = 0; i < nums.length - 2; i++) {
            // Optimization: If the current number is positive, the sum of three
            // sorted numbers can never be zero.
            if (nums[i] > 0) {
                break;
            }

            // Skip duplicate values for the first element to avoid duplicate triplets.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            // Use the two-pointer technique on the rest of the array.
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // Found a triplet
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates for the second element
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // Skip duplicates for the third element
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // Move pointers to find the next potential unique triplet
                    left++;
                    right--;
                } else if (sum < 0) {
                    // Sum is too small, need a larger number, so move the left pointer.
                    left++;
                } else { // sum > 0
                    // Sum is too big, need a smaller number, so move the right pointer.
                    right--;
                }
            }
        }
        return result;
    }
}
// The solution's efficiency comes from sorting the array first.
// The two-pointer technique then reduces the search space in linear time for each fixed element.
// Careful handling of duplicates is crucial for the correctness of the output.
