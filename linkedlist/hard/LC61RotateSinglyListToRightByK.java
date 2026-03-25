package linkedlist.hard;

import linkedlist.singlylinkedlist.ListNode;

public class LC61RotateSinglyListToRightByK {
    // Created at: 26 - March - 2026
    // Last revised at: 26 - March - 2026
    // LeetCode link: https://leetcode.com/problems/rotate-list/
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    /*
     * Problem Description:
     * Given the head of a linked list and an integer k, rotate the list to
     * the right by k places and return the new head.
     *
     * Example:
     * Input : 1 -> 2 -> 3 -> 4 -> 5, k = 2
     * Output: 4 -> 5 -> 1 -> 2 -> 3
     *
     * Input : 0 -> 1 -> 2, k = 4
     * Output: 2 -> 0 -> 1
     *
     * Constraints:
     * 0 <= number of nodes <= 500
     * -100 <= node.val <= 100
     * 0 <= k <= 2 * 10^9
     *
     * ─────────────────────────────────────────────────────────────
     * Approaches:
     *
     * 1. Brute Force (Rotate One Step at a Time)
     * Idea : Perform a single right rotation k times. Each rotation detaches
     * the last node and prepends it to the head.
     * Time : O(n * k)
     * Space : O(1)
     * Drawback: Extremely slow for large k (up to 2 * 10^9); ignores that
     * rotations are cyclic with period = list length.
     *
     * 2. ★ Circular Link + Find New Tail
     * Idea : First compute list length while walking to the tail, then form
     * a circular list by linking tail back to head. Reduce k modulo
     * length (k rotations of a length-n list cycle every n steps).
     * Walk to position (len - k - 1) — the new tail — break the
     * circle there, and return its next node as the new head.
     * Time : O(n)
     * Space : O(1)
     * Key Insight: Forming a circle avoids any actual node movement — rotation
     * becomes a pure pointer arithmetic problem of finding the
     * correct break point.
     * ─────────────────────────────────────────────────────────────
     */

    /**
     * Rotates a singly linked list to the right by {@code k} places.
     *
     * @param head the head node of the linked list
     * @param k    the number of right-rotation steps
     * @return the head of the rotated linked list
     */
    public ListNode rotateRight(ListNode head, int k) {

        // Edge case: empty list or single node — rotation has no effect
        if (head == null || head.next == null)
            return head;

        // --- Step 1: Measure the list and land mover on the tail ---
        int len = 1;
        ListNode mover = head;

        while (mover.next != null) {
            len++;
            mover = mover.next;
        }

        // --- Step 2: Form a circular list by linking tail back to head ---
        mover.next = head;
        mover = head; // reset mover to head for the next walk

        // --- Step 3: Reduce k — rotating by 'len' steps is a full cycle (no-op) ---
        k = k % len;

        // --- Step 4: Walk to the new tail, which sits at index (len - k - 1) ---
        // The new head is (len - k) steps from the original head
        int cnt = 0;
        while (cnt < len - k - 1) {
            cnt++;
            mover = mover.next;
        }

        // --- Step 5: Break the circle — mover is the new tail ---
        head = mover.next; // new head is the node just after the new tail
        mover.next = null; // sever the link to complete the rotation

        return head;
    }
}
