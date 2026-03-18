package linkedlist.doublylinkedlist;

// ============================================================
// UTILITY CLASS - Node definition for doubly linked list
// ============================================================

/**
 * Node class representing a single element in the doubly linked list.
 * Maintains references to both the next and previous nodes.
 */
public class ListNode {
    int data; // Data stored in the node
    ListNode next; // Reference to the next node (forward direction)
    ListNode prev; // Reference to the previous node (backward direction)

    /**
     * Constructor to create a new node with the given value.
     * Both next and prev are initialized to null.
     * 
     * @param data The integer value to store in this node
     */
    ListNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
