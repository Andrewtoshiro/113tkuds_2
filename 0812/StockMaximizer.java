import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class StockMaximizer {

    public static int maximizeProfit(int[] prices, int k) {
        if (k <= 0 || prices == null || prices.length < 2) {
            return 0;
        }

        List<Integer> profits = new ArrayList<>();
        int i = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            int valley = prices[i];

            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            int peak = prices[i];

            if (peak > valley) {
                profits.add(peak - valley);
            }
        }

        if (profits.isEmpty()) {
            return 0;
        }
        
        if (k >= profits.size()) {
            return profits.stream().mapToInt(Integer::intValue).sum();
        }
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(profits);
        
        int totalMaxProfit = 0;
        for (int j = 0; j < k; j++) {
            if (!maxHeap.isEmpty()) {
                totalMaxProfit += maxHeap.poll();
            } else {
                break;
            }
        }
        
        return totalMaxProfit;
    }

    public static void main(String[] args) {
        System.out.println("=== Stock Profit Maximizer ===");
        
        int[] prices1 = {2, 4, 1};
        int k1 = 2;
        System.out.println("Prices: " + Arrays.toString(prices1) + ", K = " + k1);
        System.out.println("Max Profit: " + maximizeProfit(prices1, k1));
        System.out.println();
        
        int[] prices2 = {3, 2, 6, 5, 0, 3};
        int k2 = 2;
        System.out.println("Prices: " + Arrays.toString(prices2) + ", K = " + k2);
        System.out.println("Max Profit: " + maximizeProfit(prices2, k2));
        System.out.println();
        
        int[] prices3 = {1, 2, 3, 4, 5};
        int k3 = 2;
        System.out.println("Prices: " + Arrays.toString(prices3) + ", K = " + k3);
        System.out.println("Max Profit: " + maximizeProfit(prices3, k3));
        System.out.println();
        
        int[] prices4 = {1, 5, 2, 8, 3, 10};
        int k4 = 2;
        System.out.println("Prices: " + Arrays.toString(prices4) + ", K = " + k4);
        System.out.println("Max Profit: " + maximizeProfit(prices4, k4));
        System.out.println();

        int[] prices5 = {7, 6, 4, 3, 1};
        int k5 = 2;
        System.out.println("Prices: " + Arrays.toString(prices5) + ", K = " + k5);
        System.out.println("Max Profit: " + maximizeProfit(prices5, k5));
        System.out.println();
    }
}
