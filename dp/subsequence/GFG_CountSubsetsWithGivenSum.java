package dp.subsequence;

// Created at: 10-June-2026
// Last revised at: 10-June-2026
// Link: https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1

/*
Problem Description:
--------------------
Statement:
Given an array of non-negative integers and a target sum, count the number of subsets
whose elements sum up to the given target. Since the answer can be large, return it
modulo 1e9 + 7.

Example:
nums = [2, 3, 5, 6, 8, 10], target = 10
Output: 3
Subsets: {2, 8}, {2, 3, 5}, {10}

Constraints:
- 1 <= nums.length <= 10^3
- 0 <= nums[i] <= 10^3
- 0 <= target <= 10^3
- Array may contain zeros (affects count)
*/

/*
Approach 1: Brute Force (Recursion)
Idea:
At every index, make two choices — include or exclude the current element.
If target becomes 0 at any point, count it as a valid subset.

Time Complexity:  O(2^n)
Space Complexity: O(n) — recursion stack

Drawbacks:
Recomputes overlapping subproblems. Exponential time makes it infeasible for large inputs.
*/

/*
Approach 2: Memoization (Top-Down DP)
Idea:
Same as brute force but cache results at (index, target) to avoid recomputation.
Base case handles zeros explicitly: both include and exclude can contribute to target = 0.

Time Complexity:  O(n * target)
Space Complexity: O(n * target) — dp table + O(n) recursion stack

Drawbacks:
Recursion stack overhead. dp table is O(n * target) which can be large.
*/

/*
Approach 3: Tabulation (Bottom-Up DP)
Idea:
Build a dp table where dp[i][t] = number of subsets using elements 0..i with sum t.
Base case initialization accounts for zeros to avoid undercounting.

Time Complexity:  O(n * target)
Space Complexity: O(n * target)

Drawbacks:
Full 2D table maintained. Can be space-optimized to two 1D arrays.
*/

/*
Approach 4: Space Optimized (Two 1D Arrays with Swap)
Idea:
Since dp[i][t] only depends on dp[i-1][...], maintain just prev[] and curr[].
Swap references after each row instead of allocating new arrays per iteration.
This guarantees exactly 2 arrays in memory at all times — formally O(target) space.

Key insight on zeros:
nums[0] = 0 → both include and exclude contribute to sum 0 → prev[0] = 2
nums[0] != 0 → only exclude contributes to sum 0 → prev[0] = 1
Initialization: prev[t] = (t - nums[0] == 0 ? 1 : 0) + (t == 0 ? 1 : 0)

Time Complexity:  O(n * target)
Space Complexity: O(target) — exactly two 1D arrays live at any point
*/

/*
Method to Solve:
----------------
1. Initialize prev[] using base case formula that handles zeros correctly.
2. For each index 1..n-1, compute curr[t] = include + exclude using prev[].
3. Fill curr[] fresh each iteration via Arrays.fill to avoid stale values.
4. Swap prev and curr references — no new allocation, no GC pressure.
5. After the loop, prev holds the final computed row. Return prev[target].
*/

import java.util.Arrays;

public class GFG_CountSubsetsWithGivenSum {

    private static final int MOD = 1_000_000_007;

    /**
     * Counts the number of subsets with sum equal to target.
     * Uses space-optimized bottom-up DP with reference swapping.
     *
     * @param nums   array of non-negative integers
     * @param target required subset sum
     * @return count of valid subsets modulo 1e9+7
     */
    public int perfectSum(int[] nums, int target) {
        int len = nums.length;

        int[] prev = new int[target + 1];
        int[] curr = new int[target + 1];
        int[] reference;

        // base case: index 0
        // include: nums[0] hits target → contributes 1
        // exclude: target is 0 → empty subset contributes 1
        // both can fire when nums[0] == 0 and target == 0 → prev[0] = 2
        for (int t = 0; t <= target; t++) {
            prev[t] = (t - nums[0] == 0 ? 1 : 0) + (t == 0 ? 1 : 0);
        }

        for (int index = 1; index < len; index++) {
            Arrays.fill(curr, 0); // clear stale values

            for (int t = 0; t <= target; t++) {
                int include = 0;

                if (t >= nums[index]) {
                    include = (prev[t - nums[index]]) % MOD;
                }
                int exclude = (prev[t]) % MOD;

                curr[t] = (include + exclude) % MOD;
            }

            // swap references — no new allocation
            reference = prev;
            prev = curr;
            curr = reference;
        }

        // after last iteration, prev holds the final computed row
        return prev[target];
    }
}
