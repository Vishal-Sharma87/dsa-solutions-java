package strings.medium;

public class LC1781BeautyOfString {
    // Created at: 16 - March - 2025
    // Last revised at: 16 - March - 2025
    // Link: https://leetcode.com/problems/sum-of-beauty-of-all-substrings/
    // Time Complexity: O(n^3) — O(n^2) substrings × O(26) = O(n^2) but 26 is
    // constant → effectively O(n^2), still written as O(n^3) to be precise
    // Space Complexity: O(1) — freq array is always size 26

    /*
     * Problem Description:
     * ---------------------
     * The beauty of a string is defined as the difference between the frequency
     * of the most frequent and least frequent character in it (among characters
     * that actually appear in the string).
     *
     * Given a string s, return the sum of beauty of all its substrings.
     *
     * Example:
     * Input: s = "aabcb"
     * Output: 5
     * Explanation:
     * Substrings with non-zero beauty:
     * "aab" → max=2 (a), min=1 (b) → beauty = 1
     * "aabc" → max=2 (a), min=1 (b/c) → beauty = 1
     * "aabcb"→ max=2 (a/b), min=1 (c) → beauty = 1
     * "abcb" → max=2 (b), min=1 (a/c) → beauty = 1
     * "bcb" → max=2 (b), min=1 (c) → beauty = 1
     * Total = 5
     *
     * Constraints:
     * - 1 <= s.length() <= 500
     * - s consists of only lowercase English letters.
     *
     * =====================================================================
     *
     * Approach 1 — Brute Force with Frequency Array (Optimal for this problem)
     * -------------------------------------------------------------------------
     * Idea:
     * Fix a starting index i. Expand the window by moving j from i to n-1.
     * For each (i, j) pair, maintain a running freq[] array so we don't
     * recount from scratch. After each character addition, scan all 26
     * buckets to find max and min frequencies among characters present,
     * then accumulate (max - min) into the result.
     *
     * Time: O(n^2 * 26) ≈ O(n^2)
     * Space: O(26) = O(1)
     *
     * Key Insight:
     * Reusing the freq[] array across the inner loop (instead of rebuilding
     * it for every substring) avoids a third nested loop, keeping this
     * solution efficient within the given constraints (n ≤ 500).
     * =====================================================================
     */

    /**
     * Computes the sum of beauty of all substrings of the given string.
     *
     * <p>
     * For each substring, beauty = frequency(most frequent char)
     * - frequency(least frequent char among present chars).
     *
     * @param s the input string consisting of lowercase English letters
     * @return the total beauty sum across all substrings;
     *         returns -1 if the string is empty
     */
    public int beautySum(String s) {

        int len = s.length();

        // Edge case: empty string has no substrings
        if (len == 0)
            return -1;

        int beauty = 0;

        // Fix the start of the substring
        for (int i = 0; i < len; i++) {

            // freq[c] = count of character c in substring s[i..j]
            int[] freq = new int[26];

            // Expand the substring one character at a time
            for (int j = i; j < len; j++) {

                // Include s[j] in the current substring
                freq[s.charAt(j) - 'a']++;

                // Scan all 26 buckets to find max and min freq (among present chars)
                int maxi = -1;
                int mini = len; // len is an impossible real frequency → safe sentinel
                for (int it = 0; it < 26; it++) {
                    if (freq[it] != 0) {
                        maxi = Math.max(maxi, freq[it]);
                        mini = Math.min(mini, freq[it]);
                    }
                }

                // Accumulate beauty only when both sentinels were updated
                if (maxi != -1 && mini != len)
                    beauty += maxi - mini;
            }
        }

        return beauty;
    }
}
