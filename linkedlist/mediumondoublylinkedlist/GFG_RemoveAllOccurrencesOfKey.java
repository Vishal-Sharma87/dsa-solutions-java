package linkedlist.mediumondoublylinkedlist;

import linkedlist.doublylinkedlist.ListNode;

public class GFG_RemoveAllOccurrencesOfKey {
    // Created at: 25 - March - 2026
    // Last revised at: 25 - March - 2026
    // GFG Link:
    // https://www.geeksforgeeks.org/problems/delete-all-occurrences-of-a-given-key-in-a-doubly-linked-list/1
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    /*
     * Problem Description:
     * Given the head of a doubly linked list and an integer key,
     * delete all ListNodes whose data equals key and return the updated head.
     *
     * Example:
     * Input : 2 <-> 2 <-> 10 <-> 8 <-> 4 <-> 2 <-> 5 <-> 2, key = 2
     * Output: 10 <-> 8 <-> 4 <-> 5
     *
     * Constraints:
     * 1 <= number of ListNodes <= 10^5
     * 1 <= ListNode.data, key <= 10^5
     *
     * ─────────────────────────────────────────────────────────────
     * Approaches:
     *
     * 1. Extra Space (Collect + Rebuild)
     * Idea : Traverse the list, collect all non-key ListNodes into a list,
     * then rebuild the doubly linked list from scratch.
     * Time : O(n)
     * Space : O(n)
     * Drawback: Uses O(n) extra space unnecessarily.
     *
     * 2. ★ In-place Two-Pointer (front + rear)
     * Idea : Use 'front' to scan ahead and 'rear' to track the last
     * confirmed valid ListNode. Skip key ListNodes by relinking rear.next,
     * and repair prev pointers on the fly for kept ListNodes.
     * Time : O(n)
     * Space : O(1)
     * Key Insight: By skipping key ListNodes from the very head first,
     * we guarantee head itself is always a valid ListNode before
     * the main loop begins, simplifying all subsequent relinking.
     * ─────────────────────────────────────────────────────────────
     */

    /**
     * Deletes all occurrences of {@code key} from a doubly linked list in-place.
     *
     * @param head the head ListNode of the doubly linked list
     * @param key  the value whose every occurrence must be removed
     * @return the head of the modified doubly linked list,
     *         or {@code null} if all ListNodes were removed
     */
    public ListNode deleteAllOccurOfX(ListNode head, int key) {

        // Base case: empty list — nothing to delete
        if (head == null)
            return null;

        ListNode front = head;

        // Advance front past any leading ListNodes that equal key
        while (front != null && front.data == key)
            front = front.next;

        // All ListNodes were key, or only one valid ListNode remains — return directly
        if (front == null || front.next == null)
            return front;

        // Re-anchor head to the first valid (non-key) ListNode
        head = front;

        // rear: last confirmed valid ListNode | front: current ListNode being examined
        ListNode rear = front;
        front = front.next;

        while (front != null) {

            if (front.data == key) {
                // Skip this ListNode: detach it by pointing rear past it
                rear.next = front.next;
                front = front.next; // advance without moving rear
            } else {
                // Keep this ListNode: repair its prev pointer to rear
                front.prev = rear;
                rear = front; // rear advances to this valid ListNode
                front = front.next;
            }
        }

        return head;
    }
}
