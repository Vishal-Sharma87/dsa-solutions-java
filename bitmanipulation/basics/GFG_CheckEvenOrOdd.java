package bitmanipulation.basics;

// Created at: 06-July-2026
// Last revised at: 06-July-2026
// Link: https://www.geeksforgeeks.org/problems/check-if-a-number-is-even-or-odd1108/1

/*
Problem Description:
--------------------

Statement:
Given an integer n, determine whether it is even or odd.

Return true if the number is even; otherwise return false.

Example:
Input:
n = 8

Output:
true

Explanation:
8 is divisible by 2, so it is an even number.

Constraints:

* -10^9 <= n <= 10^9
  */

/*
Approach 1: Modulus Operator

Idea:
Check whether the remainder after dividing the number by 2 is zero.

Time Complexity:
O(1)

Space Complexity:
O(1)

Drawbacks:
Uses arithmetic division, whereas the least significant bit directly
indicates parity.
*/

/*
Approach 2: Bit Manipulation (Implemented)

Idea:
The least significant bit determines whether a number is even or odd.

* Even numbers end with 0 in binary.
* Odd numbers end with 1 in binary.

Mask the least significant bit using '& 1'.

Time Complexity:
O(1)

Space Complexity:
O(1)

Key Insight:
If (n & 1) equals 0, the number is even; otherwise it is odd.
*/

/*
Method to Solve:
----------------

1. Extract the least significant bit using '& 1'.
2. If the result is 0, return true.
3. Otherwise return false.
   */

public class GFG_CheckEvenOrOdd {

    /**
     * Checks whether a number is even.
     *
     * @param number input number
     * @return true if the number is even, otherwise false
     */
    public boolean isEven(int number) {
        return (number & 1) == 0;
    }

}

// Time Complexity: O(1)
// Space Complexity: O(1)
