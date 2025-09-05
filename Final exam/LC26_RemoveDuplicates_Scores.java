import java.util.Scanner;

/**
 * Solves the "Remove Duplicates from Sorted Array" problem.
 * This simulates the process of cleaning up a sorted student score sheet where duplicate
 * entries for the same student ID need to be removed in-place to generate a unique list.
 */
public class LC26_RemoveDuplicates_Scores {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of scores
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // Perform the in-place duplicate removal
        int k = removeDuplicates(nums);

        // Print the new length
        System.out.println(k);

        // Print the resulting unique elements
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + (i == k - 1 ? "" : " "));
        }
        System.out.println();

        scanner.close();
    }

    /**
     * Removes duplicates from a sorted array in-place.
     * @param nums The sorted input array.
     * @return The new length of the array after removing duplicates.
     */
    public static int removeDuplicates(int[] nums) {
        // If the array is empty, there are no elements to process.
        if (nums.length == 0) {
            return 0;
        }

        // 'writePtr' points to the position where the next unique element should be placed.
        // It starts at 1 because the first element is always considered unique.
        int writePtr = 1;

        // 'readPtr' iterates through the array to find new unique elements.
        for (int readPtr = 1; readPtr < nums.length; readPtr++) {
            // If the current element is different from the last-known unique element,
            // it's a new unique element.
            if (nums[readPtr] != nums[writePtr - 1]) {
                // Place the new unique element at the 'writePtr' position.
                nums[writePtr] = nums[readPtr];
                // Move the write pointer forward.
                writePtr++;
            }
        }

        // 'writePtr' now holds the count of unique elements, which is the new length.
        return writePtr;
    }
}
// This solution uses the two-pointer (read/write) technique.
// It achieves the in-place modification with O(1) extra space as required.
// The code is efficient, with a time complexity of O(n) due to a single pass.
