package bitmanipulation.basics;

// Created at: 08-July-2026
// Last revised at: 08-July-2026
// Link: https://www.geeksforgeeks.org/problems/set-the-rightmost-unset-bit4436/1

/*
Problem Description:
--------------------
Statement:
Given a positive integer n, set its rightmost unset bit and return the modified
number. If all bits are already set, return the original number.

Example:
Input:
n = 6

Output:
7

Explanation:
6 = 110
The rightmost unset bit is the least significant bit.
After setting it:
111 = 7

Constraints:
- 1 <= n <= 10^9
*/

/*
Approach 1: Brute Force

Idea:
Check each bit from the least significant bit until the first unset bit is
found. Set that bit and return the resulting number.

Time Complexity:
O(number of bits)

Space Complexity:
O(1)

Drawbacks:
Requires checking every bit one by one.
*/

/*
Approach 2: Trailing Ones Traversal (Optimal)

Idea:
1. Count the trailing consecutive set bits.
2. The first bit after these trailing ones is the rightmost unset bit.
3. Build its mask using powers of two.
4. Set the bit using the OR operation.

Time Complexity:
O(number of trailing 1s)

Space Complexity:
O(1)

Key Insight:
Every trailing 1 must be skipped because they are already set. The first 0
encountered is exactly the bit that needs to be updated.
*/

/*
Method to Solve:
----------------
1. Copy the number into a temporary variable.
2. Skip all trailing set bits.
3. Build the mask for the first unset bit.
4. Set the bit using OR.
5. Return the updated number.
*/

public class GFG_SetTheRightmostUnsetBit {

    /**
     * Sets the rightmost unset bit of the given number.
     *
     * @param n input number
     * @return number after setting the rightmost unset bit
     */
    public int setBit(int n) {

        int temp = n;
        int mask = 1;

        // skip trailing set bits
        while ((temp & 1) == 1) {
            mask <<= 1;
            temp >>= 1;
        }

        // set the rightmost unset bit
        return n | mask;
    }
}

// Time Complexity: O(number of trailing 1s)
// Space Complexity: O(1)
