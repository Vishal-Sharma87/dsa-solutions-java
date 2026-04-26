package slidingwindow.hard;

// Created at: 27 - April - 2025
// Last revised at: 27 - April - 2025
// Problem: https://leetcode.com/problems/minimum-window-substring/
// Time Complexity: O(n + m)  where n = s.length(), m = t.length()
// Space Complexity: O(1)     fixed 58-size frequency array (A-z range)

/*
 * Problem Description:
 * Given two strings s and t, return the minimum window substring of s such that
 * every character in t (including duplicates) is included in the window.
 * If no such window exists, return "".
 *
 * Example:
 *   Input:  s = "ADOBECODEBANC", t = "ABC"
 *   Output: "BANC"
 *
 * Constraints:
 *   - 1 <= s.length, t.length <= 10^5
 *   - s and t consist of uppercase and lowercase English letters
 *
 * Approaches:
 *
 * 1. Brute Force — check all substrings of s
 *    Idea:    For every (i, j) pair, check if substring contains all chars of t
 *    Time:    O(n^2 * m)
 *    Space:   O(1)
 *    Drawback: Way too slow
 *
 * 2. Sliding Window — standard matched counter
 *    Idea:    Track how many distinct chars from t are fully satisfied using a
 *             separate `matched` counter. Expand r until matched == distinct(t),
 *             then shrink l as long as window stays valid.
 *    Time:    O(n + m)
 *    Space:   O(1)
 *
 * ★ 3. Sliding Window — dual-purpose frequency array
 *    Idea:    Same window logic but frequency[c] encodes three states:
 *               > 0 → still needed from t
 *               = 0 → exactly satisfied (boundary — shrinking past this breaks window)
 *               < 0 → excess or not in t at all
 *             charCnt tracks how many chars still have frequency > 0.
 *             charCnt == 0 means all of t is covered.
 *             Shrink l as long as frequency[cl] != 0 (i.e. it's excess, safe to drop).
 *    Time:    O(n + m)
 *    Space:   O(1)
 *    Key Insight: No separate matched counter needed — the frequency sign itself
 *                 tells you whether removing a char from the window is safe.
 */

class LC76MinimumWindowSubstring {

    /**
     * Finds the minimum window substring of s containing all characters of t.
     *
     * @param s the source string to search within
     * @param t the target string whose characters must all be present
     * @return minimum window substring, or "" if none exists
     */
    public String minWindow(String s, String t) {

        int sLen = s.length();
        int tLen = t.length();

        if (sLen < tLen)
            return "";

        // offset by 'A' covers A-Z (0-25) and a-z (32-57) within 58 slots
        int[] frequency = new int[58];
        int charCnt = 0;

        for (char ch : t.toCharArray()) {
            int c = ch - 'A';
            frequency[c]++;
            if (frequency[c] == 1)
                charCnt++; // new distinct char
        }

        int start = -1, end = -1;
        int l = 0, r = 0;
        int minLen = sLen + 1;

        while (r < sLen) {
            int cr = s.charAt(r) - 'A';

            // going 1→0 means this char just got fully satisfied
            if (frequency[cr] == 1)
                charCnt--;
            frequency[cr]--;

            // shrink l while the leftmost char is excess (freq < 0)
            while (charCnt == 0 && l <= r) {
                int cl = s.charAt(l) - 'A';
                if (frequency[cl] == 0)
                    break; // boundary char — stop
                frequency[cl]++;
                l++;
            }

            if (charCnt == 0 && r - l + 1 < minLen) {
                minLen = r - l + 1;
                start = l;
                end = r;
            }

            r++;
        }

        return start == -1 ? "" : s.substring(start, end + 1);
    }
}