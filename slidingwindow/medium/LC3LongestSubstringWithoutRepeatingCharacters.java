package slidingwindow.medium;

// Created at: 24-April-2025
// Last revised at: 24-April-2025
// Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/
// Time Complexity: O(N)
// Space Complexity: O(1) — fixed 256-size array, independent of input

/*
 * Problem Description:
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * Example:
 *   Input:  s = "abcabcbb"
 *   Output: 3   // "abc"
 *
 *   Input:  s = "bbbbb"
 *   Output: 1   // "b"
 *
 * Constraints:
 *   - 0 <= s.length() <= 5 * 10^4
 *   - s consists of English letters, digits, symbols, and spaces (ASCII only)
 */

/*
 * Approaches:
 *
 * 1. Brute Force
 *    Idea    : Check every substring, use a Set to detect duplicates
 *    Time    : O(N^2) or O(N^3) depending on inner loop
 *    Space   : O(min(N, M)) where M = charset size
 *    Drawback: Too slow for N = 50_000
 *
 * 2. Sliding Window + HashSet
 *    Idea    : Expand right; if duplicate found, shrink from left until window is clean
 *    Time    : O(N)
 *    Space   : O(min(N, M))
 *    Drawback: HashSet overhead — add/remove/contains on every step
 *
 * 3. ★ Sliding Window + boolean[256]
 *    Idea    : Same two-pointer shrink logic, but track seen chars in a fixed boolean
 *              array indexed by ASCII value — constraints confirm ASCII-only input
 *    Time    : O(N) — each char enters and exits the window at most once
 *    Space   : O(1) — array size is always 256, never grows with input
 *    Key Insight: Swapping HashSet for boolean[] cuts constant-factor overhead
 *                 while keeping the same asymptotic complexity
 */

public class LC3LongestSubstringWithoutRepeatingCharacters {

    /*
     * Method to solve:
     * 1. Edge case — length 0 or 1, return early
     * 2. Maintain a sliding window [left, right]
     * 3. Before placing s[right] in the window, evict from left
     * until the window no longer contains s[right]
     * 4. Update maxLen, mark s[right] as seen, advance right
     */

    /**
     * Returns the length of the longest substring without repeating characters.
     *
     * @param s input string (ASCII characters only)
     * @return length of the longest valid substring
     */
    public int lengthOfLongestSubstring(String s) {

        int len = s.length();

        if (len <= 1)
            return len;

        int left = 0;
        int right = 0;
        int mxLen = 0;

        boolean[] chars = new boolean[256]; // boolean array over HashSet — constraint says ASCII only

        while (right < len) {

            // shrink from left until s[right] is no longer a duplicate in the window
            while (left <= right && chars[s.charAt(right)]) {
                chars[s.charAt(left)] = false;
                left++;
            }

            // window [left, right] is clean — check if it's the longest so far
            if (right - left + 1 > mxLen) {
                mxLen = right - left + 1;
            }

            chars[s.charAt(right)] = true;
            right++;
        }

        return mxLen;
    }
}