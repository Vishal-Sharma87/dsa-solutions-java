package slidingwindow.medium;

// Created at: 26 - April - 2025
// Last revised at: 26 - April - 2025
// Problem: https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
// Time Complexity: O(n)
// Space Complexity: O(1)

/*
 * Problem Description:
 * Given an array `cardPoints` of integers and an integer k, you can pick exactly
 * k cards from either the beginning or the end of the row (one at a time).
 * Return the maximum score you can obtain.
 *
 * Example:
 *   Input:  cardPoints = [1,2,3,4,5,6,1], k = 3
 *   Output: 12
 *   Explanation: Pick [6,1] from the right and [1] from the left → 1+6+5 = 12
 *
 * Constraints:
 *   - 1 <= cardPoints.length <= 10^5
 *   - 1 <= cardPoints[i] <= 10^4
 *   - 1 <= k <= cardPoints.length
 *
 * Approaches:
 *
 * 1. Brute Force — try all combinations of i cards from left, (k-i) from right
 *    Idea:    For each split 0..k, compute score and track max
 *    Time:    O(k)
 *    Space:   O(1)
 *    Drawback: Conceptually clean but misses a smarter reframe
 *
 * ★ 2. Sliding Window on complement (minimum middle window)
 *    Idea:    Picking k cards from either end leaves a contiguous subarray
 *             of size (n - k) untouched in the middle. So instead of maximizing
 *             the picked cards, find the minimum sum window of size (n - k) —
 *             answer is totalSum - minWindowSum.
 *    Time:    O(n)
 *    Space:   O(1)
 *    Key Insight: "pick from ends" ↔ "skip a middle window of fixed size"
 */

class LC1423MaxPointsFromCards {

    /**
     * Returns the maximum score obtainable by picking exactly k cards from
     * either end of the array, using the minimum middle window complement trick.
     *
     * @param cp the array of card points
     * @param k  number of cards to pick
     * @return maximum total score
     */
    public int maxScore(int[] cp, int k) {

        int len = cp.length;
        if (len == 0)
            return 0;

        // total sum of all cards
        int preSum = 0;
        for (int val : cp)
            preSum += val;

        // picking all cards, no window to skip
        if (k == len)
            return preSum;

        int windowSize = len - k;

        // sum of first window
        int windowSum = 0;
        for (int i = 0; i < windowSize; i++)
            windowSum += cp[i];

        int minWindowSum = windowSum;

        // slide the window across, track minimum
        int l = 0, r = windowSize;
        while (r < len) {
            windowSum -= cp[l++];
            windowSum += cp[r++];

            if (windowSum < minWindowSum)
                minWindowSum = windowSum;
        }

        // cards we pick = everything except the minimum middle window
        return preSum - minWindowSum;
    }
}