package linkedlist.mediumonsinglylinkedlist;

import linkedlist.singlylinkedlist.ListNode;

public class LC141CycleInSinglyLL {
    // Created at: 22 - March - 2026
    // Last revised at: 22 - March - 2026
    // LeetCode link: https://leetcode.com/problems/linked-list-cycle/
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    /*
     * Problem Description:
     * Given the head of a linked list, determine if it contains a cycle.
     * A cycle exists if some node can be reached again by continuously following
     * the next pointer.
     *
     * Example:
     * Input: head = [3, 2, 0, -4], where tail connects to index 1
     * Output: true
     *
     * Input: head = [1], no cycle
     * Output: false
     *
     * Constraints:
     * - The number of nodes in the list is in the range [0, 10^4].
     * - -10^5 <= Node.val <= 10^5
     * - pos is -1 or a valid index in the list (used to denote tail connection).
     *
     * ─────────────────────────────────────────────────────────────────
     *
     * Approach 1 — HashSet (Visited Nodes)
     * Idea : Store every visited node in a HashSet. If a node is
     * encountered twice, a cycle exists.
     * Time : O(n)
     * Space : O(n)
     * Drawback: Uses extra space proportional to the number of nodes.
     *
     * ★ Approach 2 — Floyd's Slow & Fast Pointer
     * Idea : Use two pointers — slow moves 1 step per iteration,
     * fast moves 2 steps. If a cycle exists, fast will
     * eventually lap slow and they will meet. If no cycle,
     * fast reaches null.
     * fast is initialised to head.next (one step ahead) and
     * an early-exit handles the self-loop edge case (head.next == head).
     * Time : O(n)
     * Space : O(1)
     * Key Insight: In a cycle, the relative speed difference of 1 step/iteration
     * guarantees the pointers converge within at most n iterations.
     */

    /**
     * Detects whether a singly linked list contains a cycle.
     *
     * @param head the head node of the singly linked list
     * @return {@code true} if a cycle is detected; {@code false} otherwise
     */
    public boolean hasCycle(ListNode head) {

        // Edge case: empty list cannot have a cycle
        if (head == null)
            return false;

        // Edge case: single node pointing to itself is a cycle
        if (head.next == head)
            return true;

        // slow starts at head; fast starts one step ahead
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null) {

            // fast takes its first step
            fast = fast.next;

            // only continue if fast hasn't hit the end AND hasn't caught slow yet
            if (fast != null && fast != slow) {

                // slow advances one step
                slow = slow.next;

                // check if pointers meet after slow's advance
                if (slow == fast)
                    return true;

                // fast takes its second step
                fast = fast.next;
            }
        }

        // fast exhausted the list — no cycle found
        return false;
    }
}
