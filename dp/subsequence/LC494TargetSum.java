package dp.subsequence;

// Created at: 11-June-2026
// Last revised at: 11-June-2026
// Link: https://leetcode.com/problems/target-sum/

/*
Problem Description:
--------------------
Statement:
Given an array of non-negative integers and a target, assign a '+' or '-' sign to each
element and count the number of expressions that evaluate to the target sum.

Example:
nums = [1, 1, 1, 1, 1], target = 3
Output: 5
Expressions: -1+1+1+1+1, +1-1+1+1+1, +1+1-1+1+1, +1+1+1-1+1, +1+1+1+1-1

Constraints:
- 1 <= nums.length <= 20
- 0 <= nums[i] <= 1000
- 0 <= sum(nums[i]) <= 1000
- -1000 <= target <= 1000
- Answer is guaranteed to fit in a 32-bit integer (no MOD needed)
*/

/*
Key Derivation:
---------------
Let P = sum of elements assigned '+' sign (positive group)
Let N = sum of elements assigned '-' sign (their absolute values)

Then:
  P - N = target   ... (expression value)
  P + N = totalSum ... (total sum of array)

Adding both: 2P = totalSum + target → P = (totalSum + target) / 2

So the problem reduces to: count subsets with sum = (totalSum + target) / 2
which is exactly the "Count Subsets with Given Sum" problem.

Handling negative target:
If requiredSum = (totalSum + target) / 2 is negative, use complement property:
  every subset with sum X has a complementary subset with sum totalSum - X
  → counts are always equal
  → pass (totalSum - target) / 2 instead, which is always non-negative

Parity guarantee: (totalSum + target) and (totalSum - target) always have the same
parity, so one odd check is sufficient for both cases.

Base Case Checks (before DP):
1. |target| > totalSum       → impossible, return 0
2. (totalSum + target) % 2 == 1 → P won't be integer, return 0
*/

/*
Approach: Reduce + Space Optimized DP
Idea:
Derive requiredSum using the mathematical reduction above.
Apply base case guards, then delegate to perfectSum() — count subsets with sum = requiredSum.
perfectSum() uses two 1D arrays with reference swapping for O(target) space.
No MOD needed — answer fits in 32-bit integer per constraints.

Time Complexity:  O(n * requiredSum)
Space Complexity: O(requiredSum) — exactly two 1D arrays live at any point
*/

/*
Method to Solve:
----------------
1. Compute totalSum of array.
2. Guard: if |target| > totalSum or (totalSum + target) is odd → return 0.
3. Compute requiredSum = (totalSum + target) / 2.
4. If requiredSum < 0, use complement: requiredSum = (totalSum - target) / 2.
5. Count subsets with sum = requiredSum using space-optimized DP.
6. Return count.
*/

import java.util.Arrays;

public class LC494TargetSum {

    /**
     * Counts subsets with sum equal to target using space-optimized bottom-up DP.
     * Base case initialization correctly handles zeros in the array.
     * No MOD applied — answer fits in 32-bit integer per problem constraints.
     *
     * @param nums   array of non-negative integers
     * @param target required subset sum
     * @return count of valid subsets
     */
    private int perfectSum(int[] nums, int target) {
        int len = nums.length;

        int[] prev = new int[target + 1];
        int[] curr = new int[target + 1];
        int[] reference;

        // base case: index 0
        // include: nums[0] hits target → +1
        // exclude: target is 0 → empty subset → +1
        // both fire when nums[0] == 0 and target == 0 → prev[0] = 2
        for (int t = 0; t <= target; t++) {
            prev[t] = (t - nums[0] == 0 ? 1 : 0) + (t == 0 ? 1 : 0);
        }

        for (int index = 1; index < len; index++) {
            Arrays.fill(curr, 0); // clear stale values

            for (int t = 0; t <= target; t++) {
                int include = 0;

                if (t >= nums[index]) {
                    include = prev[t - nums[index]];
                }
                int exclude = prev[t];

                curr[t] = include + exclude;
            }

            // swap references — no new allocation
            reference = prev;
            prev = curr;
            curr = reference;
        }

        return prev[target];
    }

    /**
     * Counts the number of ways to assign '+' or '-' to each element
     * such that the resulting expression evaluates to target.
     *
     * @param nums   array of non-negative integers
     * @param target required expression sum (can be negative)
     * @return count of valid expressions
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;

        for (int val : nums)
            sum += val;

        // |target| > sum → no valid partition exists
        // (sum + target) odd → requiredSum won't be integer
        if (Math.abs(target) > sum || ((sum + target) & 1) == 1)
            return 0;

        int requiredSum = (sum + target) / 2;

        // if requiredSum is negative, use complement property:
        // count of subsets with sum X == count with sum (totalSum - X)
        if (requiredSum < 0) {
            requiredSum = (sum - target) / 2;
        }

        return perfectSum(nums, requiredSum);
    }
}
