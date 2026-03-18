package linkedlist.doublylinkedlist;

// Created at: 19 - March - 2026
// Last revised at: 19 - March - 2026
// LeetCode link: https://leetcode.com/problems/reverse-linked-list-ii/ (similar concept)
// Time Complexity: O(n) - single pass through all n nodes
// Space Complexity: O(1) - only three pointer variables used

/*
 * PROBLEM DESCRIPTION
 * ===================
 * Reverse a doubly linked list in-place. Each node has both a 'next' pointer
 * (pointing to the next node) and a 'prev' pointer (pointing to the previous node).
 * 
 * Example 1:
 * Input:  1 ↔ 2 ↔ 3 (head = node 1)
 * Output: 3 ↔ 2 ↔ 1
 * 
 * Example 2:
 * Input:  5 ↔ 4 (head = node 5)
 * Output: 4 ↔ 5
 * 
 * Constraints:
 * - 0 <= number of nodes <= 10^5
 * - Node values are integers
 * - Must be done in-place (no extra space for creating new list)
 * - Both next and prev pointers must be correctly maintained
 */

/*
 * APPROACHES
 * ==========
 * 
 * Approach 1: Single-Pass Pointer Reassignment ★ OPTIMAL
 * Idea: Build the reversed list by reassigning both next and prev pointers in a single 
 *       traversal. Use 'left' to track the built reversed portion. For each node, 
 *       swap its pointers: next → left (reverse), prev → right (forward in original). 
 *       The 'prev' pointer cleverly stores the original next node for traversal.
 * Time: O(n) - visits each node exactly once
 * Space: O(1) - only three pointer variables (left, curr, right)
 * Key Insight: curr.prev = right is elegant — it uses prev as temporary storage for the 
 *              next node, avoiding need for extra variables. The 'left' pointer naturally 
 *              becomes the new head after the loop completes.
 * 
 * Approach 2: Two-Pass Swap and Find (Alternative)
 * Idea: First pass swaps all next/prev for each node. Second pass traverses from head
 *       (following new prev pointers) to find the new head (node where prev is null).
 * Time: O(2n) = O(n) - two complete traversals
 * Space: O(1) - only two pointer variables
 * Drawback: Double traversal makes it less efficient than Approach 1 despite same 
 *           asymptotic complexity. More explicit but slower.
 */

public class ReverseDoublyLL {

    /**
     * Reverses a doubly linked list in-place using single-pass pointer
     * reassignment.
     * Simultaneously updates both 'next' and 'prev' pointers while iterating
     * through
     * the list once. The last processed node becomes the new head.
     * 
     * @param head The head node of the doubly linked list to reverse
     * @return The new head of the reversed doubly linked list, or null if input is
     *         null
     */
    public static ListNode reverse(ListNode head) {
        // Step 1: Handle edge case - empty list
        if (head == null) {
            return null;
        }

        // Step 2: Initialize pointers for traversal and building reversed list
        ListNode left = null; // Tracks the reversed portion being built
        ListNode curr = head; // Current node being processed
        ListNode right; // Saves the original next node before modification

        // Step 3: Iterate through each node in the original list
        while (curr != null) {
            // Step 3a: Save the original next node (needed for traversal after we modify
            // pointers)
            right = curr.next;

            // Step 3b: Reverse the next pointer - point to the reversed portion instead
            curr.next = left;

            // Step 3c: Reverse the prev pointer - point to the original next node
            // This is the key optimization: prev stores forward direction during reversal
            curr.prev = right;

            // Step 3d: Move the reversed portion boundary forward (include curr in reversed
            // part)
            left = curr;

            // Step 3e: Move to next node in original list (using saved reference)
            curr = right;
        }

        // Step 4: Return new head (the last node processed is now the head)
        return left;
    }
}
