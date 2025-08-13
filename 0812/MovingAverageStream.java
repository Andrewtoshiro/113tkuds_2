import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MovingAverageStream {

    private final int windowSize;
    private final Queue<Integer> window;
    private double windowSum;
    private final PriorityQueue<Integer> smallHalf;
    private final PriorityQueue<Integer> largeHalf;

    public MovingAverageStream(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Window size must be positive.");
        }
        this.windowSize = size;
        this.window = new LinkedList<>();
        this.windowSum = 0.0;
        this.smallHalf = new PriorityQueue<>(Collections.reverseOrder());
        this.largeHalf = new PriorityQueue<>();
    }

    public double next(int val) {
        if (window.size() == windowSize) {
            int removed = window.poll();
            windowSum -= removed;
            removeFromHeaps(removed);
        }

        window.offer(val);
        windowSum += val;
        addToHeaps(val);

        return windowSum / window.size();
    }

    public double getMedian() {
        if (window.isEmpty()) {
            throw new IllegalStateException("Window is empty, cannot get median.");
        }
        if (smallHalf.size() == largeHalf.size()) {
            return ((long)smallHalf.peek() + largeHalf.peek()) / 2.0;
        }
        return (double) smallHalf.peek();
    }

    public int getMin() {
        if (window.isEmpty()) {
            throw new IllegalStateException("Window is empty, cannot get min.");
        }
        return Collections.min(window);
    }

    public int getMax() {
        if (window.isEmpty()) {
            throw new IllegalStateException("Window is empty, cannot get max.");
        }
        return Collections.max(window);
    }

    private void addToHeaps(int num) {
        smallHalf.offer(num);
        largeHalf.offer(smallHalf.poll());
        rebalance();
    }

    private void removeFromHeaps(int num) {
        if (!smallHalf.isEmpty() && num <= smallHalf.peek()) {
            smallHalf.remove(num);
        } else {
            largeHalf.remove(num);
        }
        rebalance();
    }

    private void rebalance() {
        if (largeHalf.size() > smallHalf.size()) {
            smallHalf.offer(largeHalf.poll());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Moving Average Stream Test Case ===");
        MovingAverageStream ma = new MovingAverageStream(3);

        System.out.printf("next(1) = %.2f\n", ma.next(1));
        System.out.printf("next(10) = %.2f\n", ma.next(10));
        System.out.printf("next(3) = %.2f\n", ma.next(3));
        
        System.out.println("--- Window is now full {1, 10, 3} ---");
        System.out.printf("next(5) = %.2f\n", ma.next(5));
        
        System.out.println("\n--- Final State Metrics ---");
        System.out.println("Current Window: " + ma.window);
        System.out.println("Median: " + ma.getMedian());
        System.out.println("Min: " + ma.getMin());
        System.out.println("Max: " + ma.getMax());
    }
}
