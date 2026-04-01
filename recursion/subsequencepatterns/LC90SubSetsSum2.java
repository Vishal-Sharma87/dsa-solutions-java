package recursion.subsequencepatterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC90SubSetsSum2 {
    // Created at: 02 - April - 2026
    // Last revised at: 02 - April - 2026
    // Link: https://leetcode.com/problems/subsets-ii/
    // Time Complexity: O(n log n + 2^n)
    // Space Complexity: O(n) auxiliary stack space, O(2^n) for result

    /*
     * Problem Description:
     * Given an integer array that may contain duplicates, return all possible
     * subsets (the power set).
     * The solution must not contain duplicate subsets. Return in any order.
     *
     * Example:
     * Input: nums = [1, 2, 2]
     * Output: [[], [1], [1,2], [1,2,2], [2], [2,2]]
     *
     * Constraints:
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     *
     * -----------------------------------------------------------------------
     * Approaches:
     *
     * 1. Pick / Not-Pick without sorting (Brute Force)
     * Idea: Generate all subsets, dump them into a HashSet to deduplicate.
     * Time: O(2^n * n) — n to copy each subset into the set
     * Space: O(2^n * n)
     * Drawbacks: Extra overhead from hashing; doesn't prune at recursion level.
     *
     * ★ 2. Sort + Skip Duplicates on Exclude Branch
     * Idea: Sort first so duplicates are adjacent. At each index, always
     * explore the include branch normally. On the exclude branch,
     * fast-forward past all elements equal to nums[i] before recursing.
     * This way, duplicate values are never offered as a "skip" choice
     * more than once at the same recursion level.
     * Time: O(n log n) sort + O(2^n) recursion
     * Space: O(n) stack + O(2^n) result
     * Key Insight: Duplicates only arise on the exclude path — if you skip
     * nums[i], skipping every identical value next to it ensures
     * the same subset isn't built again from the right sibling.
     */

    /**
     * Recursively builds unique subsets using pick/skip with duplicate pruning.
     *
     * @param nums sorted input array
     * @param i    current index being considered
     * @param curr subset being built in this branch
     * @param ans  list collecting all unique subsets
     */
    private void storeUniquePowerSet(int[] nums, int i, List<Integer> curr, List<List<Integer>> ans) {
        // all elements decided — snapshot curr as a valid subset
        if (i == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        // include nums[i] and move to next index
        curr.add(nums[i]);
        storeUniquePowerSet(nums, i + 1, curr, ans);

        curr.remove(curr.size() - 1); // backtrack before exploring skip

        // skip nums[i] — but also skip every duplicate of it
        // so the same value isn't "skipped" again at this level
        int next = i;
        while (next < nums.length && nums[i] == nums[next])
            next++;

        storeUniquePowerSet(nums, next, curr, ans);
    }

    /**
     * Returns all unique subsets of {@code nums}, including the empty subset.
     * Input may contain duplicates; sorting is done internally before recursion.
     *
     * @param nums the input array, possibly with duplicates
     * @return list of all unique subsets in any order
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> uniquePowerSets = new ArrayList<>();
        if (nums.length == 0)
            return uniquePowerSets;

        // sort so duplicates are adjacent — prerequisite for skip pruning
        Arrays.sort(nums);

        storeUniquePowerSet(nums, 0, new ArrayList<>(), uniquePowerSets);
        return uniquePowerSets;
    }
}