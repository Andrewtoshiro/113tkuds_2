import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NumberArrayProcessor {

    /**
     * 1. Removes duplicate elements from an array while preserving order.
     * @param array The input array with potential duplicates.
     * @return A new array with only unique elements.
     */
    public static int[] removeDuplicates(int[] array) {
        // Use a LinkedHashSet to store unique elements while preserving insertion order.
        Set<Integer> set = new LinkedHashSet<>();
        for (int value : array) {
            set.add(value);
        }

        // Convert the set back to an array.
        int[] result = new int[set.size()];
        int i = 0;
        for (Integer value : set) {
            result[i++] = value;
        }
        return result;
    }

    /**
     * 2. Merges two already sorted arrays into a single sorted array.
     * @param arr1 The first sorted array.
     * @param arr2 The second sorted array.
     * @return A new merged and sorted array.
     */
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        // Traverse both arrays and insert the smaller element into the result array.
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        // Copy remaining elements of arr1, if any.
        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }

        // Copy remaining elements of arr2, if any.
        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }
        return result;
    }

    /**
     * 3. Finds the element that appears most frequently in an array.
     * If there's a tie, it returns the one that appears first in the tie.
     * @param array The input array.
     * @return The most frequent element.
     */
    public static int findMostFrequentElement(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }

        // Use a HashMap to store the frequency of each element.
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int value : array) {
            frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
        }

        int mostFrequentElement = -1;
        int maxFrequency = 0;

        // Iterate through the map to find the element with the highest frequency.
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostFrequentElement = entry.getKey();
            }
        }
        return mostFrequentElement;
    }

    /**
     * 4. Splits an array into two subarrays with sums that are as close as possible.
     * This implementation uses a greedy approach.
     * @param array The input array.
     * @return A list containing two lists, representing the two subarrays.
     */
    public static List<List<Integer>> splitArrayBySum(int[] array) {
        // Sort the array in descending order to use a greedy approach.
        // This is easier by sorting ascending and iterating backwards.
        Arrays.sort(array);

        List<Integer> subArray1 = new ArrayList<>();
        List<Integer> subArray2 = new ArrayList<>();
        long sum1 = 0;
        long sum2 = 0;

        // Iterate from the largest element to the smallest.
        for (int i = array.length - 1; i >= 0; i--) {
            // Add the current element to the list with the smaller sum.
            if (sum1 <= sum2) {
                subArray1.add(array[i]);
                sum1 += array[i];
            } else {
                subArray2.add(array[i]);
                sum2 += array[i];
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(subArray1);
        result.add(subArray2);
        return result;
    }


    public static void main(String[] args) {
        System.out.println("========= Number Array Processor Demo =========");

        // 1. Test removeDuplicates
        int[] withDuplicates = {1, 5, 2, 8, 5, 1, 9, 2, 10};
        System.out.println("\n1. Remove Duplicates");
        System.out.println("Original array: " + Arrays.toString(withDuplicates));
        int[] uniqueArray = removeDuplicates(withDuplicates);
        System.out.println("Array with duplicates removed: " + Arrays.toString(uniqueArray));

        // 2. Test mergeSortedArrays
        int[] sorted1 = {1, 3, 5, 7, 9};
        int[] sorted2 = {2, 4, 6, 8, 10};
        System.out.println("\n2. Merge Sorted Arrays");
        System.out.println("Array 1: " + Arrays.toString(sorted1));
        System.out.println("Array 2: " + Arrays.toString(sorted2));
        int[] mergedArray = mergeSortedArrays(sorted1, sorted2);
        System.out.println("Merged array: " + Arrays.toString(mergedArray));

        // 3. Test findMostFrequentElement
        int[] frequencyTest = {4, 7, 2, 7, 8, 2, 7, 4, 7};
        System.out.println("\n3. Find Most Frequent Element");
        System.out.println("Array: " + Arrays.toString(frequencyTest));
        int frequentElement = findMostFrequentElement(frequencyTest);
        System.out.println("Most frequent element: " + frequentElement);

        // 4. Test splitArrayBySum
        int[] toSplit = {10, 2, 5, 3, 8, 7, 1};
        System.out.println("\n4. Split Array into Two Subarrays of Similar Sum");
        System.out.println("Array to split: " + Arrays.toString(toSplit));
        List<List<Integer>> splitResult = splitArrayBySum(toSplit);
        List<Integer> sub1 = splitResult.get(0);
        List<Integer> sub2 = splitResult.get(1);
        long sum1 = sub1.stream().mapToLong(Integer::longValue).sum();
        long sum2 = sub2.stream().mapToLong(Integer::longValue).sum();
        System.out.println("Subarray 1: " + sub1 + " (Sum: " + sum1 + ")");
        System.out.println("Subarray 2: " + sub2 + " (Sum: " + sum2 + ")");
        System.out.println("================================================");
    }
}
