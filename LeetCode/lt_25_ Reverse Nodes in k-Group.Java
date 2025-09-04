/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null)
      return null;

    ListNode tail = head;

    for (int i = 0; i < k; ++i) {
      // There are less than k nodes in the list, do nothing.
      if (tail == null)
        return head;
      tail = tail.next;
    }

    ListNode newHead = reverse(head, tail);
    head.next = reverseKGroup(tail, k);
    return newHead;
  }

  // Reverses [head, tail).
  private ListNode reverse(ListNode head, ListNode tail) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != tail) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    return prev;
  }
}

// This solution employs recursion to solve the problem. It first identifies a
// sublist of k nodes, reverses it, and then recursively calls itself on the
// remainder of the list, linking the results together.

// The time complexity is O(N) because each node in the list is visited a
// constant number of times during the process of finding the tail of a
// k-group and the subsequent reversal.

// The space complexity is O(N/k) due to the recursion stack depth. In the
// worst-case scenario (k=1), the space complexity would be O(N), and in the
// best-case scenario (k=N), it would be O(1).
