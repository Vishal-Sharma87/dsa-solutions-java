package linkedlist.mediumonsinglylinkedlist;

import linkedlist.singlylinkedlist.ListNode;

// Created at: 29-June-2026
// Last revised at: 29-June-2026
// Link: https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/

/*
Problem Description:
--------------------
Statement:
Given the head of a linked list where each node contains an integer value,
insert a new node between every pair of adjacent nodes such that the new node's
value is the GCD of the two adjacent nodes' values.

Return the head of the modified linked list.

Example:
Input: head = [18, 6, 10, 3]
Output: [18, 6, 6, 2, 10, 1, 3]
Explanation:
- GCD(18, 6) = 6 → inserted between 18 and 6
- GCD(6, 10) = 2 → inserted between 6 and 10
- GCD(10, 3) = 1 → inserted between 10 and 3

Constraints:
- The number of nodes in the list is in the range [1, 5000]
- 1 <= Node.val <= 1000
*/

/*
Approach 1: Two-Pointer Traversal + Euclidean GCD ★
Idea:
Traverse the list with two pointers (first, second) representing adjacent nodes.
For each pair, compute GCD using the iterative Euclidean algorithm, create a new node,
and wire it between first and second. Advance both pointers to the next original pair.

Key insight: After inserting the GCD node, `first` jumps directly to `second` (the next
original node), not to the newly inserted node — this avoids reprocessing inserted nodes.

Time Complexity: O(n * log(min(a, b))) — n pairs, each GCD takes O(log(min)) iterations
Space Complexity: O(1) — no auxiliary space beyond the new nodes inserted

Drawbacks:
None for this problem. Recursive GCD is cleaner to read but adds call stack overhead
for large values; iterative is preferred here.
*/

/*
Method to Solve:
----------------
1. Handle edge case: if list has 0 or 1 node, return head as-is.
2. Initialize first = head, second = head.next.
3. While second != null:
   a. Compute GCD of first.val and second.val using iterative Euclidean.
   b. Create a new node with the GCD value.
   c. Wire: first.next = newNode, newNode.next = second.
   d. Advance: first = second, second = second.next.
4. Return head.
*/

// Time Complexity: O(n * log(min(a, b)))
// Space Complexity: O(1)

public class LC2807InsertGreatestCommonDivisorsInLinkedList {

    /**
     * Computes GCD of two integers using iterative Euclidean algorithm.
     *
     * @param val1 first integer
     * @param val2 second integer
     * @return greatest common divisor of val1 and val2
     */
    private int calculateGcd(int val1, int val2) {
        // ensure val1 >= val2 before starting
        if (val1 < val2) {
            int temp = val1;
            val1 = val2;
            val2 = temp;
        }

        int temp;
        while ((temp = val1 % val2) != 0) {
            val1 = val2;
            val2 = temp;
        }

        return val2;
    }

    /**
     * Inserts a node with GCD value between every pair of adjacent nodes.
     *
     * @param head head of the original linked list
     * @return head of the modified linked list with GCD nodes inserted
     */
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode first = head;
        ListNode second = head.next;

        while (second != null) {
            int gcd = calculateGcd(first.data, second.data);

            ListNode newNode = new ListNode(gcd);

            // wire new node between first and second
            first.next = newNode;
            newNode.next = second;

            // advance to next original pair — skip the inserted node
            first = second;
            second = second.next;
        }

        return head;
    }
}