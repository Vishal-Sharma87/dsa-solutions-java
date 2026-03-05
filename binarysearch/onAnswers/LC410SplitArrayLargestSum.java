package binarysearch.onAnswers;

public class LC410SplitArrayLargestSum {

    // Created at: 6 march 2026
    // Last revised at: 6 march 2026

    // link :https://leetcode.com/problems/split-array-largest-sum/

    /*
     * Problem Description -> statement, example, constraints
     * 410. Split Array Largest Sum
     * Solved
     * Hard
     * Topics
     * premium lock icon
     * Companies
     * Given an integer array nums and an integer k, split nums into k non-empty
     * subarrays such that the largest sum of any subarray is minimized.
     * 
     * Return the minimized largest sum of the split.
     * 
     * A subarray is a contiguous part of the array.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [7,2,5,10,8], k = 2
     * Output: 18
     * Explanation: There are four ways to split nums into two subarrays.
     * The best way is to split it into [7,2,5] and [10,8], where the largest sum
     * among the two subarrays is only 18.
     * Example 2:
     * 
     * Input: nums = [1,2,3,4,5], k = 2
     * Output: 9
     * Explanation: There are four ways to split nums into two subarrays.
     * The best way is to split it into [1,2,3] and [4,5], where the largest sum
     * among the two subarrays is only 9.
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 106
     * 1 <= k <= min(50, nums.length)
     */

    /*
     * PROBLEM: Split Array Largest Sum (Leetcode #410)
     * =====================================================
     * Given an integer array `nums` and an integer `k`, split nums into k
     * non-empty subarrays (contiguous) such that the MAXIMUM subarray sum
     * among all splits is MINIMIZED. Return that minimum possible maximum sum.
     *
     * EXAMPLE:
     * nums = [7, 2, 5, 10, 8], k = 2
     * Possible splits:
     * [7] | [2, 5, 10, 8] → max = 25
     * [7, 2] | [5, 10, 8] → max = 23
     * [7, 2, 5] | [10, 8] → max = 18 ← optimal
     * [7, 2, 5, 10] | [8] → max = 24
     * Answer: 18
     *
     * NOTE: This is structurally IDENTICAL to the Book Allocation problem.
     * - books[] ↔ nums[]
     * - students ↔ subarrays (k)
     * - "min of max pages" ↔ "min of max subarray sum"
     *
     * APPROACH: Binary Search on Answer
     * =====================================================
     * We don't enumerate all possible splits. Instead, we binary search
     * directly on the ANSWER — the value of the "maximum subarray sum".
     *
     * SEARCH SPACE:
     * low = max(nums[i]) → a single element must fit, so this is the floor
     * high = sum(nums[i]) → one subarray holds everything, this is the ceiling
     *
     * The true answer is guaranteed to lie within [low, high].
     *
     * FEASIBILITY CHECK (canSplit):
     * For a given candidate `maxSum`, greedily check:
     * → Keep accumulating elements into the current subarray.
     * → When adding the next element would exceed `maxSum`, close the current
     * subarray and start a new one (increment subarray count).
     * → If at any point subarray count exceeds k, `maxSum` is too small →
     * infeasible.
     * → If we finish all elements within k subarrays → feasible.
     *
     * BINARY SEARCH DIRECTION (Minimization Pattern):
     * ┌─────────────────┬────────────────────────────────────────────────────┐
     * │ canSplit = true │ mid is a valid answer, but maybe we can do better │
     * │ │ → search LEFT (high = mid - 1) │
     * ├─────────────────┼────────────────────────────────────────────────────┤
     * │ canSplit = false│ mid is too small, subarrays overflow │
     * │ │ → search RIGHT (low = mid + 1) │
     * └─────────────────┴────────────────────────────────────────────────────┘
     *
     * WHY `low` IS THE ANSWER AFTER THE LOOP:
     * Unlike the Book Allocation version which explicitly tracks `minPages`,
     * here we rely on the loop invariant:
     * → Every time canSplit is false → low moves right past infeasible values.
     * → Every time canSplit is true → high moves left past feasible values.
     * When the loop ends (low > high), `low` points to the smallest value
     * for which canSplit would return true — exactly the answer we want.
     * This works because the feasibility function is MONOTONIC:
     * if maxSum = X is feasible, then any maxSum > X is also feasible.
     *
     * TIME COMPLEXITY : O(N * log(sum - max))
     * - O(log(sum - max)) binary search iterations
     * - O(N) per greedy feasibility check
     * SPACE COMPLEXITY: O(1)
     */
    class Solution {

        /**
         * Greedy feasibility check: can we split `nums` into at most k subarrays
         * such that no subarray has a sum exceeding `maxSum`?
         *
         * @param maxSum candidate upper bound on each subarray's sum
         * @param nums   input array
         * @param k      maximum number of subarrays allowed
         * @return true if a valid split exists within the constraints, false otherwise
         */
        public boolean canSplit(int maxSum, int[] nums, int k) {

            int subarray = 1; // we always need at least one subarray
            int currSum = 0;

            for (int val : nums) {
                if (currSum + val <= maxSum) {
                    // current element fits in the ongoing subarray → extend it
                    currSum += val;
                } else {
                    // current element would breach the limit → close current subarray,
                    // open a new one starting with this element
                    subarray++;

                    // early exit: already need more subarrays than allowed
                    if (subarray > k)
                        return false;

                    // the new element becomes the start of the next subarray
                    currSum = val;
                }
            }

            // finished all elements within k subarrays and the maxSum constraint
            return true;
        }

        /**
         * Finds the minimized largest sum after splitting nums into exactly k
         * subarrays.
         *
         * @param nums input array of integers
         * @param k    number of subarrays to split into
         * @return the minimum possible value of the largest subarray sum,
         *         or -1 if splitting into k non-empty subarrays is impossible
         */
        public int splitArray(int[] nums, int k) {
            int len = nums.length;

            // edge case: can't form k non-empty subarrays from fewer than k elements
            if (len < k)
                return -1;

            // low = max(nums) → smallest valid maxSum (single largest element must fit)
            // high = sum(nums) → largest valid maxSum (entire array in one subarray)
            int low = 0, high = 0;

            for (int val : nums) {
                low = Math.max(low, val);
                high += val;
            }

            // binary search over the answer space [max(nums), sum(nums)]
            while (low <= high) {
                int mid = low + (high - low) / 2; // safe midpoint, avoids overflow

                if (canSplit(mid, nums, k)) {
                    // mid is feasible → try to minimize further by searching left
                    high = mid - 1;
                } else {
                    // mid is too small → increase the allowed sum by searching right
                    low = mid + 1;
                }
            }

            // loop invariant guarantees `low` is the smallest feasible maxSum
            return low;
        }

        // Time Complexity: O(n * log(sum(nums) - max(nums)))
        // Space Complexity: constant
    }
}
