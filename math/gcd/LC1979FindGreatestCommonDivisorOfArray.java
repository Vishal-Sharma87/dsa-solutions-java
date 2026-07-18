package math.gcd;

// Created at: 19-July-2026
// Last revised at: 19-July-2026
// Link: https://leetcode.com/problems/find-greatest-common-divisor-of-array/

/*
Problem Description:
--------------------
Statement:
Given an integer array nums, return the greatest common divisor (GCD) of the
smallest and largest number in the array.

Example:
Input: nums = [2,5,6,9,10]
Output: 2

Input: nums = [7,5,6,8,3]
Output: 1

Constraints:
- 2 <= nums.length <= 1000
- 1 <= nums[i] <= 1000
*/

/*
Approach 1: Sort and Find GCD

Idea:
Sort the array to obtain the smallest and largest elements, then apply the
Euclidean Algorithm to compute their GCD.

Time Complexity:
O(n log n)

Space Complexity:
O(1)

Drawbacks:
Sorting is unnecessary since only the minimum and maximum values are required.
*/

/*
Approach 2: Linear Scan + Euclidean Algorithm (Optimal)

Idea:
Traverse the array once to determine the minimum and maximum elements.
Then compute their GCD using the Euclidean Algorithm.

Time Complexity:
O(n)

Space Complexity:
O(1)

Key Insight:
Only the smallest and largest values are needed. A single traversal avoids the
extra cost of sorting.
*/

public class LC1979FindGreatestCommonDivisorOfArray {

    /*
     * Method to Solve:
     * ----------------
     * 1. Find the minimum and maximum values in the array.
     * 2. Apply the Euclidean Algorithm on these two values.
     * 3. Return the computed GCD.
     */

    /**
     * Returns the greatest common divisor of the smallest and largest
     * element in the array.
     *
     * @param nums input array
     * @return greatest common divisor
     */
    public int findGCD(int[] nums) {
        int minimum = Integer.MAX_VALUE;
        int maximum = Integer.MIN_VALUE;

        // find minimum and maximum
        for (int num : nums) {
            minimum = Math.min(minimum, num);
            maximum = Math.max(maximum, num);
        }

        return gcd(maximum, minimum);
    }

    /**
     * Computes the GCD using the Euclidean Algorithm.
     *
     * @param a first number
     * @param b second number
     * @return greatest common divisor
     */
    private int gcd(int a, int b) {

        // reduce until remainder becomes zero
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }

        return a;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)