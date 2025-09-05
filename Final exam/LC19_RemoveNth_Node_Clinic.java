import java.util.Scanner;

/**
 * Represents a single node in a linked list.
 * In this context, it's a single nursing record.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

/**
 * Solves the "Remove Nth Node From End of List" problem.
 * This simulates a scenario in a clinic where the Nth-to-last nursing record
 * needs to be removed from a chronological, singly-linked list of records.
 */
public class LC19_RemoveNth_Node_Clinic {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read list details
        int n = scanner.nextInt();
        if (n == 0) {
            scanner.close();
            return;
        }

        // Build the linked list from input
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        for (int i = 0; i < n; i++) {
            current.next = new ListNode(scanner.nextInt());
            current = current.next;
        }
        ListNode head = dummyHead.next;

        // Read k
        int k = scanner.nextInt();

        // Remove the node and get the new head
        ListNode newHead = removeNthFromEnd(head, k);

        // Print the modified list
        printList(newHead);
        
        scanner.close();
    }

    /**
     * Removes the k-th node from the end of a linked list.
     * @param head The head of the list.
     * @param k The position from the end of the node to remove.
     * @return The head of the modified list.
     */
    public static ListNode removeNthFromEnd(ListNode head, int k) {
        // A dummy node allows us to easily handle edge cases, like removing the head.
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;

        // 1. Move the 'fast' pointer k steps ahead of the 'slow' pointer.
        // We add +1 to the loop to create a gap of k nodes between slow and fast.
        for (int i = 0; i <= k; i++) {
            fast = fast.next;
        }

        // 2. Move both pointers forward at the same pace.
        // When 'fast' reaches the end of the list, 'slow' will be
        // at the node just before the one we want to remove.
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // 3. Bypass the node to be removed.
        slow.next = slow.next.next;

        return dummy.next;
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
// This solution uses the classic fast and slow pointer technique.
// The dummy node is a crucial element that simplifies handling the removal of the first node.
// The algorithm achieves the goal in a single pass, making it O(n) in time complexity.
