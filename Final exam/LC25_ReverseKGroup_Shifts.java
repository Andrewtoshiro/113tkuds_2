import java.util.Scanner;

/**
 * Represents a single node in a linked list, e.g., a work shift.
 * NOTE: This class is reused from previous problems. For a standalone file, it should be included.
 */
// class ListNode {
//     int val;
//     ListNode next;
//     ListNode(int val) { this.val = val; }
// }

/**
 * Solves the "Reverse Nodes in k-Group" problem.
 * This simulates a complex schedule adjustment where shifts are reversed in groups of 'k'.
 * Any remaining shifts at the end that form a group smaller than 'k' are left unchanged.
 */
public class LC25_ReverseKGroup_Shifts {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read k and the list data
        int k = Integer.parseInt(scanner.nextLine());
        ListNode head = buildListFromLine(scanner.nextLine());

        // Reverse the list in k-groups
        ListNode newHead = reverseKGroup(head, k);

        // Print the result
        printList(newHead);

        scanner.close();
    }

    /**
     * Reverses the nodes of a linked list k at a time.
     * @param head The head of the list.
     * @param k The size of the group to reverse.
     * @return The head of the modified list.
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        // Edge cases: no list, or k=1 (no change needed).
        if (head == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // groupPrev points to the node just before the current group being reversed.
        ListNode groupPrev = dummy;

        while (true) {
            // 1. Check if there are at least k nodes left to reverse.
            ListNode kth = groupPrev;
            for (int i = 0; i < k; i++) {
                kth = kth.next;
                if (kth == null) {
                    // Not enough nodes, so we're done.
                    return dummy.next;
                }
            }

            // 2. We have a group of k. Let's reverse it.
            // groupNext will be the node after the current group.
            ListNode groupNext = kth.next;

            // Standard in-place reversal logic for the sublist.
            ListNode prev = groupNext; // The new 'next' for the last node of the group.
            ListNode curr = groupPrev.next; // The first node of the group to reverse.

            while (curr != groupNext) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // 3. Reconnect the pointers.
            // 'newTail' is the original head of this group, which is now the tail.
            ListNode newTail = groupPrev.next;
            // The node before the group should now point to the new head of the group ('kth').
            groupPrev.next = kth;
            
            // 4. Move to the next segment. The new 'groupPrev' is the tail of the group we just reversed.
            groupPrev = newTail;
        }
    }

    /**
     * Helper function to build a linked list from a single line of space-separated numbers.
     */
    public static ListNode buildListFromLine(String line) {
        String[] numbers = line.trim().split("\\s+");
        if (numbers.length == 0 || numbers[0].isEmpty()) return null;
        
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        for (String numStr : numbers) {
            current.next = new ListNode(Integer.parseInt(numStr));
            current = current.next;
        }
        return dummyHead.next;
    }

    /**
     * Helper function to print the linked list.
     */
    public static void printList(ListNode head) {
        if (head == null) return;
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + (current.next != null ? " " : ""));
            current = current.next;
        }
        System.out.println();
    }
}
// This is a challenging pointer manipulation problem.
// The solution first identifies a valid k-sized group before attempting any reversal.
// Carefully managing the pointers before, during, and after the reversal is key.
