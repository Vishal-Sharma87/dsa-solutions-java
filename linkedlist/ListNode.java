package linkedlist;

// Created at: 18 - March - 2026
// Last revised at: 18 - March - 2026
// Instantiation: O(1) constant time
// Space per node: O(1) constant space

/**
 * Node class representing a single element in a singly linked list.
 * 
 * Description:
 * ListNode is the fundamental building block of a singly linked list data
 * structure.
 * Each node contains an integer data value and a reference to the next node in
 * the list.
 * This allows nodes to be chained together to form a linear sequence.
 * 
 * Usage Example:
 * // Create isolated nodes
 * ListNode node1 = new ListNode(1);
 * ListNode node2 = new ListNode(2);
 * ListNode node3 = new ListNode(3);
 * 
 * // Chain them together
 * node1.next = node2;
 * node2.next = node3;
 * // Now: 1 -> 2 -> 3 -> null
 * 
 * Or create with next reference:
 * ListNode node2 = new ListNode(2, node3);
 * ListNode node1 = new ListNode(1, node2);
 * 
 * Constraints:
 * - Data can be any integer value (positive, negative, zero)
 * - Next reference can be null (indicates end of list)
 * - Typical linked list size: up to 10^6 nodes
 */
public class ListNode {

    /**
     * The integer data value stored in this node.
     */
    public int data;

    /**
     * Reference to the next node in the linked list.
     * null indicates this is the last node in the list.
     */
    public ListNode next;

    /**
     * Constructor to create a node with data and a reference to the next node.
     * 
     * @param data the integer value to store in this node
     * @param next the reference to the next ListNode in the sequence
     */
    public ListNode(int data, ListNode next) {
        // Initialize this node's data with the provided value
        this.data = data;
        // Set the next pointer to the provided node reference
        this.next = next;
    }

    /**
     * Constructor to create a node with only data (next reference defaults to
     * null).
     * Use this when creating a new node that doesn't yet have a successor.
     * 
     * @param data the integer value to store in this node
     */
    public ListNode(int data) {
        // Initialize this node's data with the provided value
        this.data = data;
        // Initialize next pointer to null (this node is isolated)
        this.next = null;
    }
}