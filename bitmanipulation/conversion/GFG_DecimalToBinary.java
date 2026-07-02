package bitmanipulation.conversion;

// Created at: 03-July-2026
// Last revised at: 03-July-2026
// Link: https://www.geeksforgeeks.org/problems/decimal-to-binary-1587115620/1

/*
Problem Description:
--------------------
Statement:
Given a decimal number n, convert it into its binary representation and
return it as a string.

Example:
Input:
n = 10

Output:
1010

Explanation:
10 in decimal is represented as 1010 in binary.

Constraints:
- 0 <= n <= 10^9
*/

/*
Approach 1: Repeated Division by 2

Idea:
Repeatedly divide the number by 2 and store the remainder.
The remainders are obtained from least significant bit to most significant bit,
so reverse the collected digits before returning.

Time Complexity:
O(log n)

Space Complexity:
O(log n)

Key Insight:
Each remainder represents one binary digit. Reversing the sequence produces
the final binary representation.
*/

/*
Method to Solve:
----------------
1. Handle the special case when n is 0.
2. Repeatedly divide n by 2.
3. Store each remainder.
4. Reverse the collected digits.
5. Return the binary string.
*/

public class GFG_DecimalToBinary {

    /**
     * Converts a decimal number to its binary representation.
     *
     * @param n decimal number
     * @return binary representation as a string
     */
    public String decToBinary(int n) {

        if (n == 0) {
            return "0";
        }

        StringBuilder binary = new StringBuilder();

        while (n > 0) {

            // store current bit
            binary.append(n % 2);

            n /= 2;
        }

        return binary.reverse().toString();
    }
}

// Time Complexity: O(log n)
// Space Complexity: O(log n)