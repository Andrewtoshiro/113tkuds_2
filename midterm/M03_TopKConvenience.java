import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

record Product(String name, int qty) {}

public class M03_TopKConvenience {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        Comparator<Product> minHeapComparator = (p1, p2) -> {
            if (p1.qty() != p2.qty()) {
                return Integer.compare(p1.qty(), p2.qty());
            }
            return p2.name().compareTo(p1.name());
        };

        PriorityQueue<Product> topKHeap = new PriorityQueue<>(minHeapComparator);

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            
            topKHeap.offer(new Product(name, qty));

            if (topKHeap.size() > k) {
                topKHeap.poll();
            }
        }
        sc.close();

        LinkedList<Product> result = new LinkedList<>();
        while (!topKHeap.isEmpty()) {
            result.addFirst(topKHeap.poll());
        }

        for (Product p : result) {
            System.out.println(p.name() + " " + p.qty());
        }
    }
    
    /*
     * Time Complexity: O(n log k)
     * 說明:
     * 1. 程式遍歷 n 個商品，針對每個商品進行一次堆積(heap)操作。
     * 2. 維護一個大小不超過 K 的最小堆積(min-heap)，每次新增(offer)或刪除(poll)操作的時間複雜度為 O(log k)。
     * 3. 因此，處理 n 個商品的總時間複雜度為 n * O(log k)，即 O(n log k)。若數值相同，依字典序(name)穩定排序。
     */
}
