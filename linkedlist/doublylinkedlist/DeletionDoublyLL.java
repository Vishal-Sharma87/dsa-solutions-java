package linkedlist.doublylinkedlist;

// Created at: 19 - March - 2026
// Last revised at: 19 - March - 2026
// LeetCode link: https://leetcode.com/problems/design-linked-list/
// Time Complexity: O(1) - constant time, always operates at head
// Space Complexity: O(1) - no extra structures, modifies pointers only

/*
 * PROBLEM DESCRIPTION
 * ===================
 * Delete the head node from a doubly linked list.
 * Return the new head after deletion.
 * 
 * Example 1 (Empty list):
 * Delete from empty list
 * Input:  head = null
 * Output: head = null
 * 
 * Example 2 (Single node):
 * Delete from list [5]
 * Input:  head = 5
 * Output: head = null (list becomes empty)
 * 
 * Example 3 (Two nodes):
 * Delete from list [2 ↔ 3]
 * Input:  head = 2
 * Output: head = 3 (node 3's prev becomes null)
 * 
 * Example 4 (Multi-node):
 * Delete from list [1 ↔ 2 ↔ 3]
 * Input:  head = 1
 * Output: head = 2 (node 2's prev becomes null)
 * 
 * Constraints:
 * - If list is empty, return null
 * - After deletion, new head's prev must be null
 * - Must maintain doubly linked list properties
 * - No memory leaks (old head is garbage collected)
 */

/*
 * APPROACHES
 * ==========
 * 
 * Approach 1: Delete Head - Corrected Version ★ OPTIMAL
 * Idea: Check if head.next exists before updating. If it does, remove the head and
 *       update the new head's prev pointer to null. If head.next is null (single node),
 *       simply return null without accessing prev of a null pointer.
 * Time: O(1) - constant time, no traversal
 * Space: O(1) - only pointer manipulation
 * Key Insight: MUST check if head.next exists before accessing head.next.prev.
 *              Your original code has a bug for single-node lists (NullPointerException).
 *              Fix: Update new head's prev BEFORE changing head reference.
 * 
 * Approach 2: Iterative (Redundant)
 * Idea: Save head.next, update its prev, then return saved reference.
 *       More explicit but accomplishes same result.
 * Time: O(1)
 * Space: O(1)
 * Drawback: Uses extra variable, less concise than Approach 1.
 */

public class DeletionDoublyLL {

    /**
     * Deletes the head node from a doubly linked list and returns the new head.
     * Handles all edge cases: empty list, single-node list, and multi-node list.
     * 
     * Time Complexity: O(1) - constant time deletion at head
     * Space Complexity: O(1) - only pointer operations
     * 
     * @param head The current head of the doubly linked list (can be null)
     * @return The new head of the doubly linked list after deletion, or null if
     *         list becomes empty
     */
    public static ListNode deleteHead(ListNode head) {
        // Step 1: Check if list is empty
        if (head == null) {
            return null;
        }

        // Step 2: Move head to the next node
        ListNode newHead = head.next;

        // Step 3: If new head exists, remove its backward link (it's now the first
        // node)
        // CRITICAL: Must check if newHead is not null before accessing its prev field
        // This prevents NullPointerException when deleting from single-node list
        if (newHead != null) {
            newHead.prev = null;
        }

        // Step 4: Return the new head (or null if list is now empty)
        return newHead;
    }
}
