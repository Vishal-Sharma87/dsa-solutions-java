package linkedlist.mediumonsinglylinkedlist;

import linkedlist.singlylinkedlist.ListNode;

public class LC160InterSectionInLinkedList {
    // Created at: 23 - March - 2026
    // Last revised at: 23 - March - 2026
    // LeetCode link:
    // https://leetcode.com/problems/intersection-of-two-linked-lists/
    // Time Complexity: O(m + n)
    // Space Complexity: O(1)

    /*
     * Problem Description:
     * Given the heads of two singly linked lists headA and headB, return the node
     * at which the two lists intersect. If the two linked lists have no
     * intersection,
     * return null. Intersection is defined by reference (same node object), not
     * value.
     * The lists must retain their original structure after the function returns.
     *
     * Example:
     * List A: 4 → 1 ↘
     * 8 → 4 → 5
     * List B: 5 → 6 → 1 ↗
     *
     * Input: headA = [4,1,8,4,5], headB = [5,6,1,8,4,5], intersectVal = 8
     * Output: Intersected at node with value 8
     *
     * Input: headA = [2,6,4], headB = [1,5], intersectVal = 0
     * Output: null (no intersection)
     *
     * Constraints:
     * - The number of nodes in list A is m, in list B is n
     * - 1 <= m, n <= 3 * 10^4
     * - 1 <= Node.val <= 10^5
     * - 0 or 1 intersection node
     *
     * ─────────────────────────────────────────────────────────────────
     *
     * Approach 1: Length-Difference Alignment ★
     * Idea: Walk both pointers together until the shorter list is exhausted.
     * The longer list's pointer is still mid-way — the remaining steps
     * it takes to reach null is exactly the length difference (skip).
     * Reset both to their heads, advance the head of the longer list
     * by (skip) steps, then walk both in lockstep until they meet.
     * The meeting point is the intersection node (or both reach null).
     * Time: O(m + n) — at most two passes across both lists combined
     * Space: O(1) — only a fixed number of pointers and counters
     * Key Insight: Aligning the two pointers at the same distance from the
     * tail guarantees they reach the intersection node (if any)
     * in the same number of steps.
     *
     * Approach 2: Two-Pointer List Switching
     * Idea: Walk m1 through list A, then redirect it to headB when it hits null.
     * Walk m2 through list B, then redirect it to headA when it hits null.
     * Both pointers now travel the same total distance (m + n steps)
     * and will either meet at the intersection or both land on null
     * simultaneously if there is no intersection.
     * Time: O(m + n)
     * Space: O(1)
     * Key Insight: After the switch, both pointers have "seen" the length
     * difference implicitly — they self-align without any
     * explicit counting. More elegant and compact than Approach 1.
     */

    /**
     * Returns the node at which two singly linked lists intersect,
     * or null if they do not intersect.
     *
     * @param headA the head node of the first linked list
     * @param headB the head node of the second linked list
     * @return the intersecting node by reference, or null if none exists
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Base case: either list is empty — intersection is impossible
        if (headA == null || headB == null)
            return null;

        ListNode m1 = headA;
        ListNode m2 = headB;

        int skipA = 0; // extra steps A's pointer takes after B is exhausted
        int skipB = 0; // extra steps B's pointer takes after A is exhausted

        // ── Step 1: Walk both pointers until the shorter list is exhausted ────
        // Both advance in lockstep — when one hits null, the other is exactly
        // (length difference) steps away from null in its own list.
        while (m1 != null && m2 != null) {
            m1 = m1.next;
            m2 = m2.next;

            // A was shorter — count remaining steps in B to get the difference
            if (m1 == null) {
                while (m2 != null) {
                    m2 = m2.next;
                    skipB++; // B has skipB more nodes than A
                }
                break;
            }

            // B was shorter — count remaining steps in A to get the difference
            if (m2 == null) {
                while (m1 != null) {
                    m1 = m1.next;
                    skipA++; // A has skipA more nodes than B
                }
                break;
            }
        }
        // If both exited the outer while together, skipA = skipB = 0 (equal lengths)

        // ── Step 2: Reset both pointers to their respective heads ────────────
        m1 = headA;
        m2 = headB;

        // ── Step 3: Advance the longer list's pointer by the length difference ─
        // This aligns both pointers so they are equidistant from the tail
        while (skipA > 0) {
            m1 = m1.next;
            skipA--;
        }
        while (skipB > 0) {
            m2 = m2.next;
            skipB--;
        }

        // ── Step 4: Walk both in lockstep until they meet ────────────────────
        // If they intersect, m1 == m2 at the intersection node.
        // If they don't, both reach null simultaneously and the loop exits.
        while (m1 != null && m2 != null && m1 != m2) {
            m1 = m1.next;
            m2 = m2.next;
        }

        // m1 == m2 == intersection node, or m1 == null if no intersection
        return m1 == null ? null : m1;
    }
}
