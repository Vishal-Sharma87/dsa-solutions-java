package linkedlist.hard;

import linkedlist.singlylinkedlist.ListNode;

public class LC25ReverseLinkedListInKGroups {
    // Created at: 26 - March - 2026
    // Last revised at: 26 - March - 2026
    // LeetCode link: https://leetcode.com/problems/reverse-nodes-in-k-group/
    // Time Complexity: O(n)
    // Space Complexity: O(n/k) — recursion stack depth

    /*
     * Problem Description:
     * Given the head of a linked list and an integer k, reverse the nodes of the
     * list k at a time and return the modified head. If the number of remaining
     * nodes is less than k, leave them as-is (do not reverse the tail group).
     *
     * Example:
     * Input : 1 -> 2 -> 3 -> 4 -> 5, k = 2
     * Output: 2 -> 1 -> 4 -> 3 -> 5
     *
     * Input : 1 -> 2 -> 3 -> 4 -> 5, k = 3
     * Output: 3 -> 2 -> 1 -> 4 -> 5
     *
     * Constraints:
     * 1 <= number of nodes <= 5000
     * 0 <= node.val <= 1000
     * 1 <= k <= number of nodes
     *
     * ─────────────────────────────────────────────────────────────
     * Approaches:
     *
     * 1. Iterative with Stack
     * Idea : Use a stack to collect k nodes at a time, pop them to reverse,
     * then relink. Repeat for subsequent groups.
     * Time : O(n)
     * Space : O(k) — stack holds at most k nodes at once
     * Drawback: Extra O(k) space for the stack; more bookkeeping for relinking.
     *
     * 2. ★ Recursive — Cut, Reverse, Recurse
     * Idea : Walk k-1 steps to find the k-th node (lastNode). If it's null,
     * fewer than k nodes remain — return head unchanged.
     * Otherwise, detach this group by setting lastNode.next = null,
     * reverse the isolated group, then recursively process the rest
     * and attach the result to the original head (now the group's tail).
     * Time : O(n) — every node is visited exactly once across all frames
     * Space : O(n/k) — one stack frame per group of k nodes
     * Key Insight: After reversing a group, the original 'head' becomes the
     * tail of that group. Attaching the recursive result to
     * head.next naturally chains all reversed groups together.
     * ─────────────────────────────────────────────────────────────
     */

    /**
     * Reverses nodes of a singly linked list k at a time.
     * Tail groups with fewer than k nodes are left in their original order.
     *
     * @param head the head node of the linked list
     * @param k    the group size for reversal
     * @return the head of the modified linked list
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        // Base case: empty list — nothing to reverse
        if (head == null)
            return null;

        ListNode lastNode = head;
        int cnt = 0;

        // Walk k-1 steps forward to land on the k-th node of this group
        while (lastNode != null && cnt < k - 1) {
            lastNode = lastNode.next;
            cnt++;
        }

        // Fewer than k nodes remain — leave this tail group unreversed
        if (lastNode == null)
            return head;

        // Detach this group from the rest of the list
        ListNode nextHead = lastNode.next; // save the start of the next group
        lastNode.next = null; // isolate current group

        // Reverse the isolated k-node group; reversedHead is the new front
        ListNode reversedHead = reverse(head);

        // 'head' is now the tail of the reversed group — link it to the
        // recursively reversed remainder of the list
        head.next = reverseKGroup(nextHead, k);

        return reversedHead;
    }

    /**
     * 
     * @param head head of linkedlist
     * @return retrun head of linkedlist after reversal
     */
    public ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode left = null;
        ListNode curr = head;
        ListNode right;
        while (curr != null) {
            right = curr.next;
            curr.next = left;
            left = curr;
            curr = right;
        }
        return left;
    }

}
