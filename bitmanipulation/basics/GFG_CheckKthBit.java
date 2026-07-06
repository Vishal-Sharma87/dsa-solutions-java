package bitmanipulation.basics;

// Created at: 06-July-2026
// Last revised at: 06-July-2026
// Link: https://www.geeksforgeeks.org/problems/check-whether-k-th-bit-is-set-or-not-1587115620/1

/*
Problem Description:
--------------------

Statement:
Given an integer n and an integer k, determine whether the k-th bit
(0-based indexing from the right) of n is set.

Return true if the bit is set; otherwise return false.

Example:
Input:
n = 4, k = 2

Output:
true

Explanation:
Binary representation of 4 is 100. The 2nd bit is set.

Constraints:

* 0 <= n <= 10^9
* 0 <= k < 32
  */

/*
Approach 1: Brute Force

Idea:
Convert the number into its binary representation and inspect the k-th bit.

Time Complexity:
O(log n)

Space Complexity:
O(log n)

Drawbacks:
Requires additional storage for the binary representation.
*/

/*
Approach 2: Bit Manipulation (Implemented)

Idea:
Right shift the number by k positions so that the required bit becomes
the least significant bit. Mask it using 1 and compare the result.

Time Complexity:
O(1)

Space Complexity:
O(1)

Key Insight:
(n >> k) & 1 extracts the value of the k-th bit directly.
*/

/*
Method to Solve:
----------------

1. Right shift the number by k positions.
2. Extract the least significant bit using '& 1'.
3. Return true if the extracted bit is 1.
   */

public class GFG_CheckKthBit {

    /**
     * Checks whether the k-th bit of a number is set.
     *
     * @param number   input number
     * @param bitIndex zero-based bit position
     * @return true if the bit is set, otherwise false
     */
    public boolean checkKthBit(int number, int bitIndex) {
        return ((number >> bitIndex) & 1) == 1;
    }

}

// Time Complexity: O(1)
// Space Complexity: O(1)
