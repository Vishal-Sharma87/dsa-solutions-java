package strings.easy;

public class LC1903LargestOddNumInString {
    // Created at: 14 - March - 2026
    // Last revised at: 14 - March - 2026
    // Link: https://leetcode.com/problems/largest-odd-number-in-a-string/
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    /*
     * PROBLEM DESCRIPTION:
     * Given a string `num` representing a large integer, return the largest-valued
     * odd integer (as a string) that is a non-empty prefix of `num`, or an empty
     * string "" if no odd integer exists.
     *
     * A prefix of a string is a leading portion of it.
     *
     * Example:
     * Input: num = "52"
     * Output: "5"
     *
     * Input: num = "4206"
     * Output: ""
     *
     * Input: num = "35427"
     * Output: "35427"
     *
     * Constraints:
     * - 1 <= num.length <= 10^5
     * - num only consists of digits and has no leading zeros (except "0" itself)
     *
     * ============================================================
     * APPROACHES:
     *
     * Approach 1 — Brute Force (Check all prefixes):
     * Idea : Generate all prefixes from longest to shortest, return the first
     * one whose last digit is odd.
     * Time : O(n²) — n prefixes, each substring call is O(n)
     * Space : O(n) — substring storage
     * Drawback: Unnecessary work; we only care about the last digit of each prefix,
     * not the entire prefix content.
     *
     * ★ Approach 2 — Single Right-to-Left Scan (Optimal):
     * Idea : A prefix is odd if and only if its last character is an odd digit.
     * Scan from the rightmost character leftward; the first odd digit we
     * encounter marks the longest valid odd prefix — return it immediately.
     * Time : O(n) — single pass in the worst case
     * Space : O(1) — no extra storage (substring is part of the output)
     * Key Insight: We don't need to inspect the prefix itself at all — only its
     * last character determines parity.
     * ============================================================
     */

    /**
     * Finds the largest odd-valued prefix of the given numeric string.
     *
     * @param num a non-empty string of digits with no leading zeros
     * @return the longest prefix of {@code num} whose last digit is odd,
     *         or an empty string if no such prefix exists
     */
    public String largestOddNumber(String num) {
        int len = num.length();

        // start from the last character and move left
        int i = len - 1;

        while (i >= 0) {
            // check if the digit at position i is odd
            if ((num.charAt(i) - '0') % 2 == 1)
                // first odd digit from the right → longest odd prefix ends here
                return num.substring(0, i + 1);

            // digit is even, shrink the prefix by one
            i--;
        }

        // no odd digit found anywhere → no valid odd prefix exists
        return "";
    }
}
