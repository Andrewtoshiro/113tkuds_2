import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestElement {

    public static int findKthSmallestWithMaxHeap(int[] nums, int k) {
        if (k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("K is out of the valid range.");
        }
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }

    public static int findKthSmallestWithMinHeap(int[] nums, int k) {
        if (k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("K is out of the valid range.");
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
        }

        for (int i = 0; i < k - 1; i++) {
            minHeap.poll();
        }

        return minHeap.peek();
    }

    public static void main(String[] args) {
        System.out.println("=== Finding the Kth Smallest Element in an Array ===");
        
        test(new int[]{7, 10, 4, 3, 20, 15}, 3);
        test(new int[]{1}, 1);
        test(new int[]{3, 1, 4, 1, 5, 9, 2, 6}, 4);
    }
    
    public static void test(int[] nums, int k) {
        System.out.println("-----------------------------------------");
        System.out.println("Testing Array: " + Arrays.toString(nums) + ", K = " + k);

        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        System.out.println("Sorted Array: " + Arrays.toString(sorted));
        System.out.println("Correct Answer: " + sorted[k - 1]);
        
        System.out.println("\n--- Method Comparison ---");

        int result1 = findKthSmallestWithMaxHeap(nums, k);
        System.out.println("Method 1 (Max Heap): " + result1);

        int result2 = findKthSmallestWithMinHeap(nums, k);
        System.out.println("Method 2 (Min Heap): " + result2);

        System.out.println("\n--- Complexity Comparison ---");
        System.out.println("Method 1 (Max Heap): Time O(N log K), Space O(K)  <-- Recommended");
        System.out.println("Method 2 (Min Heap): Time O(N + K log N), Space O(N)");
        System.out.println("-----------------------------------------\n");
    }
}
