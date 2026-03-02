package binarysearch.onAnswers;

public class LC69SquareRoot {

    // Created at: 3 march 2026
    // Last revised at: 3 march 2026

    // link :https://leetcode.com/problems/sqrtx/description/

    /*
     * Problem Description -> statement, example, constraints
     * 69. Sqrt(x)
     * Solved
     * Easy
     * Topics
     * premium lock icon
     * Companies
     * Hint
     * Given a non-negative integer x, return the square root of x rounded down to
     * the nearest integer. The returned integer should be non-negative as well.
     * 
     * You must not use any built-in exponent function or operator.
     * 
     * For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
     * 
     * 
     * Example 1:
     * 
     * Input: x = 4
     * Output: 2
     * Explanation: The square root of 4 is 2, so we return 2.
     * Example 2:
     * 
     * Input: x = 8
     * Output: 2
     * Explanation: The square root of 8 is 2.82842..., and since we round it down
     * to the nearest integer, 2 is returned.ex
     * 
     * 
     * Constraints:
     * 
     * 0 <= x <= 231 - 1
     */

    // Method to solve the Problem

    /**
     * 
     * @param v Integer value to check whether square is less than or equals to "x"
     * @param x value to compare
     * @return false if square of "v" is greater than "x"
     */
    boolean isValid(int v, int x) {
        return (long) v * v <= (long) x;
    }

    /**
     * 
     * @param x Value that needs sqrt
     * @return
     */
    public int mySqrt(int x) {

        int low = 0;
        int high = x;

        int sqrt = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // if mid is a candidate of sqrt of x then store it and check for bigger value
            if (isValid(mid, x)) {
                sqrt = mid;
                low = mid + 1;
            } else {
                // mid is not a candidate search for a smaller value
                high = mid - 1;
            }
        }
        return sqrt;
    }

    // Time Complexity: O(log(x)) + consatnt isValid() method
    // SPace Complexity: constant
}
