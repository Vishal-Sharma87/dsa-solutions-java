package recursion.basics;

public class LC8_AtoiRecursion {
    // Created at: 27 - March - 2026
    // Last revised at: 27 - March - 2026
    // LeetCode link: https://leetcode.com/problems/string-to-integer-atoi/
    // Time Complexity: O(n)
    // Space Complexity: O(d), where d = number of digit characters processed (call
    // stack depth)

    /*
     * Problem Description:
     * Implement the myAtoi(String s) function, which converts a string to a 32-bit
     * signed integer (similar to C/C++'s atoi function).
     *
     * The algorithm:
     * 1. Skip leading whitespace.
     * 2. Check for an optional '+' or '-' sign.
     * 3. Read digits until a non-digit or end of string.
     * 4. Clamp the result to [Integer.MIN_VALUE, Integer.MAX_VALUE].
     *
     * Example:
     * Input: s = "   -42abc"
     * Output: -42
     *
     * Input: s = "2147483648"
     * Output: 2147483647 (clamped to MAX_VALUE)
     *
     * Constraints:
     * - 0 <= s.length <= 200
     * - s consists of English letters, digits, ' ', '+', '-', '.'
     *
     * -----------------------------------------------------------------------
     * Approaches:
     *
     * 1. Iterative
     * Idea: Use a while loop to process digits one at a time, checking for
     * overflow before each multiplication. O(n) time, O(1) space.
     * Drawback: Does not satisfy the recursion practice constraint.
     *
     * ★ 2. Recursive (implemented)
     * Idea: After preprocessing (whitespace, sign, leading zeros) in myAtoi,
     * delegate digit accumulation to recursiveAtoi. Each call processes
     * one character and passes the updated accumulator forward.
     * Base cases: end of string, or a non-digit character encountered.
     * Overflow is checked BEFORE accumulating, so the clamp fires
     * before any integer overflow can occur in Java.
     * Time: O(n) — each character visited at most once
     * Space: O(d) — call stack depth equals number of digits processed;
     * bounded by ~10 in practice (32-bit int), so effectively O(1)
     * Key Insight: Guard overflow BEFORE computing (ans * 10 + digit), not
     * after. Since ans == MAX/10 is the tipping point, the
     * incoming digit alone determines whether to clamp.
     */

    /**
     * Recursively accumulates digit characters into an integer magnitude,
     * stopping at the first non-digit character or end of string.
     * Overflow is clamped to Integer.MIN_VALUE or Integer.MAX_VALUE
     * before it can occur.
     *
     * @param s     the original input string
     * @param i     current index being processed
     * @param isNeg true if the number is negative (used for precise clamp boundary)
     * @param ans   accumulated magnitude so far
     * @return the final clamped magnitude (always non-negative)
     */
    public int recursiveAtoi(String s, int i, boolean isNeg, int ans) {

        // base case: past end of string
        if (i >= s.length())
            return ans;

        // extract current digit
        int digit = s.charAt(i) - '0';

        // base case: non-digit character encountered, stop parsing
        if (!(digit >= 0 && digit <= 9))
            return ans;

        // overflow pre-check: ans * 10 would exceed MAX_VALUE regardless of digit
        if (ans > Integer.MAX_VALUE / 10)
            return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        // overflow pre-check: ans * 10 is exactly at the boundary, digit decides
        if (ans == Integer.MAX_VALUE / 10) {
            if (isNeg && digit >= 8) // -2147483648 is valid, -2147483658 is not
                return Integer.MIN_VALUE;
            if (!isNeg && digit >= 7) // 2147483647 is valid, 2147483657 is not
                return Integer.MAX_VALUE;
        }

        // safe to accumulate: shift left and add current digit
        ans = ans * 10 + digit;

        // recurse on the next character
        ans = recursiveAtoi(s, i + 1, isNeg, ans);

        return ans;
    }

    /**
     * Converts a string to a 32-bit signed integer by preprocessing the input
     * (trimming whitespace, detecting sign, skipping leading zeros), then
     * delegating digit parsing to recursiveAtoi.
     *
     * @param s the input string to convert
     * @return the parsed integer clamped to [Integer.MIN_VALUE, Integer.MAX_VALUE]
     */
    public int myAtoi(String s) {

        // guard against null or empty input
        if (s == null || s.isEmpty())
            return 0;

        int len = s.length();
        int i = 0;

        // skip leading whitespace
        while (i < len && s.charAt(i) == ' ')
            i++;

        // detect optional sign character
        boolean isNeg = false;
        if (i < len && s.charAt(i) == '-') {
            isNeg = true;
            i++;
        } else if (i < len && s.charAt(i) == '+') {
            i++;
        }

        // skip leading zeros (they don't affect value, reduce recursion depth)
        while (i < len && s.charAt(i) == '0')
            i++;

        // parse digits recursively, accumulating magnitude
        int ans = recursiveAtoi(s, i, isNeg, 0);

        // apply sign; skip negation if already clamped to MIN_VALUE
        // (MIN_VALUE * -1 overflows back to MIN_VALUE in Java)
        if (isNeg && ans != Integer.MIN_VALUE)
            return ans * -1;

        return ans;
    }
}
