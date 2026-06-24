// Created at: 25-June-2026
// Last revised at: 25-June-2026
// Link: https://leetcode.com/problems/number-of-longest-increasing-subsequence/

package dp.longestincreasingsubsequence;

import java.util.Arrays;

/*
Problem Description:
--------------------

Statement:
Given an integer array nums, return the number of longest increasing
subsequences.

Example:
Input: nums = [1,3,5,4,7]
Output: 2

Explanation:
The longest increasing subsequences are:
[1,3,4,7]
[1,3,5,7]

Example:
Input: nums = [2,2,2,2,2]
Output: 5

Explanation:
Every element itself forms an LIS of length 1.

Constraints:

* 1 <= nums.length <= 2000
* -10^6 <= nums[i] <= 10^6
  */

/*
Approach 1: Dynamic Programming (Length + Count Tracking)
---------------------------------------------------------

Idea:
For every index i:

1. lis[i] stores the length of the longest increasing subsequence
   ending at i.

2. cnt[i] stores the number of LIS having length lis[i]
   and ending at i.

While processing previous indices:

* If another subsequence produces the same best length,
  accumulate its count.

* If a longer subsequence is found,
  replace both length and count.

Finally, sum counts of all indices whose LIS length equals
the global maximum length.

Time Complexity:
O(n^2)

Space Complexity:
O(n)

Key Insight:
Along with storing the LIS length, we must also track
how many ways that length can be achieved.
*/

/*
Method to Solve:
----------------

1. Initialize lis[] with 1.
2. Initialize cnt[] with 1.
3. For every index i:
   a. Check all previous indices j.
   b. If nums[i] > nums[j]:

   * Add counts if same LIS length is obtained.
   * Replace length and count if a better LIS is found.
4. Track global LIS length.
5. Sum counts of all indices contributing to the global LIS.
6. Return the total count.
   */

public class LC673NumberOfLongestIncreasingSubsequence {

    /**
     * Returns the number of longest increasing subsequences.
     *
     * @param nums input array
     * @return number of LIS
     */
    public int findNumberOfLIS(int[] nums) {

        int len = nums.length;

        int[] lis = new int[len];
        int[] cnt = new int[len];

        Arrays.fill(lis, 1);
        Arrays.fill(cnt, 1);

        int maxLen = 0;
        int maxCnt = 0;

        for (int i = 0; i < len; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[i] > nums[j]) {

                    // another way to achieve same LIS length
                    if (lis[i] == lis[j] + 1) {
                        cnt[i] += cnt[j];
                    }

                    // found a better LIS length
                    else if (lis[i] < lis[j] + 1) {
                        lis[i] = lis[j] + 1;
                        cnt[i] = cnt[j];
                    }
                }
            }

            if (lis[i] > maxLen) {
                maxLen = lis[i];
                maxCnt = cnt[i];
            } else if (lis[i] == maxLen) {
                maxCnt += cnt[i];
            }
        }

        return maxCnt;
    }
}

// Time Complexity: O(n^2)
// Space Complexity: O(n)
