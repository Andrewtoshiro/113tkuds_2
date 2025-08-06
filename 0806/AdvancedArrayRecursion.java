import java.util.Arrays;

public class AdvancedArrayRecursion {

    // --- 1. Recursive Quick Sort Algorithm ---
    
    public static void quickSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int low, int high) {
        // Base case: If the segment has 0 or 1 elements, it's already sorted.
        if (low < high) {
            // Partition the array and get the pivot's final index.
            int pivotIndex = partition(array, low, high);

            // Recursively sort the two sub-arrays.
            quickSort(array, low, pivotIndex - 1);  // Sub-array before the pivot
            quickSort(array, pivotIndex + 1, high); // Sub-array after the pivot
        }
    }

    // Helper method to partition the array. It places the pivot element
    // at its correct position and moves smaller elements to its left.
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high]; // Choosing the last element as the pivot
        int i = (low - 1);       // Index of the smaller element

        for (int j = low; j < high; j++) {
            // If the current element is smaller than or equal to the pivot
            if (array[j] <= pivot) {
                i++;
                // Swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Swap the pivot element (array[high]) with the element at i + 1
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1; // Return the pivot's final index
    }

    // --- 2. Recursive Merge of Two Sorted Arrays ---

    public static int[] recursiveMerge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        mergeHelper(arr1, 0, arr2, 0, result, 0);
        return result;
    }

    // Recursive helper to perform the merge
    private static void mergeHelper(int[] arr1, int i, int[] arr2, int j, int[] result, int k) {
        // Base case: if both arrays have been fully traversed
        if (i >= arr1.length && j >= arr2.length) {
            return;
        }

        // If arr1 is exhausted, copy the rest of arr2
        if (i >= arr1.length) {
            result[k] = arr2[j];
            mergeHelper(arr1, i, arr2, j + 1, result, k + 1);
        } 
        // If arr2 is exhausted, copy the rest of arr1
        else if (j >= arr2.length) {
            result[k] = arr1[i];
            mergeHelper(arr1, i + 1, arr2, j, result, k + 1);
        }
        // Compare elements and recurse
        else if (arr1[i] <= arr2[j]) {
            result[k] = arr1[i];
            mergeHelper(arr1, i + 1, arr2, j, result, k + 1);
        } else {
            result[k] = arr2[j];
            mergeHelper(arr1, i, arr2, j + 1, result, k + 1);
        }
    }


    // --- 3. Recursive K-th Smallest Element (Quickselect) ---
    
    public static int findKthSmallest(int[] array, int k) {
        if (k < 1 || k > array.length) {
            throw new IllegalArgumentException("k is out of bounds.");
        }
        // We need a mutable copy of the array because the algorithm shuffles it.
        int[] tempArray = Arrays.copyOf(array, array.length);
        return findKthSmallest(tempArray, 0, tempArray.length - 1, k - 1); // k-1 because k is 1-based
    }

    private static int findKthSmallest(int[] array, int low, int high, int k) {
        // Partition the array
        int pivotIndex = partition(array, low, high);

        // Check the pivot's position
        if (pivotIndex == k) {
            return array[pivotIndex]; // Found the k-th element
        } else if (pivotIndex > k) {
            // The element is in the left sub-array
            return findKthSmallest(array, low, pivotIndex - 1, k);
        } else {
            // The element is in the right sub-array
            return findKthSmallest(array, pivotIndex + 1, high, k);
        }
    }

    // --- 4. Recursive Subset Sum ---

    public static boolean hasSubsetSum(int[] array, int targetSum) {
        return subsetSumHelper(array, targetSum, array.length);
    }

    // Recursive helper for the subset sum problem
    private static boolean subsetSumHelper(int[] array, int targetSum, int n) {
        // Base case 1: If the target sum is 0, we have found a valid subset.
        if (targetSum == 0) {
            return true;
        }
        // Base case 2: If we've run out of elements but the sum is not 0.
        if (n == 0 && targetSum != 0) {
            return false;
        }

        // If the last element is greater than the target sum, we must exclude it.
        if (array[n - 1] > targetSum) {
            return subsetSumHelper(array, targetSum, n - 1);
        }

        // Recursive step: Check if a subset exists by either
        // 1. Including the last element
        // 2. Excluding the last element
        return subsetSumHelper(array, targetSum - array[n - 1], n - 1) ||
               subsetSumHelper(array, targetSum, n - 1);
    }


    public static void main(String[] args) {
        System.out.println("====== Advanced Array Recursion Demo ======");

        // 1. Test Quick Sort
        int[] unsortedArray = {10, 80, 30, 90, 40, 50, 70};
        System.out.println("\n1. Quick Sort");
        System.out.println("Original array: " + Arrays.toString(unsortedArray));
        quickSort(unsortedArray);
        System.out.println("Sorted array:   " + Arrays.toString(unsortedArray));

        // 2. Test Recursive Merge
        int[] sorted1 = {1, 4, 8, 10};
        int[] sorted2 = {2, 3, 5, 9, 11};
        System.out.println("\n2. Recursive Merge");
        System.out.println("Array 1: " + Arrays.toString(sorted1));
        System.out.println("Array 2: " + Arrays.toString(sorted2));
        int[] merged = recursiveMerge(sorted1, sorted2);
        System.out.println("Merged array: " + Arrays.toString(merged));
        
        // 3. Test K-th Smallest Element
        int[] quickselectArray = {10, 4, 5, 8, 6, 11, 26};
        int k = 3;
        System.out.println("\n3. Find K-th Smallest Element");
        System.out.println("Array: " + Arrays.toString(quickselectArray));
        int kthElement = findKthSmallest(quickselectArray, k);
        System.out.printf("The %d-rd smallest element is: %d\n", k, kthElement);

        // 4. Test Subset Sum
        int[] subsetArray = {3, 34, 4, 12, 5, 2};
        int target1 = 9;
        int target2 = 30;
        System.out.println("\n4. Subset Sum Problem");
        System.out.println("Array: " + Arrays.toString(subsetArray));
        System.out.printf("Is there a subset with sum %d? %s\n", target1, hasSubsetSum(subsetArray, target1));
        System.out.printf("Is there a subset with sum %d? %s\n", target2, hasSubsetSum(subsetArray, target2));

        System.out.println("\n==========================================");
    }
}
