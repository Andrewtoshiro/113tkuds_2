import java.util.Scanner;

/**
 * Solves the "Remove Element" problem.
 * This scenario is analogous to updating a recycling center's inventory list by removing
 * all items of a specific category ('val') that has been discontinued. The removal
 * is done in-place for efficiency.
 */
public class LC27_RemoveElement_Recycle {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of items and the value to remove
        int n = scanner.nextInt();
        int val = scanner.nextInt();

        // Read the list of items
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // Perform the in-place removal
        int k = removeElement(nums, val);

        // Print the new length
        System.out.println(k);

        // Print the resulting elements
        // The order of the remaining elements might change.
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + (i == k - 1 ? "" : " "));
        }
        System.out.println();

        scanner.close();
    }

    /**
     * Removes all instances of a given value in-place from an array.
     * @param nums The input array.
     * @param val The value to remove.
     * @return The new length of the array after removal.
     */
    public static int removeElement(int[] nums, int val) {
        // 'writePtr' keeps track of the next position to place an element
        // that is NOT equal to 'val'.
        int writePtr = 0;

        // Iterate through the array with 'readPtr'.
        for (int readPtr = 0; readPtr < nums.length; readPtr++) {
            // If the current element is one we want to keep...
            if (nums[readPtr] != val) {
                // ...copy it to the 'writePtr' position.
                nums[writePtr] = nums[readPtr];
                // And move the 'writePtr' forward.
                writePtr++;
            }
        }

        // 'writePtr' now represents the number of elements kept, which is the new length.
        return writePtr;
    }
}
// This solution uses a single-pass, two-pointer approach.
// It's highly efficient with O(n) time and O(1) space complexity.
// The logic separates the array into "kept" and "to-be-overwritten" sections.
