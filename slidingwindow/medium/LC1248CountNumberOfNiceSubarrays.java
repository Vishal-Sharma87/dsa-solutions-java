package slidingwindow.medium;

// Created at: 25-April-2026
// Last revised at: 25-April-2026
// Link: https://leetcode.com/problems/count-number-of-nice-subarrays/
// Time Complexity: O(N) — two linear passes
// Space Complexity: O(1)

/*
 * Problem Description:
 * Given an array of integers nums and an integer k, return the number of
 * contiguous subarrays that contain exactly k odd numbers.
 * Such subarrays are called "nice".
 *
 * Example:
 *   Input:  nums = [1,1,2,1,1], k = 3
 *   Output: 2   // [1,1,2,1], [1,2,1,1]
 *
 *   Input:  nums = [2,4,6], k = 1
 *   Output: 0   // no odd numbers at all
 *
 *   Input:  nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 *   Output: 16
 *
 * Constraints:
 *   - 1 <= nums.length <= 50000
 *   - 1 <= nums[i] <= 10^5
 *   - 1 <= k <= nums.length
 */

/*
 * Approaches:
 *
 * 1. Brute Force
 *    Idea    : Try every subarray, count odds inside, track those with exactly k
 *    Time    : O(N^2)
 *    Space   : O(1)
 *    Drawback: Too slow for N = 50000
 *
 * 2. Prefix Sum + HashMap
 *    Idea    : Track running odd count as a prefix; for each r, count prior indices
 *              where prefixOdd[r] - k was already seen
 *    Time    : O(N)
 *    Space   : O(N)
 *    Drawback: Extra space; same pattern works but sliding window is cleaner here
 *
 * 3. ★ Sliding Window — exactlyK = atMost(K) - atMost(K-1)
 *    Idea    : Direct sliding window for "oddCount == k" breaks down because even
 *              numbers allow multiple valid left boundaries per right pointer.
 *              Reduce: count(odds == k) = count(odds <= k) - count(odds <= k-1).
 *              Same reduction used in LC930 (Binary Subarrays With Sum) —
 *              general pattern for "exactly K" problems with non-negative elements.
 *    Time    : O(N)
 *    Space   : O(1)
 *    Key Insight: Odd/even detection via bitwise AND — (n & 1) == 1 is faster
 *                 than n % 2 == 1 since it avoids division entirely.
 */

public class LC1248CountNumberOfNiceSubarrays {

    /*
     * Helper: count subarrays with at most k odd numbers.
     * Expand r; when oddCount exceeds k, shrink from left.
     * Every valid r contributes (r - l + 1) subarrays ending at r.
     */

    /**
     * Counts subarrays containing at most k odd numbers.
     *
     * @param nums input integer array
     * @param k    upper bound on odd count (inclusive)
     * @return number of subarrays with odd count in [0, k]
     */
    private int niceArrayCountLessThanEqualK(int[] nums, int k) {

        int len = nums.length;
        if (len == 0)
            return 0;

        int l = 0, r = 0;
        int niceArrayCnt = 0;
        int oddCount = 0;

        while (r < len) {

            // bitwise check — avoids division
            if ((nums[r] & 1) == 1)
                oddCount++;

            // shrink until odd count is within k
            while (l <= r && oddCount > k) {
                if ((nums[l] & 1) == 1)
                    oddCount--;
                l++;
            }

            // every subarray ending at r with left boundary in [l, r] is valid
            if (oddCount <= k)
                niceArrayCnt += r - l + 1;

            r++;
        }

        return niceArrayCnt;
    }

    /*
     * Method to solve:
     * exactlyK(k) = atMost(k) - atMost(k-1)
     * Delegate both calls to helper and return the difference.
     */

    /**
     * Returns the count of subarrays containing exactly k odd numbers.
     *
     * @param nums input integer array
     * @param k    exact number of odd elements required in each subarray
     * @return count of nice subarrays
     */
    public int numberOfSubarrays(int[] nums, int k) {

        int len = nums.length;
        if (len == 0)
            return 0;

        return niceArrayCountLessThanEqualK(nums, k)
                - niceArrayCountLessThanEqualK(nums, k - 1);
    }
}