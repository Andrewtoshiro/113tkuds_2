class Solution {
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null || list2 == null)
      return list1 == null ? list2 : list1;
    if (list1.val > list2.val) {
      ListNode temp = list1;
      list1 = list2;
      list2 = temp;
    }
    list1.next = mergeTwoLists(list1.next, list2);
    return list1;
  }
}
// This recursive solution merges two sorted linked lists by consistently choosing the smaller node.
// The base case handles the scenario where one of the lists is empty, returning the non-empty list.
// The recursive step links the smaller of the two current nodes to the result of merging the rest of the lists.
