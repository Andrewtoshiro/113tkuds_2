import java.util.Scanner;

/**
 * Calculates the median of two sorted arrays of seismic data without merging them.
 * This is a classic computer science problem applied to the context of combining
 * data from two different earthquake monitoring sources.
 */
public class LC04_Median_QuakeFeeds {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the sizes of the two data feeds
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Read the two sorted arrays of seismic data
        double[] feed1 = new double[n];
        for (int i = 0; i < n; i++) {
            feed1[i] = scanner.nextDouble();
        }

        double[] feed2 = new double[m];
        for (int i = 0; i < m; i++) {
            feed2[i] = scanner.nextDouble();
        }

        double median = findMedianSortedArrays(feed1, feed2);
        
        [span_0](start_span)// Output the result formatted to one decimal place[span_0](end_span)
        System.out.printf("%.1f\n", median);
        
        scanner.close();
    }

    /**
     * Finds the median of two sorted arrays using a binary search approach.
     * [span_1](start_span)The time complexity is O(log(min(n, m))).[span_1](end_span)
     * @param nums1 The first sorted array.
     * @param nums2 The second sorted array.
     * @return The median of the combined arrays.
     */
    public static double findMedianSortedArrays(double[] nums1, double[] nums2) {
        [span_2](start_span)// Ensure nums1 is the smaller array to optimize the binary search range.[span_2](end_span)
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int n = nums1.length;
        int m = nums2.length;
        int low = 0;
        int high = n;

        while (low <= high) {
            // Partition the arrays. The goal is to find a split where
            // all elements on the left are smaller than all elements on the right.
            int partitionX = (low + high) / 2;
            int partitionY = (n + m + 1) / 2 - partitionX;

            // Get the max element on the left and min element on the right for both arrays
            [span_3](start_span)// Use infinities to handle edge cases where a partition is empty.[span_3](end_span)
            double maxLeftX = (partitionX == 0) ? Double.NEGATIVE_INFINITY : nums1[partitionX - 1];
            double minRightX = (partitionX == n) ? Double.POSITIVE_INFINITY : nums1[partitionX];

            double maxLeftY = (partitionY == 0) ? Double.NEGATIVE_INFINITY : nums2[partitionY - 1];
            double minRightY = (partitionY == m) ? Double.POSITIVE_INFINITY : nums2[partitionY];

            // Check if we found the correct partition
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // If the total number of elements is even
                if ((n + m) % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else { // If the total number of elements is odd
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                // The partition in nums1 is too large, move left
                high = partitionX - 1;
            } else {
                // The partition in nums1 is too small, move right
                low = partitionX + 1;
            }
        }

        // This should not be reached if the inputs are valid sorted arrays
        throw new IllegalArgumentException("Input arrays are not sorted or invalid.");
    }
}
// This solution uses a binary search on the smaller array for efficiency.
// It cleverly partitions the conceptual merged array without actually creating it.
// Handling edge cases with infinity values is key to this algorithm's correctness.
