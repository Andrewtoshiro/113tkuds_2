import java.util.Scanner;

/**
 * Represents a single node in a linked list. In this context, it could be a patient appointment.
 * NOTE: This class is reused from previous problems. For a standalone file, it should be included.
 */
// class ListNode {
//     int val;
//     ListNode next;
//     ListNode(int val) { this.val = val; }
// }

/**
 * Solves the "Merge Two Sorted Lists" problem.
 * This simulates the task of combining two separately sorted patient appointment lists
 * from two different clinics into a single, chronologically sorted list for a central display.
 */
public class LC21_MergeTwoLists_Clinics {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the sizes of the two lists
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Build the first and second linked lists
        ListNode list1 = buildList(scanner, n);
        ListNode list2 = buildList(scanner, m);

        // Merge the two lists
        ListNode mergedHead = mergeTwoLists(list1, list2);

        // Print the final merged list
        printList(mergedHead);

        scanner.close();
    }

    /**
     * Merges two sorted linked lists into one sorted linked list.
     * @param list1 The head of the first sorted list.
     * @param list2 The head of the second sorted list.
     * @return The head of the newly merged sorted list.
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // A dummy node serves as a starting point for the new list, simplifying the code.
        ListNode dummy = new ListNode(0);
        // The 'tail' pointer keeps track of the last node in the new list.
        ListNode tail = dummy;

        // Traverse both lists as long as neither is exhausted.
        while (list1 != null && list2 != null) {
            // Compare the values of the current nodes in both lists.
            if (list1.val <= list2.val) {
                // Append the smaller node from list1 to the new list.
                tail.next = list1;
                // Advance the pointer in list1.
                list1 = list1.next;
            } else {
                // Append the smaller node from list2 to the new list.
                tail.next = list2;
                // Advance the pointer in list2.
                list2 = list2.next;
            }
            // Advance the tail pointer of the new list.
            tail = tail.next;
        }

        // At this point, one of the lists is exhausted.
        // Append the remaining part of the other list to the end.
        tail.next = (list1 != null) ? list1 : list2;

        // The merged list starts after the dummy node.
        return dummy.next;
    }
    
    /**
     * Helper function to build a linked list from scanner input.
     * @param scanner The Scanner object.
     * @param size The number of nodes in the list.
     * @return The head of the created list.
     */
    public static ListNode buildList(Scanner scanner, int size) {
        if (size == 0) return null;
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        for (int i = 0; i < size; i++) {
            current.next = new ListNode(scanner.nextInt());
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
// This is a classic iterative solution for merging sorted lists.
// The use of a dummy head node elegantly avoids special handling for the first node.
// The time complexity is O(n+m) as we visit each node from both lists exactly once.
