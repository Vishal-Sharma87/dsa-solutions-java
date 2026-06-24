package dp.longestincreasingsubsequence;

// Created at: 25-June-2026
// Last revised at: 25-June-2026
// Link: https://leetcode.com/problems/longest-string-chain/

/*
Problem Description:
--------------------
Statement:
You are given an array of words where each word consists of lowercase English letters.

A word A is a predecessor of word B if and only if we can insert exactly one character
anywhere in A without changing the order of the remaining characters to make B.

Return the length of the longest possible word chain.

Example:
Input: ["a","b","ba","bca","bda","bdca"]
Output: 4

Explanation:
One valid chain is:
"a" -> "ba" -> "bda" -> "bdca"

Constraints:
1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] consists of lowercase English letters.
*/

/*
Approach 1: Recursion + Memoization

Idea:
For every word, either take it into the chain or skip it.
Track the previously selected word and recursively compute the
maximum chain length.

Time Complexity:
O(n² × L)

Space Complexity:
O(n²)

Drawbacks:
Large DP state because previous selected index must be stored.
*/

/*
Approach 2: DP on Sorted Words (Optimized)

Idea:
Sort words by length.
For each word, check all smaller words that appear before it.
If a previous word is a valid predecessor, extend the chain length.

This is similar to Longest Increasing Subsequence where
the predecessor relationship replaces the numeric comparison.

Time Complexity:
O(n² × L)

Space Complexity:
O(n)

Key Insight:
After sorting by length, any predecessor must appear before
the current word.
*/

/*
Method to Solve:
----------------
1. Sort all words by increasing length.
2. Initialize dp[i] = 1 for every word.
3. For every word, check all previous shorter words.
4. If a previous word is a valid predecessor, update dp[i].
5. Maintain the maximum chain length.
6. Return the maximum value.
*/

import java.util.Arrays;

public class LC1048LongestStringChain {

    /**
     * Checks whether prev is a valid predecessor of curr.
     *
     * @param prev shorter word
     * @param curr longer word
     * @return true if prev can become curr by inserting one character
     */
    public boolean isValidPredecessor(String prev, String curr) {

        if (prev.length() == curr.length()
                || prev.length() + 1 != curr.length()) {
            return false;
        }

        int diffCnt = 0;

        int i = prev.length() - 1;
        int j = curr.length() - 1;

        while (i >= 0 && j >= 0) {

            if (prev.charAt(i) != curr.charAt(j)) {

                diffCnt++;

                if (diffCnt == 2) {
                    return false;
                }

                j--;
            } else {
                i--;
                j--;
            }
        }

        return true;
    }

    /**
     * Finds the longest possible string chain.
     *
     * @param words input words array
     * @return longest chain length
     */
    public int longestStrChain(String[] words) {

        int n = words.length;

        // sort by length
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        Integer[] dp = new Integer[n];
        Arrays.fill(dp, 1);

        int maxChain = 1;

        for (int i = 1; i < n; i++) {

            for (int prev = 0; prev < i; prev++) {

                if (isValidPredecessor(words[prev], words[i])
                        && dp[prev] + 1 > dp[i]) {

                    dp[i] = dp[prev] + 1;
                }
            }

            maxChain = Math.max(maxChain, dp[i]);
        }

        return maxChain;
    }
}

// Time Complexity: O(n² × L)
// Space Complexity: O(n)