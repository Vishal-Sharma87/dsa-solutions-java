// Created at: 25-June-2026
// Last revised at: 25-June-2026
// Link: https://leetcode.com/problems/largest-divisible-subset/

package dp.longestincreasingsubsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Problem Description:
--------------------

Statement:
Given a set of distinct positive integers nums, return the largest subset
such that every pair (answer[i], answer[j]) satisfies:

answer[i] % answer[j] == 0
or
answer[j] % answer[i] == 0

If multiple solutions exist, return any one of them.

Example:
Input: nums = [1,2,3]
Output: [1,2]

Input: nums = [1,2,4,8]
Output: [1,2,4,8]

Constraints:

* 1 <= nums.length <= 1000
* 1 <= nums[i] <= 2 * 10^9
* All integers are unique
  */

/*
Approach 1: Dynamic Programming + Parent Tracking
-------------------------------------------------

Idea:
Sort the array first.

After sorting:
If nums[i] % nums[j] == 0 for j < i,
then nums[i] can extend the divisible subset ending at j.

This becomes very similar to Longest Increasing Subsequence.

Store:

1. lds[i]    -> size of largest divisible subset ending at i
2. parent[i] -> previous index used to build the subset

Track the ending index of the best subset and reconstruct using parent[].

Time Complexity:
O(n^2)

Space Complexity:
O(n)

Key Insight:
Sorting guarantees that if nums[i] is divisible by nums[j],
all elements before i are less than or equal to nums[i].
This converts the problem into an LIS-style DP.
*/

/*
Method to Solve:
----------------

1. Sort the array.
2. Initialize lds[] with 1.
3. Initialize parent[] with -1.
4. For every index i:
   a. Check all previous indices j.
   b. If nums[i] % nums[j] == 0, try extending j's chain.
5. Track the maximum subset size and ending index.
6. Reconstruct the subset using parent[].
7. Return the reconstructed subset.
   */

public class LC368LargestDivisibleSubset {

    /**
     * Returns the largest divisible subset.
     *
     * @param nums input array
     * @return largest divisible subset
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {

        int len = nums.length;

        int[] lds = new int[len];
        int[] parent = new int[len];

        Arrays.fill(lds, 1);
        Arrays.fill(parent, -1);

        Arrays.sort(nums);

        int maxLength = 0;
        int lastIndex = -1;

        for (int i = 0; i < len; i++) {

            for (int j = 0; j < i; j++) {

                // extend divisible chain
                if (nums[i] % nums[j] == 0 && lds[i] <= lds[j]) {
                    lds[i] = lds[j] + 1;
                    parent[i] = j;
                }
            }

            if (lds[i] > maxLength) {
                maxLength = lds[i];
                lastIndex = i;
            }
        }

        List<Integer> ldsSubset = new ArrayList<>();

        while (lastIndex != -1) {
            ldsSubset.add(nums[lastIndex]);
            lastIndex = parent[lastIndex];
        }

        return ldsSubset;
    }
}

// Time Complexity: O(n^2)
// Space Complexity: O(n)
