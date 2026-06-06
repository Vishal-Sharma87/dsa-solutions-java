package dp.basics;

// Created at: 07-June-2025
// Last revised at: 07-June-2025
// Link: https://leetcode.com/problems/house-robber-ii/

/*
Problem Description:
--------------------
Statement:
All houses are arranged in a circle — first and last house are neighbours.
You cannot rob two adjacent houses. Given nums representing money at each house,
return the maximum amount you can rob without alerting the police.

Example:
Input: nums = [2, 3, 2]
Output: 3

Input: nums = [1, 2, 3, 1]
Output: 4

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 1000
*/

/*
Approach: Break Circular Dependency into Two Linear Problems
Idea:
Since house 0 and house n-1 are neighbours, they can never both be robbed.
So split into two non-circular subproblems:
  - Combination 1: houses 0 to n-2 (exclude last)
  - Combination 2: houses 1 to n-1 (exclude first)
Run House Robber I on each combination and return the max.

This works because the optimal answer must exclude either the first or the last house.
By solving both ranges we cover all valid possibilities.

Time Complexity: O(n) — two linear passes
Space Complexity: O(1) — only two rolling variables

Key Insight:
Reducing a circular problem to two linear subproblems is a classic technique.
The circular constraint is handled by the range split, not by any special logic inside the helper.
*/

/*
Method to Solve:
----------------
1. Handle edge cases: len == 1 → return nums[0], len == 2 → return max of both
2. Call rob(nums, 0, len-1) — houses 0 to n-2
3. Call rob(nums, 1, len)   — houses 1 to n-1
4. Return max of both results

Helper rob(nums, start, end):
1. Initialize take = nums[start], skip = max(nums[start+1], take)
2. Loop i from start+2 to end (exclusive):
   a. maxi = max(nums[i] + take, skip)
   b. take = skip
   c. skip = maxi
3. Return skip
*/

public class LC213HouseRobberII {

    /**
     * Runs House Robber I logic on a subrange of the array.
     * Guaranteed to receive at least 2 houses in range — no single-house guard
     * needed.
     *
     * @param nums  array of house amounts
     * @param start start index (inclusive)
     * @param end   end index (exclusive)
     * @return max amount robable in the given range
     */
    private int rob(int[] nums, int start, int end) {
        int take = nums[start];
        int skip = Math.max(nums[start + 1], take);

        for (int i = start + 2; i < end; i++) {
            int maxi = Math.max(nums[i] + take, skip);

            // shift window forward
            take = skip;
            skip = maxi;
        }

        return skip;
    }

    /**
     * Splits circular problem into two linear subproblems.
     * compile time polymorphism in the play BABY...
     *
     * @param nums array of house amounts arranged in a circle
     * @return max amount robable without hitting adjacent houses
     */
    public int rob(int[] nums) {
        int len = nums.length;

        if (len == 1)
            return nums[0];
        if (len == 2)
            return Math.max(nums[0], nums[1]);

        // exclude last house vs exclude first house
        return Math.max(rob(nums, 0, len - 1), rob(nums, 1, len));
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)
