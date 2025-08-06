import java.util.Arrays;

public class SelectionSortImplementation {

    /**
     * 1, 2, 3. Implements selection sort, showing the process and counting operations.
     * The algorithm sorts an array by repeatedly finding the minimum element
     * from the unsorted part and putting it at the beginning.
     * @param array The array to be sorted.
     */
    public static void selectionSort(int[] array) {
        if (array == null || array.length <= 1) {
            return; // Nothing to sort
        }

        int n = array.length;
        int comparisons = 0;
        int swaps = 0;

        System.out.println("--- Selection Sort Process ---");

        // The outer loop moves the boundary of the sorted subarray.
        for (int i = 0; i < n - 1; i++) {
            // Assume the minimum is the first element of the unsorted subarray.
            int minIndex = i;

            // The inner loop finds the minimum element in the unsorted array.
            for (int j = i + 1; j < n; j++) {
                System.out.printf("  Comparing array[%d]=%d and array[%d]=%d\n", j, array[j], minIndex, array[minIndex]);
                comparisons++;
                if (array[j] < array[minIndex]) {
                    minIndex = j; // Found a new minimum, update its index.
                }
            }

            // Swap the found minimum element with the first element of the unsorted part.
            // Only swap if the minimum element isn't already in its correct place.
            if (minIndex != i) {
                System.out.printf("-> Found minimum %d at index %d. Swapping with element at index %d.\n", array[minIndex], minIndex, i);
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
                swaps++;
            } else {
                 System.out.printf("-> Element %d at index %d is already in correct place.\n", array[i], i);
            }

            // 2. Show the sorting process for each round.
            System.out.printf("State after round %d: %s\n\n", i + 1, Arrays.toString(array));
        }

        System.out.println("--- Sort Complete ---");
        // 3. Calculate the number of comparisons and swaps.
        System.out.println("Total Comparisons: " + comparisons);
        System.out.println("Total Swaps: " + swaps);
    }


    public static void main(String[] args) {
        int[] numbers = {64, 25, 12, 22, 11, 90};

        System.out.println("Original Array: " + Arrays.toString(numbers));
        System.out.println();

        selectionSort(numbers);

        System.out.println("\nFinal Sorted Array: " + Arrays.toString(numbers));
        System.out.println();

        // 4. Compare its performance with bubble sort.
        System.out.println("--- Performance Comparison with Bubble Sort ---");
        System.out.println("Selection Sort always performs O(n^2) comparisons, regardless of the input array's order.");
        System.out.println("The number of swaps is at most n-1, which is O(n). This makes it efficient when memory write operations are costly.");
        System.out.println("Bubble Sort (optimized) can have a best-case performance of O(n) if the array is already sorted.");
        System.out.println("However, Bubble Sort's average and worst cases are O(n^2) for both comparisons and swaps, making it generally less efficient than Selection Sort, which performs far fewer swaps.");
    }
}
