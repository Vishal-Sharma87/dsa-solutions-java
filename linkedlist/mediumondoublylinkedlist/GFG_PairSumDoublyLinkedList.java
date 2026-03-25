package linkedlist.mediumondoublylinkedlist;

import java.util.ArrayList;
import java.util.List;

import linkedlist.doublylinkedlist.ListNode;

public class GFG_PairSumDoublyLinkedList {
// Created at: 25 - March - 2026
// Last revised at: 25 - March - 2026
// GFG Link: https://www.geeksforgeeks.org/problems/find-pairs-with-given-sum-in-doubly-linked-list/1
// Time Complexity: O(n)
// Space Complexity: O(1) — excluding output list

/*
 * Problem Description:
 *   Given a sorted doubly linked list (no duplicates) and an integer target,
 *   find all pairs of ListNodes whose data sums to target.
 *   Return a list of [left, right] pairs in ascending order of the smaller element.
 *
 * Example:
 *   Input : 1 <-> 2 <-> 4 <-> 5 <-> 6 <-> 8 <-> 9, target = 7
 *   Output: [[1,6], [2,5]]
 *
 * Constraints:
 *   1 <= number of ListNodes <= 10^5
 *   1 <= ListNode.data <= 10^5
 *   1 <= target <= 2 * 10^5
 *   List is sorted in ascending order and contains no duplicates.
 *
 * ─────────────────────────────────────────────────────────────
 * Approaches:
 *
 * 1. Brute Force (Nested Traversal)
 *    Idea  : For every ListNode, traverse all ListNodes ahead of it and check
 *            if the pair sums to target.
 *    Time  : O(n²)
 *    Space : O(1)
 *    Drawback: Does not exploit the sorted nature of the list at all.
 *
 * 2. Hashing
 *    Idea  : Store all ListNode values in a HashSet. For each ListNode with value v,
 *            check if (target - v) exists in the set.
 *    Time  : O(n)
 *    Space : O(n)
 *    Drawback: Extra O(n) space; also requires dedup care (same ListNode reuse).
 *
 * 3. ★ Two-Pointer on Head and Tail
 *    Idea  : Place 'left' at head and 'right' at tail. Converge inward:
 *            if sum == target → record pair, move both;
 *            if sum > target  → move right inward (decrease sum);
 *            if sum < target  → move left inward (increase sum).
 *            Stop when left and right cross (left.data >= right.data).
 *    Time  : O(n)
 *    Space : O(1)
 *    Key Insight: The doubly linked list gives us O(1) backward traversal,
 *                 enabling the classic sorted two-pointer technique without
 *                 needing an auxiliary array or stack.
 * ─────────────────────────────────────────────────────────────
 */

/**
 * Finds all pairs in a sorted doubly linked list that sum to {@code target}.
 *
 * @param target the required pair sum
 * @param head   the head ListNode of the sorted doubly linked list
 * @return a list of [left, right] pairs (each sorted ascending) whose sum
 *         equals {@code target}; empty list if no such pairs exist
 */
public ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, ListNode head) {

    ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();

    // Edge case: need at least two ListNodes to form a pair
    if (head == null || head.next == null) return pairs;

    ListNode left = head;
    ListNode right = head;

    // Walk right to the tail of the list
    while (right.next != null) right = right.next;

    // Two-pointer convergence — valid only while left is strictly before right
    // (left.data < right.data ensures no ListNode is paired with itself or recrossed)
    while (left != null && right != null && left.data < right.data) {

        int sum = left.data + right.data;

        if (sum == target) {
            // Valid pair found — record and shrink window from both ends
            pairs.add(new ArrayList<>(List.of(left.data, right.data)));
            left = left.next;
            right = right.prev;
        } else if (sum > target) {
            // Sum too large — move right inward to decrease it
            right = right.prev;
        } else {
            // Sum too small — move left inward to increase it
            left = left.next;
        }
    }

    return pairs;
}
}
