package slidingwindow.hard;

// Created at: 26 - April - 2025

// Last revised at: 26 - April - 2025
// Problem: https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1
// Time Complexity: O(n)
// Space Complexity: O(1)

/*
 * Problem Description:
 * Given a string s and an integer k, find the length of the longest substring
 * that contains exactly k unique characters. If no such substring exists, return -1.
 *
 * Example:
 *   Input:  s = "aabacbebebe", k = 3
 *   Output: 7
 *   Explanation: "cbebebe" has exactly 3 unique characters and length 7
 *
 * Constraints:
 *   - 1 <= s.length() <= 10^5
 *   - 1 <= k <= 26
 *   - s contains only lowercase English letters
 *
 * Approaches:
 *
 * 1. Brute Force — check all substrings
 *    Idea:    For every pair (i, j), count distinct chars and track max length
 *    Time:    O(n^2)
 *    Space:   O(1)
 *    Drawback: Too slow for large inputs
 *
 * ★ 2. Sliding Window — shrink from left when distinct count exceeds k
 *    Idea:    Expand r freely; when charCnt > k, shrink l until charCnt == k.
 *             Update max length only when window has exactly k distinct chars.
 *    Time:    O(n)
 *    Space:   O(1) — fixed 26-size frequency array
 *    Key Insight: Inner while handles multi-step shrinking cleanly without
 *                 misstepping r like a single-shrink-per-iteration approach would
 */

class GFG_LongestSubstringWithKUniques {

    /**
     * Returns the length of the longest substring with exactly k unique characters.
     *
     * @param s the input string
     * @param k number of unique characters required
     * @return length of longest valid substring, or -1 if none exists
     */
    public int longestKSubstr(String s, int k) {

        int len = s.length();
        if (len == 0 || len < k)
            return -1;

        int l = 0, r = 0;
        int[] frequency = new int[26];
        int charCnt = 0;
        int mxLen = -1;

        while (r < len) {
            int cr = s.charAt(r) - 'a';
            if (frequency[cr] == 0)
                charCnt++;
            frequency[cr]++;

            // shrink until window has at most k distinct chars
            while (l <= r && charCnt > k) {
                int cl = s.charAt(l) - 'a';
                if (frequency[cl] == 1)
                    charCnt--;
                frequency[cl]--;
                l++;
            }

            if (charCnt == k)
                mxLen = Math.max(mxLen, r - l + 1);
            r++;
        }

        return mxLen;
    }
}