import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Represents a single node in a linked list. In this context, it's a patient's call number.
 * NOTE: This class is reused from previous problems. For a standalone file, it should be included.
 */
// class ListNode {
//     int val;
//     ListNode next;
//     ListNode(int val) { this.val = val; }
// }

/**
 * Solves the "Merge k Sorted Lists" problem.
 * This simulates merging multiple sorted patient waiting lists from 'k' different hospital departments
 * into a single, globally sorted list for a central display system.
 */
public class LC23_MergeKLists_Hospitals {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of lists (departments)
        int k = scanner.nextInt();
        scanner.nextLine(); // Consume the rest of the line

        ListNode[] lists = new ListNode[k];
        for (int i = 0; i < k; i++) {
            lists[i] = buildListFromLine(scanner.nextLine());
        }

        // Merge the k lists
        ListNode mergedHead = mergeKLists(lists);

        // Print the final merged list
        printList(mergedHead);

        scanner.close();
    }

    /**
     * Merges k sorted linked lists into one sorted linked list using a min-heap.
     * @param lists An array of the heads of the k sorted lists.
     * @return The head of the newly merged sorted list.
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // A PriorityQueue (min-heap) to always keep track of the smallest current node among all lists.
        // We provide a comparator to order ListNodes by their 'val' property.
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));

        // 1. Add the head of each non-empty list to the min-heap.
        for (ListNode head : lists) {
            if (head != null) {
                minHeap.add(head);
            }
        }

        // A dummy node to simplify building the new merged list.
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // 2. Process nodes from the heap until it's empty.
        while (!minHeap.isEmpty()) {
            // Get the node with the smallest value from the heap.
            ListNode minNode = minHeap.poll();
            
            // Append it to our result list.
            tail.next = minNode;
            tail = tail.next;
            
            // If the extracted node has a next element, add it to the heap.
            // This maintains the heap with the current heads of the remaining list portions.
            if (minNode.next != null) {
                minHeap.add(minNode.next);
            }
        }

        return dummy.next;
    }

    /**
     * Helper function to build a linked list from a single line of space-separated numbers
     * ending with -1.
     * @param line The string line from input.
     * @return The head of the created list.
     */
    public static ListNode buildListFromLine(String line) {
        String[] numbers = line.split(" ");
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        for (String numStr : numbers) {
            int val = Integer.parseInt(numStr);
            if (val == -1) break;
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummyHead.next;
    }

    /**
     * Helper function to print the linked list.
     * @param head The head of the list to print.
     */
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + (current.next != null ? " " : ""));
            current = current.next;
        }
        System.out.println();
    }
}
// The use of a PriorityQueue (min-heap) is the key to an efficient solution.
// It allows finding the minimum among k elements in O(log k) time.
// The overall time complexity is O(N log k), where N is the total number of nodes.
