package linkedlist.mediumonsinglylinkedlist;

import linkedlist.singlylinkedlist.ListNode;

public class LC234PalindromicLinkedLIst {
    // Created at: 22 - March - 2026
    // Last revised at: 22 - March - 2026
    // LeetCode link: https://leetcode.com/problems/palindrome-linked-list/
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    /*
     * Problem Description:
     * Given the head of a singly linked list, return true if it is a palindrome,
     * or false otherwise. A palindrome reads the same forwards and backwards.
     *
     * Example:
     * Input: head = [1, 2, 2, 1] → Output: true
     * Input: head = [1, 2, 3, 2, 1]→ Output: true
     * Input: head = [1, 2] → Output: false
     *
     * Constraints:
     * - The number of nodes in the list is in the range [1, 10^5].
     * - 0 <= Node.data <= 9
     *
     * ─────────────────────────────────────────────────────────────────
     *
     * Approach 1 — Copy to Array / Stack
     * Idea : Collect all node dataues into an array or stack, then
     * compare dataues from both ends (or pop against a fresh traversal).
     * Time : O(n)
     * Space : O(n)
     * Drawback: Extra space proportional to list length.
     *
     * ★ Approach 2 — Find Middle + Reverse Second Half + Compare
     * Idea : Step 1 — use slow/fast pointers to find the node just
     * before the second half (first middle for even length,
     * true middle for odd length).
     * Step 2 — reverse everything after that node in-place.
     * Step 3 — compare the reversed second half against the
     * original first half node-by-node.
     * The second half is always ≤ first half in length, so
     * iterating until fast (second half pointer) is null is safe.
     * Time : O(n)
     * Space : O(1)
     * Key Insight: Reversing only the second half avoids extra space while
     * keeping the first half intact for direct comparison.
     * For odd-length lists the middle node is part of neither
     * half and is naturally skipped.
     */

    /**
     * Reverses a singly linked list in-place using the iterative
     * three-pointer (left, curr, right) technique.
     *
     * @param head the head node of the list to reverse
     * @return the new head node (originally the tail), or {@code null} for an empty
     *         list
     */
    public ListNode reverse(ListNode head) {

        // Edge case: empty list — nothing to reverse
        if (head == null)
            return null;

        ListNode left = null; // trailing pointer — grows the reversed portion
        ListNode curr = head; // current node being re-linked
        ListNode right; // saves original next before the link is broken

        while (curr != null) {
            right = curr.next; // save next node before breaking the link
            curr.next = left; // re-link current node to reversed portion
            left = curr; // advance reversed portion's head
            curr = right; // move to the saved next node
        }

        // left is now the head of the fully reversed list
        return left;
    }

    /**
     * Determines whether a singly linked list is a palindrome.
     * Finds the middle via slow/fast pointers, reverses the second half
     * in-place, then compares it against the first half.
     *
     * @param head the head node of the singly linked list
     * @return {@code true} if the list is a palindrome; {@code false} otherwise
     */
    public boolean isPalindrome(ListNode head) {

        // Edge case: empty list or single node is trivially a palindrome
        if (head == null || head.next == null)
            return true;

        // ── Phase 1: find the node just before the second half ──────────
        // fast starts at head.next (offset by 1) so slow lands one step
        // earlier than the standard middle — i.e. at the last node of the first half
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
                slow = slow.next; // slow advances only when fast takes both steps
            }
        }
        // slow is now at the last node of the first half

        // ── Phase 2: reverse the second half in-place ───────────────────
        // slow.next is the head of the second half; reverse from there
        fast = reverse(slow.next);

        // reset slow to head to begin first-half traversal
        slow = head;

        // ── Phase 3: compare first half and reversed second half ────────
        while (fast != null) {
            if (fast.data != slow.data)
                return false; // mismatch — not a palindrome
            slow = slow.next;
            fast = fast.next;
        }

        // all nodes matched — list is a palindrome
        return true;
    }
}
