package arrays.traversal;

// Created at: 25-September-2026
// Last revised at: 25-September-2026
// Link: https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index/

/*
Problem Description:
--------------------
Statement:
Given an integer array nums, return the smallest index i such that
the sum of the digits of nums[i] is equal to i.

If no such index exists, return -1.

Example:
Input:
nums = [1,3,2,4]

Output:
2

Explanation:
The digit sum of nums[2] = 2, which equals its index.

Constraints:
- 1 <= nums.length <= 100
- 0 <= nums[i] <= 10^9
*/

/*
Approach 1: Brute Force

Idea:
For every index, calculate the digit sum of the corresponding number
and check whether it equals the index.

Time Complexity:
O(n * d)

Space Complexity:
O(1)

Drawbacks:
The digit sum is calculated independently for each element, but this is
already efficient enough because each number has only a limited number
of digits.
*/

/*
Approach 2: Single Traversal with Digit Sum

Idea:
Traverse the array from left to right.

For each index:
- Calculate the digit sum of nums[i].
- If it equals i, immediately return i.

Because indices are visited in increasing order, the first matching index
is automatically the smallest valid index.

Time Complexity:
O(n * d), where d is the maximum number of digits in nums.

Space Complexity:
O(1)

Key Insight:
A single left-to-right traversal is enough because the first matching
index is the required smallest index.
*/

/*
Method to Solve:
----------------
1. Traverse nums from index 0.
2. Calculate the digit sum of nums[i].
3. Compare the digit sum with i.
4. Return i immediately when they are equal.
5. Return -1 if no index satisfies the condition.
*/

// Time Complexity: O(n * d)
// Space Complexity: O(1)

public class LC3550SmallestIndexWithDigitSumEqualToIndex {

    /**
     * Calculates the sum of all digits in a number.
     *
     * @param number input number
     * @return sum of the digits
     */
    private int digitSum(int number) {
        if (number < 10) {
            return number;
        }

        int sum = 0;

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }

        return sum;
    }

    /**
     * Finds the smallest index whose value's digit sum equals the index.
     *
     * @param nums input array
     * @return smallest matching index, or -1 if no such index exists
     */
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (digitSum(nums[i]) == i) {
                return i;
            }
        }

        return -1;
    }
}