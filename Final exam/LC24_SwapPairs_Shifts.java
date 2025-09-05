import java.util.Scanner;

/**
 * Represents a single node in a linked list. In this context, it could be a single work shift.
 * NOTE: This class is reused from previous problems. For a standalone file, it should be included.
 */
// class ListNode {
//     int val;
//     ListNode next;
//     ListNode(int val) { this.val = val; }
// }

/**
 * Solves the "Swap Nodes in Pairs" problem.
 * This simulates a "paired shift swap" in a schedule represented by a linked list,
 * where every two adjacent shifts (1st and 2nd, 3rd and 4th, etc.) are swapped.
 */
public class LC24_SwapPairs_Shifts {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Build the linked list from a single line of input
        ListNode head = buildListFromLine(scanner.nextLine());

        // Swap the pairs and get the new head
        ListNode newHead = swapPairs(head);

        // Print the modified list
        printList(newHead);

        scanner.close();
    }

    /**
     * Swaps every two adjacent nodes of a linked list.
     * @param head The head of the list.
     * @return The head of the modified list.
     */
    public static ListNode swapPairs(ListNode head) {
        // If the list is empty or has only one node, no swap is needed.
        if (head == null || head.next == null) {
            return head;
        }

        // A dummy node simplifies the logic for the first pair.
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // 'prev' will always point to the node just before the pair to be swapped.
        ListNode prev = dummy;

        // Loop as long as there is a complete pair of nodes to swap.
        while (prev.next != null && prev.next.next != null) {
            // Identify the two nodes to be swapped.
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            // Perform the swap by re-wiring the pointers.
            // prev -> first -> second -> (rest of list)
            // becomes
            // prev -> second -> first -> (rest of list)
            prev.next = second;
            first.next = second.next;
            second.next = first;

            // Move the 'prev' pointer to the end of the just-swapped pair (which is 'first').
            prev = first;
        }

        return dummy.next;
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
// This iterative solution is efficient and operates in constant extra space.
// The dummy node is a powerful technique to avoid special edge case code for the head of the list.
// Pointer manipulation requires careful tracking of the node before the pair being swapped.
