package linkedlist.mediumonsinglylinkedlist;

import linkedlist.singlylinkedlist.ListNode;

public class GFG_LengthOfLoopOfLinkedList {
    // Created at: 22 - March - 2026
    // Last revised at: 22 - March - 2026
    // LeetCode link:
    // https://www.geeksforgeeks.org/problems/find-the-length-of-the-loop/
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    /*
     * Problem Description:
     * Given the head of a linked list, return the length of the cycle (loop) if one
     * exists.
     * If there is no cycle, return 0.
     *
     * Example:
     * Input: head = [1, 2, 3, 4, 5], tail connects to index 1 (ListNode 2)
     * Output: 4 (cycle: 2 → 3 → 4 → 5 → 2)
     *
     * Input: head = [1], head.next = head
     * Output: 1
     *
     * Input: head = [1, 2, 3], no cycle
     * Output: 0
     *
     * Constraints:
     * - The number of ListNodes in the list is in the range [0, 10^4].
     * - 1 <= ListNode.val <= 10^5
     * - pos is -1 (no cycle) or a valid index denoting the tail's next connection.
     *
     * ─────────────────────────────────────────────────────────────────
     *
     * Approach 1 — HashSet (Visited ListNodes + Entry Tracking)
     * Idea : Store every visited ListNode. The first repeated ListNode is the
     * cycle entry; record its position to compute cycle length.
     * Time : O(n)
     * Space : O(n)
     * Drawback: Extra space for the HashSet; no exploitation of cycle structure.
     *
     * ★ Approach 2 — Floyd's Detection + Entry Point + In-Cycle Count
     * Idea : Phase 1 — slow (1 step) and fast (2 steps) detect the
     * meeting point inside the cycle.
     * Phase 2 — reset slow to head; advance both at speed 1
     * to find the cycle entry ListNode (same theorem as detectCycle).
     * Phase 3 — hold slow at the entry, walk fast one step at
     * a time counting until it laps back to slow.
     * Time : O(n)
     * Space : O(1)
     * Key Insight: The cycle entry found in Phase 2 serves as a fixed
     * reference ListNode. One full traversal of the cycle from
     * that point gives the exact cycle length.
     */

    /**
     * Finds the length of the cycle in a singly linked list, if one exists.
     * Uses Floyd's cycle detection to locate the cycle entry, then counts
     * the number of ListNodes in the cycle.
     *
     * @param head the head ListNode of the singly linked list
     * @return the number of ListNodes in the cycle, or {@code 0} if no cycle exists
     */
    public int lengthOfLoop(ListNode head) {

        // Edge case: empty list has no loop
        if (head == null)
            return 0;

        // Edge case: single ListNode pointing to itself — loop length is 1
        if (head.next == head)
            return 1;

        // Both pointers start at head (canonical Floyd's formulation)
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

                    // ── Phase 2: locate cycle entry (Floyd's entry-point theorem) ──
                    slow = head;

                    while (slow != fast) {
                        slow = slow.next;
                        fast = fast.next;
                    }
                    // slow == fast == cycle entry ListNode

                    // ── Phase 3: count cycle length from entry ListNode ──
                    int cnt = 1;

                    // fast walks ahead; stop when it laps back to slow (the entry)
                    fast = fast.next;
                    while (fast != slow) {
                        cnt++;
                        fast = fast.next;
                    }

                    return cnt;
                }
            }
        }

        // fast reached null — no cycle exists
        return 0;
    }
}
