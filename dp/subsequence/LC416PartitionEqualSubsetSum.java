package dp.subsequence;

// Created at: 09-June-2026
// Last revised at: 09-June-2026
// Link: https://leetcode.com/problems/partition-equal-subset-sum/

/*
Problem Description:
--------------------
Statement:
Given an integer array nums, return true if the array can be partitioned into
two subsets such that the sum of elements in both subsets is equal.

Example:
nums = [1, 5, 11, 5]
Output: true
Explanation: [1, 5, 5] and [11] both sum to 11.

nums = [1, 2, 3, 5]
Output: false

Constraints:
1 <= nums.length <= 200
1 <= nums[i] <= 100
*/

/*
Approach 1: Reduction to Subset Sum
Idea:
If total sum S is odd → no equal partition possible.
If S is even → each partition must sum to S/2.
Problem reduces to: does any subset sum to S/2?

Time Complexity: O(n * S/2) → O(n * S)
Space Complexity: O(S)

Key Insight:
We don't need to find both partitions. Finding one subset with sum S/2
guarantees the remaining elements form the other partition.
*/

/*
Method to Solve:
----------------
1. Compute total sum of nums.
2. If sum is odd → return false immediately (bit check: (sum & 1) == 1).
3. Target = sum / 2.
4. Run space-optimized subset sum DP on nums with target.
5. Return whether any subset sums to target.
*/

import java.util.Arrays;

public class LC416PartitionEqualSubsetSum {

    /**
     * Checks whether any subset of arr sums to the given target.
     * Space-optimized bottom-up DP using two rolling 1D arrays.
     *
     * @param arr input array
     * @param sum target sum to check
     * @return true if a valid subset exists, false otherwise
     */
    private boolean isSubsetSum(int[] arr, int sum) {
        int len = arr.length;

        boolean[] prev = new boolean[sum + 1];
        boolean[] curr = new boolean[sum + 1];
        boolean[] reference;

        // base case: first element
        if (arr[0] <= sum) {
            prev[arr[0]] = true;
        }
        prev[0] = true; // empty subset

        for (int row = 1; row < len; row++) {
            // clear stale values from previous swap cycle
            Arrays.fill(curr, false);
            curr[0] = true; // empty subset always valid

            for (int target = 1; target <= sum; target++) {
                boolean include = false;
                if (target >= arr[row])
                    include = prev[target - arr[row]];

                boolean exclude = prev[target];

                curr[target] = include || exclude;
            }

            // swap arrays — reuse memory, no new allocation
            reference = prev;
            prev = curr;
            curr = reference;
        }

        return prev[sum];
    }

    /**
     * Determines if nums can be partitioned into two subsets with equal sum.
     *
     * @param nums input array of positive integers
     * @return true if equal partition exists, false otherwise
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int val : nums)
            sum += val;

        // odd sum → equal partition impossible
        if ((sum & 1) == 1)
            return false;

        return isSubsetSum(nums, sum / 2);
    }
}

// Time Complexity: O(n * sum)
// Space Complexity: O(sum)