package linkedlist.mediumonsinglylinkedlist;

import linkedlist.singlylinkedlist.ListNode;

public class LC142CycleLengthOfSinglyLinkedList {
    // Created at: 22 - March - 2026
    // Last revised at: 22 - March - 2026
    // LeetCode link: https://leetcode.com/problems/linked-list-cycle-ii/
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    /*
     * Problem Description:
     * Given the head of a linked list, return the node where the cycle begins.
     * If there is no cycle, return null.
     *
     * Example:
     * Input: head = [3, 2, 0, -4], tail connects to index 1
     * Output: node with value 2 (cycle entry point)
     *
     * Input: head = [1, 2], tail connects to index 0
     * Output: node with value 1
     *
     * Input: head = [1], no cycle
     * Output: null
     *
     * Constraints:
     * - The number of nodes in the list is in the range [0, 10^4].
     * - -10^5 <= Node.val <= 10^5
     * - pos is -1 or a valid index (denotes the tail's next connection; not passed
     * as a parameter).
     *
     * ─────────────────────────────────────────────────────────────────
     *
     * Approach 1 — HashSet (Visited Nodes)
     * Idea : Traverse the list, storing each node in a HashSet.
     * The first node seen twice is the cycle entry point.
     * Time : O(n)
     * Space : O(n)
     * Drawback: Extra space proportional to number of nodes; does not
     * exploit the mathematical structure of the cycle.
     *
     * ★ Approach 2 — Floyd's Cycle Detection + Entry Point Theorem
     * Idea : Phase 1 — use slow (1 step) and fast (2 steps) pointers,
     * both starting at head, to find the meeting point inside the cycle.
     * Phase 2 — reset slow to head; advance both slow and fast
     * one step at a time. The node where they meet again is the
     * cycle entry point.
     * Time : O(n)
     * Space : O(1)
     * Key Insight: If the distance from head to cycle entry is F, and the
     * meeting point is K steps into the cycle, then
     * F ≡ (cycle_length - K) mod cycle_length.
     * Resetting slow to head and walking both pointers at speed 1
     * neutralises this offset — they converge exactly at the entry.
     */

    /**
     * Detects the node at which a cycle begins in a singly linked list.
     * Uses Floyd's two-pointer algorithm with the cycle entry-point theorem.
     *
     * @param head the head node of the singly linked list
     * @return the {@link ListNode} where the cycle starts, or {@code null} if no
     *         cycle exists
     */
    public ListNode detectCycle(ListNode head) {

        // Edge case: empty list has no cycle
        if (head == null)
            return null;

        // Edge case: single node pointing to itself — entry is head itself
        if (head.next == head)
            return head;

        // Both pointers start at head (unlike hasCycle where fast = head.next)
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {

            // fast takes its first step
            fast = fast.next;

            if (fast != null) {

                // slow takes its one step; fast takes its second step
                slow = slow.next;
                fast = fast.next;

                if (slow == fast) {
                    // ── Phase 2: locate cycle entry ──────────────────────────
                    // Reset slow to head; fast stays at meeting point
                    slow = head;

                    // Advance both one step at a time until they meet
                    while (slow != fast) {
                        slow = slow.next;
                        fast = fast.next;
                    }

                    // Meeting point is the cycle entry node
                    return slow;
                }
            }
        }

        // fast reached null — no cycle exists
        return null;
    }
}
