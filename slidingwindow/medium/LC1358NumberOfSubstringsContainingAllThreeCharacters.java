package slidingwindow.medium;

// Created at: 25-April-2026
// Last revised at: 25-April-2026
// Link: https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
// Time Complexity: O(N) — two linear passes
// Space Complexity: O(1) — fixed frequency array of size 3

/*
 * Problem Description:
 * Given a string s consisting only of 'a', 'b', and 'c', return the number of
 * substrings that contain at least one occurrence of all three characters.
 *
 * Example:
 *   Input:  s = "abcabc"
 *   Output: 10
 *
 *   Input:  s = "aaacb"
 *   Output: 3   // "aaacb", "aacb", "acb"
 *
 *   Input:  s = "abc"
 *   Output: 1
 *
 * Constraints:
 *   - 3 <= s.length() <= 5 * 10^4
 *   - s consists only of 'a', 'b', 'c'
 */

/*
 * Approaches:
 *
 * 1. Brute Force
 *    Idea    : Try every substring; check if it contains all three characters
 *    Time    : O(N^2)
 *    Space   : O(1)
 *    Drawback: Too slow for N = 5 * 10^4
 *
 * 2. Last Seen Index
 *    Idea    : For each r, track the last seen index of 'a', 'b', 'c'.
 *              Every r contributes (1 + min(lastA, lastB, lastC)) valid substrings
 *              since any left boundary at or before the earliest last-seen index
 *              guarantees all three chars are present.
 *    Time    : O(N)
 *    Space   : O(1)
 *    Drawback: Less intuitive; harder to generalise beyond 3 fixed characters
 *
 * 3. ★ Sliding Window — exactlyK = atMost(K) - atMost(K-1)
 *    Idea    : "Contains all 3" = "at least 3 distinct" = "exactly 3 distinct"
 *              since the alphabet is fixed to {a, b, c}.
 *              Apply the same reduction used in LC930 and LC1248:
 *              count(distinct == 3) = count(distinct <= 3) - count(distinct <= 2).
 *              Helper tracks distinct char count via a frequency array of size 3.
 *    Time    : O(N)
 *    Space   : O(1)
 *    Key Insight: differentCharCnt increments only when a char's frequency crosses
 *                 0 → 1, and decrements only when it drops from 1 → 0. This makes
 *                 distinct-count tracking O(1) per pointer move without rescanning.
 */

public class LC1358NumberOfSubstringsContainingAllThreeCharacters {

    /*
     * Helper: count substrings with at most k distinct characters from {a, b, c}.
     * Expand r; track frequency per char and distinct count.
     * When distinct > k, shrink from left until window is valid again.
     * Each valid r contributes (r - l + 1) substrings ending at r.
     */

    /**
     * Counts substrings containing at most k distinct characters.
     *
     * @param s input string consisting only of 'a', 'b', 'c'
     * @param k upper bound on distinct character count (inclusive)
     * @return number of substrings with distinct char count in [0, k]
     */
    private int substringWithAtMostKChar(String s, int k) {

        int len = s.length();
        if (len == 0)
            return 0;

        int[] frequency = new int[3]; // index 0→'a', 1→'b', 2→'c'
        int l = 0, r = 0;
        int cnt = 0;
        int differentCharCnt = 0;

        while (r < len) {
            int ch = s.charAt(r) - 'a';

            // first occurrence of this char in the window
            if (frequency[ch] == 0)
                differentCharCnt++;
            frequency[ch]++;

            // shrink until distinct count is within k
            while (l <= r && differentCharCnt > k) {
                int lc = s.charAt(l) - 'a';
                if (frequency[lc] == 1)
                    differentCharCnt--; // last occurrence leaving the window
                frequency[lc]--;
                l++;
            }

            // every subarray ending at r with left boundary in [l, r] is valid
            if (differentCharCnt <= k)
                cnt += r - l + 1;

            r++;
        }

        return cnt;
    }

    /*
     * Method to solve:
     * "At least 3 distinct" = "exactly 3 distinct" for this alphabet.
     * exactlyK(3) = atMost(3) - atMost(2)
     */

    /**
     * Returns the count of substrings containing all three characters 'a', 'b', and
     * 'c'.
     *
     * @param s input string consisting only of 'a', 'b', 'c'
     * @return number of substrings containing at least one of each character
     */
    public int numberOfSubstrings(String s) {

        if (s.length() <= 2)
            return 0;

        return substringWithAtMostKChar(s, 3)
                - substringWithAtMostKChar(s, 2);
    }
}
