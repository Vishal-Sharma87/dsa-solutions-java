package dp.lcs;

import java.util.Arrays;

// Created at: 13-June-2026
// Last revised at: 13-June-2026
// Link: https://leetcode.com/problems/longest-common-subsequence/

/*
Problem Description:
--------------------
Statement:
Given two strings text1 and text2, return the length of their
Longest Common Subsequence (LCS).

A subsequence is a sequence that can be derived from another sequence
by deleting some or no elements without changing the order of the
remaining elements.

Example:
Input:
text1 = "abcde"
text2 = "ace"

Output:
3

Explanation:
The LCS is "ace".

Constraints:
1 <= text1.length, text2.length <= 1000
text1 and text2 consist of lowercase English letters.
*/

/*
Approach 1: Recursion
Idea:
Try all possibilities by either skipping a character from
the first string or the second string.

Time Complexity:
O(2^(n+m))

Space Complexity:
O(n+m)

Drawbacks:
Exponential time due to overlapping subproblems.
*/

/*
Approach 2: Memoization
Idea:
Store previously computed states (i, j).

Time Complexity:
O(n*m)

Space Complexity:
O(n*m)

Drawbacks:
Uses recursion stack space.
*/

/*
Approach 3: Tabulation
Idea:
Build the DP table bottom-up where dp[i][j] represents
the LCS length for prefixes of size i and j.

Time Complexity:
O(n*m)

Space Complexity:
O(n*m)

Key Insight:
Each state depends only on the previous row and current row.
*/

/*
Approach 4: Space Optimized DP
Idea:
Store only the previous and current rows.

Time Complexity:
O(n*m)

Space Complexity:
O(m)

Key Insight:
Only dp[i-1][*] and dp[i][*] are required.
*/

/*
Method to Solve:
----------------
1. Maintain two DP rows: prev and curr.
2. If characters match, take 1 + prev[j - 1].
3. Otherwise take max(curr[j - 1], prev[j]).
4. After processing a row, swap prev and curr.
5. Return prev[len2].
*/

// Time Complexity: O(n * m)
// Space Complexity: O(m)

public class LC1143LongestCommonSubsequence {

    /**
     * Returns the length of the longest common subsequence.
     *
     * @param text1 first string
     * @param text2 second string
     * @return length of LCS
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        int[] prev = new int[len2 + 1];
        int[] curr = new int[len2 + 1];

        for (int i = 1; i <= len1; i++) {
            Arrays.fill(curr, 0);

            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                } else {
                    int text1Preserved = curr[j - 1];
                    int text2Preserved = prev[j];

                    curr[j] = Math.max(text1Preserved, text2Preserved);
                }
            }

            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[len2];
    }
}