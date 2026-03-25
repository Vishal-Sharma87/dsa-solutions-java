package linkedlist.mediumondoublylinkedlist;

import linkedlist.doublylinkedlist.ListNode;

public class GFG_RemoveDuplicates {
// Created at: 25 - March - 2026
// Last revised at: 25 - March - 2026
// GFG Link: https://www.geeksforgeeks.org/problems/remove-duplicates-from-a-sorted-doubly-linked-list/1
// Time Complexity: O(n)
// Space Complexity: O(1)

/*
 * Problem Description:
 *   Given a sorted doubly linked list containing duplicate values,
 *   remove all duplicate ListNodes such that each value appears only once.
 *   Return the head of the modified list.
 *
 * Example:
 *   Input : 1 <-> 1 <-> 1 <-> 2 <-> 3 <-> 3 <-> 4
 *   Output: 1 <-> 2 <-> 3 <-> 4
 *
 * Constraints:
 *   1 <= number of ListNodes <= 10^5
 *   1 <= ListNode.data <= 10^4
 *   List is sorted in non-decreasing order.
 *
 * ─────────────────────────────────────────────────────────────
 * Approaches:
 *
 * 1. HashSet
 *    Idea  : Traverse the list, track seen values in a HashSet.
 *            Remove any ListNode whose value was already seen.
 *    Time  : O(n)
 *    Space : O(n)
 *    Drawback: Uses O(n) extra space; ignores the sorted property entirely.
 *
 * 2. ★ In-place Two-Pointer (rear + front)
 *    Idea  : Since the list is sorted, all duplicates of a value are
 *            contiguous. Use 'rear' as the last confirmed unique ListNode
 *            and 'front' to scan ahead. Whenever front.data == rear.data,
 *            skip front by relinking rear.next. Otherwise, repair
 *            front.prev and advance rear to front.
 *    Time  : O(n)
 *    Space : O(1)
 *    Key Insight: Sorting guarantees duplicates are always adjacent,
 *                 so a single linear scan with two pointers is sufficient —
 *                 no lookups or auxiliary structures needed.
 * ─────────────────────────────────────────────────────────────
 */

/**
 * Removes all duplicate ListNodes from a sorted doubly linked list in-place,
 * keeping exactly one ListNode per unique value.
 *
 * @param head the head ListNode of the sorted doubly linked list
 * @return the head of the deduplicated doubly linked list
 */
public ListNode removeDuplicates(ListNode head) {

    // Edge case: empty list or single ListNode — already duplicate-free
    ListNode front = head;
    if (front == null || front.next == null) return front;

    // rear: last confirmed unique ListNode | front: current ListNode being examined
    ListNode rear = front;
    front = front.next;

    while (front != null) {

        if (front.data == rear.data) {
            // Duplicate found — skip front by linking rear past it
            rear.next = front.next;
            front = front.next;             // advance without moving rear
        } else {
            // New unique value — repair its prev pointer and advance rear
            front.prev = rear;
            rear = front;
            front = front.next;
        }
    }

    return head;
}
}
