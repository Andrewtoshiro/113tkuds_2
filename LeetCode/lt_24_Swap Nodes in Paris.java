class Solution {
  public ListNode swapPairs(ListNode head) {
    final int length = getLength(head);
    // Use a dummy node to simplify handling the head of the list.
    ListNode dummy = new ListNode(0, head);
    ListNode prev = dummy;
    ListNode curr = head;

    for (int i = 0; i < length / 2; ++i) {
      ListNode next = curr.next;
      // Perform the swap: prev -> curr -> next becomes prev -> next -> curr.
      curr.next = next.next;
      next.next = curr;
      prev.next = next;
      // Move pointers forward to the next pair for the next iteration.
      prev = curr;
      curr = curr.next;
    }

    return dummy.next;
  }

  private int getLength(ListNode head) {
    int length = 0;
    for (ListNode curr = head; curr != null; curr = curr.next)
      ++length;
    return length;
  }
}
// 1. A dummy node is used as a placeholder before the actual head. This simplifies the swapping logic for the first pair, as it ensures every pair has a `prev` node.
// 2. The core swap operation involves re-wiring three `next` pointers: `curr.next`, `next.next`, and `prev.next` to reverse the order of the `curr` and `next` nodes.
// 3. After a swap, `prev` is updated to `curr` (which is now the second node in the pair) to correctly position it for the next iteration.
