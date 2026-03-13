package strings.easy;

public class LC796RotateString {
    // Created at: 14 - March - 2025
    // Last revised at: 14 - March - 2025
    // Link: https://leetcode.com/problems/rotate-string/
    // Time Complexity: O(n²)
    // Space Complexity: O(n)

    /*
     * Problem Description:
     * Given two strings s and goal, return true if and only if s can become goal
     * after some number of shifts. A shift moves the leftmost character of s to
     * the rightmost position.
     *
     * Example:
     * Input: s = "abcde", goal = "cdeab"
     * Output: true
     *
     * Input: s = "abcde", goal = "abced"
     * Output: false
     *
     * Constraints:
     * - 1 <= s.length, goal.length <= 100
     * - s and goal consist of lowercase English letters only
     *
     * -----------------------------------------------------------------------
     *
     * Approaches:
     *
     * 1. Brute Force — Simulate every rotation
     * Idea: Rotate s one step at a time (up to n times) and check if it equals
     * goal.
     * Time: O(n²) — n rotations × O(n) comparison each
     * Space: O(n) — storing the rotated string
     * Drawback: Repetitive; rebuilds string on every rotation.
     *
     * 2. [OPTIMAL] Doubled String + Manual Scan (current approach)
     * Idea: Every rotation of s is a substring of s+s. So build s+s and
     * search for goal as a contiguous substring using a manual two-pointer scan.
     * Time: O(n²) — outer scan O(2n) × inner comparison O(n) worst case
     * Space: O(n) — for the doubled StringBuilder
     * Key Insight: Doubling the string encodes all n rotations in one structure,
     * eliminating the need to reconstruct s on each shift.
     *
     * 3. Built-in Contains (cleaner O(n) on average)
     * Idea: Same doubling trick, but delegate substring search to
     * (s + s).contains(goal), which uses an optimized internal algorithm.
     * Time: O(n) average
     * Space: O(n)
     * Key Insight: Simplest expression of the doubling idea.
     */

    /**
     * Determines whether goal is a rotation of s by searching for goal
     * as a contiguous substring within the doubled string s+s.
     *
     * @param s    the original string to rotate
     * @param goal the target string to match after rotations
     * @return true if goal can be obtained by rotating s; false otherwise
     */
    public boolean rotateString(String s, String goal) {

        int len = goal.length();
        int sLen = s.length();

        // A rotation preserves length — mismatch means impossible
        if (len != sLen)
            return false;

        // Build s+s which contains every possible rotation of s as a substring
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(s);

        int i = 0;
        while (i < sb.length()) {

            // Only enter inner check when first characters match
            if (sb.charAt(i) == goal.charAt(0)) {

                int j = 0;

                // Compare goal character by character against the window starting at i
                while (j < len && j + i < sb.length()) {
                    if (sb.charAt(j + i) != goal.charAt(j))
                        break;
                    j++;
                }

                // All len characters matched — goal is a rotation of s
                if (j == len)
                    return true;
            }
            i++;
        }

        // No valid rotation window found
        return false;
    }
}
