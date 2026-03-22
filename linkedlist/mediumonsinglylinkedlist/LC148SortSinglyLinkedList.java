package linkedlist.mediumonsinglylinkedlist;

import linkedlist.singlylinkedlist.ListNode;

public class LC148SortSinglyLinkedList {
    // Created at: 23 - March - 2026
    // Last revised at: 23 - March - 2026
    // LeetCode link: https://leetcode.com/problems/sort-list/
    // Time Complexity: O(n log n)
    // Space Complexity: O(log n) — recursion call stack depth

    /*
     * Problem Description:
     * Given the head of a linked list, return the list after sorting it in
     * ascending order.
     *
     * Example:
     * Input: head = [4, 2, 1, 3]
     * Output: [1, 2, 3, 4]
     *
     * Input: head = [-1, 5, 3, 4, 0]
     * Output: [-1, 0, 3, 4, 5]
     *
     * Constraints:
     * - The number of nodes in the list is in the range [0, 5 * 10^4]
     * - -10^5 <= Node.data <= 10^5
     *
     * ─────────────────────────────────────────────────────────────────
     *
     * Approach 1: Top-Down Merge Sort (Recursive) ★
     * Idea: Recursively split the list into two halves at the middle node,
     * sort each half independently, then merge the two sorted halves.
     * Uses the fast/slow pointer trick to locate the middle in O(n).
     * middleNode() and mergeTwoSortedLists() are extracted as helpers
     * to keep each method focused and independently testable.
     * Time: O(n log n) — list is halved log n times; each level merges in O(n)
     * Space: O(log n) — implicit call stack grows log n levels deep
     * Key Insight: Starting fast at head.next (instead of head) ensures mid
     * always lands at the END of the left half, giving a clean
     * split and preventing infinite recursion on 2-node lists.
     *
     * Approach 2: Bottom-Up Merge Sort (Iterative)
     * Idea: Iteratively merge sublists of increasing size (1, 2, 4, 8 …)
     * until the entire list is sorted. No recursion needed.
     * Time: O(n log n)
     * Space: O(1) — purely iterative, no call stack overhead
     * Drawback: Significantly more complex to implement correctly due to manual
     * pointer rewiring; the O(log n) → O(1) space saving rarely
     * justifies the added implementation complexity.
     */

    /**
     * Finds the middle node of a linked list using the fast/slow pointer technique.
     * For an even-length list, returns the LAST node of the left half,
     * ensuring a clean 50/50 split when used with sortList().
     *
     * @param head the head node of the linked list
     * @return the middle (or left-middle) node of the list
     */
    public ListNode middleNode(ListNode head) {
        // Edge case: empty list has no middle
        if (head == null)
            return null;

        // ── Step 1: Initialise fast one step ahead of slow ───────────────────
        // Starting fast at head.next (not head) biases the midpoint left,
        // so mid lands at the last node of the left half — not the first of the right.
        ListNode fast = head.next;
        ListNode slow = head;

        // ── Step 2: Advance fast 2 steps, slow 1 step per iteration ─────────
        // slow advances only when fast successfully takes its 2nd step,
        // so slow always stays exactly half as far as fast.
        while (fast != null) {
            fast = fast.next; // fast takes 1st step
            if (fast != null) {
                fast = fast.next; // fast takes 2nd step
                slow = slow.next; // slow advances only on a full 2-step iteration
            }
        }
        // slow is now at the last node of the left half
        return slow;
    }

    /**
     * Merges two sorted singly linked lists into a single sorted linked list.
     *
     * @param left  the head node of the first sorted linked list
     * @param right the head node of the second sorted linked list
     * @return the head node of the merged sorted linked list
     */
    public ListNode mergeTwoSortedLists(ListNode left, ListNode right) {
        // Base cases: if either list is empty, the other is the merged result
        if (left == null)
            return right;
        if (right == null)
            return left;

        // ── Step 1: Set up a dummy head to simplify append logic ─────────────
        ListNode mergedHead = new ListNode(-1); // dummy — never returned
        ListNode tail = mergedHead; // tail tracks the last merged node

        ListNode l = left;
        ListNode r = right;

        // ── Step 2: Pick the smaller head and append it each iteration ───────
        while (l != null && r != null) {
            ListNode temp;
            if (l.data < r.data) {
                temp = l; // l is smaller: pick it
                l = l.next;
            } else {
                temp = r; // r is smaller or equal: pick it
                r = r.next;
            }
            temp.next = null; // detach node to avoid stale forward links
            tail.next = temp; // append to merged list
            tail = tail.next; // advance tail
        }

        // ── Step 3: Append whichever list still has remaining nodes ──────────
        // At most one of l or r is non-null here
        if (l != null)
            tail.next = l;
        if (r != null)
            tail.next = r;

        return mergedHead.next; // skip dummy head
    }

    /**
     * Sorts a singly linked list in ascending order using top-down merge sort.
     *
     * @param head the head node of the unsorted linked list
     * @return the head node of the sorted linked list
     */
    public ListNode sortList(ListNode head) {
        // Base case: empty list or single node is already sorted
        if (head == null || head.next == null)
            return head;

        // ── Step 1: Find the end of the left half ────────────────────────────
        ListNode mid = middleNode(head);

        // ── Step 2: Split the list into two halves ───────────────────────────
        ListNode nextNode = mid.next; // head of the right half
        mid.next = null; // sever the link — left half now ends at mid

        // ── Step 3: Recursively sort each half ───────────────────────────────
        ListNode left = sortList(head); // sort left half
        ListNode right = sortList(nextNode); // sort right half

        // ── Step 4: Merge and return the two sorted halves ───────────────────
        return mergeTwoSortedLists(left, right);
    }
}
