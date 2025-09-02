class Solution {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    int carry = 0;

    while (l1 != null || l2 != null || carry > 0) {
      if (l1 != null) {
        carry += l1.val;
        l1 = l1.next;
      }
      if (l2 != null) {
        carry += l2.val;
        l2 = l2.next;
      }
      curr.next = new ListNode(carry % 10);
      carry /= 10;
      curr = curr.next;
    }

    return dummy.next;
  }
}

// 1. This algorithm simulates manual, column-by-column addition using a 'carry' 
//    variable and a dummy head node to simplify building the result list.

// 2. The while loop's condition ensures iteration continues until both input lists are
//    exhausted and no final carry remains, handling lists of different lengths.

// 3. The modulo operator (carry % 10) calculates the current digit for the new node,
//    while integer division (carry / 10) calculates the carry-over for the next digit.
