import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class SlidingWindowMedian {

    private PriorityQueue<Integer> smallHalf;
    private PriorityQueue<Integer> largeHalf;

    public SlidingWindowMedian() {
        this.smallHalf = new PriorityQueue<>(Collections.reverseOrder());
        this.largeHalf = new PriorityQueue<>();
    }

    private void addNum(int num) {
        smallHalf.offer(num);
        largeHalf.offer(smallHalf.poll());
        rebalance();
    }

    private void removeNum(int num) {
        if (!smallHalf.isEmpty() && num <= smallHalf.peek()) {
            smallHalf.remove(num);
        } else {
            largeHalf.remove(num);
        }
        rebalance();
    }
    
    private void rebalance() {
        if (smallHalf.size() < largeHalf.size()) {
            smallHalf.offer(largeHalf.poll());
        }
    }

    private double findMedian() {
        if (smallHalf.size() > largeHalf.size()) {
            return (double) smallHalf.peek();
        }
        return ((long)smallHalf.peek() + largeHalf.peek()) / 2.0;
    }

    public double[] findSlidingWindowMedian(int[] nums, int k) {
        if (nums == null || nums.length < k || k <= 0) {
            return new double[0];
        }

        List<Double> medians = new ArrayList<>();
        
        for (int i = 0; i < k; i++) {
            addNum(nums[i]);
        }
        medians.add(findMedian());

        for (int i = k; i < nums.length; i++) {
            removeNum(nums[i - k]);
            addNum(nums[i]);
            medians.add(findMedian());
        }

        return medians.stream().mapToDouble(Double::doubleValue).toArray();
    }

    public static void main(String[] args) {
        System.out.println("=== Sliding Window Median ===");

        SlidingWindowMedian swm1 = new SlidingWindowMedian();
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println("Input: " + Arrays.toString(nums1) + ", K = " + k1);
        double[] output1 = swm1.findSlidingWindowMedian(nums1, k1);
        System.out.println("Output: " + Arrays.toString(output1));
        System.out.println();

        SlidingWindowMedian swm2 = new SlidingWindowMedian();
        int[] nums2 = {1, 2, 3, 4};
        int k2 = 2;
        System.out.println("Input: " + Arrays.toString(nums2) + ", K = " + k2);
        double[] output2 = swm2.findSlidingWindowMedian(nums2, k2);
        System.out.println("Output: " + Arrays.toString(output2));
        System.out.println();
        
        SlidingWindowMedian swm3 = new SlidingWindowMedian();
        int[] nums3 = {1, 4, 2, 3, 5, 2};
        int k3 = 4;
        System.out.println("Input: " + Arrays.toString(nums3) + ", K = " + k3);
        double[] output3 = swm3.findSlidingWindowMedian(nums3, k3);
        System.out.println("Output: " + Arrays.toString(output3));
        System.out.println();
    }
}
