package slidingwindow.medium;

// Created at: 25-April-2026
// Last revised at: 25-April-2026
// Link: https://leetcode.com/problems/binary-subarrays-with-sum/
// Time Complexity: O(N) — two linear passes over the array
// Space Complexity: O(1)

/*
 * Problem Description:
 * Given a binary array nums and an integer goal, return the number of
 * non-empty subarrays whose sum equals goal.
 *
 * Example:
 *   Input:  nums = [1,0,1,0,1], goal = 2
 *   Output: 4   // [1,0,1], [1,0,1,0], [0,1,0,1], [1,0,1]
 *
 *   Input:  nums = [0,0,0,0,0], goal = 0
 *   Output: 15
 *
 * Constraints:
 *   - 1 <= nums.length <= 3 * 10^4
 *   - nums[i] is either 0 or 1
 *   - 0 <= goal <= nums.length
 */

/*
 * Approaches:
 *
 * 1. Brute Force
 *    Idea    : Try every subarray, compute its sum, count those equal to goal
 *    Time    : O(N^2)
 *    Space   : O(1)
 *    Drawback: Too slow for N = 3 * 10^4
 *
 * 2. Prefix Sum + HashMap
 *    Idea    : Track running prefix sums; for each index r, count how many
 *              previous indices had prefixSum[r] - goal already seen
 *    Time    : O(N)
 *    Space   : O(N) — HashMap stores up to N prefix sums
 *    Drawback: Extra space; slightly more setup than sliding window
 *
 * 3. ★ Sliding Window — exactlyK = atMost(K) - atMost(K-1)
 *    Idea    : A direct sliding window for "sum == goal" breaks down because
 *              zeros allow multiple valid left boundaries for the same right end.
 *              Instead, reduce: count(sum == K) = count(sum <= K) - count(sum <= K-1).
 *              atMost(goal) counts all subarrays with sum in [0, goal]; subtracting
 *              atMost(goal-1) leaves exactly those with sum == goal.
 *    Time    : O(N)
 *    Space   : O(1)
 *    Key Insight: The "exactly K" → "atMost(K) - atMost(K-1)" reduction is a
 *                 general pattern applicable to any sliding window problem where
 *                 zeros (or duplicates) cause multiple valid windows per right pointer.
 */

public class LC930BinarySubarraysWithSum {

    /*
     * Helper: count subarrays with sum <= goal.
     * Standard shrinking sliding window — whenever sum exceeds goal,
     * evict from left until the window is valid again.
     * Every valid right pointer r contributes (r - l + 1) subarrays.
     */

    /**
     * Counts subarrays with sum less than or equal to goal.
     *
     * @param nums binary integer array
     * @param goal upper bound on subarray sum (inclusive)
     * @return number of subarrays with sum in [0, goal]
     */
    private int subarrayLessThanEqualToGoal(int[] nums, int goal) {

        int len = nums.length;
        if (len == 0)
            return 0;

        int cnt = 0;
        int l = 0, r = 0;
        int sum = 0;

        while (r < len) {
            sum += nums[r];

            // shrink until window sum is within goal
            while (l <= r && sum > goal) {
                sum -= nums[l];
                l++;
            }

            // every subarray ending at r with left boundary in [l, r] is valid
            if (sum <= goal)
                cnt += r - l + 1;

            r++;
        }

        return cnt;
    }

    /*
     * Method to solve:
     * exactlyK(goal) = atMost(goal) - atMost(goal - 1)
     * Delegate both calls to the helper and return the difference.
     */

    /**
     * Returns the number of non-empty subarrays whose sum equals goal.
     *
     * @param nums binary integer array
     * @param goal target subarray sum
     * @return count of subarrays with sum exactly equal to goal
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        int len = nums.length;
        if (len == 0)
            return 0;

        return subarrayLessThanEqualToGoal(nums, goal)
                - subarrayLessThanEqualToGoal(nums, goal - 1);
    }
}