import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class ArrayEntry {
    int value;
    int arrayIndex;
    int elementIndex;

    public ArrayEntry(int value, int arrayIndex, int elementIndex) {
        this.value = value;
        this.arrayIndex = arrayIndex;
        this.elementIndex = elementIndex;
    }
}

public class MergeKSortedArrays {

    public static int[] merge(int[][] arrays) {
        if (arrays == null || arrays.length == 0) {
            return new int[0];
        }

        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.value, b.value));

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null && arrays[i].length > 0) {
                minHeap.offer(new ArrayEntry(arrays[i][0], i, 0));
            }
        }

        List<Integer> resultList = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            ArrayEntry current = minHeap.poll();
            resultList.add(current.value);

            int nextElementIndex = current.elementIndex + 1;
            if (nextElementIndex < arrays[current.arrayIndex].length) {
                int nextValue = arrays[current.arrayIndex][nextElementIndex];
                minHeap.offer(new ArrayEntry(nextValue, current.arrayIndex, nextElementIndex));
            }
        }

        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        System.out.println("=== Merging K Sorted Arrays ===");

        int[][] input1 = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        System.out.println("Input: " + Arrays.deepToString(input1));
        int[] output1 = merge(input1);
        System.out.println("Output: " + Arrays.toString(output1));
        System.out.println();

        int[][] input2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Input: " + Arrays.deepToString(input2));
        int[] output2 = merge(input2);
        System.out.println("Output: " + Arrays.toString(output2));
        System.out.println();
        
        int[][] input3 = {{1}, {0}};
        System.out.println("Input: " + Arrays.deepToString(input3));
        int[] output3 = merge(input3);
        System.out.println("Output: " + Arrays.toString(output3));
        System.out.println();
        
        int[][] input4 = {{1, 5, 10}, {}, {2, 6, 12}};
        System.out.println("Input: " + Arrays.deepToString(input4));
        int[] output4 = merge(input4);
        System.out.println("Output: " + Arrays.toString(output4));
        System.out.println();
    }
}
