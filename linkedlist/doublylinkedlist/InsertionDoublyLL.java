package linkedlist.doublylinkedlist;

// Created at: 19 - March - 2026
// Last revised at: 19 - March - 2026
// LeetCode link: https://leetcode.com/problems/design-linked-list/
// Time Complexity: O(1) - constant time, always operates at head
// Space Complexity: O(1) - only creates one new node, no extra structures

/*
 * PROBLEM DESCRIPTION
 * ===================
 * Insert a new node with given data at the head (beginning) of a doubly linked list.
 * If the list is empty, the new node becomes the head.
 * If the list has nodes, the new node becomes the head and links to the old head.
 * 
 * Example 1 (Empty list):
 * Insert 5 into empty list
 * Input:  head = null
 * Output: head = 5 (single node)
 * 
 * Example 2 (Non-empty list):
 * Insert 1 into list [2 ↔ 3]
 * Input:  head = 2, data = 1
 * Output: head = 1 ↔ 2 ↔ 3
 * Relationships: 1.next = 2, 2.prev = 1
 * 
 * Example 3 (Single node):
 * Insert 7 into list [5]
 * Input:  head = 5, data = 7
 * Output: head = 7 ↔ 5
 * 
 * Constraints:
 * - data can be any integer value
 * - Must maintain doubly linked list properties (both next and prev)
 * - Operation must be in-place
 */

/*
 * APPROACHES
 * ==========
 * 
 * Approach 1: Direct Insertion at Head ★ OPTIMAL
 * Idea: Create a new node and insert it at the head position. If list exists, 
 *       update both the new node's next and old head's prev. If list is empty,
 *       return the new node as the head. No traversal needed since we always 
 *       insert at position 0.
 * Time: O(1) - constant time, no iteration through the list
 * Space: O(1) - only one new node created, no extra data structures
 * Key Insight: Head insertion in doubly linked list is optimal because we don't 
 *              need to traverse. We have direct access to the head, so operations 
 *              are immediate. The only work is updating two pointers (newNode.next 
 *              and head.prev), which is constant regardless of list size.
 * 
 * Why not other positions?
 * - Inserting at tail requires O(n) traversal to find tail
 * - Inserting in middle requires O(n) traversal to find position
 * - Head insertion is the only O(1) insertion operation for singly or doubly LL
 */

public class InsertionDoublyLL {

    /**
     * Inserts a new node with the given data at the head (beginning) of the doubly
     * linked list. Maintains proper doubly linked list structure by updating both
     * next and prev pointers.
     * 
     * Time Complexity: O(1) - constant time insertion at head
     * Space Complexity: O(1) - only one new node created
     * 
     * @param head The current head of the doubly linked list (can be null)
     * @param data The integer value to be inserted in the new node
     * @return The new head of the doubly linked list after insertion
     */
    public static ListNode insertAtHead(ListNode head, int data) {
        // Step 1: Create a new node with the given data
        ListNode newNode = new ListNode(data);

        // Step 2: Check if the list is non-empty
        if (head != null) {
            // Step 2a: Link the new node's next to the current head
            // This establishes forward direction from new node
            newNode.next = head;

            // Step 2b: Update the old head's prev to point to the new node
            // This maintains backward link (doubly linked requirement)
            head.prev = newNode;
        }
        // If list was empty, newNode.next and newNode.prev remain null (already
        // initialized)

        // Step 3: Return the new node as the new head
        return newNode;
    }

}
