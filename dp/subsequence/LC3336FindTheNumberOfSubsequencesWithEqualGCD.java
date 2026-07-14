package dp.subsequence;

// Created at: 15-July-2026
// Last revised at: 15-July-2026
// Link: https://leetcode.com/problems/find-the-number-of-subsequences-with-equal-gcd/

/*
Problem Description:
--------------------
Statement:
Given an integer array nums, count the number of ordered pairs of non-empty
subsequences such that both subsequences have the same GCD.
Each element can belong to the first subsequence, the second subsequence,
or neither.

Return the answer modulo 1e9 + 7.

Example:
Input: nums = [1,2]
Output: ...

Constraints:
- 1 <= nums.length <= ...
- 1 <= nums[i] <= ...
*/

/*
Approach 1: Recursive Backtracking

Idea:
Try all possible assignments for every element:
1. Put it in the first subsequence.
2. Put it in the second subsequence.
3. Skip it.

Maintain the current GCD of both subsequences.

Time Complexity:
O(3^N)

Space Complexity:
O(N)

Drawbacks:
Exponential and infeasible for larger inputs.
*/

/*
Approach 2: Memoized DP on (Index, GCD1, GCD2)

Idea:
Store the result for every state consisting of:
- current index
- GCD of first subsequence
- GCD of second subsequence

At every index:
- Update GCD1 by taking the current number.
- Update GCD2 by taking the current number.
- Skip the current number.

The answer is the sum of all three choices.

Time Complexity:
O(N × M²)

Space Complexity:
O(N × M²)

where M is the maximum value in nums.

Key Insight:
The number of distinct GCD states is limited by the maximum element,
making memoization practical.
*/

/*
Method to Solve:
----------------
1. Find the maximum element.
2. Allocate a 3D memoization table.
3. Recursively process every element.
4. Maintain the GCD of both subsequences.
5. Memoize every state.
6. Count states where both non-empty subsequences end with the same GCD.
*/

// Time Complexity: O(N × M²)
// Space Complexity: O(N × M²)

public class LC3336FindTheNumberOfSubsequencesWithEqualGCD {

    private static final int MOD = 1_000_000_007;

    /**
     * Computes the greatest common divisor.
     *
     * @param a first value
     * @param b second value
     * @return gcd of a and b
     */
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * Counts valid subsequence pairs from the first i elements.
     *
     * @param i    current index
     * @param gcd1 current GCD of first subsequence
     * @param gcd2 current GCD of second subsequence
     * @param nums input array
     * @param dp   memoization table
     * @return number of valid assignments
     */
    private int recursion(int i, int gcd1, int gcd2, int[] nums, Integer[][][] dp) {

        if (i == 0) {
            if (gcd1 == 0 || gcd2 == 0)
                return 0;

            return gcd1 == gcd2 ? 1 : 0;
        }

        if (dp[i][gcd1][gcd2] != null)
            return dp[i][gcd1][gcd2];

        // take in first subsequence
        int takeFirst = recursion(
                i - 1,
                gcd(nums[i - 1], gcd1),
                gcd2,
                nums,
                dp);

        // take in second subsequence
        int takeSecond = recursion(
                i - 1,
                gcd1,
                gcd(nums[i - 1], gcd2),
                nums,
                dp);

        // ignore current element
        int skip = recursion(
                i - 1,
                gcd1,
                gcd2,
                nums,
                dp);

        return dp[i][gcd1][gcd2] = ((takeFirst + takeSecond) % MOD + skip) % MOD;
    }

    /**
     * Counts ordered subsequence pairs having equal GCD.
     *
     * @param nums input array
     * @return number of valid pairs
     */
    public int subsequencePairCount(int[] nums) {

        int n = nums.length;

        int maxValue = nums[0];

        for (int value : nums)
            maxValue = Math.max(maxValue, value);

        Integer[][][] dp = new Integer[n + 1][maxValue + 1][maxValue + 1];

        return recursion(n, 0, 0, nums, dp);
    }
}