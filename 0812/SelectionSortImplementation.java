import java.util.Arrays;

/**
 * An implementation of the Selection Sort algorithm.
 * This program demonstrates the sorting process for each round and counts the
 * number of comparisons and swaps, as required by Exercise 1.5 in the provided document.
 */
public class SelectionSortImplementation {

    public static void main(String[] args) {
        int[] numbers = {64, 25, 12, 22, 11, 90};

        System.out.println("--- Selection Sort Implementation ---");
        System.out.println("Original array: " + Arrays.toString(numbers));
        System.out.println();

        selectionSort(numbers);

        System.out.println("\nFinal sorted array: " + Arrays.toString(numbers));
        System.out.println("-------------------------------------");
    }

    /**
     * Sorts an array using the Selection Sort algorithm.
     * Displays the process and counts comparisons and swaps.
     * @param array The array to be sorted.
     */
    public static void selectionSort(int[] array) {
        int n = array.length;
        int totalComparisons = 0;
        int totalSwaps = 0;

        // The outer loop iterates through each position in the array.
        for (int i = 0; i < n - 1; i++) {
            // Assume the first element of the unsorted part is the minimum.
            int minIndex = i;

            // The inner loop finds the true minimum in the rest of the array.
            for (int j = i + 1; j < n; j++) {
                totalComparisons++;
                if (array[j] < array[minIndex]) {
                    minIndex = j; // Found a new minimum, update its index.
                }
            }

            // --- Displaying the process for the current round ---
            System.out.printf("Round %d: \n", i + 1);
            System.out.println("  - Unsorted part: " + Arrays.toString(Arrays.copyOfRange(array, i, n)));
            System.out.printf("  - Minimum element found: %d at index %d\n", array[minIndex], minIndex);
            
            // Swap the found minimum element with the first element of the unsorted part.
            // A swap is only performed if the minimum is not already in the correct place.
            if (minIndex != i) {
                System.out.printf("  - Swapping %d (at index %d) with %d (at index %d)\n", array[i], i, array[minIndex], minIndex);
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
                totalSwaps++;
            } else {
                System.out.println("  - Element is already in its correct sorted position. No swap needed.");
            }
            System.out.println("  - Array after round: " + Arrays.toString(array) + "\n");
        }

        System.out.println("===== Sorting Complete =====");
        System.out.println("Total Comparisons: " + totalComparisons);
        System.out.println("Total Swaps: " + totalSwaps);
        System.out.println("\n*Performance Note vs. Bubble Sort:*");
        System.out.println("  - Swaps: Selection Sort is very efficient in swaps (max N-1), making it useful when write operations are expensive.");
        System.out.println("  - Comparisons: It always performs the same number of comparisons (O(N^2)), unlike an optimized Bubble Sort which can finish early.");
    }
}
