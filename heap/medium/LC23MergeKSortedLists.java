package heap.medium;

import java.util.PriorityQueue;

import linkedlist.singlylinkedlist.ListNode;

public class LC23MergeKSortedLists {
    // Created at: 03-May-2026
    // Last revised at: 03-May-2026
    // Link: https://leetcode.com/problems/merge-k-sorted-lists/
    // Time Complexity: O(N log k) — N total nodes, k lists in heap at any time
    // Space Complexity: O(k) — heap holds at most k nodes

    /*
     * Problem Description:
     * Given an array of k linked lists, each sorted in ascending order,
     * merge all of them into one sorted linked list and return it.
     *
     * Example:
     * Input: lists = [[1->4->5], [1->3->4], [2->6]]
     * Output: 1->1->2->3->4->4->5->6
     *
     * Constraints:
     * - k == lists.length
     * - 0 <= k <= 10^4
     * - 0 <= lists[i].length <= 500
     * - -10^4 <= lists[i][j] <= 10^4
     * - lists[i] is sorted in ascending order
     * - Sum of all lists[i].length will not exceed 10^4
     *
     * Approaches:
     *
     * 1. Brute Force — collect all nodes, sort, re-link
     * Idea: Dump all node values into an array, sort it, rebuild the list.
     * Time: O(N log N) Space: O(N)
     * Drawback: Wastes the fact that each list is already sorted.
     *
     * ★ 2. Min-Heap (Optimal)
     * Key Insight: At any point, the next smallest node across all k lists
     * is the minimum among current heads. A min-heap tracks that in O(log k).
     * Seed the heap with the head of every non-null list. Each poll gives the
     * global minimum; push its next node (if any) back into the heap.
     * One node remains after the while loop — attach it directly.
     * Time: O(N log k) Space: O(k)
     */

    /**
     * Merges k sorted linked lists into one sorted linked list
     * using a min-heap to always extract the current global minimum.
     *
     * @param lists array of heads of k sorted linked lists
     * @return head of the merged sorted linked list
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0)
            return null;
        if (len == 1)
            return lists[0];

        // heap ordered by node value; capacity = k
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(len, (a, b) -> a.data - b.data);

        for (int i = 0; i < len; i++) {
            if (lists[i] != null)
                minHeap.offer(lists[i]);
        }

        ListNode dummyHead = new ListNode(-1);
        ListNode dummyTail = dummyHead;

        // stop at 1 — last node gets attached outside the loop
        while (minHeap.size() > 1) {
            ListNode min = minHeap.poll();
            ListNode nextNode = min.next;

            min.next = null; // detach before linking into result
            dummyTail.next = min;
            dummyTail = min;

            if (nextNode != null)
                minHeap.offer(nextNode);
        }

        // remaining node is already the tail; no next to push
        dummyTail.next = minHeap.poll();

        return dummyHead.next;
    }

}