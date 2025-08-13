import java.util.Arrays;

public class ValidMaxHeapChecker {

    public static boolean isValidMaxHeap(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("[] → true");
            return true;
        }

        int n = arr.length;
        for (int i = (n / 2) - 1; i >= 0; i--) {
            int leftChildIdx = 2 * i + 1;
            int rightChildIdx = 2 * i + 2;

            if (leftChildIdx < n && arr[i] < arr[leftChildIdx]) {
                System.out.println(Arrays.toString(arr) + " → false (index " + i + " with value " + arr[i] + " is less than its left child " + arr[leftChildIdx] + ")");
                return false;
            }

            if (rightChildIdx < n && arr[i] < arr[rightChildIdx]) {
                System.out.println(Arrays.toString(arr) + " → false (index " + i + " with value " + arr[i] + " is less than its right child " + arr[rightChildIdx] + ")");
                return false;
            }
        }
        
        System.out.println(Arrays.toString(arr) + " → true");
        return true;
    }

    public static void main(String[] args) {
        System.out.println("=== Checking Arrays for Valid Max Heap Property ===");

        int[] test1 = {100, 90, 80, 70, 60, 75, 65};
        isValidMaxHeap(test1);

        int[] test2 = {100, 90, 80, 95, 60, 75, 65};
        isValidMaxHeap(test2);

        int[] test3 = {50};
        isValidMaxHeap(test3);

        int[] test4 = {};
        isValidMaxHeap(test4);
        
        int[] test5 = {100, 19, 36, 17, 3, 25, 1, 2, 7};
        isValidMaxHeap(test5);
        
        int[] test6 = {10, 20, 30};
        isValidMaxHeap(test6);
    }
}
