import java.util.Arrays;

/**
 * Implements several advanced array operations using recursive algorithms.
 * This implementation is based on the requirements of Exercise 2.2 from the provided document.
 */
public class AdvancedArrayRecursion {

    public static void main(String[] args) {
        System.out.println("====== Advanced Array Recursion Demo ======");

        // --- 1. Recursive Quicksort ---
        System.out.println("\n--- 1. Recursive Quicksort ---");
        int[] unsortedArray = {10, 80, 30, 90, 40, 50, 70};
        System.out.println("Original array: " + Arrays.toString(unsortedArray));
        quicksort(unsortedArray);
        System.out.println("Sorted array:   " + Arrays.toString(unsortedArray));

        // --- 2. Recursive Merge of Sorted Arrays ---
        System.out.println("\n--- 2. Recursive Merge of Sorted Arrays ---");
        int[] sortedA = {1, 4, 7, 10};
        int[] sortedB = {2, 3, 8};
        System.out.println("Array A: " + Arrays.toString(sortedA));
        System.out.println("Array B: " + Arrays.toString(sortedB));
        int[] mergedArray = mergeSortedArrays(sortedA, sortedB);
        System.out.println("Merged array: " + Arrays.toString(mergedArray));

        // --- 3. Recursive Find K-th Smallest Element ---
        System.out.println("\n--- 3. Recursive Find K-th Smallest Element (Quickselect) ---");
        int[] kArray = {10, 4, 5, 8, 6, 11, 26};
        int k = 3;
        System.out.println("Original array: " + Arrays.toString(kArray));
        try {
            int kthElement = findKthSmallest(kArray, k);
            System.out.printf("The %d-rd smallest element is: %d\n", k, kthElement);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // --- 4. Recursive Subset Sum Problem ---
        System.out.println("\n--- 4. Recursive Subset Sum Problem ---");
        int[] set = {3, 34, 4, 12, 5, 2};
        int target1 = 9;
        int target2 = 30;
        System.out.println("Set: " + Arrays.toString(set));
        System.out.printf("Is there a subset with sum %d? %s\n", target1, subsetSumExists(set, target1));
        System.out.printf("Is there a subset with sum %d? %s\n", target2, subsetSumExists(set, target2));
    }

    // =================================================================
    // 1. Quicksort
    // =================================================================
    public static void quicksort(int[] array) {
        quicksortRecursive(array, 0, array.length - 1);
    }

    private static void quicksortRecursive(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quicksortRecursive(array, low, pivotIndex - 1);
            quicksortRecursive(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                // swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        // swap array[i+1] and array[high] (or pivot)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    // =================================================================
    // 2. Merge Sorted Arrays
    // =================================================================
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        mergeRecursive(a, 0, b, 0, result, 0);
        return result;
    }

    private static void mergeRecursive(int[] a, int i, int[] b, int j, int[] result, int k) {
        // Base cases: if one of the arrays is exhausted
        if (i >= a.length) {
            while (j < b.length) result[k++] = b[j++];
            return;
        }
        if (j >= b.length) {
            while (i < a.length) result[k++] = a[i++];
            return;
        }

        // Recursive step
        if (a[i] <= b[j]) {
            result[k] = a[i];
            mergeRecursive(a, i + 1, b, j, result, k + 1);
        } else {
            result[k] = b[j];
            mergeRecursive(a, i, b, j + 1, result, k + 1);
        }
    }

    // =================================================================
    // 3. Find K-th Smallest Element
    // =================================================================
    public static int findKthSmallest(int[] array, int k) {
        if (k < 1 || k > array.length) {
            throw new IllegalArgumentException("k is out of the array's bounds.");
        }
        // We use k-1 because array indices are 0-based.
        return kthSmallestRecursive(array, 0, array.length - 1, k - 1);
    }

    private static int kthSmallestRecursive(int[] array, int low, int high, int kIndex) {
        int pivotIndex = partition(array, low, high);

        if (pivotIndex == kIndex) {
            return array[pivotIndex];
        } else if (pivotIndex > kIndex) {
            return kthSmallestRecursive(array, low, pivotIndex - 1, kIndex);
        } else {
            return kthSmallestRecursive(array, pivotIndex + 1, high, kIndex);
        }
    }

    // =================================================================
    // 4. Subset Sum Problem
    // =================================================================
    public static boolean subsetSumExists(int[] array, int target) {
        return subsetSumRecursive(array, target, array.length);
    }

    private static boolean subsetSumRecursive(int[] array, int target, int n) {
        // Base Cases
        if (target == 0) return true;
        if (n == 0) return false;

        // If last element is greater than target, ignore it
        if (array[n - 1] > target) {
            return subsetSumRecursive(array, target, n - 1);
        }

        // Recursive Step: check if sum can be obtained by either
        // (1) including the last element or (2) excluding the last element
        return subsetSumRecursive(array, target - array[n - 1], n - 1) || 
               subsetSumRecursive(array, target, n - 1);
    }
}
