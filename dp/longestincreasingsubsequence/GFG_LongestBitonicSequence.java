package dp.longestincreasingsubsequence;

// Created at: 26-June-2026
// Last revised at: 26-June-2026
// Link: https://www.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1

/*
Problem Description:
--------------------
Statement:
Given an array of positive integers nums of size n, find the length of the
longest bitonic subsequence. A bitonic subsequence first strictly increases
then strictly decreases. A purely increasing or purely decreasing sequence
is NOT considered bitonic.

Example:
Input:  nums = [1, 11, 2, 10, 4, 5, 2, 1]
Output: 6
Explanation: [1, 2, 10, 4, 2, 1] — increases to 10, then decreases.

Constraints:
1 <= n <= 1000
1 <= nums[i] <= 10^4
*/

/*
Approach 1: LIS + LDS DP (Optimal for this problem)
----------------------------------------------------
Idea:
Treat each element as a potential peak of the bitonic subsequence.
For a peak at index i:
  - lis[i] = length of longest strictly increasing subsequence ending at i (left side)
  - lds[i] = length of longest strictly increasing subsequence starting at i going right (right side)
Both arrays are computed using the standard LIS DP pattern.
Answer = max(lis[i] + lds[i] - 1) for all valid peaks i.
A valid peak must have lis[i] > 1 AND lds[i] > 1 (at least one neighbor on each side).

Time Complexity: O(n^2)
Space Complexity: O(n)

Drawbacks:
Can be improved to O(n log n) using patience sorting + binary search,
but O(n^2) is sufficient for n <= 1000.
*/

/*
Method to Solve:
----------------
1. Build lis[i] — for each i, scan j < i; if nums[i] > nums[j], extend lis[j] into lis[i].
2. Build lds[i] — for each i, scan j > i; if nums[i] > nums[j], extend lds[j] into lds[i].
   (This gives LIS starting at i going rightward — effectively the decreasing side.)
3. For each index i (excluding edges), if lis[i] > 1 and lds[i] > 1,
   compute lis[i] + lds[i] - 1 (subtract 1 to avoid double-counting the peak).
4. Return the maximum. If no valid peak exists, return 0 (no bitonic subsequence).
*/

import java.util.Arrays;

public class GFG_LongestBitonicSequence {

    /**
     * Finds the length of the longest bitonic subsequence.
     * A bitonic subsequence strictly increases then strictly decreases.
     * Purely increasing or purely decreasing sequences are not considered bitonic.
     *
     * @param n    size of the array
     * @param nums input array of positive integers
     * @return length of the longest bitonic subsequence, or 0 if none exists
     */
    public int longestBitonicSequence(int n, int[] nums) {
        int len = nums.length;

        int[] lis = new int[len];
        int[] lds = new int[len];

        Arrays.fill(lis, 1);
        Arrays.fill(lds, 1);

        // LIS ending at each index (left → right)
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && lis[i] <= lis[j]) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        // LIS starting at each index going right (right → left)
        // effectively gives the decreasing side length from each index
        for (int i = len - 1; i >= 0; i--) {
            for (int j = len - 1; j > i; j--) {
                if (nums[i] > nums[j] && lds[i] <= lds[j]) {
                    lds[i] = lds[j] + 1;
                }
            }
        }

        int maxi = 0;

        // edges excluded — a valid peak needs smaller elements on both sides
        for (int i = 1; i < len - 1; i++) {
            // skip if no valid increasing or decreasing side
            if (lis[i] == 1 || lds[i] == 1)
                continue;

            // -1 to avoid counting peak twice
            int currMax = lis[i] + lds[i] - 1;
            maxi = Math.max(maxi, currMax);
        }

        return maxi;
    }
}