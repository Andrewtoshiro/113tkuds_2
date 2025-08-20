import java.util.Scanner;

record Student(int score, int originalIndex) {}

public class M11_HeapSortWithTie {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {
            students[i] = new Student(sc.nextInt(), i);
        }
        sc.close();

        heapSort(students);

        for (int i = 0; i < n; i++) {
            System.out.print(students[i].score() + (i == n - 1 ? "" : " "));
        }
        System.out.println();
    }

    public static void heapSort(Student[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapifyDown(arr, i, 0);
        }
    }

    private static void heapifyDown(Student[] arr, int heapSize, int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heapSize && isGreater(arr[left], arr[largest])) {
            largest = left;
        }

        if (right < heapSize && isGreater(arr[right], arr[largest])) {
            largest = right;
        }

        if (largest != index) {
            swap(arr, index, largest);
            heapifyDown(arr, heapSize, largest);
        }
    }

    private static boolean isGreater(Student s1, Student s2) {
        if (s1.score() != s2.score()) {
            return s1.score() > s2.score();
        }
        return s1.originalIndex() > s2.originalIndex();
    }

    private static void swap(Student[] arr, int i, int j) {
        Student temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /*
     * Time Complexity: O(N log N)
     * 說明:
     * 1. Heap Sort 演算法包含兩個主要階段：建立初始堆積(heap)與重複提取最大元素。
     * 2. 從初始陣列建立最大堆積(max-heap)的時間複雜度為 O(N)。
     * 3. 第二階段包含一個執行 N-1 次的迴圈，每次迭代中會交換元素並對縮小的堆積呼叫 heapifyDown，此操作耗時 O(log k)，其中 k 為當前堆積大小。此階段總複雜度為 O(N log N)。
     * 4. 整體時間複雜度由第二階段主導，因此為 O(N log N)。
     */
}
