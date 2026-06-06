package dp.basics;

// Created at: 07-June-2025
// Last revised at: 07-June-2025
// Link: https://leetcode.com/problems/house-robber/

/*
Problem Description:
--------------------
Statement:
You are a robber planning to rob houses along a street.
Each house has some amount of money. Adjacent houses have security systems
connected — robbing two adjacent houses will trigger the alarm.
Given an array nums representing the amount at each house,
return the maximum amount you can rob without alerting the police.

Example:
Input: nums = [2, 7, 9, 3, 1]
Output: 12
Explanation: Rob house 0 (2) + house 2 (9) + house 4 (1) = 12

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 400
*/

/*
Approach 1: Brute Force (Pure Recursion)
Idea:
At each house i, two choices — rob it or skip it.
If robbed: gain nums[i] + best from i-2
If skipped: best from i-1
Explore all possibilities recursively.

Time Complexity: O(2^n) — two choices at every house
Space Complexity: O(n) — recursion call stack

Drawbacks:
Recomputes same subproblems repeatedly.
Exponential time makes it infeasible for large inputs.

---

Approach 2: Memoization (Top-Down DP)
Idea:
Same recursion as brute force but cache dp[i] after first computation.
Before computing, check if dp[i] is already solved.

Time Complexity: O(n) — each index solved once
Space Complexity: O(n) — dp array + call stack

Key Insight:
Eliminates redundant recomputation. Natural extension of brute force.

---

Approach 3: Tabulation (Bottom-Up DP)
Idea:
dp[i] = max amount robbed considering houses 0 to i.
dp[0] = nums[0]
dp[i] = max(nums[i] + dp[i-2], dp[i-1])
Build iteratively from base case up to n-1.

Time Complexity: O(n)
Space Complexity: O(n) — dp array

Key Insight:
No recursion overhead. Straightforward bottom-up fill.

---

Approach 4: Space Optimized
Idea:
dp[i] only depends on dp[i-1] and dp[i-2].
Track just two variables: take (max sum at i-2) and skip (max sum at i-1).
Roll them forward each iteration.

Time Complexity: O(n)
Space Complexity: O(1)

Key Insight:
Same recurrence as tabulation, zero extra space.
*/

/*
Method to Solve (Space Optimized):
------------------------------------
1. Handle single house edge case — return nums[0]
2. Initialize:
   take = nums[0]           (max rob considering only house 0)
   skip = max(nums[1], take) (max rob considering houses 0 and 1)
3. For each house i from 2 to n-1:
   a. maxi = max(nums[i] + take, skip)
   b. take = skip
   c. skip = maxi
4. Return skip
*/

public class LC198HouseRobber {

    /**
     * Brute force — explores all rob/skip combinations recursively.
     * Included as reference; not submitted.
     *
     * @param nums array of house amounts
     * @param i    current house index
     * @return max amount robable from houses 0 to i
     */
    @SuppressWarnings("unused")
    private int bruteForce(int[] nums, int i) {
        if (i < 0)
            return 0;
        if (i == 0)
            return nums[0];

        int maxi = nums[i] + bruteForce(nums, i - 2);

        return Math.max(maxi, bruteForce(nums, i - 1));
    }

    /**
     * Memoized recursion — caches results of already solved subproblems.
     *
     * @param nums array of house amounts
     * @param i    current house index
     * @param dp   memoization array of size n
     * @return max amount robable from houses 0 to i
     */
    private int memoized(int[] nums, int i, Integer[] dp) {
        if (i < 0)
            return 0;
        if (i == 0)
            return nums[0];

        if (dp[i] != null)
            return dp[i];

        int maxi = nums[i] + memoized(nums, i - 2, dp);

        return dp[i] = Math.max(maxi, memoized(nums, i - 1, dp));
    }

    /**
     * Entry point for memoized approach.
     *
     * @param nums array of house amounts
     * @return max amount robable without hitting adjacent houses
     */
    public int robMemo(int[] nums) {
        Integer[] dp = new Integer[nums.length];
        return memoized(nums, nums.length - 1, dp);
    }

    /**
     * Bottom-up tabulation — builds max rob sum from base case up to n-1.
     *
     * @param nums array of house amounts
     * @return max amount robable without hitting adjacent houses
     */
    public int robTabulation(int[] nums) {
        int len = nums.length;
        Integer[] dp = new Integer[len];

        dp[0] = nums[0];

        for (int i = 1; i < len; i++) {
            int maxi = nums[i];
            maxi += (i > 1) ? dp[i - 2] : 0;
            dp[i] = Math.max(maxi, dp[i - 1]);
        }

        return dp[len - 1];
    }

    /**
     * Space optimized — tracks only previous two max sums.
     * Main submitted solution.
     *
     * @param nums array of house amounts
     * @return max amount robable without hitting adjacent houses
     */
    public int rob(int[] nums) {
        int len = nums.length;

        if (len == 1)
            return nums[0];

        int take = nums[0]; // max sum considering house 0
        int skip = Math.max(nums[1], take); // max sum considering houses 0 and 1

        for (int i = 2; i < len; i++) {
            int maxi = Math.max(nums[i] + take, skip);

            // shift window forward
            take = skip;
            skip = maxi;
        }

        return skip;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)