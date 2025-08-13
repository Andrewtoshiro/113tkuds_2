import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class BasicMinHeapPractice {

    private List<Integer> heap;

    public BasicMinHeapPractice() {
        heap = new ArrayList<>();
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void heapifyUp(int i) {
        while (i > 0 && heap.get(i) < heap.get(parent(i))) {
            Collections.swap(heap, i, parent(i));
            i = parent(i);
        }
    }

    private void heapifyDown(int i) {
        int minIdx = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < heap.size() && heap.get(left) < heap.get(minIdx)) {
            minIdx = left;
        }

        if (right < heap.size() && heap.get(right) < heap.get(minIdx)) {
            minIdx = right;
        }

        if (i != minIdx) {
            Collections.swap(heap, i, minIdx);
            heapifyDown(minIdx);
        }
    }

    public void insert(int val) {
        heap.add(val);
        heapifyUp(heap.size() - 1);
    }

    public int extractMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }

        int min = heap.get(0);
        int lastVal = heap.remove(heap.size() - 1);

        if (!isEmpty()) {
            heap.set(0, lastVal);
            heapifyDown(0);
        }

        return min;
    }

    public int getMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public String toString() {
        return "Heap content: " + heap.toString();
    }

    public static void main(String[] args) {
        System.out.println("=== Basic Min Heap 測試 ===");
        BasicMinHeapPractice minHeap = new BasicMinHeapPractice();

        int[] valuesToInsert = {15, 10, 20, 8, 25, 5};
        System.out.print("插入順序: ");
        for (int val : valuesToInsert) {
            System.out.print(val + " ");
        }
        System.out.println("\n");

        for (int val : valuesToInsert) {
            minHeap.insert(val);
            System.out.println("插入 " + val + " 後: " + minHeap);
        }

        System.out.println("\n當前最小元素 (getMin): " + minHeap.getMin());
        System.out.println("當前 Heap 大小 (size): " + minHeap.size());
        
        System.out.println("\n期望的 extractMin 順序: 5, 8, 10, 15, 20, 25");
        System.out.println("實際的 extractMin 順序:");
        
        while (!minHeap.isEmpty()) {
            int min = minHeap.extractMin();
            System.out.print(min + " ");
        }
        
        System.out.println("\n\n所有元素取出後, Heap 是否為空 (isEmpty): " + minHeap.isEmpty());
    }
}
