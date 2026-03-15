package strings.medium;

public class LC5LongestPalindromicSubstring {
    // Created at: 16 - March - 2026
    // Last revised at: 16 - March - 2026
    // Link: https://leetcode.com/problems/longest-palindromic-substring/
    // Time Complexity: O(n²)
    // Space Complexity: O(1)

    /*
     * Problem Description:
     * Given a string s, return the longest palindromic substring in s.
     *
     * Example:
     * Input: s = "babad"
     * Output: "bab" (or "aba" — both are valid)
     *
     * Input: s = "cbbd"
     * Output: "bb"
     *
     * Constraints:
     * - 1 <= s.length <= 1000
     * - s consists of only digits and English letters.
     *
     * -----------------------------------------------------------------------
     * Approaches:
     *
     * Approach 1 — Brute Force
     * Idea: Generate all O(n²) substrings and check each one for being a
     * palindrome in O(n) time.
     * Time: O(n³)
     * Space: O(1)
     * Drawbacks: Redundant work; too slow for n = 1000.
     *
     * Approach 2 — Expand Around Center ✅ OPTIMAL
     * Idea: Every palindrome has a center — either a single character (odd
     * length) or a gap between two equal characters (even length).
     * For each index i, expand outward in both directions as long as
     * characters match, tracking the longest window found.
     * Time: O(n²) — n centers, each expanding at most O(n)
     * Space: O(1)
     * Key Insight: Reusing left/right pointers per center avoids re-scanning
     * characters, cutting the cubic brute-force down to quadratic.
     *
     * Approach 3 — Manacher's Algorithm
     * Idea: Preprocess the string with sentinel characters so every palindrome
     * becomes odd-length, then use previously computed radii to skip
     * redundant expansions.
     * Time: O(n)
     * Space: O(n)
     * Drawbacks: Complex to implement correctly in an interview setting.
     */

    /*
     * Method to solve (Expand Around Center):
     *
     * 1. Iterate over every index i as a potential palindrome center.
     *
     * 2. For EVEN-length palindromes (center is a gap):
     * - Set left = i - 1, right = i (the two characters that form the center pair).
     * - Guard with `if (i > 0)` to avoid an out-of-bounds left index.
     * - Expand while s[left] == s[right]; decrement left, increment right.
     * - After the loop, the palindrome occupies indices [left+1 .. right-1],
     * so its length = right - left - 1.
     *
     * 3. For ODD-length palindromes (center is the character at i):
     * - Set left = i - 1, right = i + 1.
     * - Expand while s[left] == s[right]; same logic as above.
     * - Length formula stays right - left - 1 (same derivation).
     *
     * 4. After each expansion, update maxLen, start, and end if the current
     * palindrome is longer than the best seen so far.
     * - start = left + 1 (first index inside the palindrome)
     * - end = right (exclusive end, ready for substring())
     *
     * 5. Return s.substring(start, end).
     */

    /**
     * Finds the longest palindromic substring in the given string
     * using the expand-around-center technique.
     *
     * @param s the input string (1 <= s.length <= 1000)
     * @return the longest substring of s that reads the same forwards and backwards
     */
    public String longestPalindrome(String s) {

        int len = s.length();
        int maxLen = -1;
        int start = 0;
        int end = 0;

        for (int i = 0; i < len; i++) {
            int left, right;

            // Even-length palindrome: center gap between (i-1) and i
            if (i > 0) {
                left = i - 1;
                right = i;
                while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                    left--;
                    right++;
                }
                if (right - left - 1 > maxLen) {
                    maxLen = right - left - 1;
                    start = left + 1;
                    end = right;
                }
            }

            // Odd-length palindrome: center at i
            left = i - 1;
            right = i + 1;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if (right - left - 1 > maxLen) {
                maxLen = right - left - 1;
                start = left + 1;
                end = right;
            }
        }

        return s.substring(start, end);
    }
}
