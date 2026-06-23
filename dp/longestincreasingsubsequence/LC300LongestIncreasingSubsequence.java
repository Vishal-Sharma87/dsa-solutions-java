package dp.longestincreasingsubsequence;

// Created at: 24-June-2025
// Last revised at: 24-June-2025
// Link: https://leetcode.com/problems/longest-increasing-subsequence/

import java.util.Arrays;

/*
Problem Description:
--------------------
Statement:
Given an integer array nums, return the length of the longest strictly
increasing subsequence.

Example:
Input:  nums = [10, 9, 2, 5, 3, 7, 101, 18]
Output: 4
Explanation: LIS is [2, 3, 7, 101]

Constraints:
- 1 <= nums.length <= 2500
- -10^4 <= nums[i] <= 10^4
*/

/*
Approach 1: Brute Force (Pick / Not Pick)
-----------------------------------------
Idea:
At each index, decide to include or exclude the element.
Include only if nums[index] > prev element in chain.
Recurse over all possibilities and return the max.

Time Complexity:  O(2^n)
Space Complexity: O(n) — recursion stack

Drawbacks:
Exponential — recomputes the same (index, prev) states repeatedly.
*/

/*
Approach 2: DP — Tabulation (Chosen)
--------------------------------------
Idea:
lisLen[i] = length of LIS ending at index i.
For each i, check all j < i. If nums[j] < nums[i] and extending j's
chain gives a longer chain at i, update lisLen[i] = lisLen[j] + 1.
Track the global max across all i.

Time Complexity:  O(n^2)
Space Complexity: O(n)

Key Insight:
Every element is its own LIS of length 1 (base case).
We only extend when we find a strictly smaller predecessor that offers
a better chain — condition: lisLen[i] <= lisLen[j].
*/

/*
Method to Solve:
----------------
1. Initialize lisLen[i] = 1 for all i (each element is a valid LIS of length 1).
2. For each i from 1 to n-1:
   a. Scan all j < i.
   b. If nums[j] < nums[i] and lisLen[j] >= lisLen[i], update lisLen[i] = lisLen[j] + 1.
3. Track global max LIS length across all i.
4. Return the max.
*/

public class LC300LongestIncreasingSubsequence {

    /**
     * Returns the length of the longest strictly increasing subsequence.
     *
     * @param nums input array
     * @return length of LIS
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;

        int[] lisLen = new int[len];
        Arrays.fill(lisLen, 1); // every element is a LIS of length 1

        int maxLis = 1;

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // extend j's chain only if it gives a longer chain at i
                if (nums[j] < nums[i] && lisLen[i] <= lisLen[j]) {
                    lisLen[i] = lisLen[j] + 1;
                }
            }
            maxLis = Math.max(maxLis, lisLen[i]);
        }

        return maxLis;
    }
}

// Time Complexity: O(n^2)
// Space Complexity: O(n)