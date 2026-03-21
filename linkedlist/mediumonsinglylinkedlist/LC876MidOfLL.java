package linkedlist.mediumonsinglylinkedlist;

import linkedlist.singlylinkedlist.ListNode;

public class LC876MidOfLL {
    // Created at: 22 - March - 2025
    // Last revised at: 22 - March - 2025
    // LeetCode link: https://leetcode.com/problems/middle-of-the-linked-list/
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    /*
     * Problem Description:
     * Given the head of a singly linked list, return the middle node of the linked
     * list.
     * If there are two middle nodes, return the second middle node.
     *
     * Example:
     * Input: head = [1, 2, 3, 4, 5] → Output: [3, 4, 5] (node with value 3)
     * Input: head = [1, 2, 3, 4] → Output: [2, 3, 4] (second middle = node 3...
     * wait: nodes are 1,2,3,4 → second middle = 3)
     *
     * Constraints:
     * - The number of nodes in the list is in the range [1, 100].
     * - 1 <= Node.val <= 100
     *
     * ─────────────────────────────────────────────────────────────────
     *
     * Approach 1 — Two-Pass (Count + Traverse)
     * Idea : First pass counts total nodes n. Second pass walks to index n/2.
     * Time : O(n)
     * Space : O(1)
     * Drawback: Requires two full traversals; more code.
     *
     * ★ Approach 2 — Slow & Fast Pointer (One-Pass)
     * Idea : Use two pointers. `fast` advances two steps per iteration;
     * `slow` (reusing `head`) advances one step only when `fast`
     * can take its second step. When `fast` exhausts the list,
     * `slow` sits exactly at the middle (second middle for even length).
     * Time : O(n)
     * Space : O(1)
     * Key Insight: fast travels 2x the distance of slow, so when fast
     * reaches the end, slow is at the halfway point.
     */

    /**
     * Finds and returns the middle node of a singly linked list.
     * For an even-length list, returns the second of the two middle nodes.
     *
     * @param head the head node of the singly linked list
     * @return the middle {@link ListNode}; {@code null} if the list is empty
     */
    public ListNode middleNode(ListNode head) {

        // Edge case: empty list
        if (head == null)
            return null;

        // fast pointer starts at head alongside slow (head itself)
        ListNode fast = head;

        while (fast != null) {

            // fast takes its first step
            fast = fast.next;

            if (fast != null) {
                // fast takes its second step — it can move twice this iteration
                fast = fast.next;

                // slow advances only when fast completed both steps
                head = head.next;
            }
        }

        // slow pointer (head) now sits at the middle node
        return head;
    }
}
