class Solution {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode slow = head;
    ListNode fast = head;

    while (n-- > 0)
      fast = fast.next;
    if (fast == null)
      return head.next;

    while (fast.next != null) {
      slow = slow.next;
      fast = fast.next;
    }
    slow.next = slow.next.next;

    return head;
  }
}

// 1. This solution uses the classic two-pointer (fast and slow) technique to solve
//    the problem in a single pass, which is highly efficient.

// 2. The 'fast' pointer is first advanced n steps to create a fixed gap between it
//    and the 'slow' pointer. The edge case where n equals the list length (fast becomes
//    null) is handled by removing the head.

// 3. Both pointers are then moved together until 'fast' reaches the end of the list;
//    at this point, 'slow' will be positioned directly before the target node,
//    allowing for its removal.
