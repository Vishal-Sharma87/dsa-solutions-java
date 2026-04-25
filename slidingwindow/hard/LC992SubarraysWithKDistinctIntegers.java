package slidingwindow.hard;

import java.util.HashMap;

// Created at: 26 - April - 2025
// Last revised at: 26 - April - 2025
// Problem: https://leetcode.com/problems/subarrays-with-k-different-integers/
// Time Complexity: O(n)
// Space Complexity: O(n)

/*
 * Problem Description:
 * Given an integer array nums and an integer k, return the number of subarrays
 * that contain exactly k distinct integers.
 *
 * Example:
 *   Input:  nums = [1,2,1,2,3], k = 2
 *   Output: 7
 *   Explanation: Subarrays with exactly 2 distinct: [1,2],[2,1],[1,2],[2,3],[1,2,1],[2,1,2],[1,2,1,2]
 *
 * Constraints:
 *   - 1 <= nums.length <= 2 * 10^4
 *   - 1 <= nums[i] <= nums.length
 *   - 1 <= k <= nums.length
 *
 * Approaches:
 *
 * 1. Brute Force — check all subarrays
 *    Idea:    For every pair (i, j), count distinct elements and match against k
 *    Time:    O(n^2)
 *    Space:   O(n)
 *    Drawback: TLE for large inputs
 *
 * ★ 2. AtMost K complement trick + Sliding Window
 *    Idea:    Exactly k distinct = atMost(k) - atMost(k-1)
 *             For atMost(k): expand r freely, shrink l when distinct > k.
 *             Every valid r contributes (r - l + 1) subarrays ending at r.
 *    Time:    O(n) — two linear passes
 *    Space:   O(n) — HashMap for frequencies
 *    Key Insight: "Exactly k" is hard to count directly in a sliding window
 *                 because the window size isn't fixed; the complement trick
 *                 converts it into two "at most" problems which are easy.
 */

class LC992SubarraysWithKDistinctIntegers {

    /**
     * Counts subarrays with at most k distinct integers using a sliding window.
     *
     * @param nums the input array
     * @param k    max distinct integers allowed in window
     * @return count of subarrays with at most k distinct integers
     */
    private int getSubarrayCountWithAtMostK(int[] nums, int k) {

        int len = nums.length;
        if (len == 0 || k > len)
            return 0;

        int l = 0, r = 0;
        int numCnt = 0;
        int subarrayCnt = 0;
        HashMap<Integer, Integer> frequency = new HashMap<>();

        while (r < len) {
            int fr = frequency.getOrDefault(nums[r], 0);
            if (fr == 0)
                numCnt++;
            frequency.put(nums[r], fr + 1);

            // shrink until window has at most k distinct
            while (l <= r && numCnt > k) {
                int fl = frequency.get(nums[l]);
                if (fl == 1) {
                    numCnt--;
                    frequency.remove(nums[l]);
                } else {
                    frequency.put(nums[l], fl - 1);
                }
                l++;
            }

            // all subarrays ending at r with left boundary in [l, r] are valid
            subarrayCnt += r - l + 1;
            r++;
        }

        return subarrayCnt;
    }

    /**
     * Returns the number of subarrays with exactly k distinct integers.
     * Uses the identity: exactly(k) = atMost(k) - atMost(k-1).
     *
     * @param nums the input array
     * @param k    exact number of distinct integers required
     * @return count of subarrays with exactly k distinct integers
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        int len = nums.length;
        if (len == 0 || k > len)
            return 0;

        return getSubarrayCountWithAtMostK(nums, k) - getSubarrayCountWithAtMostK(nums, k - 1);
    }
}