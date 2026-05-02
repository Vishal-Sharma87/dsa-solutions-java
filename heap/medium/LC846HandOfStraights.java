package heap.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC846HandOfStraights {
    // Created at: 03-May-2026
    // Last revised at: 03-May-2026
    // Link: https://leetcode.com/problems/hand-of-straights/
    // Time Complexity: O(N² / k) — heap poll is O(N log N), inner group scan is
    // O(N/k) per element
    // Space Complexity: O(N) — heap + counter and lastElement arrays

    /*
     * Problem Description:
     * Given an integer array hand and integer k, return true if the cards
     * can be rearranged into groups of k consecutive cards, false otherwise.
     *
     * Example:
     * Input: hand = [1,2,3,6,2,3,4,7,8], k = 3
     * Output: true → [1,2,3], [2,3,4], [6,7,8]
     *
     * Input: hand = [1,2,3,4,5], k = 4
     * Output: false
     *
     * Constraints:
     * - 1 <= hand.length <= 10^4
     * - 0 <= hand[i] <= 10^9
     * - 1 <= k <= hand.length
     *
     * Approaches:
     *
     * 1. Min-Heap + Group Tracking Arrays (implemented below)
     * Idea: Maintain counter[i] and lastElement[i] for each of the N/k groups.
     * Poll the global minimum; scan for a group that either is empty (-1)
     * or whose last card is exactly val-1. Mark group complete with sentinel -2.
     * Time: O(N²/k) Space: O(N)
     * Drawback: Inner group scan is O(N/k) per poll — quadratic at small k.
     * Also fragile with sentinel values (-1, -2) if card range were negative.
     *
     * ★ 2. Sorted Frequency Map (Optimal)
     * Key Insight: Process unique values in ascending order via a TreeMap.
     * For each smallest remaining card with freq > 0, it MUST start a new group —
     * decrement freq for the next k consecutive values. If any is missing → false.
     * Time: O(N log N) Space: O(N)
     */

    /**
     * Determines whether the hand can be split into groups of k consecutive cards
     * using a min-heap to process cards in ascending order and a group tracking
     * array.
     *
     * @param hand array of card values
     * @param k    required group size
     * @return true if all cards can form valid consecutive groups, false otherwise
     */
    public boolean isNStraightHand(int[] hand, int k) {
        int len = hand.length;
        if (len % k != 0)
            return false;

        int groups = len / k;

        int[] counter = new int[groups]; // cards filled so far in each group
        int[] lastElement = new int[groups]; // last card value added to each group
        Arrays.fill(lastElement, -1); // -1 = slot empty, -2 = group complete

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int val : hand)
            minHeap.offer(val);

        while (!minHeap.isEmpty()) {
            int val = minHeap.poll();
            boolean found = false;

            for (int i = 0; i < groups; i++) {
                // open a new group or extend an existing one by exactly 1
                if (lastElement[i] == -1 || lastElement[i] + 1 == val) {
                    found = true;
                    counter[i]++;
                    // mark complete with -2 so this slot is never reused
                    lastElement[i] = (counter[i] == k) ? -2 : val;
                    break;
                }
            }

            if (!found)
                return false;
        }

        return true;
    }
}
