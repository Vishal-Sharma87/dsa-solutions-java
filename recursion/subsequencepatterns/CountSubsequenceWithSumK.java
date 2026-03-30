// Created at: 31 - March - 2026
// Last revised at: 31 - March - 2026
// Link: https://www.geeksforgeeks.org/count-of-subsets-with-sum-equal-to-k/
// Time Complexity: O(2^n)
// Space Complexity: O(n) — recursion stack depth

package recursion.subsequencepatterns;

/*
 * Problem Description:
 * Given an integer array `nums` and an integer `target`, return the count of
 * all subsequences whose elements sum exactly to `target`.
 *
 * Example:
 *   Input:  nums = [1, 2, 3], target = 3
 *   Output: 2  → subsequences: [1,2] and [3]
 *
 * Constraints:
 *   - Array may contain both positive and negative integers
 *   - 1 <= nums.length <= 20
 */

/*
 * Approaches:
 *
 * Approach 1 — Positive-only optimized (pruned recursion):
 *   Idea: At each index, only recurse into "include" if remaining target
 *         stays >= 0, since negatives are impossible with positive-only input.
 *   Time: O(2^n)  Space: O(n)
 *   Drawback: Breaks when array has negative numbers — a negative element
 *             could still lead to a valid subsequence even if target < 0 mid-way.
 *
 * Approach 2 — General (current implementation):
 *   Idea: At each index, always explore both include and exclude paths.
 *         Base case: if we've processed all elements, check if remaining target == 0.
 *   Time: O(2^n)  Space: O(n)
 *   Key Insight: Works for both positive and negative integers because we never
 *                prune early — a negative element can pull the running sum back
 *                toward zero from an overshot value.
 *
 * Method to solve:
 *   1. At index i, branch into two recursive calls:
 *      - include nums[i]: subtract it from target, move to i+1
 *      - exclude nums[i]: keep target as-is, move to i+1
 *   2. At base case (i == nums.length), return 1 if target == 0, else 0.
 *   3. Sum both branches — counts propagate back up the tree.
 */

public class CountSubsequenceWithSumK {

    /**
     * Returns the count of subsequences in {@code nums} that sum to {@code target}.
     *
     * @param nums   input array (may contain negatives)
     * @param target the required subsequence sum
     * @return number of valid subsequences
     */
    public int getSubSequenceCountWithSumTarget(int[] nums, int target) {
        return getCount(nums, 0, target);
    }

    /**
     * Recursive helper that counts valid subsequences from index {@code i} onward.
     *
     * @param nums   input array
     * @param i      current index being processed
     * @param target remaining sum needed
     * @return count of subsequences from i..n-1 that sum to target
     */
    private int getCount(int[] nums, int i, int target) {
        if (i == nums.length)
            return target == 0 ? 1 : 0; // valid only if nothing left to cover

        // general case: works for negatives too, no pruning
        int include = getCount(nums, i + 1, target - nums[i]);
        int exclude = getCount(nums, i + 1, target);

        return include + exclude;
    }
}