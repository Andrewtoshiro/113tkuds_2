import java.util.*;

/**
 * Implements several array processing utilities as a demonstration.
 * [span_0](start_span)This implementation is based on the requirements of Exercise 1.3 from the provided document.[span_0](end_span)
 */
public class NumberArrayProcessor {

    public static void main(String[] args) {
        System.out.println("====== Number Array Processor Demo ======");

        // --- 1. Remove Duplicates ---
        System.out.println("\n--- 1. Remove Duplicates ---");
        int[] arrayWithDuplicates = {1, 5, 2, 8, 5, 2, 1, 9, 8, 10};
        System.out.println("Original array: " + Arrays.toString(arrayWithDuplicates));
        int[] uniqueArray = removeDuplicates(arrayWithDuplicates);
        System.out.println("Array with duplicates removed: " + Arrays.toString(uniqueArray));

        // --- 2. Merge Two Sorted Arrays ---
        System.out.println("\n--- 2. Merge Two Sorted Arrays ---");
        int[] sortedA = {1, 4, 7, 10};
        int[] sortedB = {2, 3, 8, 11, 15};
        System.out.println("Array A: " + Arrays.toString(sortedA));
        System.out.println("Array B: " + Arrays.toString(sortedB));
        int[] mergedArray = mergeSortedArrays(sortedA, sortedB);
        System.out.println("Merged sorted array: " + Arrays.toString(mergedArray));

        // --- 3. Find Most Frequent Element ---
        System.out.println("\n--- 3. Find Most Frequent Element ---");
        int[] frequencyArray = {2, 7, 5, 7, 8, 5, 7, 2, 7, 9};
        System.out.println("Original array: " + Arrays.toString(frequencyArray));
        int mostFrequent = findMostFrequentElement(frequencyArray);
        System.out.println("Most frequent element: " + mostFrequent);

        // --- 4. Split Array into Two ---
        System.out.println("\n--- 4. Split Array into Two ---");
        int[] arrayToSplit = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("Original array: " + Arrays.toString(arrayToSplit));
        int[][] splitArrays = splitArray(arrayToSplit);
        System.out.println("First half:  " + Arrays.toString(splitArrays[0]));
        System.out.println("Second half: " + Arrays.toString(splitArrays[1]));
    }

    /**
     * [span_1](start_span)Removes duplicate elements from an array.[span_1](end_span)
     * This implementation uses a LinkedHashSet to preserve the order of elements.
     */
    public static int[] removeDuplicates(int[] array) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int value : array) {
            set.add(value);
        }

        int[] result = new int[set.size()];
        int i = 0;
        for (Integer value : set) {
            result[i++] = value;
        }
        return result;
    }

    /**
     * [span_2](start_span)Merges two already sorted arrays into a single sorted array.[span_2](end_span)
     */
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) {
            result[k++] = a[i++];
        }

        while (j < b.length) {
            result[k++] = b[j++];
        }
        return result;
    }

    /**
     * [span_3](start_span)Finds the element that appears most frequently in an array.[span_3](end_span)
     * If there's a tie, the one encountered first during the scan of frequencies is returned.
     */
    public static int findMostFrequentElement(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty or null.");
        }

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int value : array) {
            frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
        }

        int mostFrequentElement = 0;
        int maxCount = -1;
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentElement = entry.getKey();
            }
        }
        return mostFrequentElement;
    }

    /**
     * [span_4](start_span)Splits an array into two sub-arrays of equal or nearly equal size.[span_4](end_span)
     * Returns a 2D array containing the two halves.
     */
    public static int[][] splitArray(int[] array) {
        int midIndex = array.length / 2;
        int[] firstHalf = Arrays.copyOfRange(array, 0, midIndex);
        int[] secondHalf = Arrays.copyOfRange(array, midIndex, array.length);

        return new int[][]{firstHalf, secondHalf};
    }
}
