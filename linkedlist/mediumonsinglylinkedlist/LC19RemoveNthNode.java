package linkedlist.mediumonsinglylinkedlist;

import linkedlist.singlylinkedlist.ListNode;

public class LC19RemoveNthNode {
    // Created at: 23 - March - 2026
    // Last revised at: 23 - March - 2026
    // LeetCode link:
    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    // Time Complexity: O(n)
    // Space Complexity: O(n) — recursion call stack

    /*
     * Problem Description:
     * Given the head of a linked list, remove the nth node from the END of the
     * list and return the head of the modified list.
     *
     * Example:
     * Input: head = [1, 2, 3, 4, 5], n = 2
     * Output: [1, 2, 3, 5]
     *
     * Input: head = [1], n = 1
     * Output: []
     *
     * Constraints:
     * - The number of nodes in the list is sz
     * - 1 <= sz <= 30
     * - 0 <= Node.val <= 100
     * - 1 <= n <= sz
     *
     * ─────────────────────────────────────────────────────────────────
     *
     * Approach 1: Recursive Post-Order Counter
     * Idea: Recurse to the end of the list, then increment a counter on the
     * way back (post-order). When the counter hits n, the current node
     * is the one to remove — return head.next instead of head.
     * A second cnt++ fires immediately after the match to push cnt past n,
     * preventing any ancestor node from accidentally matching.
     * Time: O(n) — visits every node once
     * Space: O(n) — call stack grows to the full length of the list
     * Drawback: Uses a class-level field (cnt) which makes the solution
     * stateful and non-reentrant — calling this method twice on the
     * same object (e.g., in tests) will produce wrong results unless
     * cnt is manually reset. Not safe for concurrent use.
     *
     * Approach 2: Two-Pointer (Fast / Slow) ★
     * Idea: Advance a "fast" pointer n steps ahead of a "slow" pointer.
     * Then move both one step at a time until fast reaches the last node.
     * At that point, slow is right before the target node, so
     * slow.next = slow.next.next removes it in O(1).
     * A dummy head node handles the edge case of removing the head itself.
     * Time: O(n) — single pass
     * Space: O(1) — no recursion, only two pointers
     * Key Insight: The n-step gap between fast and slow ensures they maintain
     * exactly the right distance so slow lands one node before
     * the target when fast reaches the end.
     */

    int cnt = 0;

    /**
     * Removes the nth node from the end of the list using recursive post-order
     * counting, and returns the head of the modified list.
     *
     * @param head the head node of the linked list
     * @param n    the 1-indexed position from the end of the node to remove
     * @return the head node of the list after removal
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Base case: past the last node — start unwinding with cnt = 0
        if (head == null)
            return null;

        // ── Step 1: Recurse to the end first (post-order) ────────────────────
        // Reattach head.next to whatever the recursive call returns
        // (either the same node, or its successor if that node was removed)
        head.next = removeNthFromEnd(head.next, n);

        // ── Step 2: Increment counter on the way back up ─────────────────────
        // cnt = 1 when unwinding from the last node, 2 from second-to-last, etc.
        cnt++;

        // ── Step 3: Check if this is the node to remove ──────────────────────
        if (n == cnt) {
            cnt++; // push cnt past n so no ancestor node re-triggers this branch
            return head.next; // skip current node — effectively removes it
        }

        // ── Step 4: Not the target node — return it unchanged ────────────────
        return head;
    }
}
