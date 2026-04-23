package slidingwindow.medium;

// Created at: 24-April-2026
// Last revised at: 24-April-2026
// Link: https://leetcode.com/problems/longest-repeating-character-replacement/
// Time Complexity: O(N) — inner freq scan is O(26), treated as constant
// Space Complexity: O(1) — fixed 26-size frequency array

/*
 * Problem Description:
 * Given a string s of uppercase English letters and an integer k, you can replace
 * at most k characters in the string with any uppercase letter. Return the length
 * of the longest substring containing the same letter after performing the replacements.
 *
 * Example:
 *   Input:  s = "AABABBA", k = 1
 *   Output: 4   // replace one 'B' → "AABA" or "ABBA" → 4 consecutive same chars
 *
 *   Input:  s = "ABAB", k = 2
 *   Output: 4   // replace both 'B's → "AAAA"
 *
 * Constraints:
 *   - 1 <= s.length() <= 10^5
 *   - s consists of only uppercase English letters
 *   - 0 <= k <= s.length()
 */

/*
 * Approaches:
 *
 * 1. Brute Force
 *    Idea    : Try every substring; for each, count max frequency char.
 *              Valid if (windowSize - maxFreq) <= k
 *    Time    : O(N^2 * 26)
 *    Space   : O(1)
 *    Drawback: Way too slow for N = 10^5
 *
 * 2. ★ Sliding Window + freq array (shrinking window)
 *    Idea    : Expand r; maintain charFreq[] for the window. A window is valid if
 *              (windowSize - maxFreq) <= k — meaning we only need to replace the
 *              non-dominant characters, and their count fits within k.
 *              On violation, shrink from left by 1 and recompute maxFreq.
 *    Time    : O(26N) → O(N) since 26 is constant
 *    Space   : O(1)
 *    Drawback: Recomputes maxFreq via full O(26) scan on every iteration
 *
 * 3. Sliding Window + freq array (fixed-size slide, no shrink)
 *    Idea    : Same validity check, but instead of shrinking on violation, slide
 *              the window forward (l++ alongside r++) — window never collapses.
 *              Track a global maxFreq that only ever increases; no re-scan needed.
 *    Time    : O(N) — strict, no inner loop
 *    Space   : O(1)
 *    Key Insight: We only care about windows larger than our current best. Once
 *                 a window of size W is invalid, no window smaller than W can beat us —
 *                 so sliding is enough. maxFreq is monotonically non-decreasing,
 *                 so we never need to recompute it downward.
 *    Note    : Approach 3 is a strict O(N) micro-optimization. Both pass LC comfortably;
 *              Approach 2 is slightly easier to reason about in an interview.
 */

public class LC424LongestRepeatingCharacterReplacement {

    /*
     * Method to solve:
     * 1. Expand r — update charFreq for s[r]
     * 2. Recompute maxFreq across all 26 buckets
     * 3. If (windowSize - maxFreq) <= k → valid window, update mxLen
     * 4. Else → evict s[l] from freq array, advance l
     * 5. Advance r
     */

    /**
     * Returns the length of the longest substring achievable with at most k
     * character replacements.
     *
     * @param s uppercase English letter string
     * @param k maximum number of character replacements allowed
     * @return length of the longest valid repeating-character substring
     */
    public int characterReplacement(String s, int k) {

        int len = s.length();
        if (len <= 1)
            return len;

        int l = 0;
        int r = 0;
        int mxLen = 0;

        int[] charFreq = new int[26];

        while (r < len) {

            charFreq[s.charAt(r) - 'A']++;

            // O(26) scan — constant time since alphabet is fixed size
            int maxFreq = 1;
            for (int f : charFreq)
                maxFreq = Math.max(maxFreq, f);

            if (r - l + 1 - maxFreq <= k) {
                // replacements needed = non-dominant chars in window; fits within k
                mxLen = Math.max(mxLen, r - l + 1);
            } else {
                // window invalid — evict leftmost char and slide l forward
                charFreq[s.charAt(l) - 'A']--;
                l++;
            }

            r++;
        }

        return mxLen;
    }
}