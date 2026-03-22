package linkedlist.mediumonsinglylinkedlist;

import linkedlist.singlylinkedlist.ListNode;

public class LC_328OddEvenLinkedList {
    // Created at: 23 - March - 2026
    // Last revised at: 23 - March - 2026
    // LeetCode link: https://leetcode.com/problems/odd-even-linked-list/
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    /*
     * Problem Description:
     * Given the head of a singly linked list, group all nodes with odd indices
     * together followed by nodes with even indices, and return the reordered list.
     * The first node is considered odd, the second even, and so on.
     * The relative order within the odd and even groups must be preserved.
     * Note: "odd/even" refers to node POSITION (1-indexed), not node VALUE.
     *
     * Example:
     * Input: head = [1, 2, 3, 4, 5]
     * Output: [1, 3, 5, 2, 4]
     *
     * Input: head = [2, 1, 3, 5, 6, 4, 7]
     * Output: [2, 3, 6, 7, 1, 5, 4]
     *
     * Constraints:
     * - The number of nodes is in the range [0, 10^4]
     * - -10^6 <= Node.val <= 10^6
     *
     * ─────────────────────────────────────────────────────────────────
     *
     * Approach 1: Dual Dummy-Head Lists with Boolean Flag ★
     * Idea: Maintain two separate dummy-headed lists — one for odd-positioned
     * nodes and one for even-positioned nodes. Traverse the original list
     * once, routing each node into its respective list using a boolean
     * flag that toggles each step. Finally, join the odd tail to the
     * even list's real head.
     * Time: O(n) — single pass through the list
     * Space: O(1) — only a fixed number of pointers allocated
     * Key Insight: Detaching each node (mover.next = null) before appending
     * prevents dangling links, making the merge step trivially safe.
     *
     * Approach 2: In-Place Two-Pointer Rewiring
     * Idea: Keep an odd pointer and an even pointer starting at nodes 1 and 2.
     * On each step, odd.next skips to odd.next.next and even.next skips
     * to even.next.next. After the loop, link the odd tail to the saved
     * even head.
     * Time: O(n)
     * Space: O(1)
     * Drawback: Requires saving the even head before the loop begins and
     * careful null checks for both pointers — slightly more
     * error-prone to implement than the dummy-head approach.
     */

    /**
     * Reorders a singly linked list so that all odd-indexed nodes appear before
     * all even-indexed nodes, preserving relative order within each group.
     *
     * @param head the head node of the original linked list
     * @return the head node of the reordered linked list
     */
    public ListNode oddEvenList(ListNode head) {
        // Base case: 0 or 1 node — already trivially ordered
        if (head == null || head.next == null)
            return head;

        // ── Step 1: Set up two dummy-headed lists ────────────────────────────
        // Dummy heads simplify append logic by eliminating null checks at the start
        ListNode oddHead = new ListNode(-1);
        ListNode oddTail = oddHead; // tail tracks last appended odd node

        ListNode evenHead = new ListNode(-1);
        ListNode evenTail = evenHead; // tail tracks last appended even node

        // ── Step 2: Traverse and route each node by position ─────────────────
        ListNode mover = head;
        boolean isOdd = true; // node 1 is odd, toggles every step

        while (mover != null) {
            ListNode nextNode = mover.next; // save next before detaching

            if (isOdd) {
                oddTail.next = mover; // append to odd list
                mover.next = null; // detach to avoid stale links
                oddTail = oddTail.next; // advance odd tail
            } else {
                evenTail.next = mover; // append to even list
                mover.next = null; // detach to avoid stale links
                evenTail = evenTail.next; // advance even tail
            }

            isOdd = !isOdd; // flip position parity
            mover = nextNode; // advance to the saved next node
        }

        // ── Step 3: Join odd list tail to the real head of even list ─────────
        oddTail.next = evenHead.next; // skip the even dummy head

        return oddHead.next; // skip the odd dummy head
    }
}
