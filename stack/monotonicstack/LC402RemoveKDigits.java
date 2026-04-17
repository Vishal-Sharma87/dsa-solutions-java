package stack.monotonicstack;

// Created at: 18-April-2025
// Last revised at: 18-April-2025
// https://leetcode.com/problems/remove-k-digits/

/*
 * Problem Description:
 * Given a string `num` representing a non-negative integer and an integer `k`,
 * remove exactly k digits from the number to make the result as small as possible.
 * Return the result as a string. No leading zeros allowed (except "0" itself).
 *
 * Example:
 *   Input:  num = "1432219", k = 3
 *   Output: "1219"
 *
 * Constraints:
 *   - 1 <= k <= num.length <= 10^5
 *   - num consists only of digits
 *   - num does not have leading zeros (except "0" itself)
 */

/*
 * Approaches:
 *
 * 1. Stack (Monotonic) ★
 *    Idea: Maintain a monotonically non-decreasing stack. For each digit, pop
 *          anything larger sitting on top while we still have removals left.
 *          This greedily ensures the smallest prefix at every position.
 *    Time:  O(n)
 *    Space: O(n)
 *
 * 2. StringBuilder as Implicit Stack ★ (implemented below)
 *    Idea: Same greedy logic, but use a StringBuilder directly as the stack —
 *          the last character is the "top". Avoids reversing at the end.
 *    Time:  O(n)
 *    Space: O(n)
 *    Key Insight: Both approaches are the same algorithm. StringBuilder just
 *                 saves a reversal step since we read left-to-right naturally.
 */

// Time Complexity:  O(n) — each digit is appended and deleted at most once
// Space Complexity: O(n) — StringBuilder holds up to n characters

public class LC402RemoveKDigits {

    /**
     * Removes k digits from {@code num} to produce the smallest possible number.
     *
     * @param num the non-negative integer as a string
     * @param k   number of digits to remove
     * @return the smallest number achievable as a string, without leading zeros
     */
    public String removeKDigits(String num, int k) {
        int len = num.length();

        // removing all digits (or more) → just return "0"
        if (k >= len)
            return "0";

        StringBuilder curr = new StringBuilder();

        for (char ch : num.toCharArray()) {
            // pop larger digits from the "top" while we can still remove
            while (k > 0 && curr.length() > 0 && curr.charAt(curr.length() - 1) > ch) {
                curr.deleteCharAt(curr.length() - 1);
                k--;
            }

            curr.append(ch);
        }

        // num was non-decreasing — remove from the tail (largest end)
        while (k > 0 && curr.length() > 0) {
            curr.deleteCharAt(curr.length() - 1);
            k--;
        }

        // skip leading zeros
        int i = 0;
        while (i < curr.length() && curr.charAt(i) == '0')
            i++;

        // all zeros
        if (i == curr.length())
            return "0";

        return curr.substring(i);
    }
}