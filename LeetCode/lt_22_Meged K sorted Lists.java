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
  public ListNode mergeKLists(ListNode[] lists) {
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    // Use a min-heap to keep track of the smallest node among all lists.
    Queue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

    // Add the head of each list to the min-heap initially.
    for (final ListNode list : lists)
      if (list != null)
        minHeap.offer(list);

    // Repeatedly extract the minimum node and add its next node to the heap.
    while (!minHeap.isEmpty()) {
      ListNode minNode = minHeap.poll();
      if (minNode.next != null)
        minHeap.offer(minNode.next);
      curr.next = minNode;
      curr = curr.next;
    }

    return dummy.next;
  }
}
// This solution utilizes a PriorityQueue (min-heap) to efficiently find the minimum element across all k lists.
// The time complexity is O(N log k) where N is the total number of nodes and k is the number of lists.
// A dummy node is used as a sentinel to simplify the logic for building the newly merged list.
