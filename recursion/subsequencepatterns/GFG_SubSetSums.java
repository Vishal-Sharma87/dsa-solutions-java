package recursion.subsequencepatterns;

import java.util.ArrayList;

public class GFG_SubSetSums {
    // Created at: 02 - April - 2026
    // Last revised at: 02 - April - 2026
    // Link: https://www.geeksforgeeks.org/problems/subset-sums2234/1
    // Time Complexity: O(2^n)
    // Space Complexity: O(n) auxiliary stack space, O(2^n) for result

    /*
     * Problem Description:
     * Given an array of integers, return the sum of all possible subsets (including
     * the empty subset).
     * The result can be in any order.
     *
     * Example:
     * Input: arr = [2, 3]
     * Output: [0, 2, 3, 5] (subsets: {}, {2}, {3}, {2,3})
     *
     * Constraints:
     * 1 <= arr.size() <= 15
     * 0 <= arr[i] <= 10^4
     *
     * -----------------------------------------------------------------------
     * Approaches:
     *
     * ★ 1. Pick / Not-Pick Recursion
     * Idea: At every index, make two choices — include the element in the
     * running sum, or skip it. Once all elements are processed, the
     * accumulated sum is one valid subset sum.
     * Time: O(2^n) — two branches per element, n levels deep
     * Space: O(n) recursion stack + O(2^n) result list
     * Key Insight: No explicit subset list is needed; carry the sum as a
     * parameter so each leaf of the recursion tree is a result.
     */

    /**
     * Recursively explores all pick/skip choices for each index,
     * accumulating subset sums into {@code ans}.
     *
     * @param nums the input array
     * @param i    current index being considered
     * @param curr running subset sum so far
     * @param ans  list collecting all subset sums
     */
    private void storeSubSetSum(int[] nums, int i, int curr, ArrayList<Integer> ans) {
        // processed all elements — curr holds one complete subset sum
        if (i == nums.length) {
            ans.add(curr);
            return;
        }

        // pick nums[i] — add it to running sum
        storeSubSetSum(nums, i + 1, curr + nums[i], ans);

        // skip nums[i] — running sum unchanged
        storeSubSetSum(nums, i + 1, curr, ans);
    }

    /**
     * Returns the sums of all subsets of {@code arr}, including the empty subset
     * (sum = 0).
     *
     * @param arr the input array
     * @return list of all 2^n subset sums in any order
     */
    public ArrayList<Integer> subsetSums(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();

        if (arr.length == 0)
            return ans;

        // kick off recursion from index 0 with running sum 0
        storeSubSetSum(arr, 0, 0, ans);
        return ans;
    }
}
