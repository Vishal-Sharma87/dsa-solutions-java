package recursion.subsequencepatterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC39CombinationSum {
    // Created at: 31 - March - 2026
    // Last revised at: 31 - March - 2026
    // Link: https://leetcode.com/problems/combination-sum/
    // Time Complexity: O(2^t) where t = target (branching factor bounded by
    // target/min_element)
    // Space Complexity: O(t) — max recursion depth

    /*
     * Problem Description:
     * Given an array of distinct positive integers `candidates` and a `target`,
     * return all unique combinations where the chosen numbers sum to target.
     * The same number may be used an unlimited number of times.
     * Combinations are order-independent — [2,2,3] and [3,2,2] count as one.
     *
     * Example:
     * Input: candidates = [2, 3, 6, 7], target = 7
     * Output: [[2,2,3], [7]]
     *
     * Constraints:
     * - 1 <= candidates.length <= 30
     * - 2 <= candidates[i] <= 40, all distinct
     * - 1 <= target <= 500
     */

    /*
     * Approaches:
     *
     * Approach 1 — Unsorted recursion (include/exclude):
     * Idea: At each index, decide to include (stay at i, allowing reuse) or
     * exclude (move to i+1). Guard: only include if nums[i] <= target.
     * Base case at i == nums.length — add to ans if target == 0.
     * Time: O(2^t) Space: O(t)
     * Drawback: When nums[i] > target, exclude still recurses deeper on all
     * remaining elements, even if they're also too large.
     * Wastes calls that immediately hit dead ends.
     *
     * Approach 2 — Sorted + early pruning (current implementation):
     * Idea: Sort the array first. Shift base case to target == 0 (hit the sum
     * → record immediately, no need to reach a leaf). When nums[i] > target,
     * return entirely — since array is sorted, all elements from i onward
     * are also too large, pruning the whole subtree.
     * Time: O(2^t) worst case, but significantly fewer branches in practice.
     * Space: O(t)
     * Key Insight: Sorting turns a soft guard (skip include) into a hard cutoff
     * (kill the entire branch), avoiding redundant exclude recursions.
     *
     * Method to solve (Approach 2):
     * 1. Sort candidates.
     * 2. At each index i, if target == 0, record curr and return.
     * 3. If nums[i] <= target: add to curr, recurse staying at i (reuse allowed),
     * backtrack, then recurse at i+1 (exclude).
     * 4. If nums[i] > target: return immediately — rest of array is also too large.
     */

    // ─── Approach 1: Unsorted ────────────────────────────────────────────────

    /**
     * Explores include/exclude at each index without pruning entire subtrees.
     * Correct for unsorted input; less efficient when large elements cluster at end.
     *
     * @param nums   candidates array (unsorted)
     * @param i      current index
     * @param target remaining sum needed
     * @param curr   current combination being built
     * @param ans    accumulator for valid combinations
     */
    private void getCombinationsUnsorted(int[] nums, int i, int target,
                                         List<Integer> curr, List<List<Integer>> ans) {
        if (i == nums.length) {
            if (target == 0)
                ans.add(new ArrayList<>(curr));
            return;
        }

        // include — stay at i to allow reuse
        if (nums[i] <= target) {
            curr.add(nums[i]);
            getCombinationsUnsorted(nums, i, target - nums[i], curr, ans);
            curr.remove(curr.size() - 1); // backtrack
        }

        // exclude — move forward
        getCombinationsUnsorted(nums, i + 1, target, curr, ans);
    }

    /**
     * Returns all combinations summing to target (Approach 1 — unsorted).
     *
     * @param candidates array of distinct positive integers
     * @param target     desired combination sum
     * @return list of all valid combinations
     */
    public List<List<Integer>> combinationSumUnsorted(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates.length == 0 || target <= 0)
            return ans;
        getCombinationsUnsorted(candidates, 0, target, new ArrayList<>(), ans);
        return ans;
    }

    // // ─── Approach 2: Sorted + early pruning ──────────────────────────────────

    // /**
    //  * Same include/exclude logic, but sorting enables a hard cutoff:
    //  * if nums[i] > target, the entire remaining subtree is dead.
    //  *
    //  * @param nums   candidates array (pre-sorted ascending)
    //  * @param i      current index
    //  * @param target remaining sum needed
    //  * @param curr   current combination being built
    //  * @param ans    accumulator for valid combinations
    //  */
    // private void getCombinations(int[] nums, int i, int target,
    //         List<Integer> curr, List<List<Integer>> ans) {
    //     if (target == 0) {
    //         ans.add(new ArrayList<>(curr)); // exact match — record and stop
    //         return;
    //     }
    //     if (i == nums.length)
    //         return;

    //     if (nums[i] <= target) {
    //         curr.add(nums[i]);
    //         getCombinations(nums, i, target - nums[i], curr, ans); // reuse allowed
    //         curr.remove(curr.size() - 1); // backtrack

    //         getCombinations(nums, i + 1, target, curr, ans); // skip this element
    //     } else {
    //         return; // sorted → everything from i onward is also too large
    //     }
    // }

    // /**
    //  * Returns all combinations summing to target (Approach 2 — sorted, pruned).
    //  *
    //  * @param candidates array of distinct positive integers
    //  * @param target     desired combination sum
    //  * @return list of all valid combinations
    //  */
    // public List<List<Integer>> combinationSum(int[] candidates, int target) {
    //     List<List<Integer>> ans = new ArrayList<>();
    //     if (candidates.length == 0 || target <= 0)
    //         return ans;
    //     Arrays.sort(candidates); // enables subtree pruning
    //     getCombinations(candidates, 0, target, new ArrayList<>(), ans);
    //     return ans;
    // }
}
