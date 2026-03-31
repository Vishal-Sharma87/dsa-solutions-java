package recursion.subsequencepatterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC40CombinationSum_2 {
    // Created at: 01 - April - 2025
    // Last revised at: 01 - April - 2025
    // Link: https://leetcode.com/problems/combination-sum-ii/
    // Time Complexity: O(2^n)
    // Space Complexity: O(n) — recursion stack depth

    /*
     * Problem Description:
     * Given a collection of candidate numbers (candidates) and a target number
     * (target),
     * find all unique combinations in candidates where the candidate numbers sum to
     * target.
     * Each number in candidates may only be used once in the combination.
     * The solution set must not contain duplicate combinations.
     *
     * Example:
     * Input: candidates = [10,1,2,7,6,1,5], target = 8
     * Output: [[1,1,6],[1,2,5],[1,7],[2,6]]
     *
     * Constraints:
     * - 1 <= candidates.length <= 100
     * - 1 <= candidates[i] <= 50
     * - 1 <= target <= 30
     */

    /*
     * Approaches:
     *
     * Approach 1: Backtracking with sort-based duplicate skipping ★
     * Idea: Sort the array so duplicates are adjacent. At each index, make a binary
     * choice — include the element and recurse forward, or skip it and fast-forward
     * past all adjacent elements with the same value to avoid duplicate subtrees.
     * Time: O(2^n) — each element has two choices: include or skip
     * Space: O(n) — recursion depth bounded by the length of candidates
     * Key Insight: Skipping duplicates happens only on the EXCLUDE branch. The
     * include
     * branch is free to pick the same value again (from the next index),
     * which is how [1,1,6] gets produced when two 1s exist in the input.
     */
    /**
     * Explores all combinations recursively using include/exclude decisions.
     * Duplicate combinations are avoided by skipping adjacent equal values on the
     * exclude path.
     *
     * @param nums   sorted candidate array
     * @param i      current index being considered
     * @param target remaining sum needed
     * @param curr   current combination being built
     * @param unique accumulator for valid unique combinations
     */
    private void getUniqueCombinations(int[] nums, int i, int target, List<Integer> curr,
            List<List<Integer>> unique) {

        // found a valid combination
        if (target == 0) {
            unique.add(new ArrayList<>(curr));
            return;
        }

        if (i >= nums.length)
            return;

        // array is sorted — if current element exceeds target, everything after will
        // too
        if (nums[i] > target)
            return;

        // include: pick nums[i], move to next index (each element used at most once)
        curr.add(nums[i]);
        getUniqueCombinations(nums, i + 1, target - nums[i], curr, unique);
        curr.remove(curr.size() - 1); // backtrack

        // exclude: skip all adjacent duplicates so we don't re-explore the same subtree
        int next = i;
        while (next < nums.length && nums[next] == nums[i])
            next++;

        // call with `next`, not `next + 1` — next already points past the duplicates
        getUniqueCombinations(nums, next, target, curr, unique);
    }

    /**
     * Returns all unique combinations from candidates that sum to target.
     * Each candidate may only be used once.
     *
     * @param candidates array of candidate numbers (may contain duplicates)
     * @param target     the target sum
     * @return list of all unique combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        if (candidates.length == 0)
            return ans;

        // sort so duplicates are adjacent — required for the skip logic to work
        Arrays.sort(candidates);

        getUniqueCombinations(candidates, 0, target, new ArrayList<>(), ans);

        return ans;
    }
}
