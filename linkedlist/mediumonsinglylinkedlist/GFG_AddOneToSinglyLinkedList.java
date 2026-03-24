package linkedlist.mediumonsinglylinkedlist;

import linkedlist.singlylinkedlist.ListNode;

public class GFG_AddOneToSinglyLinkedList {

    // Created at: 25 - March - 2026
    // Last revised at: 25 - March - 2026
    // GFG link:
    // https://www.geeksforgeeks.org/problems/add-1-to-a-number-represented-as-linked-list/1
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    /*
     * Problem Description:
     *
     * Given a linked list that represents a non-negative integer where each ListNode
     * stores a single digit, add 1 to the number and return the updated linked
     * list.
     * The head of the list holds the most significant digit.
     *
     * Example:
     * Input: 4 → 5 → 9
     * Output: 4 → 6 → 0
     *
     * Input: 9 → 9 → 9
     * Output: 1 → 0 → 0 → 0
     *
     * Constraints:
     * 1 <= N <= 10^5 (number of ListNodes)
     * 0 <= ListNode.data <= 9
     */

    /*
     * Approaches:
     *
     * 1. Recursive (addCarry helper)
     * Idea: Recurse to the last ListNode, add carry from right to left while
     * unwinding the call stack. Prepend a new ListNode if final carry != 0.
     * Time: O(n)
     * Space: O(n) — call stack depth equals list length
     * Drawback: Causes StackOverflowError for large inputs (N up to 10^5)
     * because Java's default thread stack cannot handle that depth.
     *
     * ★ 2. Iterative — Rightmost Non-Nine ListNode
     * Idea: A digit changes only if it is 9 (becomes 0 with carry) or is the
     * first non-9 from the right (gets incremented by 1). Find the
     * rightmost non-9 ListNode in one pass, increment it, zero out everything
     * after it. If no such ListNode exists, all digits are 9 — prepend 1 and
     * zero out all original ListNodes.
     * Time: O(n)
     * Space: O(1) — no extra data structures or call stack used
     * Key Insight: Only two regions matter — the trailing 9s (flip to 0) and
     * the single ListNode just before them (increment by 1). Everything
     * to the left of that ListNode is untouched.
     */

    /*
     * ============================================================
     * APPROACH 1 — RECURSIVE (TLEs / StackOverflow on large N)
     * ============================================================
     */

    /**
     * Recursive helper that propagates carry from the tail back to the head.
     * Processes ListNodes right-to-left by unwinding the call stack.
     *
     * @param head  the current ListNode being processed during unwinding
     * @param carry the carry passed into the list initially (always 1 from addOne)
     * @return the carry to be passed to the previous (left) ListNode
     */
    private int addCarry(ListNode head, int carry) {

        // base case: past the tail, return the initial carry to the last ListNode
        if (head == null)
            return carry;

        // recurse first so we process right-to-left on the way back
        int newCarry = addCarry(head.next, carry);

        // add incoming carry to this ListNode's digit
        int val = newCarry + head.data;

        // update digit in place
        head.data = val % 10;

        // pass carry leftward
        return val / 10;
    }

    /**
     * Adds one to the number represented by the given linked list (recursive).
     *
     * @param head the head ListNode of the linked list representing the number
     * @return the head of the updated linked list after adding one
     */
    public ListNode addOneRecursive(ListNode head) {

        // edge case: empty list
        if (head == null)
            return null;

        // kick off recursion with carry = 1
        int carry = addCarry(head, 1);

        // if carry remains after processing all ListNodes, prepend a new ListNode
        if (carry != 0) {
            ListNode newListNode = new ListNode(carry);
            newListNode.next = head;
            head = newListNode;
        }

        return head;
    }

    /*
     * ============================================================
     * APPROACH 2 ★ — ITERATIVE (Optimal, O(1) space)
     * ============================================================
     */

    /**
     * Adds one to the number represented by the given linked list (iterative).
     * Finds the rightmost non-9 ListNode, increments it, and zeros out everything
     * after.
     *
     * @param head the head ListNode of the linked list representing the number
     * @return the head of the updated linked list after adding one
     */
    public ListNode addOne(ListNode head) {

        // edge case: empty list
        if (head == null)
            return null;

        // step 1: scan the list to find the rightmost ListNode whose value is not 9
        ListNode lastNonNine = null;
        ListNode curr = head;

        while (curr != null) {
            if (curr.data != 9) {
                lastNonNine = curr; // keep updating; last update = rightmost non-9
            }
            curr = curr.next;
        }

        // step 2: all ListNodes are 9 (e.g. 9 → 9 → 9 becomes 1 → 0 → 0 → 0)
        if (lastNonNine == null) {
            ListNode newListNode = new ListNode(1); // new leading 1
            newListNode.next = head;
            head = newListNode;

            // zero out all original ListNodes
            curr = newListNode.next;
            while (curr != null) {
                curr.data = 0;
                curr = curr.next;
            }
            return head;
        }

        // step 3: increment the rightmost non-9 ListNode (the carry stops here)
        lastNonNine.data += 1;

        // step 4: every ListNode after lastNonNine was a 9 → becomes 0
        curr = lastNonNine.next;
        while (curr != null) {
            curr.data = 0;
            curr = curr.next;
        }

        return head;
    }
}