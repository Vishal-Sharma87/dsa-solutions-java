package recursion.subsequencepatterns;

import java.util.ArrayList;
import java.util.List;

public class LC216CombinationSum3 {
    // Created at: 02 - April - 2026
    // Last revised at: 02 - April - 2026
    // Link: https://leetcode.com/problems/combination-sum-iii/
    // Time Complexity: O(C(9, k)) — at most C(9,k) valid combinations explored
    // Space Complexity: O(k) auxiliary stack space, O(C(9,k)) for result

    /*
     * Problem Description:
     * Find all valid combinations of k numbers that sum to n, using digits 1–9.
     * Each digit may be used at most once. Return all possible combinations.
     *
     * Example:
     * Input: k = 3, n = 7
     * Output: [[1,2,4]]
     *
     * Input: k = 3, n = 9
     * Output: [[1,2,6], [1,3,5], [2,3,4]]
     *
     * Constraints:
     * 2 <= k <= 9
     * 1 <= n <= 60
     *
     * -----------------------------------------------------------------------
     * Approaches:
     *
     * 1. Brute Force
     * Idea: Generate all subsets of {1..9}, filter those of size k with sum n.
     * Time: O(2^9 * k) — 512 subsets, each checked in O(k)
     * Space: O(k)
     * Drawbacks: Explores many irrelevant subsets; no early pruning.
     *
     * ★ 2. Backtracking with Pruning
     * Idea: Build combinations iteratively from validStart to min(9, target).
     * The upper bound min(9, target) prunes branches where the current
     * candidate alone already exceeds the remaining target.
     * k tracks how many numbers are still needed — once k == 0, check
     * if target was exactly met.
     * Time: O(C(9, k))
     * Space: O(k) stack depth
     * Key Insight: Starting next candidate from i+1 (not i) naturally avoids
     * reuse and keeps the combination strictly increasing.
     */

    /**
     * Recursively builds all k-length combinations from [validStart..9] that sum to
     * target.
     *
     * @param validStart lowest digit allowed next (ensures no reuse and no
     *                   duplicates)
     * @param k          how many more numbers still need to be picked
     * @param target     remaining sum to hit exactly
     * @param curr       combination being built in this branch
     * @param ans        list collecting all valid combinations
     */
    private void generateCombinations(int validStart, int k, int target,
            List<Integer> curr, List<List<Integer>> ans) {
        // used up all k slots — valid only if remaining target is 0
        if (k == 0) {
            if (target == 0)
                ans.add(new ArrayList<>(curr));
            return;
        }

        // cap at min(9, target) — no point trying digits that exceed remaining sum
        for (int i = validStart; i <= Math.min(9, target); i++) {
            curr.add(i);
            generateCombinations(i + 1, k - 1, target - i, curr, ans);
            curr.remove(curr.size() - 1); // backtrack
        }
    }

    /**
     * Returns all k-length combinations from digits 1–9 (each used at most once)
     * that sum to n.
     *
     * @param k number of digits each combination must contain
     * @param n target sum
     * @return list of all valid combinations
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();

        // max possible sum with k digits is k*9 — prune before recursion
        if (k * 9 < n)
            return ans;

        generateCombinations(1, k, n, new ArrayList<>(), ans);
        return ans;
    }
}
