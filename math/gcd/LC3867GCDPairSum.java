package math.gcd;

// Created at: 17-July-2026
// Last revised at: 17-July-2026
// Link: https://leetcode.com/problems/gcd-pair-sum/

/*
Problem Description:
--------------------
Statement:
Given an integer array, compute the required GCD pair sum as defined in the
problem.

Example:
Input:
nums = [...]

Output:
...

Constraints:
Refer to the original LeetCode problem.
*/

/*
Approach 1: Brute Force

Idea:
Compute the GCD for every required pair directly and accumulate the answer.

Time Complexity:
O(n² · log M)

Space Complexity:
O(1)

Drawbacks:
Too slow for large inputs.
*/

/*
Approach 2: Prefix GCD + Sorting

Idea:
1. Maintain the maximum element seen so far.
2. Compute the GCD of every element with the current maximum.
3. Store these values in an array.
4. Sort the GCD array.
5. Pair the smallest and largest values while accumulating their GCD.

Time Complexity:
O(n log n)

Space Complexity:
O(n)

Key Insight:
Reducing the values using prefix maximums significantly simplifies the later
pairing process.
*/

/*
Method to Solve:
----------------
1. Build the prefix GCD array.
2. Sort the computed GCD values.
3. Pair values from both ends.
4. Add each pair's GCD to the final answer.
*/

import java.util.Arrays;

public class LC3867GCDPairSum {

    /**
     * Computes the greatest common divisor of two numbers.
     *
     * @param a first number
     * @param b second number
     * @return greatest common divisor
     */
    public int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }

        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }

        return a;
    }

    /**
     * Computes the required GCD pair sum.
     *
     * @param nums input array
     * @return total GCD pair sum
     */
    public long gcdSum(int[] nums) {
        int len = nums.length;

        int maximum = 0;
        int[] prefixGcd = new int[len];

        // build prefix gcd values
        for (int i = 0; i < len; i++) {
            maximum = Math.max(maximum, nums[i]);
            prefixGcd[i] = gcd(nums[i], maximum);
        }

        // sort for pairing
        Arrays.sort(prefixGcd);

        int left = 0;
        int right = len - 1;

        long gcdSum = 0;

        // pair smallest with largest
        while (left < right) {
            gcdSum += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }

        return gcdSum;
    }
}

// Time Complexity: O(n log n)
// Space Complexity: O(n)