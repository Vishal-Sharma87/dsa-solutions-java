package recursion.hard;

import java.util.HashSet;
import java.util.List;

public class LC139WordBreak {
    // Created at : 06 - April - 2026
    // Last revised at: 06 - April - 2026
    // Link : https://leetcode.com/problems/word-break/
    // Time Complexity : O(n^2) — n substrings checked, each lookup O(1) with
    // HashSet
    // Space Complexity: O(n) — dp array + recursion stack

    /*
     * Problem Description:
     * ---------------------
     * Given a string s and a dictionary of strings wordDict, return true if s can
     * be segmented into a space-separated sequence of one or more dictionary words.
     * The same word in the dictionary may be reused multiple times.
     *
     * Example:
     * Input : s = "leetcode", wordDict = ["leet", "code"]
     * Output: true
     * Explanation: "leet" + "code" = "leetcode"
     *
     * Input : s = "catsandog", wordDict = ["cats", "dog", "sand", "an", "cat"]
     * Output: false
     *
     * Constraints:
     * - 1 <= s.length() <= 300
     * - 1 <= wordDict.length <= 1000
     * - 1 <= wordDict[i].length <= 20
     * - s and wordDict[i] consist of only lowercase English letters
     * - All strings in wordDict are unique
     *
     * -------------------------------------------------------------------------
     * Approaches:
     * -------------------------------------------------------------------------
     *
     * ★ Approach 1: Recursion + Memoization (Top-Down DP)
     * Idea : Try every prefix from the current index. If it's in the dict,
     * recurse on the remaining suffix. Cache results by start index
     * to avoid recomputing overlapping subproblems.
     * Time : O(n^2)
     * Space : O(n)
     */

    /**
     * Checks if s[start..end] can be broken into dictionary words.
     * Uses memoization via dp[] to avoid redundant calls.
     *
     * @param s     the input string
     * @param start current index to try breaking from
     * @param dict  word dictionary as a HashSet for O(1) lookup
     * @param dp    memo array; null = unvisited, true/false = cached result
     * @return true if the suffix starting at `start` can be fully segmented
     */
    private boolean isPossible(String s, int start, HashSet<String> dict, Boolean[] dp) {
        // consumed the whole string — valid segmentation
        if (start >= s.length())
            return true;

        // already solved this suffix
        if (dp[start] != null)
            return dp[start];

        StringBuilder sb = new StringBuilder();

        for (int i = start; i < s.length(); i++) {
            sb.append(s.charAt(i)); // grow the current prefix one char at a time

            // prefix is a word AND the rest can also be segmented
            if (dict.contains(sb.toString()) && isPossible(s, i + 1, dict, dp)) {
                return dp[start] = true;
            }
        }

        return dp[start] = false; // no valid split found from this index
    }

    /**
     * Entry point. Converts wordDict to a HashSet and kicks off the memoized
     * recursion.
     *
     * @param s        the string to segment
     * @param wordDict list of valid words
     * @return true if s can be fully segmented using words from wordDict
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0)
            return false; // guard: empty string is invalid input per constraints

        HashSet<String> dict = new HashSet<>(wordDict); // O(1) lookups

        Boolean[] dp = new Boolean[s.length() + 1]; // +1 to handle the base case cleanly

        return isPossible(s, 0, dict, dp);
    }
}
