package math.easy;

// Created at: 08-July-2026
// Last revised at: 08-July-2026
// Link: https://leetcode.com/problems/sum-and-multiply/

/*
Problem Description:
--------------------
Statement:
Given a positive integer n, remove all zero digits while preserving the order of
the remaining digits to form a new integer x. Also compute the sum of all
non-zero digits. Return the product x * sum.

Example:
Input:
n = 10520

Output:
1560

Explanation:
Non-zero digits form x = 152.
Sum of non-zero digits = 1 + 5 + 2 = 8.
Answer = 152 * 8 = 1216.

Constraints:
- 1 <= n <= 10^9
*/

/*
Approach 1: String Simulation

Idea:
Convert the number into a string, skip every '0', build the new number, compute
the digit sum, and return their product.

Time Complexity:
O(d)

Space Complexity:
O(d)

Drawbacks:
Requires extra memory for string conversion.
*/

/*
Approach 2: Digit Processing (Optimal)

Idea:
Traverse the digits from right to left.
Whenever a non-zero digit is found:
- Add it to the reconstructed number.
- Add it to the digit sum.
- Update the decimal multiplier.
Finally return reconstructedNumber × digitSum.

Time Complexity:
O(d)

Space Complexity:
O(1)

Key Insight:
Processing digits directly avoids string conversion while naturally preserving
the order of the remaining digits using a decimal multiplier.
*/

/*
Method to Solve:
----------------
1. Initialize reconstructed number, multiplier, and digit sum.
2. Extract digits using modulo.
3. Ignore zero digits.
4. Build the new number and accumulate the digit sum.
5. Return their product.
*/

public class LC3754SumAndMultiply {

    /**
     * Removes zero digits, computes the sum of remaining digits,
     * and returns their product.
     *
     * @param n input integer
     * @return product of reconstructed number and digit sum
     */
    public long sumAndMultiply(int n) {

        long reconstructedNumber = 0;
        long multiplier = 1;
        int digitSum = 0;

        while (n > 0) {

            int digit = n % 10;

            // skip zero digits
            if (digit > 0) {
                reconstructedNumber += multiplier * digit;
                digitSum += digit;
                multiplier *= 10;
            }

            n /= 10;
        }

        return reconstructedNumber * digitSum;
    }
}

// Time Complexity: O(d)
// Space Complexity: O(1)