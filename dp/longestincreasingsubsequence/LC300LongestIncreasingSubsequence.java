package dp.longestincreasingsubsequence;

// Created at: 24-June-2025
// Last revised at: 25-June-2026
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

Explanation:
The LIS is [2, 3, 7, 101].

Constraints:
- 1 <= nums.length <= 2500
- -10^4 <= nums[i] <= 10^4
*/

/*
Approach 1: Brute Force (Pick / Not Pick)
-----------------------------------------
Idea:
At every index, either include the current element or skip it.

An element can only be included if it is greater than the previously
chosen element.

Explore all valid possibilities and return the maximum length.

Time Complexity:
O(2^n)

Space Complexity:
O(n)

Drawbacks:
Exponential due to repeated computation of the same states.
*/

/*
Approach 2: Dynamic Programming (Tabulation)
--------------------------------------------
Idea:
lisLen[i] stores the length of the longest increasing subsequence
ending at index i.

For every index i, scan all previous indices j.

If nums[j] < nums[i], then nums[i] can extend the subsequence ending at j.

Time Complexity:
O(n^2)

Space Complexity:
O(n)

Key Insight:
Every element itself forms an increasing subsequence of length 1.
*/

/*
Approach 3: Patience Sorting + Binary Search (Optimal)
------------------------------------------------------
Idea:
Maintain an array where index i stores the smallest possible tail
for an increasing subsequence of length i + 1.

For every number:
1. Find the last position containing a value smaller than the current number.
2. Place the current number at the next position.
3. Extend the LIS length if required.

The maintained array does not represent the actual LIS.
It only stores the best possible tail for each subsequence length.

Time Complexity:
O(n log n)

Space Complexity:
O(1)

Key Insight:
Smaller tails are always better because they provide more opportunities
to extend the subsequence later.

Example:
nums = [10,9,2,5,3,7,101,18]

tails:
[10]
[9]
[2]
[2,5]
[2,3]
[2,3,7]
[2,3,7,101]
[2,3,7,18]

Length = 4
*/

/*
Method to Solve:
----------------
DP Solution:
1. Initialize lisLen[] with 1.
2. For every index i:
   a. Check all previous indices j.
   b. If nums[j] < nums[i], extend the best chain ending at j.
3. Track the global maximum.
4. Return the answer.

Binary Search Solution:
1. Maintain tails of increasing subsequences.
2. For each element, find the last smaller tail.
3. Replace or extend the tails array.
4. Return the final LIS length.
*/

public class LC300LongestIncreasingSubsequence {

    /**
     * Returns the LIS length using DP tabulation.
     *
     * @param nums input array
     * @return length of longest increasing subsequence
     */
    public int lengthOfLISDP(int[] nums) {

        int len = nums.length;

        int[] lisLen = new int[len];
        Arrays.fill(lisLen, 1);

        int maxLis = 1;

        for (int i = 1; i < len; i++) {

            for (int j = 0; j < i; j++) {

                // extend j's chain only if it improves LIS at i
                if (nums[j] < nums[i] && lisLen[i] <= lisLen[j]) {
                    lisLen[i] = lisLen[j] + 1;
                }
            }

            maxLis = Math.max(maxLis, lisLen[i]);
        }

        return maxLis;
    }

    /**
     * Finds the last index whose value is smaller than target.
     *
     * @param nums   tails array
     * @param start  starting index
     * @param end    ending index
     * @param target current value
     * @return last index containing a value smaller than target
     */
    private int findLastSmallerIndex(
            int[] nums,
            int start,
            int end,
            int target) {

        int lastSmaller = -1;

        while (start <= end) {

            int mid = start + (end - start) / 2;

            if (nums[mid] < target) {
                lastSmaller = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return lastSmaller;
    }

    /**
     * Returns the LIS length using patience sorting and binary search.
     *
     * @param nums input array
     * @return length of longest increasing subsequence
     */
    public int lengthOfLISBinarySearch(int[] nums) {

        int len = nums.length;
        int end = -1;

        for (int i = 0; i < len; i++) {

            int lastSmaller = findLastSmallerIndex(nums, 0, end, nums[i]);

            nums[lastSmaller + 1] = nums[i];

            if (lastSmaller + 1 > end) {
                end = lastSmaller + 1;
            }
        }

        return end + 1;
    }
}

// DP Time Complexity: O(n^2)
// DP Space Complexity: O(n)

// Binary Search Time Complexity: O(n log n)
// Binary Search Space Complexity: O(1)