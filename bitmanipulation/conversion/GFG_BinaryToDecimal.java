package bitmanipulation.conversion;

// Created at: 03-July-2026
// Last revised at: 03-July-2026
// Link: https://www.geeksforgeeks.org/problems/binary-number-to-decimal-number3525/1

/*
Problem Description:
--------------------
Statement:
Given a binary string, convert it into its decimal equivalent and return
the decimal value.

Example:
Input:
b = "1011"

Output:
11

Explanation:
1 × 2³ + 0 × 2² + 1 × 2¹ + 1 × 2⁰ = 11

Constraints:
- 1 <= |b| <= 30
- b contains only '0' and '1'
*/

/*
Approach 1: Positional Weight Calculation

Idea:
Traverse the binary string from right to left.
Each bit contributes its value multiplied by 2 raised to its position.
Use bit shifting to efficiently compute powers of two.

Time Complexity:
O(n)

Space Complexity:
O(1)

Key Insight:
The rightmost bit has weight 2⁰, the next bit has weight 2¹, and so on.
Bit shifting provides an efficient way to compute these powers.
*/

/*
Method to Solve:
----------------
1. Initialize the decimal value as zero.
2. Traverse the binary string from right to left.
3. Compute the positional weight using left shift.
4. Add the contribution of each bit.
5. Return the decimal value.
*/

public class GFG_BinaryToDecimal {

    /**
     * Converts a binary string to its decimal equivalent.
     *
     * @param binary input binary string
     * @return decimal value
     */
    public int binaryToDecimal(String binary) {

        int decimal = 0;
        int length = binary.length();

        for (int index = length - 1; index >= 0; index--) {

            int bit = binary.charAt(index) - '0';

            // add current bit contribution
            decimal += bit * (1 << (length - 1 - index));
        }

        return decimal;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)