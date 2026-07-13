package math.enumeration;

// Created at: 14-July-2026
// Last revised at: 14-July-2026
// Link: https://leetcode.com/problems/sequential-digits/

/*
Problem Description:
--------------------
Statement:
Given two integers low and high, return a sorted list of all integers in the
inclusive range [low, high] whose digits are sequential.

A sequential digit number is a number where every digit is exactly one greater
than the previous digit.

Example:
Input:
low = 100
high = 300

Output:
[123, 234]

Constraints:
10 <= low <= high <= 10^9
*/

/*
Approach 1: Enumerate Sequential Numbers

Idea:
Instead of checking every number in the given range, generate only valid
sequential digit numbers.

Treat the sequence 123456789 as the source. For each possible digit length,
start with the first sequential number (12, 123, 1234, ...), then slide the
window numerically by removing the leading digit and appending the next digit.

Since there are only 36 possible sequential numbers, every candidate can be
generated in constant time.

Time Complexity:
O(1)

Space Complexity:
O(1)

Key Insight:
Generate only valid candidates instead of searching the entire range.
*/

import java.util.ArrayList;
import java.util.List;

public class LC1291SequentialDigits {

    /**
     * Returns the number of digits in the given integer.
     *
     * @param num input number
     * @return number of digits
     */
    private int getLength(int num) {
        int length = 0;

        while (num != 0) {
            length++;
            num /= 10;
        }

        return length;
    }

    /*
     * Method to Solve:
     * ----------------
     * 1. Determine the digit lengths of low and high.
     * 2. Generate sequential numbers for every valid length.
     * 3. Slide the numeric window by removing the leading digit.
     * 4. Append the next digit.
     * 5. Store numbers that lie within the given range.
     */

    // Time Complexity: O(1)
    // Space Complexity: O(1)

    /**
     * Returns all sequential digit numbers within the given range.
     *
     * @param low  lower bound
     * @param high upper bound
     * @return list of sequential digit numbers
     */
    public List<Integer> sequentialDigits(int low, int high) {

        int[] starts = {
                0,
                1,
                12,
                123,
                1234,
                12345,
                123456,
                1234567,
                12345678,
                123456789
        };

        int[] divisors = {
                1,
                1,
                10,
                100,
                1000,
                10000,
                100000,
                1000000,
                10000000,
                100000000
        };

        int[] digits = {
                1, 2, 3, 4, 5, 6, 7, 8, 9
        };

        int lowLength = getLength(low);
        int highLength = Math.min(getLength(high), 9);

        List<Integer> result = new ArrayList<>();

        for (int length = lowLength; length <= highLength; length++) {

            int number = starts[length];
            int divisor = divisors[length];

            if (number >= low && number <= high) {
                result.add(number);
            }

            // slide the numeric window
            for (int digit = length; digit < 9; digit++) {

                number %= divisor;
                number *= 10;
                number += digits[digit];

                if (number >= low && number <= high) {
                    result.add(number);
                }
            }
        }

        return result;
    }
}