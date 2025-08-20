import java.util.Scanner;

public class M01_BuildHeap {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String type = sc.next();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        sc.close();

        buildHeap(arr, n, type);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();
    }

    public static void buildHeap(int[] arr, int n, String type) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, n, i, type);
        }
    }

    public static void heapifyDown(int[] arr, int n, int i, String type) {
        int extreme = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (type.equals("max")) {
            if (leftChild < n && arr[leftChild] > arr[extreme]) {
                extreme = leftChild;
            }
            if (rightChild < n && arr[rightChild] > arr[extreme]) {
                extreme = rightChild;
            }
        } else {
            if (leftChild < n && arr[leftChild] < arr[extreme]) {
                extreme = leftChild;
            }
            if (rightChild < n && arr[rightChild] < arr[extreme]) {
                extreme = rightChild;
            }
        }

        if (extreme != i) {
            int temp = arr[i];
            arr[i] = arr[extreme];
            arr[extreme] = temp;

            heapifyDown(arr, n, extreme, type);
        }
    }

    /*
     * Time Complexity: O(n)
     * 說明:
     * 1. buildHeap 函式從最後一個非葉節點 (n/2 - 1) 開始，向上遍歷至根節點(0)，並對每個節點執行 heapifyDown。
     * 2. heapifyDown 的操作次數與節點的高度 h 成正比，即 O(h)。
     * 3. 雖然單次 heapifyDown 的最壞時間複雜度為 O(log n)，但建堆過程中，大部分節點都位於樹的底層，高度很小。精確計算所有節點的總執行成本後，可證明其時間複雜度為線性的 O(n)，而非 O(n log n)。  
*/


    
}
