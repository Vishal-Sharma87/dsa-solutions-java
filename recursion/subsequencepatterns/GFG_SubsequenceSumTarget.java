package recursion.subsequencepatterns;

public class GFG_SubsequenceSumTarget {
    // Created at: 29-March-2025
    // Last revised at: 29-March-2025
    // Link:
    // https://www.geeksforgeeks.org/problems/check-if-there-exists-a-subsequence-with-sum-k/
    // Time Complexity: O(2^N) — each element is either included or skipped
    // Space Complexity: O(N) — recursion depth

    /*
     * Problem Description:
     * Given an array of integers and a target K, determine whether any subsequence
     * of the array sums exactly to K.
     *
     * Example:
     * Input: arr = [1, 2, 3, 4], K = 5
     * Output: true (subsequences [1,4] and [2,3] both sum to 5)
     *
     * Input: arr = [1, 2, 3], K = 7
     * Output: false
     *
     * Constraints:
     * 1 <= N <= 20
     * -10^4 <= arr[i] <= 10^4
     * -10^4 <= K <= 10^4
     */

    /*
     * Approach 1: Recursion with positive-only pruning (original)
     *
     * Idea:
     * At each index i, make two choices — include or skip.
     * Include branch is only taken if target - nums[i] >= 0,
     * which prunes paths that overshoot the target.
     *
     * Limitation: pruning assumes all elements are positive.
     * With negative numbers, overshooting doesn't rule out a valid path
     * (a later negative could bring the sum back), so this can miss valid answers.
     *
     * Time: O(2^N)
     * Space: O(N)
     *
     * Drawback:
     * Incorrect for arrays with negative numbers — include branch is
     * silently skipped when target - nums[i] < 0, even if a valid
     * subsequence exists through that path.
     */

    /**
     * Recursively checks if any subsequence of nums[i..end] sums to target.
     * Assumes all elements are non-negative — prunes include branch if it
     * overshoots.
     *
     * @param nums   input array
     * @param i      current index
     * @param target remaining sum needed
     * @return true if a valid subsequence exists, false otherwise
     */
    public boolean isExists(int[] nums, int i, int target) {
        if (i >= nums.length)
            return false;

        if (target - nums[i] == 0)
            return true; // including nums[i] hits target exactly

        // only include if it doesn't overshoot — valid only for positive arrays
        if (target - nums[i] > 0 && isExists(nums, i + 1, target - nums[i]))
            return true;

        return isExists(nums, i + 1, target); // skip nums[i]
    }

    // -----------------------------------------------------------------------
    // Approach 2: Recursion without pruning (handles negatives correctly)
    //
    // Drops the target - nums[i] > 0 guard so both branches are always explored.
    // target == 0 is promoted to a top-level base case — cleaner than checking
    // target - nums[i] == 0 inline, and naturally handles K = 0.
    // -----------------------------------------------------------------------

    /*
     * Approach 2: Recursion (Include / Skip) — general, no pruning
     *
     * Idea:
     * At each index i, unconditionally try both choices:
     * - Include nums[i]: subtract from target, move to i+1
     * - Skip nums[i]: move to i+1 with target unchanged
     *
     * If target hits 0 at any point, a valid subsequence was found.
     * No pruning — works correctly for arrays with negative numbers.
     *
     * Time: O(2^N)
     * Space: O(N)
     */

    /**
     * Recursively checks if any subsequence of nums[i..end] sums to target.
     * No pruning — handles arrays with negative numbers correctly.
     *
     * @param nums   input array
     * @param i      current index
     * @param target remaining sum needed
     * @return true if a valid subsequence exists, false otherwise
     */
    public boolean isExists2(int[] nums, int i, int target) {
        if (target == 0)
            return true; // valid subsequence found
        if (i >= nums.length)
            return false; // exhausted array, target not met

        if (isExists2(nums, i + 1, target - nums[i]))
            return true; // include nums[i]
        return isExists2(nums, i + 1, target); // skip nums[i]
    }

    /**
     * Returns true if any subsequence of arr sums to K.
     *
     * @param N   size of the array
     * @param arr input array
     * @param K   target sum
     * @return true if such a subsequence exists, false otherwise
     */
    public boolean checkSubsequenceSum(int N, int[] arr, int K) {
        return isExists(arr, 0, K);
    }
}
